package com.mycompany.placassolarssl;

/**
 * @author Erik
 * 
 * Esta clase almacena todos los atributos y métodos relacionados con un electrodoméstico
 */
public class Electrodomestico {
    
    private String descripcion;
    private int consumo;
    private boolean interruptor;

    // En el propio constructor reviso que el consumo sea mayor que 0 y si es el caso,
    // instancia el objeto Electrodomestico
    public Electrodomestico(String descripcion, int consumo)throws InstantiationException, ErrorValorException {
        if (consumo <= 0){
            throw new ErrorValorException(ErrorValorException.POTENC_INSUF);
        }

        this.descripcion = descripcion;
        this.consumo = consumo;
        this.interruptor = false;
    }
    // Getters y setters de la clase
    
    public void setInterruptor(boolean estado) {
        this.interruptor = estado;
    }

    public boolean isInterruptor() {
        return interruptor;
    }

    public int getConsumo() {
        return consumo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
