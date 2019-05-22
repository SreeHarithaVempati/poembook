import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { User } from '../User';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  newUser: User;
  password = ''; activestatus: boolean;
  userid = '';
  error: any;
  status: boolean;
  statusCreation = '';
  form: FormGroup;
  submitted: boolean = false;
  authservice: AuthenticationService;
  u = new User();
  isLoggedIn = false;
  isLoginFailed = false;

  
  constructor(private authService: AuthenticationService, private router: Router,private fb: FormBuilder, private myRoute: Router, authservice1: AuthenticationService) {
    this.authservice = authservice1;
    this.form = this.fb.group({
      userid: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  get f() {
    return this.form.controls;
  }

  ngOnInit() {
  }

  onSubmit() {
    this.submitted = true
    if (this.form.valid) {
      this.u.userId = this.f.userid.value;
      this.u.password = this.f.password.value;
      this.authservice.loginUser(this.u).subscribe(data => {
        console.log(this.u.userId);
        this.myRoute.navigate(['dashboard']);
        console.log("Logged in!!! ");
        if (data['token']){
          this.authService.setToken(data['token']);
          this.router.navigate(['dashboard']);
        }
        this.error = "Access denied";
      },
        error => {
          this.error = error;
          if (this.error.status = 409) {
            this.error = "Invalid credentials! Please try again";
          }
        }
      );
    }
    if (this.form.invalid) {
      this.error = "Please follow the instructions and fill again!";
    }
  }

}
