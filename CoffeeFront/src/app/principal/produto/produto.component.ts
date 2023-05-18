import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Produto } from './produto';
import { ProdutoService } from './produto.service';

@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.css']
})
export class ProdutoComponent {
  constructor(private rota: Router, private service: ProdutoService) { 
    this.listar();
  }
  produto: Produto = new Produto();
  produtos: Produto[] = [];
  opcao = 'cad'
  
  listar(){
    this.service.listar().subscribe((resposta: Produto[]) => {
      this.produtos = resposta
    });
  }
  add(){
    console.log(JSON.stringify(this.produto));
    this.service.add(this.produto).subscribe((resposta: Produto) => {
      this.produto = new Produto();
      this.listar();
    });
  }
  editar(id?: number){
    this.service.produtobyId(id).subscribe((resposta: Produto) => {
      this.produto = resposta;
      this.opcao = 'edit';
    });
  }
  editarProduto(){
    if(this.produto.id != undefined)
    this.service.editar(this.produto, this.produto.id).subscribe((resposta: Produto) => {
      this.produto = new Produto();
      this.listar();
      this.opcao = 'cad';
    });
  }
  deleteproduto(id?: number){
    if(confirm("Deseja realmente excluir este produto?")){
      this.service.delete(id).subscribe((resposta: Produto) => {
        this.listar();
      });
    }
  }
  voltar(){
    this.rota.navigate(['inicio/admin']);
  }
}
