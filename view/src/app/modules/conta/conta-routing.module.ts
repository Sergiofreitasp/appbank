import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContaCadastrarEditarComponent } from './pages/conta-cadastrar-editar/conta-cadastrar-editar.component';
import { ContaConsultarComponent } from './pages/conta-consultar/conta-consultar.component';
import { ContaDepositarSacarComponent } from './pages/conta-depositar-sacar/conta-depositar-sacar.component';
import { ContaExtratoComponent } from './pages/conta-extrato/conta-extrato.component';

import { ContaListarComponent } from './pages/conta-listar/conta-listar.component';
import { ContaOperacoesComponent } from './pages/conta-operacoes/conta-operacoes.component';
import { ContaTransferirComponent } from './pages/conta-transferir/conta-transferir.component';
import { ContaComponent } from './pages/conta.component';

const routes: Routes = [
  {
    path: '',
    component: ContaComponent,
    children: [
      {
        path: '',
        component: ContaListarComponent
      },
      {
        path: 'cadastrar',
        component: ContaCadastrarEditarComponent
      },
      {
        path: 'editar/:id',
        component: ContaCadastrarEditarComponent
      },
      {
        path: 'depositar',
        component: ContaDepositarSacarComponent
      },
      {
        path: 'sacar',
        component: ContaDepositarSacarComponent
      },
      {
        path: 'operacoes',
        component: ContaOperacoesComponent
      },
      {
        path: 'transferir',
        component: ContaTransferirComponent
      },
      {
        path: 'consultar',
        component: ContaConsultarComponent
      },
      {
        path: 'extrato',
        component: ContaExtratoComponent
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContaRoutingModule { }
