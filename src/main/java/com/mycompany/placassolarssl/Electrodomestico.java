package com.mycompany.placassolarssl;

/**
 *
 * @author Erik
 */
public class Electrodomestico {

    private String descripcion;
    private int consumo;
    private boolean interruptor;

    public Electrodomestico(String descripcion, String potencia)throws InstantiationException {
        int potenciaOK = Integer.parseInt(potencia);
        if (potenciaOK <= 0){
            throw new InstantiationException(ErroresPosibles.POTENC_INSUF);
        }

        this.descripcion = descripcion;
        this.consumo = potenciaOK;
        this.interruptor = false;
    }

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
