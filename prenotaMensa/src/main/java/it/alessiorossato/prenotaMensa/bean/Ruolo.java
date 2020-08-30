package it.alessiorossato.prenotaMensa.bean;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Ruolo  {

	private Integer tokenRuolo;


	private String nome;


	private Set<Utente> utenti= new HashSet<>();

	public Ruolo() {

	}

	public Integer getTokenRuolo() {
		return tokenRuolo;
	}

	public void setTokenRuolo(Integer tokenRuolo) {
		this.tokenRuolo = tokenRuolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(Set<Utente> utenti) {
		this.utenti = utenti;
	}

	@Override
	public String toString() {
		return "Ruolo{" +
				"tokenRuolo=" + tokenRuolo +
				", nome='" + nome + '\'' +
				", utenti=" + utenti +
				'}';
	}
}
