import { Pedido } from "../pedido";

export class Comanda{
    codigo?: number;
    data_criada?: string;
    cliente?: string;
    mesa?: number;
    ativo?: boolean;
    pedidos?: Pedido[];

    constructor(codigo?: number, data_criada?: string, cliente?: string, ativo?: boolean, mesa?: number, pedidos?: Pedido[]){
        this.codigo = codigo;
        this.data_criada = data_criada;
        this.cliente = cliente;
        this.mesa = mesa;
        this.ativo = ativo;
        this.pedidos = [];
    }
}