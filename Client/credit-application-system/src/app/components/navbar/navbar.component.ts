import { MenuItem } from 'primeng/api';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  items: MenuItem[] = [];
  logoSrc: string =environment.navbarIconSrc;

  constructor() {}

  ngOnInit(): void {
    this.items = [
      {
        label: 'Sign In',
        routerLink: 'login'
      },
      {
        label: 'Sign Up',
        routerLink: 'register'
      },
    ];
  }
}
