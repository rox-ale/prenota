import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";


import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { FormsModule } from "@angular/forms";
import { AdminComponent } from './admin/admin.component';
import { AggiungiGiornoComponent } from './aggiungi-giorno/aggiungi-giorno.component';
import { VisualizzaPrenotazioniAdminComponent } from './visualizza-prenotazioni-admin/visualizza-prenotazioni-admin.component';
import { VisualizzaUtentiComponent } from './visualizza-utenti/visualizza-utenti.component';
import { VisualizzaMenuUtentiComponent } from './visualizza-menu-utenti/visualizza-menu-utenti.component';



@NgModule({
  declarations: [
    AdminComponent,
    AggiungiGiornoComponent,
    VisualizzaPrenotazioniAdminComponent,
    VisualizzaUtentiComponent,
    VisualizzaMenuUtentiComponent

  ],
  imports: [CommonModule, FormsModule, NgbModule],
  exports: [

  ] ,
  entryComponents: [

  ]
})
export class AdminModule {}
