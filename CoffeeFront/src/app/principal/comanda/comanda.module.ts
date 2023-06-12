import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComandaComponent } from './comanda.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    ComandaComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule
  ]
})
export class ComandaModule { }
