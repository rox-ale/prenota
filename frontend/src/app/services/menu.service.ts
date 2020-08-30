import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpResponse, HttpParams, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { IGiorno } from '../model/giorno.model';
import { IMenu } from '../model/menu.model';

@Injectable({
  providedIn: 'root'
})
export class MenuService {
  public resourceUrl = environment.SERVER_API_URL + '/api/menu';
  constructor(protected http: HttpClient) { }


  test(): Observable<HttpResponse<IGiorno[]>> {
    return this.http.get<IGiorno[]>(`${this.resourceUrl}/test`, { observe: 'response' });
  }

  utentiGiornoMenu(giornoToken:number): Observable<any> {
/*
    let data = new HttpParams();
    data = data.append('token', String (giornoToken));

    return this.http.get<any>(`${this.resourceUrl}/utentiGiornoMenu`,  { params : data,   observe: 'response'});
*/
/*
    let data = {token: giornoToken};
    this.http.get<any>(`${this.resourceUrl}/utentiGiornoMenu`, {params: data});

*/

    const options =
   { params: new HttpParams().set('token', String ( giornoToken)) }  ;

  return this.http.get<any[]>(`${this.resourceUrl}/utentiGiornoMenu`, options)


  }



  menuUtenti(giornoToken): Observable<HttpResponse<IMenu[]>> {

   // const options = { params: new HttpParams().set('token', String ( giornoToken)) }  ;
/*
    const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json'}), params : {token:giornoToken}, observe: 'response'}


    return this.http.get<IMenu[]>(`${this.resourceUrl}/menuUtenti`,httpOptions);

*/
    let params = new HttpParams()
    .set('token', giornoToken);


    return this.http.get<IMenu[]>(`${this.resourceUrl}/menuUtenti?token=`+giornoToken,  { observe: 'response' });


}


}
