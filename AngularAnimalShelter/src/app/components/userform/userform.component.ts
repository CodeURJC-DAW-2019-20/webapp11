import { Component, OnInit } from '@angular/core';
import { WebUser } from '../../models/WebUser/webUser.model';
import { UserformService } from '../../services/userform/userform.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-userform',
  templateUrl: './userform.component.html',
  styleUrls: ['./userform.component.css']
})
export class UserformComponent {

  data: FormData;
  user: WebUser;
  alert:boolean;
  correct:boolean;
  alertText:string;


  constructor(private router: Router, private userFormService: UserformService) { 
    this.data = new FormData;
    
    this.user = {
      userName:'',
      userDni:'',
      userAge:null,
      userAdress:'',
      userHouseSize:'',
      userGarden:'',
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

  createWebUser(){
    if((this.user.userName=='') || (this.user.userDni == null)  || (this.user.userAge == null) || (this.user.userAdress =='') || (this.user.userHouseSize =='')|| (this.user.userGarden =='') || (this.user.userGarden =='') || (this.user.userNumChildren == null) || (this.user.userNumPeopleInHouse ==null) || (this.user.userEmail =='') || (this.user.userPassword =='')){
      this.alertText='All fields must be Completed';
      this.correct=false;
      this.alert=true;
    }else{
      this.alert=false;
      
      this.data.append('jsondata',JSON.stringify(this.user));
      this.data.append('password',this.user.userPassword);
      this.userFormService.createWebUser(this.data).subscribe(
        user => {
          this.alertText='Your user is been created successfull.';
          this.correct=true;
          setTimeout(() => { this.router.navigate(['/']); },5000);
          console.log(this.data)
          /*this.router.navigate(['/']);*/
        },
        error => {
          this.alertText=`Your profile hasn\'t been created. \nPlease, try again. If the problem persist, contact with the Tecnical Service.`;
          this.correct=false;
          this.alert=true;
            this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/userform']);
          })
        }
      ); 
    }
  }

}
