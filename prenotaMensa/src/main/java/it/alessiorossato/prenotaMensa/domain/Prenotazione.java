package it.alessiorossato.prenotaMensa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "prenotazione")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenPrenotazione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("prenotazione")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("prenotazione")
    private Utente utente;

    public Prenotazione() {
    }

    public Integer getTokenPrenotazione() {
        return tokenPrenotazione;
    }

    public void setTokenPrenotazione(Integer tokenPrenotazione) {
        this.tokenPrenotazione = tokenPrenotazione;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "tokenPrenotazione=" + tokenPrenotazione +
                ", menu=" + menu +
                ", utente=" + utente +
                '}';
    }
}
