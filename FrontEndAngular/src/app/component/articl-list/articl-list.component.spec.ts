import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticlListComponent } from './articl-list.component';

describe('ArticlListComponent', () => {
  let component: ArticlListComponent;
  let fixture: ComponentFixture<ArticlListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ArticlListComponent]
    });
    fixture = TestBed.createComponent(ArticlListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
