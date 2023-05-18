import { Status } from "./status";

export class Pedido{
    codigo!: number;
    descricao!: string;
    nome!: string;
    mesa!: number;
    status!: Status;
}