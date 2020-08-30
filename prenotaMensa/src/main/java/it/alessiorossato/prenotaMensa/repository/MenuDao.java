package it.alessiorossato.prenotaMensa.repository;

import it.alessiorossato.prenotaMensa.domain.Giorno;
import it.alessiorossato.prenotaMensa.domain.Menu;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class MenuDao {
    @Autowired
    EntityManager entityManager;



    public Set<Menu> salvaMenu(Set<Menu> menu){
        Session session = entityManager.unwrap(Session.class);
        menu.forEach(m->{
            session.saveOrUpdate(m);
        });

        return menu;

    }

    public Menu salvaMenu(Menu menu){
        Session session = entityManager.unwrap(Session.class);

       session.saveOrUpdate(menu);

        return menu;

    }
    public Set<Menu> menuPrenotabili(Giorno g){

        Query query=    entityManager.createQuery("SELECT m FROM Menu m where m.giorno= :giorno");
        query.setParameter("giorno",g);
        List<Menu> resultList = query.getResultList();
        return new HashSet<>(resultList);

    }

    public List<Menu> utentiGiornoMenu(Long token){

        Query query=    entityManager.createQuery("SELECT m FROM Menu m  join m.giorno g   " +
                //"join  m.prenota p join p.utente  " +
                " where g.tokenGiorno= :giorno");
        query.setParameter("giorno",token.intValue());
        List<Menu> resultList = query.getResultList();
        return resultList;

    }


    public List<Menu> menuUtenti(Long token){

        Query query=    entityManager.createQuery("SELECT g FROM Menu m left join fetch m.giorno g   join  m.prenota p left join fetch p.utente   where g.tokenGiorno= :giorno");
        query.setParameter("giorno",token.intValue());
        List<Menu> resultList = query.getResultList();
        return resultList;

    }

    public void eliminaMenuDaGiorno(Long tokenGiorno) {

        Query query = entityManager.createQuery("Select m.tokenMenu from Giorno g join g.listaMenu m where g.tokenGiorno=:token ");
        query.setParameter("token", tokenGiorno.intValue());
        List<Integer> resultList = query.getResultList();

        Query entityQuery = entityManager.createQuery(
                "DELETE FROM Menu m    WHERE m.tokenMenu in ( :tokenList)");
        entityQuery.setParameter("tokenList", resultList);

        entityQuery.executeUpdate();
    }
}
