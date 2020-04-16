import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

const BASE_URL = environment.apiBase + '/animals/';

@Injectable({
  providedIn: 'root'
})
export class AnimalformService {
  
  constructor(private http: HttpClient) { }
  
  createAnimal( animalData: FormData ) {
    return this.http.post(BASE_URL, animalData).pipe(
      catchError(error => this.handleError(error))
    );
  }

  private handleError(error: any) {
		return Observable.throw("Server error (" + error.status + "): " + error.text())
  }
  
}