package it.alessiorossato.prenotaMensa.services.imp;


import it.alessiorossato.prenotaMensa.domain.Utente;
import it.alessiorossato.prenotaMensa.exception.UserNotFoundException;
import it.alessiorossato.prenotaMensa.repository.UserRepository;
import it.alessiorossato.prenotaMensa.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public List<Utente> getAll(){
        return userRepository.findAll();
    }

    @Override
    public Utente modificaPassword(Long idUtente, String password) throws UserNotFoundException {
        Optional<Utente> user = userRepository.findById(idUtente);
        if(user.isPresent()){
            String passwordEncoder=encoder.encode(password);
            Utente utente=user.get();
            utente.setPassword(passwordEncoder);
            userRepository.save(utente);
            return utente;
        }else{
            throw new UserNotFoundException("Utente non trovato");
        }
    }

    public void utenteAutenticato(String username, LocalDateTime ultimoAccesso, String remoteAddr, String serveName, String hostAddress, String hostName){
        Optional<Utente> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            Utente utente = user.get();
          /*  utente.setRemoteAddr(remoteAddr);
            utente.setServeName(serveName);
            utente.setHostAddress(hostAddress);
            utente.setHostName(hostName);
            utente.setUltimoAccesso(ultimoAccesso);*/
            userRepository.save(utente);
        }else{
            //lanciare eccezione
        }
    }


    public Optional<Utente> getUtente(String username){
        Optional<Utente> byUsername = userRepository.findByUsername(username);
        return byUsername;
    }
}
