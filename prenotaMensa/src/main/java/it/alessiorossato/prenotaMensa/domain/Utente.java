package it.alessiorossato.prenotaMensa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(	name = "utente",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")

        })
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenUtente;

    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 120)
    private String password;
    @NotNull
    private boolean enable;

    @NotNull
    private String nome;
    @NotNull
    private String cognome;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "token_ruolo")
    private Ruolo ruolo;
/*
    @ManyToMany(mappedBy = "listaUtenti")
    private Set<Menu> listaMenu = new HashSet<>();*/

    @OneToMany(mappedBy = "utente",fetch = FetchType.LAZY)
    private Set<Prenotazione> prenota = new HashSet<>();

    public Utente() {
    }

    public Long getTokenUtente() {
        return tokenUtente;
    }

    public void setTokenUtente(Long tokenUtente) {
        this.tokenUtente = tokenUtente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public Set<Prenotazione> getPrenota() {
        return prenota;
    }

    public void setPrenota(Set<Prenotazione> prenota) {
        this.prenota = prenota;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "tokenUtente=" + tokenUtente +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enable=" + enable +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", ruolo=" + ruolo +
            //    ", prenota=" + prenota +
                '}';
    }
}
