import { JwtResponse } from './JwtResponse';
import { LoginRequest } from './../models/account/loginRequest';
import { SignUpResponse } from './../models/account/signUpResponse';
import { SignUpRequest } from '../models/account/signUpRequest';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  apiUrl: string = environment.baseUrl;
  userToken: any;
  decodedToken: any;
  jwtHelper: JwtHelperService = new JwtHelperService();
  TOKEN_KEY = 'token';
  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private toastrService: ToastrService
  ) {}

  signUp(signUpRequest: SignUpRequest): Observable<SignUpResponse> {
    return this.httpClient.post<SignUpResponse>(
      this.apiUrl + 'accounts',
      signUpRequest
    );
  }

  signIn(loginRequest: LoginRequest){
    this.loginforToken(loginRequest).subscribe(
      (data) => {
        // this.saveToken(data.body.data.token);
        // this.userToken = data.body.token;
        console.log(data);
        
        
        
        // console.log(this.jwtHelper.decodeToken(data));
        
        // this.decodedToken = this.jwtHelper.decodeToken(data.body.data.token);
        // this.router.navigateByUrl('/cars');

        // this.toastrService.success('Giriş başarılı');

        // console.log(this.decodedToken)
        // console.log(this.getUserRoles());
        // console.log(this.getUserName());
        // console.log(this.getUserId());
      },
      (responseError) => {
        // let errorMessage = ErrorHelper.getMessage(responseError);
        // this.toastrService.error(errorMessage);
        console.log(responseError);
        console.log(loginRequest);
        
        
        this.toastrService.error("Login işlemi başarısız oldu");
        
      }
    );
  }

  loginforToken(loginRequest: LoginRequest): Observable<any> {

    return this.httpClient.post<JwtResponse>(this.apiUrl + 'login', loginRequest,{});
  }

  saveToken(token:any) {
    localStorage.setItem(this.TOKEN_KEY, token);
  }
}
