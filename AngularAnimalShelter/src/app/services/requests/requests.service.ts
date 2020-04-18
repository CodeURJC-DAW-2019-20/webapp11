import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';

const BASE_URL = environment.apiBase + '/shelters/petitions/';

@Injectable({
  providedIn: 'root'
})
export class RequestsService {

  constructor(private http: HttpClient) { }
  private url;

  evaluateRequest(idAdoption : number,response: String) {
    console.log("hola");
    this.url = environment.apiBase +  '/shelters/petitions/' +idAdoption+ '?response=' +response;


    console.log("request")
    return this.http.post(this.url,"hey").pipe(			
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