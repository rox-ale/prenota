package it.alessiorossato.prenotaMensa.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "giorno")
public class Giorno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenGiorno;
    @Column(name = "data")
    private LocalDate data;

    @OneToMany(mappedBy = "giorno",fetch = FetchType.LAZY)

    private Set<Menu> listaMenu = new HashSet<>();

    public Giorno() {
    }

    public Integer getTokenGiorno() {
        return tokenGiorno;
    }

    public void setTokenGiorno(Integer tokenGiorno) {
        this.tokenGiorno = tokenGiorno;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Set<Menu> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(Set<Menu> listaMenu) {
        this.listaMenu = listaMenu;
    }

    @Override
    public String toString() {
        return "Giorno{" +
                "tokenGiorno=" + tokenGiorno +
                ", data=" + data +
              //  ", listaMenu=" + listaMenu +
                '}';
    }
}
