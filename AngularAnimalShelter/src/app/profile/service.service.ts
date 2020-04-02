import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { NgForm } from '@angular/forms';

const BASE_URL = environment.apiBase + '/users/';


@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  constructor(private http: HttpClient) {

  }
  createGallery(galleryData: FormData) {
    return this.http.post(BASE_URL + 'galleries', galleryData).pipe(

      catchError(error => this.handleError(error))
    );
  }
  getGallery(): Observable<any> {
    return this.http.get(BASE_URL + 'galleries');
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw("Server error (" + error.status + "): " + error.text())

  }

}
