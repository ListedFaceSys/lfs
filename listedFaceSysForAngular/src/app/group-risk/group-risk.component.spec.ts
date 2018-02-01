import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupRiskComponent } from './group-risk.component';

describe('GroupRiskComponent', () => {
  let component: GroupRiskComponent;
  let fixture: ComponentFixture<GroupRiskComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupRiskComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupRiskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
