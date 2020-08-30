import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { NavigationExtras, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';


@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService, private router: Router) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError(err => {
            console.log('errori Interceptor',err);

            if(err.status==423){
              console.log("catch err in ", err);
              let navigationExtras: NavigationExtras = {
                queryParams: {
                  //cassa: JSON.stringify(c)

                }
              };


              this.router.navigate(["/locked"], navigationExtras);

            }

            console.log('numero errore ',err.status);

            if ([401, 403].indexOf(err.status) !== -1) {
                // auto logout if 401 Unauthorized or 403 Forbidden response returned from api
                console.log('Errore interceptor' ,err.status);
                this.authenticationService.logout();
                location.reload(true);
            }

           // const error = err.error.message || err.statusText;
            return throwError(err);
        }))
    }
}
