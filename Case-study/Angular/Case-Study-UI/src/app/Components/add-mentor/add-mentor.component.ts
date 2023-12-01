import { Component } from '@angular/core';
import { FormGroup,FormControl,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginServiceService } from 'src/app/Services/login-service.service';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-add-mentor',
  templateUrl: './add-mentor.component.html',
  styleUrls: ['./add-mentor.component.css']
})
export class AddMentorComponent {

  addMentorForm:FormGroup;

  constructor(private router:Router, private userservice:UserService){
    this.addMentorForm = new FormGroup({
      "name":new FormControl("",Validators.required),
      "emailId":new FormControl("",Validators.required),
      "password":new FormControl("",Validators.required),
    });
  }

  addMentorMethod(){
      console.log(this.addMentorForm.value.emailId);
      console.log(this.addMentorForm.value.password);
      this.userservice.postMentor({"name":this.addMentorForm.value.name,
      "emailId":this.addMentorForm.value.emailId,
      "role":"MENTOR"}).subscribe((user)=>{
        console.log(user);
        alert("mentor added successfully...")
        this.router.navigateByUrl("/dashboard");
      },
      err=>{
        console.log(err);
      })
  }

}
