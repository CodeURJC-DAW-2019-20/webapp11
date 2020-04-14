import { TestBed } from '@angular/core/testing';

import { AnimalformService } from '../../components/animalform/animalform.service';

describe('AnimalformService', () => {
  let service: AnimalformService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnimalformService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
