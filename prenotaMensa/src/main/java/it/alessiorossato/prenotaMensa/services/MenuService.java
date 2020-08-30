package it.alessiorossato.prenotaMensa.services;

import it.alessiorossato.prenotaMensa.domain.Giorno;
import it.alessiorossato.prenotaMensa.domain.Menu;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface MenuService extends Serializable {


    public Menu salvaMenu(Menu menu);

    public List<Menu> utentiGiornoMenu(Long token);

    public List<Menu> menuUtenti(Long token);

    public     List<Menu>  giornoMenuUtenti(Long token);
}
