package it.alessiorossato.prenotaMensa.util;

import it.alessiorossato.prenotaMensa.bean.Giorno;
import it.alessiorossato.prenotaMensa.bean.Utente;
import it.alessiorossato.prenotaMensa.domain.Menu;
import it.alessiorossato.prenotaMensa.domain.Prenotazione;

import java.util.ArrayList;
import java.util.List;

public class ConvertDomainBean {
    public static it.alessiorossato.prenotaMensa.bean.Menu convertiMenu(Menu menu) {
        it.alessiorossato.prenotaMensa.bean.Menu menuB = new it.alessiorossato.prenotaMensa.bean.Menu();
        menuB.setContorno(menu.getContorno());
        menuB.setExtra(menu.getExtra());
        //menuB.setGiorno();
        //menuB.setListaUtenti();
        menuB.setNote(menu.getNote());
        menuB.setNome(menu.getNome());
        menuB.setPasto(menu.getPasto());
        menuB.setTokenMenu(menu.getTokenMenu());

        return menuB;
    }

    public static List<it.alessiorossato.prenotaMensa.bean.Menu> convertiMenu(List<Menu> menu) {
        List<it.alessiorossato.prenotaMensa.bean.Menu> menuList = new ArrayList<>();
        for (Menu m : menu) {
            it.alessiorossato.prenotaMensa.bean.Menu convertiMenu = convertiMenu(m);
            menuList.add(convertiMenu);
        }
        return menuList;
    }


    public static it.alessiorossato.prenotaMensa.bean.Giorno convertiGiorno(it.alessiorossato.prenotaMensa.domain.Giorno giorno) {
        it.alessiorossato.prenotaMensa.bean.Giorno giornoBean = new it.alessiorossato.prenotaMensa.bean.Giorno();
        giornoBean.setData(giorno.getData());
        giornoBean.setTokenGiorno(giorno.getTokenGiorno());

  /*      List<it.alessiorossato.prenotaMensa.bean.Menu> menus = convertiMenu(new ArrayList<>(giorno.getListaMenu()));
        giornoBean.setListaMenu(new ArrayList<>(menus));
*/
        return giornoBean;
    }


    public static List<Giorno> convertiGiorno(List<it.alessiorossato.prenotaMensa.domain.Giorno> giorni) {
        List<Giorno> listaGiorni = new ArrayList<>();

        for (it.alessiorossato.prenotaMensa.domain.Giorno g : giorni) {

            Giorno convertiGiorno = convertiGiorno(g);
            listaGiorni.add(convertiGiorno);

        }

        return listaGiorni;
    }


    public static it.alessiorossato.prenotaMensa.bean.Prenotazione convertiPrenotazione(Prenotazione prenotazione) {
        it.alessiorossato.prenotaMensa.bean.Prenotazione p = new it.alessiorossato.prenotaMensa.bean.Prenotazione();
        p.setTokenPrenotazione(prenotazione.getTokenPrenotazione());
        p.setMenu(convertiMenu(prenotazione.getMenu()));
        //p.setUtente(convert);

        return p;
    }


    public static List<it.alessiorossato.prenotaMensa.bean.Prenotazione> convertiPrenotazione(List<it.alessiorossato.prenotaMensa.domain.Prenotazione> prenotazioni) {
        List<it.alessiorossato.prenotaMensa.bean.Prenotazione> listaPrenotazioni = new ArrayList<>();

        for (it.alessiorossato.prenotaMensa.domain.Prenotazione p : prenotazioni) {

            it.alessiorossato.prenotaMensa.bean.Prenotazione prenotazione = convertiPrenotazione(p);
            listaPrenotazioni.add(prenotazione);
        }

        return listaPrenotazioni;
    }


    public static it.alessiorossato.prenotaMensa.bean.Utente convertiUtente(it.alessiorossato.prenotaMensa.domain.Utente u) {

        Utente utente = new Utente();
        utente.setTokenUtente(u.getTokenUtente());
        utente.setUsername(u.getUsername());
        utente.setEnable(u.isEnable());
        utente.setCognome(u.getCognome());
        utente.setNome(u.getNome());

        return utente;
    }

}
