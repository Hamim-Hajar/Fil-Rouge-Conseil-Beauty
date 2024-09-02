import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddArticlComponent } from './add-articl.component';

describe('AddArticlComponent', () => {
  let component: AddArticlComponent;
  let fixture: ComponentFixture<AddArticlComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddArticlComponent]
    });
    fixture = TestBed.createComponent(AddArticlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
