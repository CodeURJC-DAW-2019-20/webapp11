import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';

const BASE_URL= environment.apiBase+'/messages/';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(private http: HttpClient) { }
    sendEmail(emailData : FormData){
        return this.http.post(BASE_URL,emailData).pipe(
          catchError(error => this.handleError(error))
        );
    }
    private handleError(error: any) {
      return Observable.throw("Server error (" + error.status + "): " + error.text())
    }
}
