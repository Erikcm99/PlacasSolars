/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.placassolarssl;

/**
 *
 * @author Erik
 */
public class Placa {

    private int superficiePlaca;
    private float precioPlaca;
    private int potenciaPlaca;

    public Placa(int superficie, float precio, int potencia) throws InstantiationException {

        if (superficie <= 0) {
            throw new InstantiationException(ErroresPosibles.SUPERF_INSUF);
        }
        if (precio <= 0) {
            throw new InstantiationException(ErroresPosibles.PRECIO_INSUF);
        }
        if (potencia <= 0) {
            throw new InstantiationException(ErroresPosibles.POTENC_INSUF);
        }
        this.superficiePlaca = superficie;
        this.precioPlaca = precio;
        this.potenciaPlaca = potencia;
    }

    public int getPotenciaPlaca() {
        return potenciaPlaca;
    }

    public int getSuperficiePlaca() {
        return superficiePlaca;
    }

    public float getPrecioPlaca() {
        return precioPlaca;
    }

}
