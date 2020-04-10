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
  alert:boolean;
  alertText:string;

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
    this.alert=false;
  }

  createShelter(){
    if(this.shelter.shelterName.length == 0 || this.shelter.shelterNif.length == 0
      || this.shelter.shelterEmail.length == 0 || this.shelter.shelterDescription.length == 0
      || this.shelter.shelterAdress.length == 0 || this.shelter.shelterPassword.length == 0){
      this.alertText='All fields must be Completed';
      this.alert=true;

    } else {
      this.alert=false;
      this.alertText='';
      const formData = new FormData();
      formData.append('jsondata',JSON.stringify(this.shelter));
      formData.append('password',this.shelter.shelterPassword);

      this.shelterFormService.createShelter(formData).subscribe(
        shelter => {
          if(shelter){
            this.router.navigate(['/']); 
          } else {
            /*this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
              this.router.navigate(['/shelterform']);
            });*/
            this.shelter.shelterName='';
            this.shelter.shelterEmail='';
            this.shelter.shelterNif='';
            this.shelter.shelterPassword='';
            this.shelter.shelterDescription='';
            this.shelter.shelterAdress='';
            this.shelter.shelterPassword='';
            this.alertText='Profile already registered. Please, send a mail to Tecnical Service for recovering your data';
            this.alert=true;
          }
          console.log(shelter)
        },
        error => {
          /*
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/shelterform']);
          });*/
          this.shelter.shelterName='';
          this.shelter.shelterEmail='';
          this.shelter.shelterNif='';
          this.shelter.shelterPassword='';
          this.shelter.shelterDescription='';
          this.shelter.shelterAdress='';
          this.shelter.shelterPassword='';
          this.alertText='Your profile hasn\'t been created. Please, try again. If the problem persist, contact with the Tecnical Service.';
          this.alert=true;
        }
      );
    }
    

  }

}
