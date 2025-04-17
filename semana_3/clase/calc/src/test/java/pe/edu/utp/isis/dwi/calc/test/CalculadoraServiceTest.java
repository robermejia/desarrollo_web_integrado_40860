package pe.edu.utp.isis.dwi.calc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.isis.dwi.calc.service.CalculadoraService;
import pe.edu.utp.isis.dwi.calc.service.ConvertidorImpl;

@ExtendWith(MockitoExtension.class)
public class CalculadoraServiceTest {

    @Mock
    private ConvertidorImpl convertidorImpl;
    
    @InjectMocks
    private CalculadoraService calculadoraService;
    
    @Test
    public void sumar() {
        double resultado;
        resultado = calculadoraService.sumar(10,20);
        assertEquals(30,resultado,"Suma OK");
    }
    
    @Test
    public void restar() {
        double resultado;
        resultado = calculadoraService.restar(30,20);
        assertEquals(10,resultado,"Resta OK");
    }
    
    @Test
    public void multiplicar() {
        double resultado;
        resultado = calculadoraService.multiplicar(5,4);
        assertEquals(20,resultado,"Multiplicacion OK");
    }
    
    @Test
    public void dividir() {
        double resultado;
        resultado = calculadoraService.dividir(100,20);
        assertEquals(5,resultado,"Divide OK");
    }
    
    @Test
    public void aBinario() {
        when(convertidorImpl.aBinario(5)).thenReturn("00000101");
        
        String resultado;
        resultado = calculadoraService.aBinario(5);
        assertEquals("00000101",resultado,"aBinario OK");
    }
    
    @Test
    public void aOctal() {
        
    }
    
    @Test
    public void aHexadecimal() {
        
    }
}