import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShelterformComponent } from './shelterform.component';

describe('ShelterformComponent', () => {
  let component: ShelterformComponent;
  let fixture: ComponentFixture<ShelterformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShelterformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShelterformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
