import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpResponse } from '@angular/common/http';

import { Observable } from 'rxjs';
import { IGiorno } from '../model/giorno.model';
import { IMenu } from '../model/menu.model';
import { IPrenotazione } from '../model/prenotazione.model';

@Injectable({
  providedIn: 'root'
})
export class PrenotazioneService {
  public resourceUrl = environment.SERVER_API_URL + '/api/prenotazione';
  constructor(protected http: HttpClient) { }


  inserisciPrenotazione(menu: IMenu): Observable<any> {
    console.log('Sto chiamando ' + this.resourceUrl + '/prenotaMenu',' lista ',menu);
    //  return this.http.post<any>(this.resourceUrl+'/inserisci', comanda, { observe: 'response' });
    return this.http.post<any>(this.resourceUrl + '/prenotaMenu', menu, { observe: 'response' });
  }


  visualizzaPrenotazioniUtente(): Observable<HttpResponse<IPrenotazione[]>> {
    return this.http.get<IPrenotazione[]>(`${this.resourceUrl}/prenotazioniUtente`, { observe: 'response' });
  }

  cancellaPrenotazione(token:number): Observable<HttpResponse<any>> {

    let r=this.http.post<any>(this.resourceUrl + '/elimina', token, { observe: 'response' });
    console.log('cancellap ',r);
    return r;
  }

}
