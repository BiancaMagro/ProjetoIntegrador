import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Funcao } from 'src/app/login/funcao';
import { Usuario } from 'src/app/login/Usuario';
import {Pedido} from "../pedido";


@Injectable({
  providedIn: 'root'
})
export class AdminService {
  constructor(private http: HttpClient) { }
  private readonly url = 'http://localhost:8080/usuarios'
  private readonly funcao = 'http://localhost:8080/funcao'
  private readonly options = {headers: new HttpHeaders({'Content-Type': 'application/json'})}

  listar(): Observable<Usuario[]>{
    return this.http.get<Usuario[]>(this.url)
  }
  pesquisar(login?: string): Observable<Usuario>{
    return this.http.get<Usuario>(this.url+"/"+login)
  }
  criar(usuario: Usuario): Observable<Usuario>{
    return this.http.post<Usuario>(this.url, JSON.stringify(usuario), this.options)
  }
  editar(usuario: Usuario, login?: string): Observable<Usuario>{
    return this.http.put<Usuario>(this.url+"/"+login, JSON.stringify(usuario), this.options)
  }
  deletar(login?: string): Observable<Usuario>{
    return this.http.delete<Usuario>(this.url+"/"+login)
  }
  listFuncao(): Observable<Funcao[]>{
    return this.http.get<Funcao[]>(this.funcao)
  }
  listLog(): Observable<Pedido[]>{
    return this.http.get<Pedido[]>(this.url+"/prontos");
  }
}
