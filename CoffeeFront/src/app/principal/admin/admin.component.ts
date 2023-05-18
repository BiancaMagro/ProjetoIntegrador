import { Component } from '@angular/core';
import { Funcao } from 'src/app/login/funcao';
import { Usuario } from 'src/app/login/Usuario';
import { AdminService } from './admin.service';
import {Pedido} from "../pedido";
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  constructor(private service: AdminService, private rota: Router){
    this.listar();
  }
  usuario: Usuario = new Usuario();
  usuarios: Usuario[] = [];
  funcoes: Funcao[] = [];
  login? = '';
  opcao = 'cad'
  tela = true;
  teste = ";"
  pedidos: Pedido[] = []
  senha = ''
//parte lógica poem a coisas na página , define conteudo
  listar(){
    this.service.listar().subscribe((dados: Usuario[])=>{
      this.usuarios = dados;
      this.service.listFuncao().subscribe((funcs: Funcao[])=>{
        this.funcoes = funcs;
      })
    })
    this.service.listLog().subscribe((peds: Pedido[])=>{
      this.pedidos = peds;
    })
  }
  criar(){
    if(this.senha != this.usuario.senha){
      alert("Senhas diferentes!")
    }else {
      this.service.criar(this.usuario).subscribe(() => {
        this.usuario = new Usuario();
        this.listar();
      })
    }
  }
  editar(login?: string){
    this.service.pesquisar(login).subscribe((dado: Usuario)=>{
      this.usuario = dado;
      this.login = dado.login;
      this.opcao = 'edit'
    })
  }
  update(){
    this.service.editar(this.usuario, this.login).subscribe(()=>{
      this.usuario = new Usuario();
      this.login = '';
      this.listar();
      this.opcao = 'cad'
    })
  }
  deletar(login?: string){
    if(confirm("tem certeza?")){
      this.service.deletar(login).subscribe(()=>{
        this.listar();
      })
    }
  }
  troca(){
    if(this.tela) {
      this.tela = false;
    }else{
      this.tela = true;
    }
  }
  produtos(){
    this.rota.navigate(['inicio/produto'])
  }
}
