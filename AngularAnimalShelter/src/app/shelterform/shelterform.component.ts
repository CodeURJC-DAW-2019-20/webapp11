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
      shelterPassword: '',
      role: 'SHELTER'
    };
  }

  createShelter(){
    if(this.shelter.shelterName.length == 0 || this.shelter.shelterNif.length == 0
      || this.shelter.shelterEmail.length == 0 || this.shelter.shelterDescription.length == 0
      || this.shelter.shelterAdress.length == 0 || this.shelter.shelterPassword.length == 0){
      
      alert('Fill all the blanks');

    } else {
      const formData = new FormData();
      formData.append('jsondata',JSON.stringify(this.shelter));
      formData.append('password',this.shelter.shelterPassword);

      this.shelterFormService.createShelter(formData).subscribe(
        shelter => {
          if(shelter){
            this.router.navigate(['/']); 
          } else {
            this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
              this.router.navigate(['/shelterform']);
            });
            alert('User already registered');
          }
          console.log(shelter)
        },
        error => {
          alert('Something went wrong, try again');
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/shelterform']);
          });
        }
      );
    }
    

  }

}
