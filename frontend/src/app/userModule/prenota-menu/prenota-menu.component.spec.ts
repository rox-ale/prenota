import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrenotaMenuComponent } from './prenota-menu.component';

describe('PrenotaMenuComponent', () => {
  let component: PrenotaMenuComponent;
  let fixture: ComponentFixture<PrenotaMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrenotaMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrenotaMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
