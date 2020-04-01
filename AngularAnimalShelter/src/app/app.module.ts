import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; 
import { ReactiveFormsModule } from '@angular/forms';

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
import { AnimalviewComponent } from './animalview/animalview.component';
import { UserformService } from './userform/userform.service';
import { LoginService } from './auth/login.service';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { BasicAuthInterceptor } from './auth/auth.interceptor';
import { ErrorInterceptor } from './auth/error.interceptor';

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
    HttpClientModule
  ],
  providers: [
    ShelterFormService,
    UserformService,
    LoginService,
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    { provide: LocationStrategy, useClass: HashLocationStrategy }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
