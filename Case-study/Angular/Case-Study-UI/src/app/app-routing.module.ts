import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddMentorComponent } from './Components/add-mentor/add-mentor.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { MentorDetailsComponent } from './Components/mentor-details/mentor-details.component';
import { SignInComponent } from './Components/sign-in/sign-in.component';

const routes: Routes = [
  {path:"", component:SignInComponent},
  {path:"dashboard",component:DashboardComponent},
  {path:"mentordeatils/:id",component:MentorDetailsComponent},
  {path:"addmentor",component:AddMentorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
