import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { InExtrato } from 'src/app/core/dtos/in-extrato.dto';
import { OutExtrato } from 'src/app/core/dtos/out-extrato.dto';
import { ContaService } from 'src/app/core/services/conta.service';
import { SweetalertCustom } from 'src/app/shared/utils/sweetalert-custom';
import { ValidatorsCustom } from 'src/app/shared/utils/validators-custom';

@Component({
  selector: 'app-conta-extrato',
  templateUrl: './conta-extrato.component.html',
  styleUrls: ['./conta-extrato.component.scss']
})
export class ContaExtratoComponent extends FormBase implements OnInit {

  extrato = new Array<OutExtrato>();
  constructor(
    private formBuilder: FormBuilder,
    private contaService: ContaService,
    public router: Router,
  ) {
    super();
  }

  ngOnInit(): void {
    this.createFormGoup();
    this.validateMensageError();
  }

  createFormGoup() {
    const currentDate = new Date();
    this.form = this.formBuilder.group({
      agencia             : new FormControl('', [Validators.required]),
      numeroConta              : new FormControl('', [Validators.required]),
      dataIncial               : new FormControl(currentDate, [Validators.required, ValidatorsCustom.validDate]),
      dataFinal         : new FormControl(currentDate, [Validators.required, , ValidatorsCustom.validDate]),
      
    });
  }

  validateMensageError() {
    this.createValidateFieldMessage({
      agencia: {
        required: 'Agência obrigatória.'
      },
      numeroConta: {
        required: 'Número da conta é obrigatório.'
      },
      dataIncial: {
        required: 'Campo obrigatório.',
      },
      dataFinal: {
        required: 'Campo obrigatório.'
      }
    });
  }

  formToInExtratoDTO(): InExtrato {
    const formValue = this.form.value;
    const entity = new InExtrato({
      agencia       : formValue.agencia,
      numeroConta       : formValue.numeroConta,
      dataIncial        : formValue.dataIncial,
      dataFinal        : formValue.dataFinal,
    });
    return entity;
  }

  private getExtrato(){
    const entity = this.formToInExtratoDTO();
    this.contaService.extrato(entity).subscribe(
      (response) => {
        this.extrato = response;
      }
    );
  }

  onSubmit(){
    if(this.form.valid){
       this.getExtrato();
    }
  }

}
