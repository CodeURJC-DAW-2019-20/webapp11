import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class StadisticsService {
  private BASE_URL = environment.apiBase + '/statistics';

  constructor(private _http: HttpClient) {}
  adoptionsMonth(month: any){
    return this._http.get(`${this.BASE_URL}/statistics/${month}`)
  
  }
  private handleError(error: any) {
    console.error(error);
    return Observable.throw("Server error (" + error.status + "): " + error.text())
  }
}
