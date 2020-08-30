import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
 
import { environment } from 'src/environments/environment';
import { AuthenticationService } from '../services/authentication.service';


@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // add auth header with jwt if user is logged in and request is to api url
     /*  const currentUser = this.authenticationService.currentUserValue;
        const isLoggedIn = currentUser && currentUser.token;
        const isApiUrl = request.url.startsWith(environment.apiUrl);
        if (isLoggedIn && isApiUrl) {
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${currentUser.token}`
                }
            });
        }

        return next.handle(request);*/

//console.log('request ',request);
  let AuthToken = this.authenticationService.getAuthToken();
  let User = this.authenticationService.loggedUser();

//console.log('Auth ',AuthToken);
//console.log('User ',User);
  if (AuthToken && User) {
    request = request.clone({
        setHeaders :
        {
          Authorization: AuthToken
        }
      })
  }

  return next.handle(request);

    }
}
