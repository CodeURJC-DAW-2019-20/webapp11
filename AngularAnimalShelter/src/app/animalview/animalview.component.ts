import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-animalview',
  templateUrl: './animalview.component.html',
  styleUrls: ['./animalview.component.css']
})
export class AnimalviewComponent implements OnInit {
  animalPhoto?:string;
	animalName:string;
	animalAge:number;
	animalType:string;
	animalSize:string;
	animalDescription:string;

  constructor(private ruta : ActivatedRoute) { }

  ngOnInit(): void {
    this.animalName= this.ruta.snapshot.params.name;
    this.animalAge= this.ruta.snapshot.params.age;
    this.animalType= this.ruta.snapshot.params.type;
    this.animalSize= this.ruta.snapshot.params.size;
     this.animalPhoto= this.ruta.snapshot.params.photo;
    this.animalDescription= this.ruta.snapshot.params.description;
    
  }

}
