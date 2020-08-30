import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AggiungiGiornoComponent } from './aggiungi-giorno.component';

describe('AggiungiGiornoComponent', () => {
  let component: AggiungiGiornoComponent;
  let fixture: ComponentFixture<AggiungiGiornoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AggiungiGiornoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AggiungiGiornoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
