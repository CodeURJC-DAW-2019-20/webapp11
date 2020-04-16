import { Component, OnInit } from '@angular/core';

import { StatisticsService } from '../../services/statistics/statistics.service';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';




@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
     
             
export class StatisticsComponent {
  actualMonth: any = new Date().getMonth()+1;
   meses = ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];
   adoptions: Array<number>=[];
  constructor (private statistics: StatisticsService){}
  ngOnInit(): void {
    let itera= this.actualMonth;
    let salida=0;
    while(salida<=6){
      if (itera>0) {
          this.statistics.adoptionsMonth(itera).subscribe( quantity => {
            this.adoptions.push(quantity);
           });
          itera--;
      } else {
        itera=11;
      }
      this.barChartLabels.push(this.meses[itera]);
      salida++;
    }
    //console.log(this.adoptions);

  }
  
  public barChartLabels:String []=[];
  public barChartOptions: ChartOptions = {
    responsive: true,  
  }
  public barCharData : ChartDataSets[] =[
    {data: this.adoptions, label: "Animals Adopted"}
  ];
  public barChartType: ChartType = 'bar';
 
}


  
  
