import { Component, OnInit } from '@angular/core';
import { PrenotazioneService } from 'src/app/services/prenotazione.service';
import { IPrenotazione } from 'src/app/model/prenotazione.model';

@Component({
  selector: 'app-visualizza-prenotazioni',
  templateUrl: './visualizza-prenotazioni.component.html',
  styleUrls: ['./visualizza-prenotazioni.component.css'],
})
export class VisualizzaPrenotazioniComponent implements OnInit {
  listaPrenotazioni: IPrenotazione[];
  days = [
    'DOMENICA',
    'LUNEDI',
    'MARTEDI',
    'MERCOLEDI',
    'GIOVEDI',
    'VENERDI',
    'SABATO',
  ];

  constructor(private prenotazioneService: PrenotazioneService) {
    this.listaPrenotazioni = new Array();

    this.prenotazioneService.visualizzaPrenotazioniUtente().subscribe((res) => {
      console.log('risposta prenotazione ', res);
      this.listaPrenotazioni = res['body'];
      console.log('listaPrenotazioni ', this.listaPrenotazioni);

    });
  }

  ngOnInit(): void {}

  public getNomeGiorno(data: Date) {
    let v = new Date(data);
    return this.days[v.getDay()];
  }

  public btnDeleteIsDisabled(data: Date) {
    console.log('data ricevuta ', data);
    data = new Date(data);
    let now = new Date();
    data.setDate(data.getDate() - 1);
    data.setHours(data.getHours() + 13);

    return now > data;
  }

  deletePrenotazione(id: number) {
    console.log('id eliminazione ',id);
    let risposta = confirm('Sei sicuro di volere eliminare la prenotazione?');
    if (risposta) {
      this.prenotazioneService.cancellaPrenotazione(id).subscribe((res) => {
        console.log('Risposta deletePrenotazione ', res);
        let obj = this.listaPrenotazioni.find(
          (o) => o.tokenPrenotazione === id
        );
        const idx = this.listaPrenotazioni.indexOf(obj);
        this.listaPrenotazioni.splice(idx, 1);
      });
    }
  }
}
