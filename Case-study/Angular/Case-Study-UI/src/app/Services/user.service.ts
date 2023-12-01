import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  getAllMentorsApi:string;
  getAllTaskByUserIdApi:string;
  getAllCoursesApi:string;
  constructor(private http: HttpClient) { 
    this.getAllMentorsApi="http://localhost:8081/api/user/all/mentor";
    this.getAllTaskByUserIdApi="http://localhost:8081/api/user/task/all/";
    this.getAllCoursesApi="http://localhost:8082/api/course/all";
  }

  getAllMentors() {
    return this.http.get(this.getAllMentorsApi);
  }

  getAllTaskByUserId(userid){
    return this.http.get(this.getAllTaskByUserIdApi+userid);
  }

  getAllCourses(){
    return this.http.get(this.getAllCoursesApi);
  }
}
