import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  user:any=[];
  courses:any=[];
  constructor(private userservice:UserService, private router:Router){

  }

  ngOnInit(): void {
    this.userservice.getAllMentors().subscribe((res)=>{
      this.user=res;
      console.log(this.user);
    });

    this.userservice.getAllCourses().subscribe((res)=>{
      this.courses=res;
      console.log(this.courses);
    });
  }

  viewDetails(userid){
    console.log(userid);
    this.router.navigateByUrl("/mentordeatils/"+userid);
  }

  addMentor(){
    this.router.navigateByUrl("/addmentor");
  }

  addTask(){
    this.router.navigateByUrl("/addtask");
  }
}

