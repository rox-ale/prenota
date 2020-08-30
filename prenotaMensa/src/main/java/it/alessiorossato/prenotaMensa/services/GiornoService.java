package it.alessiorossato.prenotaMensa.services;

import it.alessiorossato.prenotaMensa.domain.Giorno;
import it.alessiorossato.prenotaMensa.domain.Utente;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface GiornoService extends Serializable {
    public Set<Giorno> listaGiorni();

    public Giorno salvaGiorno(Giorno giorno);

    public List<Giorno> giorniPrenotabili();

    public boolean giornoPrenotato(Utente utente, Giorno giorno);

    public List<Giorno> listaGiorniPrenotabili(LocalDate data );

    public void eliminaGiorno(Long tokenGiorno);
}
