import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimalviewComponent } from './animalview.component';

describe('AnimalviewComponent', () => {
  let component: AnimalviewComponent;
  let fixture: ComponentFixture<AnimalviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnimalviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnimalviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
