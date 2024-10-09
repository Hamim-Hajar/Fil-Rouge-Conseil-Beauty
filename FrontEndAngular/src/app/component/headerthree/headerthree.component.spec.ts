import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderthreeComponent } from './headerthree.component';

describe('HeaderthreeComponent', () => {
  let component: HeaderthreeComponent;
  let fixture: ComponentFixture<HeaderthreeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HeaderthreeComponent]
    });
    fixture = TestBed.createComponent(HeaderthreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
