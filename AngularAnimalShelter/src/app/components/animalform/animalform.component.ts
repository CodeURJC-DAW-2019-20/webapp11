import { Component, OnInit } from '@angular/core';
import { Animal } from '../../models/Animal/animal.model';
import { AnimalformService} from '../../services/animalform/animalform.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-animalform',
  templateUrl: './animalform.component.html',
  styleUrls: ['./animalform.component.css']
})
export class AnimalformComponent  {
  data: FormData;
  animal: Animal;
  file:any;
  alert:boolean;
  alertText:string;

  constructor(private router: Router, private animalFormService: AnimalformService) { 
    this.data = new FormData;
    this.animal = {
      animalName:'',
      animalAge:null,
      animalType:'',
      animalSize:'',
      animalDescription:''
    }
    this.alert=false;
  }
  loadAnimalImage(event){
     this.file = event.target.files;
     this.data.append('animalPhoto', this.file[0]);

  }

  createAnimal(){
    if((this.animal.animalName=='') || (this.animal.animalAge == null)  || (this.animal.animalType =='') || (this.animal.animalSize =='') || (this.animal.animalDescription =='')){
      this.alertText='All fields must be Completed';
      this.alert=true;
    }else {
      this.alert=false;
      this.data.append('jsondata',JSON.stringify(this.animal));
      this.animalFormService.createAnimal(this.data).subscribe();
    }
    
   

   
  }

}
