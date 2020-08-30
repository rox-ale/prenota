import { Component, OnInit } from '@angular/core';
import { GiornoService } from 'src/app/services/giorno.service';
import { IGiorno } from 'src/app/model/giorno.model';
import { NavigationExtras, Router } from '@angular/router';

@Component({
  selector: 'app-visualizza-prenotazioni-admin',
  templateUrl: './visualizza-prenotazioni-admin.component.html',
  styleUrls: ['./visualizza-prenotazioni-admin.component.css']
})
export class VisualizzaPrenotazioniAdminComponent implements OnInit {
  giorni:IGiorno[];
  constructor(private giornoService:GiornoService,  private router: Router) {

    this.giorni=new Array();
      giornoService.listaGiorniDisponibili().subscribe(
        res=>{
          console.log("risposta giorni disponibili ",res);
          this.giorni=res['body'];
        }

      );

   }

  ngOnInit(): void {
  }


  goToPage(g: IGiorno) {


    let navigationExtras: NavigationExtras = {
      queryParams: {
        giorno: JSON.stringify(g)
      }
    };

    this.router.navigate(["/visualizzaUtenti"], navigationExtras);
  }

  goToPageMenuUtenti(g: IGiorno) {


    let navigationExtras: NavigationExtras = {
      queryParams: {
        giorno: JSON.stringify(g)
      }
    };

    this.router.navigate(["/visualizzaMenuUtenti"], navigationExtras);
  }

  eliminaGiorno(tokenGiorno){
    let risposta = confirm("Sei sicuro di volere eliminare? \nEliminando cancelli anche tutte le prenotazioni associate");
    if (risposta) {
      this.giornoService.cancellaGiorno(tokenGiorno).subscribe(result => {
        console.log("Storico: ", result);

        let obj = this.giorni.find(o => o.tokenGiorno === tokenGiorno);
        const idx = this.giorni.indexOf(obj);
        this.giorni.splice(idx, 1);
      });
    }
  }


}
