import { Component, OnInit } from '@angular/core';
import { GiornoService } from 'src/app/services/giorno.service';
import { PrenotazioneService } from 'src/app/services/prenotazione.service';
import { Giorno } from 'src/app/model/giorno.model';
import { IMenu } from 'src/app/model/menu.model';
@Component({
  selector: 'app-prenota-menu',
  templateUrl: './prenota-menu.component.html',
  styleUrls: ['./prenota-menu.component.css']
})
export class PrenotaMenuComponent implements OnInit {
giorni:Giorno[];

  constructor(private giornoService:GiornoService,
    private prenotaService:PrenotazioneService) {

    this.giorni=new Array();
           this.giornoService.giorniPrenotabili().subscribe(

        res=>{
          console.log('giorni ',res);
          this.giorni=res['body'];

          console.log('array giorni ',this.giorni);
        }

      );

   }

  ngOnInit(): void {
  }


  prenotaMenu(menu:IMenu, i:number){
    this.prenotaService.inserisciPrenotazione(menu).subscribe(
      res=>{
        console.log('risposta prenotazione ',res);
        this.giorni[i].prenotato=true;
      }
    );

  }

/*
  avanti(){

 return (index + 1) % v.length;

  }*/
}
