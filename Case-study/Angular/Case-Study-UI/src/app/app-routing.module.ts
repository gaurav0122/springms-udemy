import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { SignInComponent } from './Components/sign-in/sign-in.component';

const routes: Routes = [
  {path:"", component:SignInComponent},
  {path:"dashboard",component:DashboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
