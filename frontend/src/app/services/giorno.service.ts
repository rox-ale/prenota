import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';

import { Observable } from 'rxjs';
import { IGiorno } from '../model/giorno.model';

@Injectable({
  providedIn: 'root'
})
export class GiornoService {
  public resourceUrl = environment.SERVER_API_URL + '/api/giorno';
  constructor(protected http: HttpClient) { }

  aggiornaGiorno(giorno: IGiorno): Observable<any> {
    console.log('Sto chiamando ' + this.resourceUrl + '/inserisci',' lista ',giorno);
    //  return this.http.post<any>(this.resourceUrl+'/inserisci', comanda, { observe: 'response' });
    return this.http.post<any>(this.resourceUrl + '/inserisci', giorno, { observe: 'response' });
  }

  giorniPrenotabili(): Observable<HttpResponse<IGiorno[]>> {
    return this.http.get<IGiorno[]>(`${this.resourceUrl}/giorniPrenotabili`, { observe: 'response' });
  }

  listaGiorniDisponibili(): Observable<HttpResponse<IGiorno[]>> {
    return this.http.get<IGiorno[]>(`${this.resourceUrl}/listaGiorniDisponibili`, { observe: 'response' });
  }


  cancellaGiorno(tokenGiorno): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${this.resourceUrl}/elimina`, tokenGiorno, { observe: 'response' });
  }

}
