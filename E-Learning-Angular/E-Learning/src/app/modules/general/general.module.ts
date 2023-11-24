import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GeneralRoutingModule } from './general-routing.module';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ForgotPasswordComponent
  ],
  imports: [
    CommonModule,
    GeneralRoutingModule,
    ReactiveFormsModule
  ]
})
export class GeneralModule { }
