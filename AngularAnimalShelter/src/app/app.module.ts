// Main

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; 
import { ReactiveFormsModule } from '@angular/forms';

// Routing

import { AppRoutingModule } from './app-routing.module';

// Components

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { AnimalsComponent } from './components/animals/animals.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { RequestsComponent } from './components/requests/requests.component';
import { AnimalformComponent } from './components/animalform/animalform.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ContactComponent } from './components/contact/contact.component';
import { UserformComponent } from './components/userform/userform.component';
import { ShelterformComponent } from './components/shelterform/shelterform.component';
import { AnimalviewComponent } from './components/animalview/animalview.component';

// Auth and misc.
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { BasicAuthInterceptor } from './auth/auth.interceptor';
import { ErrorInterceptor } from './auth/error.interceptor';
import { ChartsModule } from 'ng2-charts';

// Services

import { LoginService } from './auth/login.service';
import { UserformService } from './services/userform/userform.service';
import { AnimalformService } from './services/animalform/animalform.service';
import { ShelterformService } from './services/shelterform/shelterform.service';
import { AnimalsService } from './services/animals/animals.service';
import { ProfileService } from './services/profile/profile.service';
import { StatisticsService } from './services/statistics/statistics.service';

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
    ShelterformComponent,
    AnimalviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ChartsModule
  ],
  providers: [
    ShelterformService,
    UserformService,
    LoginService,
    AnimalformService,
    AnimalsService,
    ProfileService,
    StatisticsService,
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    { provide: LocationStrategy, useClass: HashLocationStrategy }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
