import { TestBed } from '@angular/core/testing';

import { FavrecipeService } from './favrecipe.service';

describe('FavrecipeService', () => {
  let service: FavrecipeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FavrecipeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
