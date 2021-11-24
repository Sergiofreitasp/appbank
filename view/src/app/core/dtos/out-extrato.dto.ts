export class OutExtrato {

   
    data: Date;
    tipoTrasacao: string;
    valor: number;
    
    
    

    constructor(obj?) {
        if (obj) {
            this.data = obj.data;
            this.tipoTrasacao = obj.tipoTrasacao;
            this.valor = obj.valor;
        }
    }
}