import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError, map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { WebUser } from '../models/WebUser/webUser.model';
import { Shelter } from '../models/Shelter/shelter.model';

const BASE_URL = environment.apiBase;
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  user: WebUser | Shelter;
  role: string = "";
  isLogged:boolean;

  constructor(private http: HttpClient) { 
    let user = JSON.parse(localStorage.getItem('currentUser'));
        if (user) {
            console.log('Logged user');
            this.setCurrentUser(user);
        }
  }

  logIn(user: string, pass: string) {

    let auth = window.btoa(user + ':' + pass);

    const headers = new HttpHeaders({
        Authorization: 'Basic ' + auth,
        'X-Requested-With': 'XMLHttpRequest',
    });

    return this.http.get<WebUser | Shelter>('/api/logIn', { headers })
        .pipe(map(user => {
            
            if (user) {
                this.setCurrentUser(user);
                user.authdata = auth;
                localStorage.setItem('currentUser', JSON.stringify(user));
            }

            return user;
        }));
  }
  
  logOut() {
    this.removeCurrentUser();
    return this.http.get(URL + '/logOut').pipe(
        map(response => {
            
            return response;
        }),
    );
  }

  private setCurrentUser(user: WebUser | Shelter) {
      this.isLogged = true;
      this.user = user;
      this.role = user.role;
      console.log(this.user)
  }

  removeCurrentUser() {
      localStorage.removeItem('currentUser');
      this.isLogged = false;
      this.role = "";
  }
}
