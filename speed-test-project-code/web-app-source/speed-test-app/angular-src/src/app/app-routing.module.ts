import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HistoryDetailsComponent } from './components/history-details/history-details.component';
import { MainPageComponent } from './components/main-page/main-page.component';


const routes: Routes = [
  {path : '', component : MainPageComponent},
  {path : 'history-details', component : HistoryDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
