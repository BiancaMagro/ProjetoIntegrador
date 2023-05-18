import {ErrorHandler, Injectable} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService extends ErrorHandler{

  constructor() {
    super();
  }

  override handleError(error: HttpErrorResponse | any) {
    if(error instanceof HttpErrorResponse){
      switch (error.status){
        case 400:
          alert('Usuário ou Senha incorretos')
          break;
        case 500:
          alert('Erro no backend')
          break;
      }
    }
  }
}
