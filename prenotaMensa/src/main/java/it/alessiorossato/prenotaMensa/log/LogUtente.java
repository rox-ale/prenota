/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessiorossato.prenotaMensa.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Alessio
 */
public class LogUtente {

    private Class nomeClasse;
    Logger log;

    public LogUtente(Class nomeClasse) {
        this.nomeClasse = nomeClasse;
        log = LoggerFactory.getLogger(nomeClasse);

    }

    public void debug(String messaggio, Object... os) {
        messaggio = getUtenteConnesso() + " " + messaggio;
        log.debug(messaggio, os);
    }

    public void debug(String messaggio) {
        messaggio = getUtenteConnesso() + " " + messaggio;
        log.debug(messaggio);
    }

    public void info(String messaggio, Object... os) {
        messaggio = getUtenteConnesso() + " " + messaggio;
        log.info(messaggio, os);
    }

    public void info(String messaggio) {
        messaggio = getUtenteConnesso() + " " + messaggio;
        log.info(messaggio);
    }

    public void warn(String messaggio, Object... os) {
        messaggio = getUtenteConnesso() + " " + messaggio;
        log.warn(messaggio, os);
    }

    public void warn(String messaggio) {
        messaggio = getUtenteConnesso() + " " + messaggio;
        log.warn(messaggio);
    }

    public void error(String messaggio, Object... os) {
        messaggio = getUtenteConnesso() + " " + messaggio;
        log.error(messaggio, os);
    }

    public void error(String messaggio) {
        messaggio = getUtenteConnesso() + " " + messaggio;
        log.error(messaggio);
    }


    private String getUtenteConnesso() {
        try {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            //  System.out.println("UTENTE: "+principal.getUsername());
            return "Utente:" + principal.getUsername()+" --> ";

        } catch (Exception e) {
            return "";
        }

    }

}
