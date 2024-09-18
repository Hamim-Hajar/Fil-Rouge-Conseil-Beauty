import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardspecialistComponent } from './dashboardspecialist.component';

describe('DashboardspecialistComponent', () => {
  let component: DashboardspecialistComponent;
  let fixture: ComponentFixture<DashboardspecialistComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DashboardspecialistComponent]
    });
    fixture = TestBed.createComponent(DashboardspecialistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
