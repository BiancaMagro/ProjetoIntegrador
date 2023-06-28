import { Component } from '@angular/core';
import { Comanda } from './comanda';
import { PrincipalService } from '../garcom/garcom.service';
import { Pedido } from '../pedido';
import { Produto } from '../produto/produto';
import { Status } from '../status';
import { ProdutoService } from '../produto/produto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-comanda',
  templateUrl: './comanda.component.html',
  styleUrls: ['./comanda.component.css']
})
export class ComandaComponent {
  comanda: Comanda = JSON.parse(localStorage.getItem("comanda")!);
  constructor(private service: PrincipalService, private prodServ: ProdutoService, private rota: Router) { this.listar()}
  pedidos: Pedido[] = [];
  pedido: Pedido = new Pedido();
  opcao = 'cad';
  produtos: Produto[] = [];

  listar(){
    this.service.getPedidosComanda(this.comanda.codigo!).subscribe((dados: Pedido[])=>{
      this.pedidos = dados;
//      for(let ped of this.pedidos){
  //      console.log(ped.produto!.preco)
    //    ped.produto!.preco = ped.produto!.preco! * ped.quantidade!;
      //  console.log(ped.produto!.preco)
      //}
    })
    this.prodServ.listar().subscribe((dados: Produto[])=>{
      this.produtos = dados;
    })
  }

  criar(){
    this.pedido.comanda = this.comanda.codigo;
    this.pedido.dataPedido = new Date().toISOString().split('T')[0];
    if(this.pedido.produto?.indcozinha){
      this.pedido.status = new Status(1)
    }else{
      this.pedido.status = new Status(3)
    }
    console.log(JSON.stringify(this.pedido))
    console.log(JSON.stringify(this.pedido.produto))
    this.service.criar(this.pedido).subscribe(()=>{
      this.pedido = new Pedido();
      setTimeout(()=>{
        this.listar();
      }, 500)
    })
  }
  
  voltar(){
    this.rota.navigate(['/inicio/garcom'])
  }
  entregar(id: number){
    this.service.pesquisar(id).subscribe((dados: Pedido)=>{
      dados.status!.codigo! = 4;
      this.service.editar(dados, dados.codigo!).subscribe((dados: Pedido)=>{
        this.listar();
      })
    })
  }
  editar(id: number){
    this.opcao = 'edit';
    this.service.pesquisar(id).subscribe((dados: Pedido)=>{
      this.pedido =dados;
    })
  }
  update(){
    this.service.editar(this.pedido, this.pedido.codigo!).subscribe((dados: Pedido)=>{
      this.pedido = new Pedido();
      this.listar();
    })
  }
}
