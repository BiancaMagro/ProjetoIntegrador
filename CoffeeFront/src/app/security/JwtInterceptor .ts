 import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    constructor() { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = localStorage.getItem("jwt")
        if (token) {
            request = request.clone({
                setHeaders: { Authorization: `Bearer ${token}` }//pega a requisição e add o token no cabeçalho
            });
        }
        return next.handle(request);
    }
}
