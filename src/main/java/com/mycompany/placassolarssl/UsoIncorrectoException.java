package com.mycompany.placassolarssl;

/**
 *
 * @author Erik
 */

// Excepciones que se muestran cuando se hace un uso incorrecto de un comando
public class UsoIncorrectoException extends Exception {
    public static final String USO_ADDPLACA = "ERROR: Número de paràmetres incorrecte.\nÚs: addPlaca [nif] [superficie] [preu] [potencia]";
    public static final String USO_ADDCASA = "ERROR: Número de paràmetres incorrecte.\nÚs: addCasa [nif] [nom] [superficie]";
    public static final String USO_ADDELECTRO = "ERROR: Número de paràmetres incorrecte.\nÚs: addAparell [nif] [descripció] [potència]";
    public static final String USO_ONCASA = "ERROR: Número de paràmetres incorrecte.\nÚs: onCasa [nif]";
    public static final String USO_ONELECTRO = "ERROR: Número de paràmetres incorrecte.\nÚs: onAparell [nif] [descripció aparell]";
    public static final String USO_OFFELECTRO = "ERROR: Número de paràmetres incorrecte.\nÚs: offAparell [nif] [descripció aparell]";
    public static final String USO_INFO = "ERROR: Número de paràmetres incorrecte.\nÚs: info [nif]";
    public static final String USO_LIST = "ERROR: Número de paràmetres incorrecte.\nÚs: list";
    public static final String COMANDA_INCORRECTA = "ERROR: Comanda incorrecta.";
    
    public UsoIncorrectoException(String mensaje) {
        super(mensaje);
    }
 
}
