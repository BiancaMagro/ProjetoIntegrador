import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './principal/admin/admin.component';
import { CozinhaComponent } from './principal/cozinha/cozinha.component';
import { GarcomComponent } from './principal/garcom/garcom.component';
import { PrincipalComponent } from './principal/principal.component';
import { ProdutoComponent } from './principal/produto/produto.component';
import {AuthGuardService} from "./security/auth.guard";

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: "full"},
  {path: 'login', component: LoginComponent},
  {path: 'inicio', component: PrincipalComponent, canActivate: [AuthGuardService], children: [
    {path: 'garcom', component: GarcomComponent, canActivate: [AuthGuardService]},
    {path: 'cozinha', component: CozinhaComponent, canActivate: [AuthGuardService]},
    {path: 'admin', component: AdminComponent, canActivate: [AuthGuardService]},
    {path: 'produto', component: ProdutoComponent, canActivate: [AuthGuardService]},
  ]},
];




@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
