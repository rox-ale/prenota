package it.alessiorossato.prenotaMensa.repository;

import it.alessiorossato.prenotaMensa.domain.Menu;
import it.alessiorossato.prenotaMensa.domain.Prenotazione;
import it.alessiorossato.prenotaMensa.domain.Utente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PrenotazioneDao {
    @Autowired
    EntityManager entityManager;

    public Prenotazione salvaPrenotazione(Prenotazione prenotazione) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(prenotazione);

        return prenotazione;

    }


    public List<Prenotazione> prenotazioniUtente(Utente utente, LocalDate data) {

        Query query = entityManager.createQuery("select p from Utente u join u.prenota as p join p.menu m    join  m.giorno g where u=:utente and  g.data >:data");

        query.setParameter("utente", utente);
        query.setParameter("data", data);
        List<Prenotazione> resultList = query.getResultList();
        return resultList;
    }


    public void deletePrenotazione(Long token) {
        Query query = entityManager.createQuery(
                "DELETE FROM Prenotazione p WHERE p.tokenPrenotazione = :token");
        int deletedCount = query.setParameter("token", token.intValue()).executeUpdate();
    }


    public void eliminaPrenotazioneDaGiorno(Long tokenGiorno) {

        Query query = entityManager.createQuery("Select p.tokenPrenotazione from Giorno g join g.listaMenu m join m.prenota p where g.tokenGiorno=:token ");
        query.setParameter("token", tokenGiorno.intValue());
        List<Integer> resultList = query.getResultList();

        Query entityQuery = entityManager.createQuery(
                "DELETE FROM Prenotazione p    WHERE p.tokenPrenotazione in ( :tokenList)");
        entityQuery.setParameter("tokenList", resultList);

        entityQuery.executeUpdate();
    }
}
