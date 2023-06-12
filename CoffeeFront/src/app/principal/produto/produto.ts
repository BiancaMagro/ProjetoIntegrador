export class Produto{
    id?: number;
    nome?: string;
    preco?: number;
    indcozinha?: boolean;
    ativo?: boolean;
    
    constructor(id?: number, nome?: string, preco?: number, indCozina?: boolean){
        this.id = id;
        this.nome = nome;
        this.indcozinha = indCozina;
        this.preco = preco;
        this.ativo = true;
    }
}