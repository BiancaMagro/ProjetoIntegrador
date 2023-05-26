import { Funcao } from "./funcao";

export class Usuario{
    nome?: string;
    login?: string;
    cpf?: string;
    senha?: string;
    token?: string;
    funcao?: Funcao;

    constructor(nome?: string, login?: string, cpf?: string, senha?:string, token?: string, funcao?: Funcao){
        this.cpf = cpf;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.token = token;
        this.funcao = funcao;
    }
}
