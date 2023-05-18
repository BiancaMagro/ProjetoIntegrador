import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pedido } from '../pedido';

@Injectable({
  providedIn: 'root'
})
export class CozinhaService {
  constructor(private http: HttpClient) { }
  private readonly url = 'http://localhost:8080/pedidos'
  private readonly options = {headers: new HttpHeaders({'Content-Type': 'application/json'})}

  listar(): Observable<Pedido[]>{
    return this.http.get<Pedido[]>(this.url+"/cozinha")
  }
  pesquisar(id: number): Observable<Pedido>{//pegar um pedido
    return this.http.get<Pedido>(this.url+"/"+id)
  }
  editar(pedido: Pedido, id: number): Observable<Pedido>{
    return this.http.put<Pedido>(this.url+"/"+id, JSON.stringify(pedido), this.options)
  }
}
