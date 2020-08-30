import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";


import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { FormsModule } from "@angular/forms";
import { UserComponent } from './user/user.component';
import { PrenotaMenuComponent } from './prenota-menu/prenota-menu.component';
import { VisualizzaPrenotazioniComponent } from './visualizza-prenotazioni/visualizza-prenotazioni.component';


@NgModule({
  declarations: [
    UserComponent,
    PrenotaMenuComponent,
    VisualizzaPrenotazioniComponent
 
  ],
  imports: [CommonModule, FormsModule, NgbModule],
  exports: [

  ] ,
  entryComponents: [

  ]
})
export class UserModule {}
