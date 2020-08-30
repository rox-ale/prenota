import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaMenuUtentiComponent } from './visualizza-menu-utenti.component';

describe('VisualizzaMenuUtentiComponent', () => {
  let component: VisualizzaMenuUtentiComponent;
  let fixture: ComponentFixture<VisualizzaMenuUtentiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisualizzaMenuUtentiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisualizzaMenuUtentiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
