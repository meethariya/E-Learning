import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LearnerRoutingModule } from './learner-routing.module';
import { HomeComponent } from './components/home/home.component';


@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    LearnerRoutingModule
  ]
})
export class LearnerModule { }
