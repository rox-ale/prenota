package it.alessiorossato.prenotaMensa.util;

import it.alessiorossato.prenotaMensa.bean.Giorno;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ConvertBeanDomain {

    public static it.alessiorossato.prenotaMensa.domain.Menu convertiMenu(it.alessiorossato.prenotaMensa.bean.Menu menu) {
        it.alessiorossato.prenotaMensa.domain.Menu menuB = new it.alessiorossato.prenotaMensa.domain.Menu();
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

    public static List<it.alessiorossato.prenotaMensa.domain.Menu> convertiMenu(List<it.alessiorossato.prenotaMensa.bean.Menu> menu) {
        List<it.alessiorossato.prenotaMensa.domain.Menu> menuList = new ArrayList<>();
        for (it.alessiorossato.prenotaMensa.bean.Menu m : menu) {
            it.alessiorossato.prenotaMensa.domain.Menu convertiMenu = convertiMenu(m);
            menuList.add(convertiMenu);
        }
        return menuList;
    }

    public static it.alessiorossato.prenotaMensa.domain.Giorno convertiGiorno(Giorno giorno){
        it.alessiorossato.prenotaMensa.domain.Giorno giornoDomain=new it.alessiorossato.prenotaMensa.domain.Giorno();
        giornoDomain.setData(giorno.getData());
        giornoDomain.setTokenGiorno(giorno.getTokenGiorno());

        List<it.alessiorossato.prenotaMensa.domain.Menu> menus = convertiMenu(giorno.getListaMenu());
        giornoDomain.setListaMenu(new HashSet<>(menus ));

        return giornoDomain;
    }
}
