package it.alessiorossato.prenotaMensa.services.imp;

import it.alessiorossato.prenotaMensa.domain.Giorno;
import it.alessiorossato.prenotaMensa.domain.Menu;
import it.alessiorossato.prenotaMensa.repository.GiornoDao;
import it.alessiorossato.prenotaMensa.repository.MenuDao;
import it.alessiorossato.prenotaMensa.services.GiornoService;
import it.alessiorossato.prenotaMensa.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MenuServiceImp implements MenuService {


    @Autowired
    MenuDao menuDao;

    @Autowired
    GiornoDao giornoDao;

    @Override
    public Menu salvaMenu(Menu menu) {

        Menu salvaMenu = menuDao.salvaMenu(menu);
        return salvaMenu;
    }

    public List<Menu> utentiGiornoMenu(Long token) {
        List<Menu> giornoMenu = menuDao.utentiGiornoMenu(token);
        return giornoMenu;
    }

    public List<Menu> menuUtenti(Long token) {
        List<Menu> menuList = menuDao.menuUtenti(token);
        return menuList;
    }

    @Override
    public List<Menu> giornoMenuUtenti(Long token) {

        List<Giorno> giornos = giornoDao.giornoMenuUtenti(token);
        if (giornos.size() == 1) {
            Set<Menu> listaMenu = giornos.get(0).getListaMenu();
            List<Menu> menu = new ArrayList<>(listaMenu);
            return menu;
        }
        return new ArrayList<>();

    }
}
