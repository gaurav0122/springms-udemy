import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SignInUser } from '../Models/UserModel';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  signInApi: string;
  constructor(private http: HttpClient) {
    this.signInApi = "http://localhost:8081/api/user/signup";
  }

  signInService(user: SignInUser) {
    return this.http.post(this.signInApi, user);
  }
}
