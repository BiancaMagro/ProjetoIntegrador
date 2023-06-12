import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrincipalComponent } from './principal.component';
import {RouterModule, RouterOutlet} from "@angular/router";
import {GarcomModule} from "./garcom/garcom.module";
import { ProdutoModule } from './produto/produto.module';
import { ComandaModule } from './comanda/comanda.module';

@NgModule({
  declarations: [
    PrincipalComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    RouterOutlet,
    GarcomModule,
    ProdutoModule,
    ComandaModule
  ]
})
export class PrincipalModule { }
