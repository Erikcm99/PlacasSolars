package com.mycompany.placassolarssl;

/**
 *
 * @author Erik
 */
public class ElectricException extends Exception{
    public static final String CASA_YA_ON = "ERROR: La casa ja té l'interruptor encès.";
    public static final String CASA_APAGADA = "ERROR: No es pot encendre l'aparell. L'interruptor general està apagat.";
    public static final String PLOMOS_DOWN = "ERROR: Han saltat els ploms. La casa ha quedat completament apagada.";
    public static final String ELECTRO_YA_APAGADO = "ERROR: L'aparell ja està apagat.";
    public static final String ELECTRO_YA_ENCENDIDO = "ERROR: L'aparell ja està encès.";
    
    public ElectricException(String mensaje) {
        super(mensaje);
    }
}
