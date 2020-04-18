import { Component, OnInit } from '@angular/core';
import { AnimalsService } from '../../services/animals/animals.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-animals',
  templateUrl: './animals.component.html',
  styleUrls: ['./animals.component.css']
})
export class AnimalsComponent implements OnInit {

  animalType: string;
  animalName: string;
  animal: any[];
  page: number = 0;
  loading: boolean;
  src=environment.apiBase2+ "/animal"
  animals: any = [];
  isbuttonvisible:boolean;
  user: any
  suitedbuttonVisible:boolean;

  constructor(private dataService: AnimalsService) {
    this.animal = Array();
    this.loading = false;
    this.isbuttonvisible= false;
    this.suitedbuttonVisible= false;

  }
  
  ngOnInit(): void {
    this.animalType = "all";
    this.animalName = null;
    if(JSON.parse(localStorage.getItem('currentUser'))!=null){
      this.suitedbuttonVisible=true;
    }
  }

  saverange() {
    this.page = 0;
    this.animal = [];
  }
  
  increment() {
    this.page = this.page + 1;
    console.log(this.page);
    this.searchByType();
  }

  searchByType() {
    this.loading = true;
    this.dataService.getAnimalsByType(this.animalType, this.page)
      .subscribe(animal => {
        if(animal.length >= 3){
          this.isbuttonvisible=true;
        }
        if(animal.length == 0){
          this.isbuttonvisible= false;
        }
        this.loading = false;
        for (let ani of animal) {
          this.animal.push(ani);
        }

      }
      );
  }

  searchByName() {
    this.dataService.getAnimalsByName(this.animalName)
      .subscribe(
        animal => {
        this.animal = animal; this.animal = Array.of(this.animal);
        });
  }
  

   searchSuitedAnimal() {
    this.loading = true;
    this.dataService.getSuitedAnimal()
      .subscribe(animal => {
        if(animal.length >= 3){
          this.isbuttonvisible=true;
        }
        if(animal.length == 0){
          this.isbuttonvisible= false;
        }
        this.loading=false;
        this.animal = animal;
       
      }
      );
  }
 


}
