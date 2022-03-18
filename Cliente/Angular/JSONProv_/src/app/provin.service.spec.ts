import { TestBed } from '@angular/core/testing';

import { ProvinService } from './provin.service';

describe('ProvinService', () => {
  let service: ProvinService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProvinService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
