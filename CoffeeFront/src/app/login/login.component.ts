import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { Usuario } from './Usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private http: HttpClient, private rota: Router){}
  usuario: Usuario = new Usuario();

  login(){
    this.http.post<Usuario>("http://localhost:8080/login", JSON.stringify(this.usuario), {headers: new HttpHeaders({ "Content-Type": "application/json"})
    }).subscribe((dado: Usuario)=>{
      const token = dado.token;
      this.usuario = dado;
      if(token){
        console.log(token)
        console.log(dado.funcao?.funcao)
        localStorage.setItem("jwt", token);
        console.log(token)
        if(dado.funcao?.funcao)
        localStorage.setItem("funcao", dado.funcao.funcao);
        this.rota.navigate(["/inicio"])
      }
    })
  }
}
