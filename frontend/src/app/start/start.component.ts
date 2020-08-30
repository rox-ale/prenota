import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {

  constructor(private router: Router,
    private userService:UserService
    ) { 

      console.log('Start component');
      if(this.userService.isAdmin()){
    
        this.router.navigate(['admin']);
    }else{
       
        this.router.navigate(['user']);
    }

    }

  ngOnInit(): void {
  }

}
