import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrackMoreComponent } from './track-more.component';

describe('TrackMoreComponent', () => {
  let component: TrackMoreComponent;
  let fixture: ComponentFixture<TrackMoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrackMoreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrackMoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
