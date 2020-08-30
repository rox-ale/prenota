import { Component, OnInit } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  aggiungiMenu() {

    let navigationExtras: NavigationExtras = {
      queryParams: {

      }
    };

    this.router.navigate(["/aggiungiGiorno"], navigationExtras);
  }

  visualizzaPrenotazioni() {

    let navigationExtras: NavigationExtras = {
      queryParams: {

      }
    };

    this.router.navigate(["/visualizzaPrenotazioniAdmin"], navigationExtras);
  }
}
