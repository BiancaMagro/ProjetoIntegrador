export class Status{
    codigo?: number;
    status?: string;

    constructor(cod?: number, stat?: string) {
      this.codigo = cod;
      this.status = stat;
    }
}
