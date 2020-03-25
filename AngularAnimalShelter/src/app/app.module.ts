import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { AnimalsComponent } from './animals/animals.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { RequestsComponent } from './requests/requests.component';
import { AnimalformComponent } from './animalform/animalform.component';
import { ProfileComponent } from './profile/profile.component';
import { ContactComponent } from './contact/contact.component';
import { UserformComponent } from './userform/userform.component';
import { ShelterformComponent } from './shelterform/shelterform.component';
import { ShelterFormService } from './shelterform/shelter-form.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    AnimalsComponent,
    StatisticsComponent,
    RequestsComponent,
    AnimalformComponent,
    ProfileComponent,
    ContactComponent,
    UserformComponent,
    ShelterformComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule
  ],
  providers: [
    ShelterFormService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
