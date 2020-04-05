import { Component, OnInit } from '@angular/core';
import { Animal } from '../models/Animal/animal.model';
import { ServiceService } from './service.service';
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

  constructor(private dataService: ServiceService) {

    this.animal = Array();
    this.loading = false;

  }
  saverange() {
    this.page = 0;
    this.animal = [];
  }
  increment() {

    this.page = this.page + 1;
    console.log(this.page);
    this.searchCustomers();
  }

  ngOnInit(): void {
    this.animalType = null;
    this.animalName = null;


  }
  private searchCustomers() {
    this.loading = true;
    this.dataService.getAnimalsByType(this.animalType, this.page)
      .subscribe(animal => {
        this.loading = false;
        for (let ani of animal) {
          this.animal.push(ani);
        }

      }
      );
  }

  onSubmit() {
    this.searchCustomers();
  }
  private searchByName() {
    this.dataService.getAnimalsByName(this.animalName)
      .subscribe(
        animal => {
        this.animal = animal; this.animal = Array.of(this.animal);
        });
  }


  onSubmitName() {
    this.searchByName();
  }
}
