import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pedido } from '../pedido';
import { Status } from '../status';

@Injectable({
  providedIn: 'root'
})
export class PrincipalService {
  constructor(private http: HttpClient) { }
  private readonly url = 'http://localhost:8080/pedidos'
  private readonly status = 'http://localhost:8080/status'
  private readonly options = {headers: new HttpHeaders({'Content-Type': 'application/json'})}

  listar(): Observable<Pedido[]>{
    return this.http.get<Pedido[]>(this.url)
  }
  pesquisar(id: number): Observable<Pedido>{
    return this.http.get<Pedido>(this.url+"/"+id)
  }
  criar(pedido: Pedido): Observable<Pedido>{
    return this.http.post<Pedido>(this.url, JSON.stringify(pedido), this.options)
  }
  editar(pedido: Pedido, id: number): Observable<Pedido>{
    return this.http.put<Pedido>(this.url+"/"+id, JSON.stringify(pedido), this.options)
  }
  deletar(id: number): Observable<Pedido>{
    return this.http.delete<Pedido>(this.url+"/"+id)
  }
  listFuncao(): Observable<Status[]>{
    return this.http.get<Status[]>(this.status)
  }
  pesqFuncao(id: number): Observable<Status>{
    return this.http.get<Status>(this.status+"/"+id)
  }
}
