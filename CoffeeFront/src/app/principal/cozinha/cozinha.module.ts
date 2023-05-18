import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CozinhaComponent } from './cozinha.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    CozinhaComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule
  ]
})
export class CozinhaModule { }
