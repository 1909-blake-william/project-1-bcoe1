import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {

  credentials = {
    username: '',
    password: ''
  };
  errorMessage = '';
  errorMessageSubscription: Subscription;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    // every time the subject publishes new content
    // it will invoke the subscriber method
    this.errorMessageSubscription = this.authService.$loginError.subscribe(errorMessage => {
      this.errorMessage = errorMessage;
    });
  }

  login() {
    this.authService.login(this.credentials);
  }

  // logout(){
  //   this.authService.login() not sure what to do here yet
  // }


  ngOnDestroy() {
    this.errorMessageSubscription.unsubscribe();
  }

}
