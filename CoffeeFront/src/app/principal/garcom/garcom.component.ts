import { Component } from '@angular/core';
import { Pedido } from '../pedido';
import { Status } from '../status';
import { PrincipalService } from './garcom.service';
import { Comanda } from '../comanda/comanda';
import { Router } from '@angular/router';

@Component({
  selector: 'app-garcom',
  templateUrl: './garcom.component.html',
  styleUrls: ['./garcom.component.css']
})
export class GarcomComponent {
  constructor(private service: PrincipalService, private rota: Router){
    this.listar()
  }
  pedido: Pedido = new Pedido();
  pedidos: Pedido[] = [];
  status: Status = new Status();
  statuses: Status[] = []
  comanda: Comanda = new Comanda();
  comandas: Comanda[] = [];
  opcao = 'cad';
  id = 0;

  listar(){
    this.service.getComandas().subscribe((dados: Comanda[])=>{
      this.comandas = dados;
      for(let c of this.comandas){
        c.pedidos = []
        this.service.getPedidosComanda(c.codigo!).subscribe((dados: Pedido[])=>{
          if(dados.length > 0){
            
          }else{

          }
        })
      }
    })
    this.service.listar().subscribe((dados: Pedido[])=>{
      this.pedidos = dados;
      this.service.listFuncao().subscribe((stats: Status[])=>{
        this.statuses = stats;
      })
    })
  }
  criarComanda(){
    this.comanda.data_criada = new Date().toISOString().split('T')[0];
    console.log(this.comanda)
    this.service.criaComanda(this.comanda).subscribe(()=>{
      this.comanda = new Comanda();
      this.listar();
    })
  }
  criar(){
    this.pedido.data_criada = new Date().toISOString().split('T')[0];
    this.pedido.status = new Status(1);
    this.service.criar(this.pedido).subscribe(()=>{
      this.pedido = new Pedido();
      this.listar();
    })
  }
  update(id: number){
    this.service.pesquisar(id).subscribe((dado: Pedido)=>{
      this.pedido = dado;
      this.opcao = 'edit';
      if(dado.codigo)
      this.id = dado.codigo
    })
  }
  editar(){
    this.pedido.status = new Status(1);
    this.service.editar(this.pedido, this.id).subscribe(()=>{
      this.pedido = new Pedido();
      this.listar();
      this.opcao = 'cad';
    })
  }
  deletar(id: number){
    if(confirm("tem certeza que desja excluir este pedido?!")){
      this.service.deletar(id).subscribe(()=>{
        this.listar();
      })
    }
  }
  entregar(p: number){
    this.service.pesquisar(p).subscribe((dado: Pedido)=>{
      dado.status = new Status(3);//muda o dois para o 3, 3 é entregue
      if(dado.codigo)
      this.service.editar(dado, dado.codigo).subscribe(()=>{
        this.listar();
      })
    })
  }
  ver(comanda: Comanda){
    localStorage.setItem('comanda', JSON.stringify(comanda));
    this.rota.navigate(['inicio/comanda']);
  }
}
