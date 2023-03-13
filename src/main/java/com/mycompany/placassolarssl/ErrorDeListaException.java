package com.mycompany.placassolarssl;

/**
 *
 * @author Erik
 */
public class ErrorDeListaException extends Exception {

    public static final String NO_CASAS = "No hi ha cases registrades.";
    public static final String NO_EXISTE_CASA = "ERROR: No hi ha cap casa registrada amb aquest nif.";
    public static final String CASA_REGISTRADA = "ERROR: Ja hi ha una casa registrada amb aquest nif";
    public static final String ELECTRO_NO_REGISTRADO = "ERROR: No hi ha cap aparell registrat amb aquesta descripció a la casa indicada.";
    public static final String ELECTRO_REGISTRADO = "ERROR: Ja existeix un aparell amb aquesta descripció a la casa indicada.";

    public ErrorDeListaException(String mensaje) {
        super(mensaje);
    }

}
