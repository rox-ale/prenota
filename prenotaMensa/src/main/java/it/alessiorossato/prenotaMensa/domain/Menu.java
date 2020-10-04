package it.alessiorossato.prenotaMensa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenMenu;

    @Column(name = "numero")
    private String nome;
    @Column(name = "pasto")
    private String pasto;
    @Column(name = "contorno")
    private String contorno;
    @Column(name = "extra")
    private String extra;
    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)

    private Giorno giorno;


/*
    @ManyToMany
    @JoinTable(
            name = "menu_utente",
            joinColumns = @JoinColumn(name = "token_menu"),
            inverseJoinColumns = @JoinColumn(name = "token_utente"))

    private Set<Utente> listaUtenti = new HashSet<>();*/

    @OneToMany(mappedBy = "menu",fetch = FetchType.LAZY)
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
              //  ", giorno=" + giorno +
            //    ", prenota=" + prenota +
                '}';
    }
}
