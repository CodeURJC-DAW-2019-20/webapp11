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


const APP_ROUTES: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'animals', component: AnimalsComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'statistics', component: StatisticsComponent },
  { path: 'animalform', component: AnimalformComponent },
  { path: 'requests', component: RequestsComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'userform', component: UserformComponent },
  { path: 'shelterform', component: ShelterformComponent },
  { path: 'animal/:name/:type/:age/:description/:size/:photo', component: AnimalviewComponent },
];

export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES);
