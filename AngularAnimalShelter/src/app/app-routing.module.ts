import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AnimalsComponent } from './components/animals/animals.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { RequestsComponent } from './components/requests/requests.component';
import { AnimalformComponent } from './components/animalform/animalform.component';
import { ContactComponent } from './components/contact/contact.component';
import { ProfileComponent } from './components/profile/profile.component';
import { UserformComponent } from './components/userform/userform.component';
import { ShelterformComponent } from './components/shelterform/shelterform.component';
import { AnimalviewComponent } from './components/animalview/animalview.component';


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'animals', component: AnimalsComponent },
  { path: 'statistics', component: StatisticsComponent },
  { path: 'animalform', component: AnimalformComponent },
  { path: 'request', component: RequestsComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'userform', component: UserformComponent },
  { path: 'shelterform', component: ShelterformComponent },
  { path: 'animal/:name/:type/:age/:description/:size/:photo', component: AnimalviewComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
