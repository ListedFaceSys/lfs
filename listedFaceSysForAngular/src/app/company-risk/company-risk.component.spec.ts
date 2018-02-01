import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyRiskComponent } from './company-risk.component';

describe('CompanyRiskComponent', () => {
  let component: CompanyRiskComponent;
  let fixture: ComponentFixture<CompanyRiskComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompanyRiskComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompanyRiskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
