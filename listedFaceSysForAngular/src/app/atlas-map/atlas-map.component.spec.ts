import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AtlasMapComponent } from './atlas-map.component';

describe('AtlasMapComponent', () => {
  let component: AtlasMapComponent;
  let fixture: ComponentFixture<AtlasMapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AtlasMapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AtlasMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
