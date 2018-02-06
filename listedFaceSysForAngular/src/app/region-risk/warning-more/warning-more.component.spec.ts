import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarningMoreComponent } from './warning-more.component';

describe('WarningMoreComponent', () => {
  let component: WarningMoreComponent;
  let fixture: ComponentFixture<WarningMoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarningMoreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarningMoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
