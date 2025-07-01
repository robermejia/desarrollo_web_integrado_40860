import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface IPersona {
  tipoDocumentoIdentidad: string;
  numeroDocumentoIdentidad: string;
  apellidosNombresDenominacionRazonSocial: string; 
}

@Injectable({
  providedIn: 'root'
})
export class PersonaService {

  private apiUrl = 'http://localhost:8085/personas'; 

  constructor(private http : HttpClient) { }

  consultarPersona(tipo: string, numero: string): Observable<IPersona> {
    const params = new HttpParams()
    .set('tipo',tipo)
    .set('numero',numero)

    return this.http.get<IPersona>(this.apiUrl, {params});
  }
}
