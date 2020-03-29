import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  private BASE_URL = environment.apiBase + '/animals';

  constructor(private http: HttpClient) { }
  getAnimalsByType(animalType: string): Observable<any> {
    return this.http.get(`${this.BASE_URL}/animalType/${animalType}`);
    
  }
  getAnimalsByName(animalName: string): Observable<any> {
    return this.http.get(`${this.BASE_URL}/animalName/${animalName}`);
    
  }


  private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}