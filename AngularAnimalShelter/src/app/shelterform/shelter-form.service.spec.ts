import { TestBed } from '@angular/core/testing';

import { ShelterFormService } from './shelter-form.service';

describe('ShelterFormService', () => {
  let service: ShelterFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShelterFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
