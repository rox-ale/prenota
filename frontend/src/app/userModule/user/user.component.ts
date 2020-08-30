import { Component, OnInit } from '@angular/core';
import { GiornoService } from 'src/app/services/giorno.service';
import { MenuService } from 'src/app/services/menu.service';
import { NavigationExtras, Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private giornoService:GiornoService, private menuService:MenuService,
    private router:Router
    ) { }

  ngOnInit(): void {
  }

  selezionaGiorni(){


      let navigationExtras: NavigationExtras = {
        queryParams: {

        }
      };

      this.router.navigate(["/prenotaMenu"], navigationExtras);
  }



  visualizzaPrenotazioni(){


    let navigationExtras: NavigationExtras = {
      queryParams: {

      }
    };

    this.router.navigate(["/visualizzaPrenotazioni"], navigationExtras);
}




  test(){
    this.menuService.test().subscribe();

  }
}
