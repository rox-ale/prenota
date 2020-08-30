import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import {AuthenticationService} from '../services/authentication.service';
import { NAME_APPLICATION } from '../app.constants';
import { UserService } from '../services/user.service';

@Component({ templateUrl: 'login.component.html',
styleUrls: ['./login.component.css'] })
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;
    error = '';

    applicazione=NAME_APPLICATION;
    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private userService:UserService
    ) {
        // redirect to home if already logged in
        if (this.authenticationService.currentUserValue) {
            console.log('constructor');
            if(this.userService.isAdmin()){
                console.log('utente admin');
                this.router.navigate(['admin']);
            }else{
                console.log('utente user');
                this.router.navigate(['user']);
            }
            //this.router.navigate(['/']);
        }
    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

        // get return url from route parameters or default to '/'
        console.log('this.route.snapshot.queryParams[\'returnUrl\']',this.route.snapshot.queryParams['returnUrl'])
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    onSubmit() {
        console.log('onsubmit');
        this.submitted = true;

        // stop here if form is invalid
        if (this.loginForm.invalid) {
            return;
        }

        this.loading = true;
        this.authenticationService.login(this.f.username.value, this.f.password.value)
            .pipe(first())
            .subscribe(
                data => {
                    console.log('login data',data);

                    
                    if(this.userService.isAdmin()){
                        console.log('utente admin');
                        this.router.navigate(['/admin']);
                    }else{
                        console.log('utente user');
                        this.router.navigate(['/user']);
                    }
                    //this.router.navigate([this.returnUrl]);
                },
                error => {
                  console.log('errore login ',error);
                    this.error = error.error;
                    this.loading = false;
                });
    }
}
