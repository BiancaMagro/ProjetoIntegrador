export class Produto{
    id?: number;
    nome?: string;
    descricao?: string;
    preco?: number;
    ativo?: boolean;
    constructor(id?: number, nome?: string, descricao?: string, preco?: number){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.ativo = true;
    }
}