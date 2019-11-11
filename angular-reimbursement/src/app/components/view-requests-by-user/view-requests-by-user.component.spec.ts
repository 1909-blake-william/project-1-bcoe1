import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRequestsByUserComponent } from './view-requests-by-user.component';

describe('ViewRequestsByUserComponent', () => {
  let component: ViewRequestsByUserComponent;
  let fixture: ComponentFixture<ViewRequestsByUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewRequestsByUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewRequestsByUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
