import { Funcao } from "./funcao";

export class Usuario{
    login?: string;
    senha?: string;
    token?: string;
    funcao?: Funcao;

    constructor(login?: string, senha?:string, token?: string, funcao?: Funcao){
        this.login = login;
        this.senha = senha;
        this.token = token;
        this.funcao = funcao;
    }
}
