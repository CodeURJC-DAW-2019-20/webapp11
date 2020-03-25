import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ShelterFormService } from './shelter-form.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-shelterform',
  templateUrl: './shelterform.component.html',
  styleUrls: ['./shelterform.component.css']
})
export class ShelterformComponent implements OnInit {

  private password: string = "";
  private shelterData: any = {
    shelterName: "",
    shelterNif: "",
    shelterEmail: "",
    shelterDescription: "",
    shelterAdress:"",
  }

  constructor(private router: Router, private shelterFormService: ShelterFormService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.shelterData;
    this.password;
  }

  createShelter(){
    const formData = new FormData();
    formData.append('jsondata',this.shelterData);
    formData.append('password',this.password);

    this.shelterFormService.createShelter(formData).subscribe(
      shelter => this.router.navigate(['/']),
      error => this.router.navigate(['/shelterform'])
    );

  }

}
