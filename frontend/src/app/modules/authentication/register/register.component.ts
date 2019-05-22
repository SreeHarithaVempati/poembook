import { Component, OnInit } from '@angular/core';
import { User } from '../User';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { formArrayNameProvider } from '@angular/forms/src/directives/reactive_directives/form_group_name';
import { NgForm, FormBuilder, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  newUser: User;
  username = '';
  password = '';
  userId = '';
  error1 = '';
  error: any;
  status = '';
  form: FormGroup;
  submitted: boolean = false;
  authservice: AuthenticationService;
  u = new User();
  
  constructor(private fb: FormBuilder, private router: Router, authservice1: AuthenticationService) {
    this.authservice = authservice1;
    this.form = this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(30)]],
      lastName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(30)]],
      userId: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });  }

  ngOnInit() {
  }

  get f() {
    return this.form.controls;
  }

  onSubmit(){
    this.submitted = true
    if (this.form.valid) {
      this.u.firstName = this.f.firstName.value;
      this.u.lastName = this.f.lastName.value;
      this.u.userId = this.f.userId.value;
      this.u.password = this.f.password.value;
      this.authservice.registerUser(this.u).subscribe(data => {
        this.error1 = "You have been successfully registered. Please click login to continue!";
       this.router.navigate(['login']);

      },
        error => {
          this.error = error;
          if (this.error.status = 409) {
            console.log(this.error);
            this.error1 = this.error.error.error;
          }
        }
      );
    }
    if (this.form.invalid) {
      this.error1 = "Please follow the instructions and fill again!";
    }
  }

  resetInput(registerForm: NgForm){
    registerForm.reset();
  }

}
