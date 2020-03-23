import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AnimalsComponent } from './animals/animals.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { RequestsComponent } from './requests/requests.component';
import { AnimalformComponent } from './animalform/animalform.component';
import { ContactComponent } from './contact/contact.component';
import { ProfileComponent } from './profile/profile.component';
import { UserformComponent } from './userform/userform.component';
import { ShelterformComponent } from './shelterform/shelterform.component';


const routes: Routes = [
  {
    path:'',component:HomeComponent,
  },
  {
    path:'animals', component:  AnimalsComponent
  },
  {
    path:'statistics', component:   StatisticsComponent
  
  },
  {
    path:'animalform', component:  AnimalformComponent
  },
  {
    path:'request', component:   RequestsComponent
  
  },
  {
    path:'contact', component:   ContactComponent
  
  },
  {
    path:'profile', component:   ProfileComponent
  
  },
  {
    path:'userform', component:   UserformComponent
  
  },
  {
    path:'shelterform', component:  ShelterformComponent
  
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
