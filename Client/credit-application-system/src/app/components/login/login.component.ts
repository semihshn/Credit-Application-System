import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  model: any = {};
  loading = false;
  returnUrl: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private toastrService: ToastrService,
    private authservice: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.createLoginForm();
  }
  createLoginForm() {
    this.loginForm = this.formBuilder.group({
      mail: ["",Validators.required],
      password: ["",Validators.required],
    });
  }
  login() {
    if (this.loginForm.valid) {
      // console.log(this.loginForm.value);
      let loginModel = Object.assign({}, this.loginForm.value);
      this.authservice.signIn(loginModel);
    }
  }
}
