import { Component, OnInit } from '@angular/core';

import { StadisticsService } from './stadistics.service';


@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent {
  actualMonth: any = new Date().getMonth();
  mesesLetra: Array<String> = ["Enero","Febrero","Marzo","Abril","Mayo", "Junio","Julio","Agosto", "Septiembre","Octubre","Noviembre","Diciembre"];
  meses :Array<number> =[1,2,3,4,5,6,7,8,9,10,11,12];
  adoptions: any = [];
  constructor (private statistics: StadisticsService){}
  ngOnInit(){
    
    this.meses.forEach(element => {
      this.statistics.adoptionsMonth(element)
                .subscribe(res=>{

                  res.toString();
                  
                  this.adoptions.push(res);
                });
                
    });
    let i =0;
    let itera= this.actualMonth;
    let visibles =[];
    while(i < 6){
      if(itera<0){
        itera=6;
        visibles.push(this.adoptions[itera]);
      }else{
        visibles.push(this.adoptions[itera]);
      }
        i++;

    }
    

    }
             
}


  
  
