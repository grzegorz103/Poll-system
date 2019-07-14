import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PollUsersComponent } from './poll-users.component';

describe('PollUsersComponent', () => {
  let component: PollUsersComponent;
  let fixture: ComponentFixture<PollUsersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PollUsersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PollUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
