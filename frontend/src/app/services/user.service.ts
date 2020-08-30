import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
/* import { Utente,Role } from '../model';
import { environment } from 'src/environments/environment'; */
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthenticationService } from './authentication.service';
//import { SERVER_API_URL } from '../app.constants';

import { Utente } from '../model/utente.model';
import { Role } from '../model/role.model';
import { environment } from 'src/environments/environment';
@Injectable({ providedIn: 'root' })
export class UserService {
  token: string;
  ruoli: string[];

    constructor(private http: HttpClient,
      private auth:AuthenticationService
      ) {




     }

    getAll() {
        return this.http.get<Utente[]>(environment.SERVER_API_URL +'/api/users/all');
    }

    getById(id: number) {
        return this.http.get<Utente>(`${environment.apiUrl}/users/${id}`);
    }

    isAdmin(){
      console.log('chiamo is admin');
      this.token = this.auth.getAuthToken();

      const helper = new JwtHelperService();
      const decodedToken = helper.decodeToken(this.token);
     // console.log('Decod user service ',decodedToken);
      this.ruoli = decodedToken['authorities'];
console.log('service ruoli ',this.ruoli)
      if(this.ruoli.includes(Role.admin)){
        return true;
      }else{
        return false;
      }
    }

    isUser(){
      console.log('chiamo is user');
      this.token = this.auth.getAuthToken();

      const helper = new JwtHelperService();
      const decodedToken = helper.decodeToken(this.token);
     // console.log('Decod user service ',decodedToken);
      this.ruoli = decodedToken['authorities'];

      if(this.ruoli.includes(Role.user)){
        return true;
      }else{
        return false;
      }
    }

    public getAuthorities(): string[] {
     this.token = this.auth.getAuthToken();

      const helper = new JwtHelperService();
      const decodedToken = helper.decodeToken(this.token);
      //console.log('Decod user service ',decodedToken);
     return decodedToken['authorities'];

    }


}
