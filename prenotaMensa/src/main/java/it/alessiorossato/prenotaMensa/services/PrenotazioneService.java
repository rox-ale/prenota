package it.alessiorossato.prenotaMensa.services;

import it.alessiorossato.prenotaMensa.domain.Menu;
import it.alessiorossato.prenotaMensa.domain.Prenotazione;
import it.alessiorossato.prenotaMensa.domain.Utente;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneService extends Serializable {


    public Prenotazione salvaPrenotazione(Prenotazione prenotazione);

    public List<Prenotazione> prenotazioniUtente(Utente utente, LocalDate data );

    public void deletePrenotazione(Long token);
}
