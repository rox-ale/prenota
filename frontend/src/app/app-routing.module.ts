import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login';
import { AuthGuard } from './_helpers';
import { StartComponent } from './start/start.component';

import { Role } from './model/role.model';

import { ForbiddenComponent } from './forbidden/forbidden.component';
import { AdminComponent } from './adminModule/admin/admin.component';
import { AggiungiGiornoComponent } from './adminModule/aggiungi-giorno/aggiungi-giorno.component';
import { UserComponent } from './userModule/user/user.component';
import { PrenotaMenuComponent } from './userModule/prenota-menu/prenota-menu.component';
import { VisualizzaPrenotazioniComponent } from './userModule/visualizza-prenotazioni/visualizza-prenotazioni.component';
import { VisualizzaPrenotazioniAdminComponent } from './adminModule/visualizza-prenotazioni-admin/visualizza-prenotazioni-admin.component';
import { VisualizzaUtentiComponent } from './adminModule/visualizza-utenti/visualizza-utenti.component';
import { VisualizzaMenuUtentiComponent } from './adminModule/visualizza-menu-utenti/visualizza-menu-utenti.component';


const routes: Routes = [ {
  path: '',
  component: StartComponent,
  canActivate: [AuthGuard]
},    {
  path: 'login',
  component: LoginComponent
},
{
  path: 'admin',
  component: AdminComponent,
  canActivate: [AuthGuard],
  data: { roles: ['ROLE_ADMIN']}
},{
  path: 'aggiungiGiorno',
  component: AggiungiGiornoComponent,
  canActivate: [AuthGuard],
  data: { roles: ['ROLE_ADMIN']}
}, {
  path: 'visualizzaPrenotazioniAdmin',
  component: VisualizzaPrenotazioniAdminComponent,
  canActivate: [AuthGuard],
  data: { roles: [Role.admin]}
},{
  path: "visualizzaUtenti",
  component: VisualizzaUtentiComponent,
  canActivate: [AuthGuard],
  data: { roles: [Role.admin] }
},{
  path: "visualizzaMenuUtenti",
  component: VisualizzaMenuUtentiComponent,
  canActivate: [AuthGuard],
  data: { roles: [Role.admin] }
},
{
  path: 'user',
  component: UserComponent,
  canActivate: [AuthGuard],
  data: { roles: ['ROLE_USER']}
},{
  path: 'visualizzaPrenotazioni',
  component: VisualizzaPrenotazioniComponent,
  canActivate: [AuthGuard],
  data: { roles: ['ROLE_USER']}
},  {
  path: 'prenotaMenu',
  component: PrenotaMenuComponent,
  canActivate: [AuthGuard],
  data: { roles: [Role.user]}
},
{
  path: 'forbidden',
  component: ForbiddenComponent
},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
