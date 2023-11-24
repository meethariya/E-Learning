import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GeneralModule } from './modules/general/general.module';
import { HttpClientModule } from '@angular/common/http';
import { TrainerModule } from './modules/trainer/trainer.module';
import { LearnerModule } from './modules/learner/learner.module';
import { UtilModule } from './modules/util/util.module';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    GeneralModule,
    HttpClientModule,
    TrainerModule,
    LearnerModule,
    UtilModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
