import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Produto } from "./produto";


@Injectable({
    providedIn: 'root'
  })
export class ProdutoService{
    constructor(private http: HttpClient){}
    private readonly url = 'http://localhost:8080/produtos'
    private readonly options = {headers: {'Content-Type': 'application/json'}}

    listar(): Observable<Produto[]>{
        return this.http.get<Produto[]>(this.url, this.options);
    }
    produtobyId(id?: number): Observable<Produto>{
        return this.http.get<Produto>(this.url+"/"+id, this.options);
    }
    add(produto: Produto): Observable<Produto>{
        return this.http.post<Produto>(this.url, JSON.stringify(produto), this.options);
    }
    editar(produto: Produto, id: number): Observable<Produto>{
        return this.http.put<Produto>(this.url+"/"+id, JSON.stringify(produto), this.options);
    }
    delete(id?: number): Observable<Produto>{
        return this.http.delete<Produto>(this.url+"/"+id, this.options);
    }
    allPrdutos(): Observable<Produto[]>{
        return this.http.get<Produto[]>(this.url, this.options);
    }
}