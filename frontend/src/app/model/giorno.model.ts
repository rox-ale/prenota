import { Menu, IMenu } from './menu.model';

export interface IGiorno {
    tokenGiorno?: number;
    data?: Date;
    listaMenu?: IMenu[];
    prenotato?:boolean;

  }

  export class Giorno implements IGiorno {
    constructor(
      public tokenGiorno?: number,
      public data?: Date,
      public listaMenu?: IMenu[],
      public prenotato?:boolean
    ) {}
  }
