import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaUtentiComponent } from './visualizza-utenti.component';

describe('VisualizzaUtentiComponent', () => {
  let component: VisualizzaUtentiComponent;
  let fixture: ComponentFixture<VisualizzaUtentiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisualizzaUtentiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisualizzaUtentiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
