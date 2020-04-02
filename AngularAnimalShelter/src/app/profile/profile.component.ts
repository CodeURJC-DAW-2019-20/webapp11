import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user:any


  constructor() {
    this.user= JSON.parse(localStorage.getItem('currentUser'));

   }

  ngOnInit(): void {
  }

}
