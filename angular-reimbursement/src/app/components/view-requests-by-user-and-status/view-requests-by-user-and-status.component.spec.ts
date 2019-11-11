import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRequestsByUserAndStatusComponent } from './view-requests-by-user-and-status.component';

describe('ViewRequestsByUserAndStatusComponent', () => {
  let component: ViewRequestsByUserAndStatusComponent;
  let fixture: ComponentFixture<ViewRequestsByUserAndStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewRequestsByUserAndStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewRequestsByUserAndStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
