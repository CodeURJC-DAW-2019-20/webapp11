import { Injectable } from '@angular/core';
import { WebUser } from '../models/WebUser/webUser.model';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

const BASE_URL = environment.apiBase + '/users/';

@Injectable({
  providedIn: 'root'
})
export class UserformService {

  constructor(private http: HttpClient) { }

  
  createWebUser(userData: FormData){
    return this.http.post(BASE_URL, userData).pipe(			
			catchError(error => this.handleError(error))
    );
  }

  private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}
