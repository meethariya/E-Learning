import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./modules/general/general.module').then((m) => m.GeneralModule),
  },
  {
    path: 'trainer',
    loadChildren: () =>
      import('./modules/trainer/trainer.module').then((m) => m.TrainerModule),
  },
  {
    path: 'learner',
    loadChildren: () =>
      import('./modules/learner/learner.module').then((m) => m.LearnerModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
