import { Component, OnInit } from '@angular/core';
import { Shelter } from '../../models/Shelter/shelter.model';
import { ShelterformService } from '../../services/shelterform/shelterform.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-shelterform',
  templateUrl: './shelterform.component.html',
  styleUrls: ['./shelterform.component.css']
})
export class ShelterformComponent {

  data: FormData = new FormData();
  shelter: Shelter;
  alert:boolean;
  alertText:string;

  constructor(private router: Router, private shelterformService: ShelterformService) { 
    this.data = new FormData();
    
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

  createShelter( formulary: NgForm ) {
    if( formulary.invalid ) {
      this.alertText='All fields must be Completed';
      this.alert=true;

      Object.values(formulary.controls).forEach( control => {
        control.markAsTouched();
      })
    } 
    
    else {
      this.alert=false;

      this.data.append('jsondata',JSON.stringify(this.shelter));
      this.data.append('password',this.shelter.shelterPassword);

      this.shelterformService.createShelter(this.data).subscribe(
        response => {
          this.router.navigate(['home']); 
          },
        error => {
          this.alertText='Your new shelter hasn\'t been created. Please, try again. If the problem persist, contact with the Tecnical Service.';
          this.alert=true;
          this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/shelterform']);
          });
        }
      );
    }
  }
}
