package it.alessiorossato.prenotaMensa.repository;

import it.alessiorossato.prenotaMensa.domain.Utente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Utente, Long> {
	Optional<Utente> findByUsername(String username);

	Boolean existsByUsername(String username);

}
