import { Giorno } from './giorno.model';
import { IPrenotazione } from './prenotazione.model';

export interface IMenu {
    token_menu?: number;
    nome?: string;
    pasto?: string;
    contorno?: string;
    extra?: string;
    note?: string;
    giorno?: Giorno;
    prenota?:IPrenotazione[];
  }

  export class Menu implements IMenu {
    constructor(
       public  token_menu?: number,
      public  nome?: string,
      public pasto?: string,
      public contorno?: string,
      public extra?: string,
      public note?: string,
      public giorno?: Giorno,
      public prenota?:IPrenotazione[]
    ) {}
  }
