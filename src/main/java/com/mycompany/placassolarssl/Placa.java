package com.mycompany.placassolarssl;

/**
 *
 * @author Erik
 * 
 * Esta clase almacena todos los atributos y métodos relacionados con un elect
 */
public class Placa {

    private int superficiePlaca;
    private double precioPlaca;
    private int potenciaPlaca;
    
    // En el propio constructor reviso que tanto la potencia como el precio y la 
    // superficie sean mayor que 0 y si es el caso, instancia el objeto Placa
    
    public Placa(int superficie, double precio, int potencia) throws InstantiationException, ErrorValorException {

        if (superficie <= 0) {
            throw new ErrorValorException(ErrorValorException.SUPERF_INSUF);
        }
        if (precio <= 0) {
            throw new ErrorValorException(ErrorValorException.PRECIO_INSUF);
        }
        if (potencia <= 0) {
            throw new ErrorValorException(ErrorValorException.POTENC_INSUF);
        }
        this.superficiePlaca = superficie;
        this.precioPlaca = precio;
        this.potenciaPlaca = potencia;
    }
    
    // Getters y setters de la clase
    
    public int getPotenciaPlaca() {
        return potenciaPlaca;
    }

    public int getSuperficiePlaca() {
        return superficiePlaca;
    }

    public double getPrecioPlaca() {
        return precioPlaca;
    }
}
