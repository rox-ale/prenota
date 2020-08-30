package it.alessiorossato.prenotaMensa.controller;


import it.alessiorossato.prenotaMensa.bean.Giorno;
import it.alessiorossato.prenotaMensa.bean.Menu;
import it.alessiorossato.prenotaMensa.domain.Prenotazione;
import it.alessiorossato.prenotaMensa.domain.Utente;
import it.alessiorossato.prenotaMensa.exception.EccezioneGenericaException;
import it.alessiorossato.prenotaMensa.exception.GiornoException;
import it.alessiorossato.prenotaMensa.log.LogUtente;
import it.alessiorossato.prenotaMensa.payload.response.HeaderUtil;
import it.alessiorossato.prenotaMensa.services.GiornoService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/giorno")
public class GiornoController {

    private static final String ENTITY_NAME = "Giorno";
    private static final LogUtente log = new LogUtente(GiornoController.class);

    @Autowired
    GiornoService giornoService;

    @Autowired
    UserService userService;


    @PostMapping("/inserisci")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Giorno> aggiornaMenu(@RequestBody Giorno giorno) throws URISyntaxException, EccezioneGenericaException {
        try {
            log.info("****** Aggiorno/inserisco un giorno " + giorno + "  ******* ");
            it.alessiorossato.prenotaMensa.domain.Giorno giornoDomain = ConvertBeanDomain.convertiGiorno(giorno);
            it.alessiorossato.prenotaMensa.domain.Giorno salvaGiorno = giornoService.salvaGiorno(giornoDomain);

            Giorno convertiGiorno = ConvertDomainBean.convertiGiorno(salvaGiorno);

            log.info("Ho aggiornato/inserito il giorno");
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
                    .body(convertiGiorno);

        } catch (Exception e) {
            log.error("Eccezione aggiorna/inserisci giorno " + e.getMessage());
            throw new EccezioneGenericaException(e.getMessage());
        }


    }


    @RequestMapping(value = "/giorniPrenotabili", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Giorno>> listaGiorni() throws EccezioneGenericaException {
        log.info("Richiesta giorni prenotabili");

        try {
            Utente utenteConnesso = Utils.getUtenteConnesso(userService);

            List<it.alessiorossato.prenotaMensa.domain.Giorno> giorniPrenotabili = giornoService.giorniPrenotabili();
            List<Giorno> giornoList = new ArrayList<>();

            for (it.alessiorossato.prenotaMensa.domain.Giorno g : giorniPrenotabili) {

                Giorno giornoB = ConvertDomainBean.convertiGiorno(g);
                giornoB.setListaMenu(ConvertDomainBean.convertiMenu(new ArrayList<>(g.getListaMenu())));
                giornoList.add((giornoB));
            }


            for (it.alessiorossato.prenotaMensa.bean.Giorno g : giornoList) {
                g.setPrenotato(giornoService.giornoPrenotato(utenteConnesso, ConvertBeanDomain.convertiGiorno(g)));
            }

            log.info("Ho recuperato i giorni prenotabili " + giornoList);
            return new ResponseEntity<List<Giorno>>(giornoList, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Eccezione recupero giorni prenotabili " + e.getMessage());
            throw new EccezioneGenericaException(e.getMessage());
        }

    }

    @RequestMapping(value = "/listaGiorniDisponibili", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Giorno>> listaGiorniDisponibili() throws EccezioneGenericaException {
        log.info("Richiesta giorni prenotabili");

        try {

            LocalDate data = LocalDate.now();
            data = data.minus(Period.ofDays(1));


            List<it.alessiorossato.prenotaMensa.domain.Giorno> giorniPrenotabili = giornoService.listaGiorniPrenotabili(data);
            List<Giorno> giornoList = ConvertDomainBean.convertiGiorno(giorniPrenotabili);

            log.info("Ho recuperato i giorni disponibli " + giornoList);
            return new ResponseEntity<List<Giorno>>(giornoList, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Eccezione recupero giorni prenotabili " + e.getMessage());
            throw new EccezioneGenericaException(e.getMessage());
        }

    }


    @PostMapping("/elimina")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> eliminaGiorno(@RequestBody Long tokenGiorno) throws URISyntaxException, EccezioneGenericaException {
        try {
            log.info("****** Elimino il giorno  con id  ******* " + tokenGiorno);
            giornoService.eliminaGiorno(tokenGiorno);
            log.info("Eliminato giorno");
            return ResponseEntity.ok().build();


        } catch (
                Exception e) {
            log.error("Eccezione recupero giorni prenotabili " + e.getMessage());
            throw new EccezioneGenericaException(e.getMessage());
        }

    }
}
