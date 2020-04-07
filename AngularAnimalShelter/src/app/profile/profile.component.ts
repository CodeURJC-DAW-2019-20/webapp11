import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { ServiceService } from './service.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { environment } from 'src/environments/environment';



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  data: FormData;
  user: any
  gallery: any[];
  file: any[];
  userGallery: string;
  page: number = 0;
  loading: boolean;
  src=environment.apiBase2 + '/user';
  src2=environment.apiBase3;
  isbuttonvisible:boolean;


  constructor(private service: ServiceService, private router: Router) {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.data = new FormData;
    this.gallery = Array();
    this.loading = false;
    this.isbuttonvisible= false;

  }
  saverange() {
    this.page = 0;
    this.gallery = [];
  }
  increment() {

    this.page = this.page + 1;
    this.searchgallery();
  }
  loadGalleryImage(event) {

    this.file = event.target.files;
    this.data.set('userGallery', this.file[0]);

  }

  createGallery() {
    console.log(this.data.get('userGallery'))
    this.service.createGallery(this.data).subscribe();

  }

  searchgallery() {
    this.loading = true;
    this.service.getGallery(this.page)
      .subscribe(gallery => {
        if(gallery.length >= 3){
          this.isbuttonvisible=true;
        }
        if(gallery.length == 0){
          this.isbuttonvisible= false;
        }
        this.loading = false;
        for (let ani of gallery) {
          this.gallery.push(ani); 
        }

      }
      );

  }
  onShowGallery() {
    this.searchgallery();
  }




}
