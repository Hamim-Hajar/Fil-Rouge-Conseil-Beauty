import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisiteurDashboardComponent } from './visiteur-dashboard.component';

describe('VisiteurDashboardComponent', () => {
  let component: VisiteurDashboardComponent;
  let fixture: ComponentFixture<VisiteurDashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VisiteurDashboardComponent]
    });
    fixture = TestBed.createComponent(VisiteurDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
