import { Component, OnInit } from '@angular/core';
import { Animal } from '../models/Animal/animal.model';
import { ServiceService} from './service.service';



@Component({
  selector: 'app-animals',
  templateUrl: './animals.component.html',
  styleUrls: ['./animals.component.css']
})
export class AnimalsComponent implements OnInit {

  animalType: string;
  animalName: string;
    animal: any[];

  animals:any=[];

  constructor(private dataService: ServiceService) { 

    
  }

  ngOnInit(): void {
   this.animalType=null;
   this.animalName=null;
 
  }
  private searchCustomers() {
    this.dataService.getAnimalsByType(this.animalType)
      .subscribe(animal => this.animal = animal);
  }
 
  onSubmit() {
    this.searchCustomers();
  }
  private searchByName() {
    this.dataService.getAnimalsByName(this.animalName)
      .subscribe(
          animal =>{ this.animal = animal; this.animal = Array.of(this.animal);
          });
  }
  
 
  onSubmitName() {
    this.searchByName();
  }
}
