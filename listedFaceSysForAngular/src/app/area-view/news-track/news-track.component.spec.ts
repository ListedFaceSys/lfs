import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsTrackComponent } from './news-track.component';

describe('NewsTrackComponent', () => {
  let component: NewsTrackComponent;
  let fixture: ComponentFixture<NewsTrackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsTrackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsTrackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
