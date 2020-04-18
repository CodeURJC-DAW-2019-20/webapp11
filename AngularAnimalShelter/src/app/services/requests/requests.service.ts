import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';

const BASE_URL = environment.apiBase + '/petitions/';

@Injectable({
  providedIn: 'root'
})
export class RequestsService {

  constructor(private http: HttpClient) { }

  evaluateRequest(idAdoption : number) {
    return this.http.post(BASE_URL, idAdoption).pipe(			
			catchError(error => this.handleError(error))
    );
  }

  getRequests() {
    return this.http.get(BASE_URL).pipe(
      catchError(error => this.handleError(error))
    );
  }

  private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}

}