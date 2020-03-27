import { Component, OnInit } from '@angular/core';
import { Animal } from '../models/Animal/animal.model';
import { AnimalformService} from './animalform.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-animalform',
  templateUrl: './animalform.component.html',
  styleUrls: ['./animalform.component.css']
})
export class AnimalformComponent  {
  data: FormData;
  animal: Animal;

  constructor(private router: Router, private animalFormService: AnimalformService) { 
    this.data = new FormData;
    this.animal={
      animalName:'',
      animalAge:0,
      animalType:'',
      animalSize:'',
      animalDescription:''
    }
  }
  loadAnimalImage(event){
    const file = event.target.files;
    this.data.append('animalPhoto', file[0]);
  }

  createAnimal(){
    this.data.append('jsondata',JSON.stringify(this.animal));
    this.animalFormService.createAnimal(this.data).subscribe(
      error => this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
        this.router.navigate(['/animalform']);
      })
    );
  }

}
