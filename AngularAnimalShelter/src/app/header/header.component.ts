import { Component, OnInit } from '@angular/core';
import { LoginService } from '../auth/login.service';
import { WebUser } from '../models/WebUser/webUser.model';
import { Shelter } from '../models/Shelter/shelter.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  
  constructor(public loginService:LoginService, private router: Router) { }
  public isMenuCollapsed = true;

  ngOnInit(): void {
  }
  
  logIn(email: string, pass: string) {

    if(email.length == 0 || pass.length == 0){
      alert('Invalid user or password');
      return
    }
    
    this.loginService.logIn(email, pass).subscribe(
      user => {
        console.log(user);
        this.router.navigate(['/']);
      },
      error => alert('Invalid user or password')
    );

  }

  logOut() {
    this.loginService.logOut();
    this.router.navigate(['/']);
}

}
