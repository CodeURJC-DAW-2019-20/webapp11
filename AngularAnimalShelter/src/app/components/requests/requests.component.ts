import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { RequestsService } from '../../services/requests/requests.service';
import { Adoption } from 'src/app/models/Adoption/adoption.model';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  requests: any;
  
  src=environment.apiBase2+ "/animal"


  constructor(private router: Router, private requestService: RequestsService, private ruta : ActivatedRoute) {
   }

  ngOnInit(): void {
   this.getRequest();
  }


  valueRequest( animalid ,response) {
    this.requestService.evaluateRequest(animalid,response).subscribe(
      response => {
        this.router.navigate(['requests']);
      
      },
      error => {
          this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
          this.router.navigate(['requests']);
        });
      });
  }
  getRequest(){
    this.requestService.getRequests().subscribe(data=>{
      console.log(data);
      this.requests=data;
    });

  }

}
