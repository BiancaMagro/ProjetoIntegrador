import { Component } from '@angular/core';
import { Pedido } from '../pedido';
import { Status } from '../status';
import { PrincipalService } from './garcom.service';

@Component({
  selector: 'app-garcom',
  templateUrl: './garcom.component.html',
  styleUrls: ['./garcom.component.css']
})
export class GarcomComponent {
  constructor(private service: PrincipalService){
    this.listar()
  }
  pedido: Pedido = new Pedido();
  pedidos: Pedido[] = [];
  status: Status = new Status();
  statuses: Status[] = []
  opcao = 'cad';
  id = 0;

  listar(){
    this.service.listar().subscribe((dados: Pedido[])=>{
      this.pedidos = dados;
      this.service.listFuncao().subscribe((stats: Status[])=>{
        this.statuses = stats;
      })
    })
  }
  criar(){
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
      this.service.editar(dado, dado.codigo).subscribe(()=>{
        this.listar();
      })
    })
  }
}
