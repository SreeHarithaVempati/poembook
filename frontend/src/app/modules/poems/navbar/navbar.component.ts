import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { AuthenticationService } from '../../authentication/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router:Router,private dashboard:DashboardComponent, public router2:Router, private authService:AuthenticationService) { }

  ngOnInit() {
  }
  logout() {
    this.authService.deleteToken();
    this.router.navigate(['/login']);
    }
}
