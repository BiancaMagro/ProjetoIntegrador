import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GarcomComponent } from './garcom.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ComandaComponent } from '../comanda/comanda.component';



@NgModule({
  declarations: [
    GarcomComponent,
    ComandaComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule
  ]
})
export class GarcomModule { }
