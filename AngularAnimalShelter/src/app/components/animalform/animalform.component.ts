import { Component } from '@angular/core';
import { Animal } from '../../models/Animal/animal.model';
import { AnimalformService} from '../../services/animalform/animalform.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-animalform',
  templateUrl: './animalform.component.html',
  styleUrls: ['./animalform.component.css']
})
export class AnimalformComponent  {

  data: FormData = new FormData();
  animal: Animal;
  file:any;
  alert:boolean;
  alertText:string;

  constructor(private router: Router, private animalFormService: AnimalformService) { 
    this.data = new FormData();
    this.animal = {
      animalName:'',
      animalAge:null,
      animalType:'dog',
      animalSize:'no',
      animalDescription:''
    }
    this.alert=false;
  }
  loadAnimalImage(event){
     this.file = event.target.files;
     this.data.append('animalPhoto', this.file[0]);
  }

  createAnimal( formulary: NgForm ) {

    if( formulary.invalid ) { 
      this.alertText='All fields must be Completed';
      this.alert=true;

      Object.values(formulary.controls).forEach( control => {
        control.markAsTouched();
      })
    }

    else {
      this.alert=false;

      this.data.append('jsondata',JSON.stringify(this.animal));

      this.animalFormService.createAnimal(this.data).subscribe(
        response => {
          this.router.navigate(['home']);
        },
        error => {
          this.alertText=`The animal hasn\'t been created. \nPlease, try again. If the problem persist, contact with the Tecnical Service.`;
          this.alert=true;
          this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
            this.router.navigate(['animalform']);
          });
        }
      );
    }
  }
}
