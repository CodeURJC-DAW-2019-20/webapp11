import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdoptionService } from '../../services/adoptions/adoption.service';
import { Adoption } from 'src/app/models/Adoption/adoption.model';
import { Animal } from 'src/app/models/Animal/animal.model';
import { environment } from 'src/environments/environment';
 

@Component({
  selector: 'app-animalview',
  templateUrl: './animalview.component.html',
  styleUrls: ['./animalview.component.css']
})
export class AnimalviewComponent implements OnInit {
  data = new FormData();
  animal: Animal;
  adoption: Adoption ;
  today = new Date();
  user:any;
  src=environment.apiBase2+ "/animal";

  constructor(private router: Router, private ruta : ActivatedRoute, private adoptionService: AdoptionService) {
    this.user=JSON.parse(localStorage.getItem('currentUser'));
    this.data = new FormData();
    this.adoption={
      inCourse:false,
      adoptionDate:this.today,

    }
    
      this.animal = {
        animalName:'',
        animalAge:null,
        animalType:'dog',
        animalSize:'no',
        animalDescription:''
      }
    
   }

  ngOnInit(): void {
    
    this.animal.animalName = this.ruta.snapshot.params.name;
    this.animal.animalAge = this.ruta.snapshot.params.age;
    this.animal.animalType = this.ruta.snapshot.params.type;
    this.animal.animalSize = this.ruta.snapshot.params.size;
    this.animal.animalPhoto = this.ruta.snapshot.params.photo;
    this.animal.animalDescription = this.ruta.snapshot.params.description;
    
  }

  createAdoption() {
    this.adoption.adoptionDate;  // Fix
    this.adoption.animal = this.animal;
    this.adoption.inCourse = true;
    this.adoption.user = this.user;  // Fix
    console.log("hiadoption",this.adoption);


    this.adoptionService.createRequest(this.adoption).subscribe(
      response => {
        this.router.navigate(['animals']);
      },
      error => {
          this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
          this.router.navigate(['animals']);
        });
      });
  }

}
