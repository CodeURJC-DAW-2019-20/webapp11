import { Component } from '@angular/core';
import { WebUser } from '../../models/WebUser/webUser.model';
import { UserformService } from '../../services/userform/userform.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-userform',
  templateUrl: './userform.component.html',
  styleUrls: ['./userform.component.css']
})
export class UserformComponent {

  data: FormData = new FormData();
  user: WebUser;
  alert:boolean;
  alertText:string;

  constructor(private router: Router, private userFormService: UserformService) { 
    this.data = new FormData();
    
    this.user = {
      userName:'',
      userDni:'',
      userAge:null,
      userAdress:'',
      userHouseSize:'xs',
      userGarden:'no',
      userNumChildren:null,
      userNumPeopleInHouse:null,
      userEmail:'',
      userPassword:'',
      role: 'USER'
    }
    this.alert=false;
  }


  loadImage(event){
    const file = event.target.files;
    this.data.append('userPhoto', file[0]);
  }

  createWebUser( formulary: NgForm ) {
    
    if ( formulary.invalid ) {
      this.alertText='All fields must be Completed';
      this.alert=true;

      Object.values(formulary.controls).forEach( control => {
        control.markAsTouched();
      })
    }
    
    else {
      this.alert=false;
      
      this.data.append('jsondata',JSON.stringify(this.user));
      this.data.append('password',this.user.userPassword);

      this.userFormService.createWebUser(this.data).subscribe(
        response => {
          this.router.navigate(['home']);
        },
        error => {
          this.alertText=`Your profile hasn\'t been created. \nPlease, try again. If the problem persist, contact with the Tecnical Service.`;
          this.alert=true;
          this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
            this.router.navigate(['userform']);
          });
        }
      );
    }
  }

}
