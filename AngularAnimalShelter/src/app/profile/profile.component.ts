import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { ServiceService } from './service.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  data: FormData;
  user: any
  animal: any[];
  file: any[];
  userGallery: string;

  constructor(private service: ServiceService, private router: Router) {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.data = new FormData;


  }
  loadGalleryImage(event) {

    this.file = event.target.files;
    this.data.set('userGallery', this.file[0]);

  }

  createGallery() {
    console.log("hola");
    console.log(this.data.get('userGallery'))
    this.service.createGallery(this.data).subscribe();

  }

  searchgallery() {

    this.service.getGallery()
      .subscribe(
        animal => {
          this.animal = animal;

        }
      );

  }




}
