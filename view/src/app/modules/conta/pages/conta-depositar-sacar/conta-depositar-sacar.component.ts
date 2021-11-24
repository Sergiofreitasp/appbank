import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { InSaqueDepositoDTO } from 'src/app/core/dtos/in-saque-deposito.dto';
import { ContaService } from 'src/app/core/services/conta.service';
import { SweetalertCustom } from 'src/app/shared/utils/sweetalert-custom';
import { ValidatorsCustom } from 'src/app/shared/utils/validators-custom';

@Component({
  selector: 'app-conta-depositar-sacar',
  templateUrl: './conta-depositar-sacar.component.html',
  styleUrls: ['./conta-depositar-sacar.component.scss']
})
export class ContaDepositarSacarComponent extends FormBase implements OnInit {

  nameScreen = "";

  constructor(
    public router: Router,
    private formBuilder: FormBuilder,
    private contaService: ContaService
  ) { super(); }

  ngOnInit(): void {
    this.getScreen();
    this.createFormGoup();
    this. validateMensageError();
  }

  getScreen() {
    if (this.router.url.includes('depositar')) {
      this.nameScreen = 'Depositar';
    } else if (this.router.url.includes('sacar')){
      this.nameScreen = 'Saque';
    }
  }

  createFormGoup() {
    this.form = this.formBuilder.group({
      agencia             : new FormControl('', [Validators.required]),
      numeroConta              : new FormControl('', [Validators.required]),
      valor               : new FormControl(0, [Validators.required, ValidatorsCustom.lessThanOne]),
    });
  }

  validateMensageError() {
    this.createValidateFieldMessage({
      agencia: {
        required: 'Agência obrigatória.'
      },
      numeroConta: {
        required: 'Número obrigatório.'
      },
      valor: {
        required: 'Valor obrigatório.',
        lessThanOne: 'Valor informado deve ser maior que zero.'
      }
    });
  }

  formToSacarDepositarDTO(): InSaqueDepositoDTO {
    const formValue = this.form.value;
    const entity = new InSaqueDepositoDTO({
      agencia       : formValue.agencia,
      numeroConta        : formValue.numeroConta,
      valor         : formValue.valor,
    });
    return entity;
  }

  private depositar(){
    const entity = this.formToSacarDepositarDTO();
    this.contaService.depositar(entity).subscribe(
      () => {
        SweetalertCustom.showAlertTimer('Operação realizada com sucesso.', {type: 'success'}).then(() => {
          this.router.navigate(['conta/operacoes']);
        });
      }
    );
  }

  private sacar(){
    const entity = this.formToSacarDepositarDTO();
    this.contaService.sacar(entity).subscribe(
      () => {
        SweetalertCustom.showAlertTimer('Operação realizada com sucesso.', {type: 'success'}).then(() => {
          this.router.navigate(['conta/operacoes']);
        });
      }
    );
  }

  onSubmit(){
    if(this.form.valid){
      if(this.router.url.includes('depositar')){
        this.depositar();
      }else if(this.router.url.includes('sacar')){
        this.sacar();
      }
    }
  }

}
