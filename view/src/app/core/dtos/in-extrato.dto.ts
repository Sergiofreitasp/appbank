export class InExtrato {

   
    agencia: string;
    numeroConta: string;
    dataIncial: Date;
    dataFinal: Date;
    
    

    constructor(obj?) {
        if (obj) {
            this.agencia = obj.agencia;
            this.numeroConta = obj.numeroConta;
            this.dataIncial = obj.dataIncial;
            this.dataFinal = obj.dataFinal;
        }
    }
}