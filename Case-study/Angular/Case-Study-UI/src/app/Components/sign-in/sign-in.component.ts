import { Component } from '@angular/core';
import { FormGroup,FormControl,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignInUser } from 'src/app/Models/UserModel';
import { LoginServiceService } from 'src/app/Services/login-service.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent {

  loginForm:FormGroup;

  constructor(private router:Router, private loginservice:LoginServiceService){
    this.loginForm = new FormGroup({
      "emailId":new FormControl("",Validators.required),
      "password":new FormControl("",Validators.required),
      "role":new FormControl("",Validators.required)
    });
  }

  signUser(){
    console.log(this.loginForm);
    console.log(this.loginForm.value.emailId);
    console.log(this.loginForm.value.password);
    console.log(this.loginForm.value.role);
    
    
    let email= this.loginForm.value.emailId;
    let password= this.loginForm.value.password;
    let role= this.loginForm.value.role;
   
    
    this.loginservice.signInService({emailId:email,password:password,role:role}).subscribe((res)=>{
      console.log(res);
      if(res===true){
        this.router.navigateByUrl("/dashboard")
      }else{
        
        alert("Invalid cedintiols");
      }
    },
    (err)=>{
      console.log(err.error.text);
      alert("Invalid cedintiols");
    });


  }
}
