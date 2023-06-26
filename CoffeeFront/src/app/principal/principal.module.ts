import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrincipalComponent } from './principal.component';
import {RouterModule, RouterOutlet} from "@angular/router";
import {GarcomModule} from "./garcom/garcom.module";
import { ProdutoModule } from './produto/produto.module';

@NgModule({
  declarations: [
    PrincipalComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    RouterOutlet,
    GarcomModule,
    ProdutoModule
  ]
})
export class PrincipalModule { }
