package it.alessiorossato.prenotaMensa.services.imp;

import it.alessiorossato.prenotaMensa.domain.Menu;
import it.alessiorossato.prenotaMensa.domain.Prenotazione;
import it.alessiorossato.prenotaMensa.domain.Utente;
import it.alessiorossato.prenotaMensa.repository.MenuDao;
import it.alessiorossato.prenotaMensa.repository.PrenotazioneDao;
import it.alessiorossato.prenotaMensa.services.MenuService;
import it.alessiorossato.prenotaMensa.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PrenotazioneServiceImp implements PrenotazioneService {


    @Autowired
    PrenotazioneDao prenotazioneDao;

    @Override

    public Prenotazione salvaPrenotazione(Prenotazione prenotazione) {

        Prenotazione salvaPrenotazione = prenotazioneDao.salvaPrenotazione(prenotazione);
        return salvaPrenotazione;

    }

    @Override
    public List<Prenotazione> prenotazioniUtente(Utente utente, LocalDate data ){
        List<Prenotazione> prenotazioniUtente = prenotazioneDao.prenotazioniUtente(utente, data);
        return prenotazioniUtente;
    }

    @Override
    @Transactional
    public void deletePrenotazione(Long token){
        prenotazioneDao.deletePrenotazione(token);
    }
}
