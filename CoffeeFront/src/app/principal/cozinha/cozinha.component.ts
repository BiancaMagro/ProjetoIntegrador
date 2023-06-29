import { Component } from '@angular/core';
import { Pedido } from '../pedido';
import { CozinhaService } from './cozinha.service';
import {Status} from "../status";

@Component({
  selector: 'app-cozinha',
  templateUrl: './cozinha.component.html',
  styleUrls: ['./cozinha.component.css']
})
export class CozinhaComponent {
  constructor(private service: CozinhaService){
    this.listar()
  }
  teste = ""
  pedidos: Pedido[] = []
  pedido: Pedido = new Pedido()
  id = 0;
  tela = false;

  listar(){
    this.service.listar().subscribe((dados: Pedido[])=>{
      this.pedidos = dados;
    })
  }
  continuar(id: number){
    this.service.pesquisar(id).subscribe((dado: Pedido)=>{
      dado.status = new Status(2)
      this.service.editar(dado, id).subscribe((dado: Pedido)=>{
        this.listar()
      })
    })
  }
  terminar(id: number){
    this.service.pesquisar(id).subscribe((dado: Pedido)=>{
      dado.status = new Status(3)
      this.service.editar(dado, id).subscribe((dado: Pedido)=>{
        this.listar()
      })
    })
  }
}
