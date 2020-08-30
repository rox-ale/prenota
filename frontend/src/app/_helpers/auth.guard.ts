import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthenticationService } from '../services/authentication.service';


@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService,
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        let token = this.authenticationService.getAuthToken();

        const helper = new JwtHelperService();
        const decodedToken = helper.decodeToken(token);

        console.log('Auth guard dec ',decodedToken);



        if (!this.authenticationService.isLogged())
        {
          this.router.navigate(['login'], { queryParams: { returnUrl: state.url } });
          return false;
        }
        {
          let ruoli = decodedToken['authorities'];
          console.log('Ruoli ',ruoli);
          console.log('Route ',route);

     
         
          if (route.data.roles == null || route.data.roles.length === 0){
            console.log('auth guard true');
            return true;
          }
     
          else if ( this. checkRole(ruoli,route.data.roles)){
               console.log('return true roles');
              return true;
             }
         
          else
          {
            console.log('forbidden');
            this.router.navigate(['forbidden']);
           //this.router.navigate(['/']);
            return false;
          }
        }
    }


    checkRole(ruoliUtente,ruoliComponent){
      var response=false;
 console.log('ruoliUtente',ruoliUtente,' ruoliComponent ',ruoliComponent);
      ruoliUtente.forEach(ruoloUtente =>{
 
        ruoliComponent.forEach(ruoloComponente => {
 
          if(ruoloUtente==ruoloComponente){
  
            response= true;
            
          }
        });
      } );
 
      return response;      
    }
}
