import { Produto } from "./produto/produto";
import { Status } from "./status";

export class Pedido{
    codigo?: number;
    produto?: Produto;
    quantidade?: number;
    status?: Status;
    data_criada?: string;
    comanda?: number;

    constructor(codigo?: number, produto?: Produto, quantidade?: number, status?: Status, data_criada?: string, comanda?: number){
        this.codigo = codigo;
        this.produto = produto;
        this.quantidade = quantidade;
        this.status = status;
        this.data_criada = data_criada;
        this.comanda = comanda;
    }
}