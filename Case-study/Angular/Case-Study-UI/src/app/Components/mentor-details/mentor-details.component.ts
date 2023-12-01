import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-mentor-details',
  templateUrl: './mentor-details.component.html',
  styleUrls: ['./mentor-details.component.css']
})
export class MentorDetailsComponent {

  mentorId;
  tasks:any;
  constructor(private actroute:ActivatedRoute, private userservice:UserService){
    
  }

  ngOnInit(): void {
    this.actroute.params.subscribe(params=>{
      this.mentorId = params["id"];
      console.log(this.mentorId);
      this.userservice.getAllTaskByUserId(this.mentorId).subscribe((task)=>{
        this.tasks = task;
        console.log(task);
      });
    });
  }
}