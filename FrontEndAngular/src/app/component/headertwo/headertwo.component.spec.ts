import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeadertwoComponent } from './headertwo.component';

describe('HeadertwoComponent', () => {
  let component: HeadertwoComponent;
  let fixture: ComponentFixture<HeadertwoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HeadertwoComponent]
    });
    fixture = TestBed.createComponent(HeadertwoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
