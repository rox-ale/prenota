package it.alessiorossato.prenotaMensa.services.imp;

import it.alessiorossato.prenotaMensa.domain.Giorno;
import it.alessiorossato.prenotaMensa.domain.Menu;
import it.alessiorossato.prenotaMensa.domain.Utente;
import it.alessiorossato.prenotaMensa.repository.GiornoDao;
import it.alessiorossato.prenotaMensa.repository.MenuDao;
import it.alessiorossato.prenotaMensa.repository.PrenotazioneDao;
import it.alessiorossato.prenotaMensa.services.GiornoService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class GiornoServiceImp implements GiornoService {

    @Autowired
    GiornoDao giornoDao;

    @Autowired
    MenuDao menuDao;

    @Autowired
    PrenotazioneDao prenotazioneDao;

    @Override
    public Set<Giorno> listaGiorni() {
        return giornoDao.listaGiorni();
    }


    @Override
    @Transactional
    public Giorno salvaGiorno(Giorno giorno) {
        Giorno g = giornoDao.salvaGiorno(giorno);
        g.getListaMenu().stream().forEach(x -> {

            x.setGiorno(g);
            System.out.println("X=> " + x);
        });
        g.setListaMenu(menuDao.salvaMenu(g.getListaMenu()));
        return g;

    }

    @Override
    public List<Giorno> giorniPrenotabili() {
        List<Giorno> giorniPrenotabili = giornoDao.giorniPrenotabili();
        giorniPrenotabili.forEach(g -> {
            Set<Menu> menuPrenotabili = menuDao.menuPrenotabili(g);
            g.setListaMenu(menuPrenotabili);
        });

        return giorniPrenotabili;
    }

    @Override
    public boolean giornoPrenotato(Utente utente, Giorno giorno) {
        return giornoDao.giornoPrenotato(utente, giorno);
    }


    @Override
    public List<Giorno> listaGiorniPrenotabili(LocalDate data) {
        return giornoDao.listaGiorniPrenotabili(data);
    }

    @Override
    @Transactional
    public void eliminaGiorno(Long tokenGiorno) {
        prenotazioneDao.eliminaPrenotazioneDaGiorno(tokenGiorno);
        menuDao.eliminaMenuDaGiorno(tokenGiorno);
        giornoDao.eliminaGiorno(tokenGiorno);
    }
}
