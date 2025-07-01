import { Component } from '@angular/core';
import { Persona } from "./persona/persona";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [Persona],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'appconsulta';
}
