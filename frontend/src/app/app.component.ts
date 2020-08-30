import { Component } from '@angular/core';

import { Router } from '@angular/router';
import { AuthenticationService } from './services/authentication.service';
import { UserService } from './services/user.service';
import { Utente } from './model/utente.model';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentUser: Utente;
  public isMenuCollapsed = true;
  //public applicazione=NAME_APPLICATION;
  constructor(
      private router: Router,
      private authenticationService: AuthenticationService,
      private userService:UserService
  ) {
      this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  get isAdmin() {
     // return this.currentUser && this.currentUser.role === Role.Admin;
     return this.userService.isAdmin();
  }

  logout() {
      this.authenticationService.logout();
      this.router.navigate(['/login']);
  }
}