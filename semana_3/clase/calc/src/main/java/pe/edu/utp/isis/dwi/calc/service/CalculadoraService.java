package pe.edu.utp.isis.dwi.calc.service;

import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {
    private Convertidor convertidor;
    
    public CalculadoraService(Convertidor convertidor) {
        this.convertidor = convertidor;
    }
    
    public double sumar(double n1, double n2) {
        return n1 + n2;
    }
    
    public double restar(double n1, double n2) {
        return n1 - n2;
    }
    
    public double multiplicar(double n1, double n2) {
        return n1 * n2;
    }
    
    public double dividir(double n1, double n2) {
        return n1 / n2;
    }
    
    public String aBinario(int n) {
        return convertidor.aBinario(n);
    }
}