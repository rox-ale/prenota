import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaPrenotazioniAdminComponent } from './visualizza-prenotazioni-admin.component';

describe('VisualizzaPrenotazioniAdminComponent', () => {
  let component: VisualizzaPrenotazioniAdminComponent;
  let fixture: ComponentFixture<VisualizzaPrenotazioniAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisualizzaPrenotazioniAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisualizzaPrenotazioniAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
