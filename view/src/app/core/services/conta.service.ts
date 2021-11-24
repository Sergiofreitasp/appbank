import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ClienteDTO } from '../dtos/cliente.dto';
import { ContaDTO } from '../dtos/conta.dto';
import { InExtrato } from '../dtos/in-extrato.dto';
import { InSaqueDepositoDTO} from '../dtos/in-saque-deposito.dto';
import { OutExtrato } from '../dtos/out-extrato.dto';
import { TransferenciaDTO} from '../dtos/transferencia.dto';
import { ApiService } from './api.service';

@Injectable({
    providedIn: 'root'
})
export class ContaService {

    private controller = 'contas';

    constructor(private apiService: ApiService) {}

    getAll(): Observable<ContaDTO[]> {
        return this.apiService.get(`${this.controller}/`).pipe(map(response => response.body.map(item => new ContaDTO(item))));
    }

    getById(id: number): Observable<ContaDTO> {
        return this.apiService.get(`${this.controller}/${id}/`).pipe(map(response => new ContaDTO(response.body)));
    }

    create(obj: ContaDTO): Observable<any> {
        return this.apiService.post(`${this.controller}/`, obj);
    }

    update(obj: ContaDTO): Observable<any> {
        return this.apiService.put(`${this.controller}/${obj.id}/`, obj);
    }

    delete(id: number): Observable<any> {
        return this.apiService.delete(`${this.controller}/${id}/`);
    }

    getSaldo(agencia: String, numeroConta: String): Observable<any> {
        return this.apiService.get(`${this.controller}/consultar-saldo/${agencia}/${numeroConta}/`).pipe(map(response => new String(response.body))); // perguntar
    }

    depositar(obj: InSaqueDepositoDTO): Observable<any> {
        return this.apiService.post(`${this.controller}/deposito/`, obj);
    }

    sacar(obj: InSaqueDepositoDTO): Observable<any> {
        return this.apiService.post(`${this.controller}/saque/`, obj);
    }

    transferencia(obj: TransferenciaDTO): Observable<any> {
        return this.apiService.post(`${this.controller}/transferencia/`, obj);
    }

    consultar(cpf: string): Observable<any> {
        return this.apiService.get(`${this.controller}/consultar-contas-cliente/${cpf}/`).pipe(map(response => response.body.map(item => new ContaDTO(item))));
    }

    extrato(obj: InExtrato): Observable<any> {
        return this.apiService.post(`${this.controller}/consultarExtrato/`, obj).pipe(map(response => response.body.map(item => new OutExtrato(item))));
    }
}
