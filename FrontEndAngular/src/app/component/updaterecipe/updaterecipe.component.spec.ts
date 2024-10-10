import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdaterecipeComponent } from './updaterecipe.component';

describe('UpdaterecipeComponent', () => {
  let component: UpdaterecipeComponent;
  let fixture: ComponentFixture<UpdaterecipeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdaterecipeComponent]
    });
    fixture = TestBed.createComponent(UpdaterecipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
