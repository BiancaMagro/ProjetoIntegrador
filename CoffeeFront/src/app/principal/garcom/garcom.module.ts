import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GarcomComponent } from './garcom.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    GarcomComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule
  ]
})
export class GarcomModule { }
