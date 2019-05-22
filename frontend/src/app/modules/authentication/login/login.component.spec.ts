import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { By, BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { RegisterComponent } from '../register/register.component';
import { DebugElement } from '@angular/core';
import { DashboardComponent } from '../../poems/dashboard/dashboard.component';
import { FavouriteComponent } from '../../poems/favourite/favourite.component';
import { NavbarComponent } from '../../poems/navbar/navbar.component';
import { AuthenticationService } from '../authentication.service';
import { RouterTestingModule } from '@angular/router/testing';


fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let de: DebugElement;
  let el: HTMLElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers:[AuthenticationService],
      imports: [ReactiveFormsModule,RouterTestingModule, FormsModule, BrowserModule, HttpClientModule, AppRoutingModule],
      declarations: [RegisterComponent, LoginComponent,DashboardComponent,FavouriteComponent,NavbarComponent]
    })
      .compileComponents();
      fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    de = fixture.debugElement.query(By.css('form'));
    el = de.nativeElement;
    fixture.detectChanges();
  }));


  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should be valid when all fields are valid', () => {

    let email = component.form.controls["email"]
    email.setValue("bhanuprat123")
    let password = component.form.controls["password"]
    password.setValue("B222@kcbr")
    expect(component.form.valid).toBeTruthy();

  }
  )
  it('should be valid when all fields are valid', () => {

    let email = component.form.controls["email"]
    email.setValue("")
    let password = component.form.controls["password"]
    password.setValue("")
    expect(component.form.invalid).toBeTruthy();

  }
  )
  it('should call onSubmit when submit clicked on form', () => {

    spyOn(component, 'onSubmit');
    el = fixture.debugElement.query(By.css('button')).nativeElement;
    el.click();
    expect(component.onSubmit).toHaveBeenCalledTimes(1);
  })
});
