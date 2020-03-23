import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimalformComponent } from './animalform.component';

describe('AnimalformComponent', () => {
  let component: AnimalformComponent;
  let fixture: ComponentFixture<AnimalformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnimalformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnimalformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
