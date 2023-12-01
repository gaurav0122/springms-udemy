import { Component } from '@angular/core';
import { FormGroup,FormControl,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginServiceService } from 'src/app/Services/login-service.service';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent {

  addTaskForm:FormGroup;

  constructor(private router:Router, private userservice:UserService){
    this.addTaskForm = new FormGroup({
      "courseId":new FormControl("",Validators.required),
      "mentorId":new FormControl("",Validators.required),
      "startdate":new FormControl("",Validators.required),
      "endDate":new FormControl("",Validators.required),
      "hoursADay":new FormControl("",Validators.required),
    });
  }

  addTaskMethod(){

    this.userservice.addTask(this.addTaskForm.value.mentorId,this.addTaskForm.value.courseId,{
          "startdate":this.addTaskForm.value.startdate,
        "endDate":this.addTaskForm.value.endDate,
        "hoursADay":this.addTaskForm.value.hoursADay
    }).subscribe(res=>{
      console.log(res);
      this.router.navigateByUrl("dashboard");
    })
  }
}
