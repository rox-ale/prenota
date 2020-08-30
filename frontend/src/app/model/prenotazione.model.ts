import { Giorno } from './giorno.model';
import { IUtente } from './utente.model';
import { IMenu } from './menu.model';
export interface IPrenotazione {
    tokenPrenotazione?: number;
    menu?: IMenu;
    utente?: IUtente;

  }

  export class Prenotazione implements IPrenotazione {
    constructor(
       public      tokenPrenotazione       ?: number,
      public  menu?: IMenu,
      public utente?: IUtente
    ) {}
  }
