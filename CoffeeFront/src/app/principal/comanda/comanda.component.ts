import { Component } from '@angular/core';
import { Comanda } from './comanda';

@Component({
  selector: 'app-comanda',
  templateUrl: './comanda.component.html',
  styleUrls: ['./comanda.component.css']
})
export class ComandaComponent {
  comanda: Comanda = JSON.parse(localStorage.getItem("comanda")!);
  constructor() { }
  
}
