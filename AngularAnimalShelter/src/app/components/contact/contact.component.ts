import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import {ContactData} from '../../models/ContactData/contacData.model'
import { ContactService } from '../../services/contact/contact.service';




@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent  {

  data :FormData = new FormData();
  emailData: ContactData;
  alert: boolean;
  alertText :string;

  constructor(private router : Router,private contactService: ContactService) { 
    this.data= new FormData();
    this.emailData= {
      name: '',
      email:'',
      subject:'',
      message:''

    }
    this.alert=false;
    
  }
  sendEmail(formulary :NgForm){

    if(formulary.invalid){
      this.alertText= 'All fileds must be completed';
      this.alert= true;
      
      Object.values(formulary.controls).forEach( control => {
        control.markAsTouched();
      })

    } else {
      this.alert= false;
      this.data.append('name',this.emailData.name);
      this.data.append('email',this.emailData.email);
      this.data.append('subject',this.emailData.subject);
      this.data.append('message',this.emailData.message)
      
      

      this.contactService.sendEmail(this.data).subscribe(
        response => {
          this.router.navigate(['home']);
        },
        error=>{
          this.alertText= 'Email hasn\' t been sent. \Please, try again';
          this.alert= true;
          this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
            this.router.navigate(['contact']);
          });
        }

      );
    }
  }
  
}
