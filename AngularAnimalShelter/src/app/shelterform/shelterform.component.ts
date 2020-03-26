import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ShelterFormService } from './shelter-form.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Shelter } from '../models/Shelter/shelter.model';

@Component({
  selector: 'app-shelterform',
  templateUrl: './shelterform.component.html',
  styleUrls: ['./shelterform.component.css']
})
export class ShelterformComponent {

  shelter: Shelter;

  constructor(private router: Router, private shelterFormService: ShelterFormService) { 
    this.shelter = { 
      shelterName: '',
      shelterNif: '',
      shelterEmail: '',
      shelterDescription: '',
      shelterAdress: '',
      shelterPassword: ''
    };
  }

  createShelter(){
    const formData = new FormData();
    formData.append('jsondata',JSON.stringify(this.shelter));
    formData.append('password',this.shelter.shelterPassword);

    this.shelterFormService.createShelter(formData).subscribe(
      shelter => this.router.navigate(['/']),
      error => this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
        this.router.navigate(['/shelterform']);
      })
    );

  }

}
