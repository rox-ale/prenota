package it.alessiorossato.prenotaMensa.bean;

import java.util.HashSet;
import java.util.Set;

public class Menu {

    private Integer tokenMenu;


    private String nome;

    private String pasto;

    private String contorno;

    private String extra;

    private String note;

    private Giorno giorno;


    private Set<Prenotazione> prenota = new HashSet<>();

    public Menu() {
    }

    public Integer getTokenMenu() {
        return tokenMenu;
    }

    public void setTokenMenu(Integer tokenMenu) {
        this.tokenMenu = tokenMenu;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPasto() {
        return pasto;
    }

    public void setPasto(String pasto) {
        this.pasto = pasto;
    }

    public String getContorno() {
        return contorno;
    }

    public void setContorno(String contorno) {
        this.contorno = contorno;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Giorno getGiorno() {
        return giorno;
    }

    public void setGiorno(Giorno giorno) {
        this.giorno = giorno;
    }

    public Set<Prenotazione> getPrenota() {
        return prenota;
    }

    public void setPrenota(Set<Prenotazione> prenota) {
        this.prenota = prenota;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "tokenMenu=" + tokenMenu +
                ", nome='" + nome + '\'' +
                ", pasto='" + pasto + '\'' +
                ", contorno='" + contorno + '\'' +
                ", extra='" + extra + '\'' +
                ", note='" + note + '\'' +
                ", giorno=" + giorno +
                ", prenota=" + prenota +
                '}';
    }
}
