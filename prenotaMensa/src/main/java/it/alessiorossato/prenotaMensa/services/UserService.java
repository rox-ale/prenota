package it.alessiorossato.prenotaMensa.services;

import it.alessiorossato.prenotaMensa.domain.Utente;
import it.alessiorossato.prenotaMensa.exception.UserNotFoundException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService extends Serializable {
    public List<Utente> getAll();
    public Utente modificaPassword(Long idUtente, String password) throws UserNotFoundException;

    public void utenteAutenticato(String username, LocalDateTime ultimoAccesso, String remoteAddr, String serveName, String hostAddress, String hostName);
    public Optional<Utente> getUtente(String username);
}
