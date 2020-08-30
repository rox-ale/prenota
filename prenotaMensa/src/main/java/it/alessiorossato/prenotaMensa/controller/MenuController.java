package it.alessiorossato.prenotaMensa.controller;


import it.alessiorossato.prenotaMensa.bean.Prenotazione;
import it.alessiorossato.prenotaMensa.domain.Menu;
import it.alessiorossato.prenotaMensa.exception.EccezioneGenericaException;
import it.alessiorossato.prenotaMensa.log.LogUtente;
import it.alessiorossato.prenotaMensa.services.MenuService;
import it.alessiorossato.prenotaMensa.services.UserService;
import it.alessiorossato.prenotaMensa.util.ConvertDomainBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private static final String ENTITY_NAME = "Menu";
    private static final LogUtente log = new LogUtente(MenuController.class);


    @Autowired
    UserService userService;

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public void salvaMenu() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object details = authentication.getName();
        System.out.println("Details " + details);
    }


    @RequestMapping(value = "/utentiGiornoMenu", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<it.alessiorossato.prenotaMensa.bean.Menu>> utentiGiornoMenu(@RequestParam Long token) throws EccezioneGenericaException {
        log.info("Richiesta menu con utente del giorno con token " + token);

        try {


            List<Menu> utentiGiornoMenu = menuService.utentiGiornoMenu(token);

            List<it.alessiorossato.prenotaMensa.bean.Menu> listaUtentiMenu = new ArrayList<>();
            for (Menu m : utentiGiornoMenu) {
                it.alessiorossato.prenotaMensa.bean.Menu menuB = new it.alessiorossato.prenotaMensa.bean.Menu();
                menuB.setTokenMenu(m.getTokenMenu());
                menuB.setNome(m.getNome());
                Set<Prenotazione> prenotazioni = new HashSet<>();
                for (it.alessiorossato.prenotaMensa.domain.Prenotazione p : m.getPrenota()) {
                    Prenotazione pr = new Prenotazione();
                    pr.setUtente(ConvertDomainBean.convertiUtente(p.getUtente()));
                    prenotazioni.add(pr);
                }
                menuB.setPrenota(prenotazioni);
                listaUtentiMenu.add(menuB);
            }

            log.info("Ho recuperato i giorni disponibli " + listaUtentiMenu);
            return new ResponseEntity<List<it.alessiorossato.prenotaMensa.bean.Menu>>(listaUtentiMenu, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Eccezione recupero utenti menu " + e.getMessage());
            throw new EccezioneGenericaException(e.getMessage());
        }

    }

    @RequestMapping(value = "/menuUtenti", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<it.alessiorossato.prenotaMensa.bean.Menu>> menuUtenti(@RequestParam Long token) throws EccezioneGenericaException {
        log.info("Richiesta lista menu con utenti del giorno con token " + token);

        try {


            List<Menu> menuUtenti = menuService.giornoMenuUtenti(token);
            List<it.alessiorossato.prenotaMensa.bean.Menu> listaMenu = new ArrayList<>();

            for (it.alessiorossato.prenotaMensa.domain.Menu menu : menuUtenti) {
                it.alessiorossato.prenotaMensa.bean.Menu menuB = ConvertDomainBean.convertiMenu(menu);

                List<it.alessiorossato.prenotaMensa.bean.Prenotazione> listaPrenotazioni = new ArrayList<>();
                Set<it.alessiorossato.prenotaMensa.domain.Prenotazione> prenotazioneSet = menu.getPrenota();
                for (it.alessiorossato.prenotaMensa.domain.Prenotazione p : prenotazioneSet) {
                    it.alessiorossato.prenotaMensa.bean.Prenotazione prenotazioneBean = new it.alessiorossato.prenotaMensa.bean.Prenotazione();
                    prenotazioneBean.setUtente(ConvertDomainBean.convertiUtente(p.getUtente()));
                    listaPrenotazioni.add(prenotazioneBean);
                }

                menuB.setPrenota(new HashSet<>(listaPrenotazioni));
                listaMenu.add(menuB);
            }


            log.info("Ho recuperato i menu  " + listaMenu);
            return new ResponseEntity<List<it.alessiorossato.prenotaMensa.bean.Menu>>(listaMenu, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Eccezione recupero utenti menu " + e.getMessage());
            throw new EccezioneGenericaException(e.getMessage());
        }


    }

}
