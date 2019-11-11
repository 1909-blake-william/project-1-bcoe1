import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRequestsByStatusComponent } from './view-requests-by-status.component';

describe('ViewRequestsByStatusComponent', () => {
  let component: ViewRequestsByStatusComponent;
  let fixture: ComponentFixture<ViewRequestsByStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewRequestsByStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewRequestsByStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
