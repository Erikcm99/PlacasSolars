package com.mycompany.placassolarssl;

/**
 *
 * @author Erik
 */

// Excepción de errores en la introducciión de un valor de un objeto a instanciar
public class ErrorValorException extends Exception{
    public static final String SUPERF_INSUF = "ERROR: Superfície incorrecta. Ha de ser més gran de 0.";
    public static final String PRECIO_INSUF = "ERROR: Preu incorrecte. Ha de ser més gran que 0.";
    public static final String POTENC_INSUF = "ERROR: Potència incorrecte. Ha de ser més gran de 0.";
    public static final String ESPACIO_TEJADO_INSUF = "ERROR: No hi ha espai disponible per a instal·lar aquesta placa.";
    public static final String SUPERF_TEJADO10 = "ERROR: Superficie incorrecta. Ha de ser més gran de 10.";
    
    public ErrorValorException(String mensaje) {
        super(mensaje);
    }
}
