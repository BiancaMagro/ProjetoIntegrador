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
      this.pedido = dado;
      this.id = dado.codigo!;
      this.tela = true;
    })
  }
  terminar(){
    this.pedido.status = new Status(2)
    this.service.editar(this.pedido, this.id).subscribe(()=>{
      this.listar();
      this.tela = false;
    })
  }
}
