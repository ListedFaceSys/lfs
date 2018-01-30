import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NtbShowComponent } from './ntb-show.component';

describe('NtbShowComponent', () => {
  let component: NtbShowComponent;
  let fixture: ComponentFixture<NtbShowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NtbShowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NtbShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
