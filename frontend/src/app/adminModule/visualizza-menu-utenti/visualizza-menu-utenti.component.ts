import { Component, OnInit } from '@angular/core';
import { IGiorno } from 'src/app/model/giorno.model';
import { MenuService } from 'src/app/services/menu.service';
import { ActivatedRoute } from '@angular/router';
import { IMenu } from 'src/app/model/menu.model';


@Component({
  selector: 'app-visualizza-menu-utenti',
  templateUrl: './visualizza-menu-utenti.component.html',
  styleUrls: ['./visualizza-menu-utenti.component.css']
})
export class VisualizzaMenuUtentiComponent implements OnInit {

  giorno:IGiorno;
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
      menuService.menuUtenti(this.giorno.tokenGiorno).subscribe(

        res=>{
          console.log('menu utenti giorno ',res);
          let menu=res;
          console.log('variabile menu ',menu);
          this.giorno.listaMenu= menu['body'];

          console.log('variabile giorno ',this.giorno);
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
