import { Component, OnInit } from '@angular/core';
import { MenuService } from 'src/app/services/menu.service';
import { ActivatedRoute } from '@angular/router';
import { IGiorno } from 'src/app/model/giorno.model';

class UtenteMenu{
  public nomeCognome:string;
  public menu:string;
}

@Component({
  selector: 'app-visualizza-utenti',
  templateUrl: './visualizza-utenti.component.html',
  styleUrls: ['./visualizza-utenti.component.css']
})

export class VisualizzaUtentiComponent implements OnInit {

  giorno:IGiorno;

  listaUtenti:UtenteMenu[];

  days = [
    'DOMENICA',
    'LUNEDI',
    'MARTEDI',
    'MERCOLEDI',
    'GIOVEDI',
    'VENERDI',
    'SABATO',
  ];


  constructor(private menuService:MenuService,  private route: ActivatedRoute) {
  this.listaUtenti=new Array();

    this.route.queryParams.subscribe(params => {
      this.giorno = JSON.parse(params['giorno']);

      console.log('giorno utente menu ', this.giorno);

/*       this.cassaService.prodotti(this.cassa.id).subscribe(result => {
        // to see the result
        console.log("\nRicevuto", result.body);
        this.prodotto = result.body;
        this.sortByKey(this.prodotto, "id");
        this.prodotto.forEach(ele => {
          this.sortByKey(ele.prodottos, "id");
        });
      }); */
      console.log('token giorno ', this.giorno.tokenGiorno);
      menuService.utentiGiornoMenu(this.giorno.tokenGiorno).subscribe(

        res=>{
          console.log('menu utenti giorno ',res);

          res.forEach(element => {
       //     console.log('element ',element);
            let nomeMenu=element.nome;
       //     console.log('nome menu ',nomeMenu);
       //     console.log('element ',element);
            element.prenota.forEach(prenota => {
        //      console.log('prenota ',prenota);
        //      console.log('nome ',prenota.utente.nome,' cognome ',prenota.utente.cognome);

              let utenteMenu=new UtenteMenu();
              utenteMenu.menu=nomeMenu;
              utenteMenu.nomeCognome=prenota.utente.cognome+ ' '+prenota.utente.nome;
              this.listaUtenti.push(utenteMenu);
            });
          });
        }

      );


    });







  }

  ngOnInit(): void {
  }

  public getNomeGiorno(data: Date) {
    let v = new Date(data);
    return this.days[v.getDay()];
  }

}
