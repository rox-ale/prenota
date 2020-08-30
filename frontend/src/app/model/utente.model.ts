
export interface IUtente {
    id?: number;
    username?: string;
    password?: string;
    enable?:boolean;
    roles?:string ;
    nome?:string;
    cognome?:string;
  }

  export class Utente implements IUtente {
    constructor(
      public id?: number,
      public username?: string,
      public password?: string,
      public enable?:boolean,
      public roles?:string,
      public nome?:string,
      public cognome?:string
    ) {}
  }
