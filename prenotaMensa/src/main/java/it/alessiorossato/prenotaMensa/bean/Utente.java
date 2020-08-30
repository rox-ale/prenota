package it.alessiorossato.prenotaMensa.bean;


import java.util.HashSet;
import java.util.Set;

public class Utente {

 public Long tokenUtente;

    private String username;

    private String password;

    private boolean enable;

 public String nome;
    private String cognome;


    private Ruolo ruolo;

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
                ", prenota=" + prenota +
                '}';
    }
}
