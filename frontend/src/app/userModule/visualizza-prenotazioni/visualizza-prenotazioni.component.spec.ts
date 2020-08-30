import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaPrenotazioniComponent } from './visualizza-prenotazioni.component';

describe('VisualizzaPrenotazioniComponent', () => {
  let component: VisualizzaPrenotazioniComponent;
  let fixture: ComponentFixture<VisualizzaPrenotazioniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisualizzaPrenotazioniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisualizzaPrenotazioniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
