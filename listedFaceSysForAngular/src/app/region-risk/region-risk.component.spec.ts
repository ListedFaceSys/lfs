import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegionRiskComponent } from './region-risk.component';

describe('RegionRiskComponent', () => {
  let component: RegionRiskComponent;
  let fixture: ComponentFixture<RegionRiskComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegionRiskComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegionRiskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
