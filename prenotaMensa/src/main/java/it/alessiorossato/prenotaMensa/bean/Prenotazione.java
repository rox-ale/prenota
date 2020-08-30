package it.alessiorossato.prenotaMensa.bean;


public class Prenotazione {

    private Integer tokenPrenotazione;


    private Menu menu;


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
                "tokenPrenota=" + tokenPrenotazione +
                ", menu=" + menu +
                ", utente=" + utente +
                '}';
    }
}
