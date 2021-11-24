import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { ContaDTO } from 'src/app/core/dtos/conta.dto';
import { ContaService } from 'src/app/core/services/conta.service';
import { SweetalertCustom } from 'src/app/shared/utils/sweetalert-custom';
import { ValidatorsCustom } from 'src/app/shared/utils/validators-custom';

@Component({
  selector: 'app-conta-consultar',
  templateUrl: './conta-consultar.component.html',
  styleUrls: ['./conta-consultar.component.scss']
})
export class ContaConsultarComponent extends FormBase implements OnInit {

  contas = new Array<ContaDTO>();
  
  constructor(
    public router: Router,
    private formBuilder: FormBuilder,
    private contaService: ContaService
  ) {
    super();
  }

  ngOnInit(): void {
    this.createFormGoup();
    this. validateMensageError();
  }

  createFormGoup() {
    this.form = this.formBuilder.group({
      cpf             : new FormControl('', [Validators.required,  ValidatorsCustom.validCpf]),
    });
  }

  validateMensageError() {
    this.createValidateFieldMessage({
      cpf: {
        required: 'Cpf é obrigatório.'
      }
    });
  }

  private consultar(){
    const cpf = this.form.value.cpf;
    this.contaService.consultar(cpf).subscribe(
      (response) => {
        this.contas = response;
      }
    );
  }

  onSubmit(){
    if(this.form.valid){
       this.consultar();
    }
  }

}
