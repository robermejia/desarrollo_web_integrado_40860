import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PersonaService, IPersona } from '../persona';

@Component({
  selector: 'app-persona',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './persona.html',
  styleUrl: './persona.css'
})
export class Persona {
  tipo = '1';
  numero = '13312121';
  persona ?: IPersona;
  error = ''; 

  constructor(private personaService: PersonaService) {}

  consultarPersona() {
    this.personaService.consultarPersona(this.tipo, this.numero).subscribe({
      next: (data) => {
        this.persona = data;
        this.error = '';
      },
      error: (err) => {
        this.error = 'Error en la consulta';
        this.persona = undefined;
      } 
    });
  }
}
