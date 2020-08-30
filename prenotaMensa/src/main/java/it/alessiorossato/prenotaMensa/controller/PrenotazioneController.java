package it.alessiorossato.prenotaMensa.controller;


import it.alessiorossato.prenotaMensa.bean.Giorno;
import it.alessiorossato.prenotaMensa.bean.Menu;
import it.alessiorossato.prenotaMensa.domain.Prenotazione;
import it.alessiorossato.prenotaMensa.domain.Utente;
import it.alessiorossato.prenotaMensa.exception.EccezioneGenericaException;
import it.alessiorossato.prenotaMensa.exception.UserNotFoundException;
import it.alessiorossato.prenotaMensa.log.LogUtente;
import it.alessiorossato.prenotaMensa.payload.response.HeaderUtil;
import it.alessiorossato.prenotaMensa.repository.PrenotazioneDao;
import it.alessiorossato.prenotaMensa.services.PrenotazioneService;
import it.alessiorossato.prenotaMensa.services.UserService;
import it.alessiorossato.prenotaMensa.util.ConvertBeanDomain;
import it.alessiorossato.prenotaMensa.util.ConvertDomainBean;
import it.alessiorossato.prenotaMensa.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/prenotazione")
public class PrenotazioneController {

    private static final String ENTITY_NAME = "Prenotazione";
    private static final LogUtente log = new LogUtente(PrenotazioneController.class);


    @Autowired
    UserService userService;

    @Autowired
    PrenotazioneService prenotazioneService;

    @RequestMapping(value = "/prenotaMenu", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<it.alessiorossato.prenotaMensa.bean.Prenotazione> prenotaMenu(@RequestBody Menu menu) throws UserNotFoundException, EccezioneGenericaException {
        try {
            log.info("****** Aggiorno/inserisco una prenotazione " + menu + "  ******* ");
            Utente utente = Utils.getUtenteConnesso(userService);

            it.alessiorossato.prenotaMensa.domain.Menu menuDomain = ConvertBeanDomain.convertiMenu(menu);


            Prenotazione prenotazione = new Prenotazione();

            prenotazione.setMenu(menuDomain);
            prenotazione.setUtente(utente);

            Prenotazione salvaPrenotazione = prenotazioneService.salvaPrenotazione(prenotazione);

            it.alessiorossato.prenotaMensa.bean.Prenotazione convertiPrenotazione = ConvertDomainBean.convertiPrenotazione(salvaPrenotazione);
            log.info("Ho aggiornato/inserito la prenotazione");

            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
                    .body(convertiPrenotazione);
        } catch (Exception e) {
            throw new EccezioneGenericaException(e.getMessage());
        }
    }


    @RequestMapping(value = "/prenotazioniUtente", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<it.alessiorossato.prenotaMensa.bean.Prenotazione>> prenotazioniUtente() throws EccezioneGenericaException {
        log.info("Richiesta giorni prenotabili");

        try {
            Utente utenteConnesso = Utils.getUtenteConnesso(userService);

            LocalDate data = LocalDate.now();
            data =                    data.minus(Period.ofMonths(1));

            List<it.alessiorossato.prenotaMensa.domain.Prenotazione> prenotazioniUtente = prenotazioneService.prenotazioniUtente(utenteConnesso, data);

            List<it.alessiorossato.prenotaMensa.bean.Prenotazione> prenotazioniList = new ArrayList<>();

            for (it.alessiorossato.prenotaMensa.domain.Prenotazione pr : prenotazioniUtente) {
                it.alessiorossato.prenotaMensa.bean.Prenotazione p=new it.alessiorossato.prenotaMensa.bean.Prenotazione();
                p.setTokenPrenotazione(pr.getTokenPrenotazione());
                Menu menu = ConvertDomainBean.convertiMenu(pr.getMenu());
                menu.setGiorno(ConvertDomainBean.convertiGiorno(pr.getMenu().getGiorno()));
                p.setMenu(menu);
                prenotazioniList.add(p);
            }

            log.info("Ho recuperato le seguenti prenotazioni " + prenotazioniList);
            return new ResponseEntity<List<it.alessiorossato.prenotaMensa.bean.Prenotazione>>(prenotazioniList, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Eccezione recupero menu prenotati " + e.getMessage());
            throw new EccezioneGenericaException(e.getMessage());
        }

    }


    @PostMapping(value="/elimina")
    public ResponseEntity<?> eliminaPrenotazione(@RequestBody Long token) throws  EccezioneGenericaException {
        try{
            log.info("****** Elimino la prenotazione con token  ******* " + token);
            prenotazioneService.deletePrenotazione(token);
            log.info("Prenotazione eliminata");
            return new ResponseEntity<>(token, HttpStatus.OK);
        }catch(Exception e){
            log.error("Errore elimina prenotazione "+e.getMessage());
            throw new EccezioneGenericaException(e.getMessage());
        }


    }



}