package it.alessiorossato.prenotaMensa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ruolo")
public class Ruolo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tokenRuolo;

	@NotNull
	@Column(name = "nome")

	private String nome;

	@OneToMany (mappedBy = "ruolo", orphanRemoval=true)
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
				"tokeRruolo=" + tokenRuolo +
				", nome='" + nome + '\'' +
			//	", utenti=" + utenti +
				'}';
	}
}
