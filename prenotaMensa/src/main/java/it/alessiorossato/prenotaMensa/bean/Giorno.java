package it.alessiorossato.prenotaMensa.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Giorno {

    private Integer tokenGiorno;

    private LocalDate data;

    private List<Menu> listaMenu = new ArrayList<>();

    public boolean prenotato=false;

    public Giorno() {
    }

    public Integer getTokenGiorno() {
        return tokenGiorno;
    }

    public void setTokenGiorno(Integer tokenGiorno) {
        this.tokenGiorno = tokenGiorno;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Menu> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(List<Menu> listaMenu) {
        this.listaMenu = listaMenu;
    }

    public boolean isPrenotato() {
        return prenotato;
    }

    public void setPrenotato(boolean prenotato) {
        this.prenotato = prenotato;
    }

    @Override
    public String toString() {
        return "Giorno{" +
                "tokenGiorno=" + tokenGiorno +
                ", data=" + data +
                ", listaMenu=" + listaMenu +
                ", prenotato=" + prenotato +
                '}';
    }
}
