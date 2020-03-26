import { Component, OnInit } from '@angular/core';
import { WebUser } from '../models/WebUser/webUser.model';
import { UserformService } from './userform.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-userform',
  templateUrl: './userform.component.html',
  styleUrls: ['./userform.component.css']
})
export class UserformComponent {

  data: FormData;
  user: WebUser;

  constructor(private router: Router, private userFormService: UserformService) { 
    this.data = new FormData;
    this.user = {
      userName:'',
      userDni:'',
      userAge:0,
      userAdress:'',
      userHouseSize:'',
      userGarden:'',
      userNumChildren:0,
      userNumPeopleInHouse:0,
      userEmail:'',
      userPassword:''
    }
  }

  loadImage(event){
    const file = event.target.files;
    this.data.append('userPhoto', file[0]);
  }

  createWebUser(){
    this.data.append('jsondata',JSON.stringify(this.user));
    this.data.append('password',this.user.userPassword);

    this.userFormService.createWebUser(this.data).subscribe(
      shelter => this.router.navigate(['/']),
      error => this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
        this.router.navigate(['/userform']);
      })
    );
  }

}
