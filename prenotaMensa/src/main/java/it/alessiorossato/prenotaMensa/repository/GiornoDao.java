package it.alessiorossato.prenotaMensa.repository;

import it.alessiorossato.prenotaMensa.domain.Giorno;
import it.alessiorossato.prenotaMensa.domain.Menu;
import it.alessiorossato.prenotaMensa.domain.Utente;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class GiornoDao {
    @Autowired
    EntityManager entityManager;

    public Set<Giorno> listaGiorni() {
        Query query = entityManager.createQuery("SELECT g FROM Giorno g ");

//       query.setParameter("deptName", dept);

        List<Giorno> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        return new HashSet<>(resultList);

    }


    public Giorno salvaGiorno(Giorno giorno) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(giorno);

        return giorno;

    }


    public List<Giorno> giorniPrenotabili() {
        LocalDate oggi = LocalDate.now();
        Query query = entityManager.createQuery("SELECT g FROM Giorno g where g.data> :data");
        query.setParameter("data", oggi);
        List<Giorno> resultList = query.getResultList();
        return resultList;

    }


    public boolean giornoPrenotato(Utente utente, Giorno giorno) {
        Query query = entityManager.createQuery("SELECT g FROM Giorno g join g.listaMenu as m "

                + "join m.prenota p join p.utente as u "
                +
                " where g = :giorno "
                +
                "and  u =:utente"
        );
        query.setParameter("giorno", giorno);
        query.setParameter("utente", utente);
        List<Giorno> resultList = query.getResultList();
        return resultList.size() != 0;
    }

    public List<Giorno> listaGiorniPrenotabili(LocalDate data) {

        Query query = entityManager.createQuery("select g from Giorno g where   g.data >=:data");

        query.setParameter("data", data);
        List<Giorno> resultList = query.getResultList();
        return resultList;
    }


    public List<Giorno> giornoMenuUtenti(Long token) {

        Query query = entityManager.createQuery("SELECT g FROM Giorno g  " +
                // "join g.listaMenu m  " +
                //    "join m.prenota p join p.utente     " +
                "where g.tokenGiorno= :giorno");
        query.setParameter("giorno", token.intValue());
        List<Giorno> result = query.getResultList();
        return result;

    }


    public void eliminaGiorno(Long tokenGiorno) {

        Query query = entityManager.createQuery("Select g.tokenGiorno from Giorno g where g.tokenGiorno=:token ");
        query.setParameter("token", tokenGiorno.intValue());
        List<Integer> resultList = query.getResultList();

        Query entityQuery = entityManager.createQuery(
                "DELETE FROM Giorno g where g.tokenGiorno in ( :tokenList)");
        entityQuery.setParameter("tokenList", resultList);

        entityQuery.executeUpdate();
    }
}
