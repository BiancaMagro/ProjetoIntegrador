import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, RouterOutlet} from "@angular/router";
import { ProdutoComponent } from './produto.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    ProdutoComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    RouterOutlet,
  ]
})
export class ProdutoModule { }