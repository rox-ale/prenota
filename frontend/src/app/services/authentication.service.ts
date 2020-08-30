import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
// import { User } from '../_models';
import { environment } from 'src/environments/environment';
//import { SERVER_API_URL } from '../app.constants';

export const CONST_UTENTE = "Utente";
export const CONST_AUTH_TOKEN = "AuthToken";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<any>;
    public currentUser: Observable<any>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<any>(sessionStorage.getItem(CONST_AUTH_TOKEN));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): any {
        return this.currentUserSubject.value;
    }

    login(username: string, password: string) {
        return this.http.post<any>(environment.SERVER_API_URL +"/api/auth/signin", { username, password })
            .pipe(map(data => {
                // login successful if there's a jwt token in the response
               /* if (user && user.token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    this.currentUserSubject.next(user);
                }*/

                sessionStorage.setItem(CONST_UTENTE, username);
                sessionStorage.setItem(CONST_AUTH_TOKEN, `Bearer ${data.token}`);
                this.currentUserSubject.next(data);
                return data;
            }));
    }

    logout() {
        // remove user from local storage to log user out
        sessionStorage.removeItem(CONST_AUTH_TOKEN);
        sessionStorage.removeItem(CONST_UTENTE);
        this.currentUserSubject.next(null);
    }


    loggedUser()
    {
      let utente = sessionStorage.getItem(CONST_UTENTE);

      return (sessionStorage.getItem(CONST_UTENTE) != null) ? utente : "";
    }

    getAuthToken()
    {
      if (this.loggedUser())
        return sessionStorage.getItem(CONST_AUTH_TOKEN);
      else
        return "";
    }

    isLogged()
    {
      return (sessionStorage.getItem(CONST_UTENTE) != null) ? true : false;
    }

}
