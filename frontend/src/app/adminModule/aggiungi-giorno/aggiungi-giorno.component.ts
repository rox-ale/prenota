import { Component, OnInit } from '@angular/core';
import { Giorno } from 'src/app/model/giorno.model';
import { Menu } from 'src/app/model/menu.model';
import { GiornoService } from 'src/app/services/giorno.service';
@Component({
  selector: 'app-aggiungi-giorno',
  templateUrl: './aggiungi-giorno.component.html',
  styleUrls: ['./aggiungi-giorno.component.css']
})
export class AggiungiGiornoComponent implements OnInit {

  giorno: Giorno;
  errore = '';
  constructor(private giornoService:GiornoService) {
    this.giorno = new Giorno();
    this.giorno.listaMenu = new Array();

  }

  ngOnInit(): void {
  }


  aggiungiMenu() {
    let menu = new Menu();
    menu.nome = '';
    menu.pasto = '';
    menu.note = '';
    menu.extra = '';
    this.giorno.listaMenu.push(menu);

  }

  rimuoviMenu(i) {
    this.giorno.listaMenu.splice(i, 1);
  }

  salva(){
    this.giornoService.aggiornaGiorno(this.giorno).subscribe(
      res=>{
        console.log('risposta ',res);
        this.giorno=new Giorno();
        this.giorno.listaMenu = new Array();
      }
    );
  }

}
