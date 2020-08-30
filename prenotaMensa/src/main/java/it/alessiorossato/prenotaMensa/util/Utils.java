package it.alessiorossato.prenotaMensa.util;

import it.alessiorossato.prenotaMensa.domain.Utente;
import it.alessiorossato.prenotaMensa.exception.UserNotFoundException;
import it.alessiorossato.prenotaMensa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class Utils {


    public static Utente getUtenteConnesso(UserService userService) throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        Optional<Utente> utente = userService.getUtente(name);

        if (utente.isPresent()) {
            return utente.get();
        }

        throw new UserNotFoundException("Utente non trovato");
    }
}
