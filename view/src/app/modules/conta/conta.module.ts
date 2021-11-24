import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxMaskModule } from 'ngx-mask';
import { CoreModule } from 'src/app/core/core.module';
import { ContaService } from 'src/app/core/services/conta.service';
import { SharedModule } from 'src/app/shared/shared.module';
import { ContaRoutingModule } from './conta-routing.module';
import { ContaCadastrarEditarComponent } from './pages/conta-cadastrar-editar/conta-cadastrar-editar.component';
import { ContaListarComponent } from './pages/conta-listar/conta-listar.component';
import { ContaComponent } from './pages/conta.component';
import { ContaDepositarSacarComponent } from './pages/conta-depositar-sacar/conta-depositar-sacar.component';
import { ContaOperacoesComponent } from './pages/conta-operacoes/conta-operacoes.component';
import { ContaTransferirComponent } from './pages/conta-transferir/conta-transferir.component';
import { ContaConsultarComponent } from './pages/conta-consultar/conta-consultar.component';
import { ContaExtratoComponent } from './pages/conta-extrato/conta-extrato.component';

@NgModule({
  declarations: [
    ContaComponent,
    ContaListarComponent,
    ContaCadastrarEditarComponent,
    ContaDepositarSacarComponent,
    ContaOperacoesComponent,
    ContaTransferirComponent,
    ContaConsultarComponent,
    ContaExtratoComponent,
  ],
  imports: [
    CommonModule,
    ContaRoutingModule,
    SharedModule.forRoot(),
    CoreModule,
    FormsModule,
    ReactiveFormsModule,
    NgxMaskModule.forRoot(),
  ],
  providers: [
    ContaService,
  ]
})
export class ContaModule { }
