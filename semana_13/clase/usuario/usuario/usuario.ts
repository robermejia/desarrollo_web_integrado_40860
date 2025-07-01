import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-usuario',
  standalone: true,
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './usuario.html',
  styleUrl: './usuario.css'
})
export class Usuario {
  formulario: FormGroup;
  enviado = false;

  constructor(private formBuilder: FormBuilder) {
    this.formulario = this.formBuilder.group 
    (
      {
        nombre: ['',[Validators.required, Validators.minLength(3)]],
        correo: ['',[Validators.required, Validators.email]],
        password: ['',[Validators.required, Validators.minLength(6)]],
        confirmacionPassword: ['',[Validators.required]],
        genero: ['',[Validators.required]],
        tipo: ['',[Validators.required]],
        activo:[true,[Validators.requiredTrue]]
      },
      {
        validators: this.validarPassword
      }        
    );
  }

  get controles(): {[key : string]:AbstractControl} {
    return this.formulario.controls;
  }

  validarPassword(group: AbstractControl) {
    const password = group.get('password')?.value;
    const confirmacionPassword = group.get('confirmacionPassword')?.value;
    return password === confirmacionPassword? null: {coincidencia: true}
  }

  onSubmit(): void {
    this.enviado = true;
    if(this.formulario.invalid) return;

    alert('Registro correcto');

    this.formulario.reset;
    this.enviado = false;
  }
}
