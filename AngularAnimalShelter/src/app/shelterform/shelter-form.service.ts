import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';

const BASE_URL = environment.apiBase + '/shelters/';

@Injectable({
  providedIn: 'root'
})
export class ShelterFormService {

  constructor(private http: HttpClient) { }

  createShelter(shelterData : any){
    return this.http.post(BASE_URL, shelterData).pipe(			
			catchError(error => this.handleError(error))
    );
  }

  private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}

}
