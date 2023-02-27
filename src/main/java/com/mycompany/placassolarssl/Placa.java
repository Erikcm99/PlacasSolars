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
    private double precioPlaca;
    private int potenciaPlaca;

    public Placa(String superficie, String precio, String potencia) throws InstantiationException {
        double precioOK = Double.parseDouble(precio);
        int potenciaOK = Integer.parseInt(potencia);
        int superficieOK = Integer.parseInt(superficie);

        if (superficieOK <= 0) {
            throw new InstantiationException(ErroresPosibles.SUPERF_INSUF);
        }
        if (precioOK <= 0) {
            throw new InstantiationException(ErroresPosibles.PRECIO_INSUF);
        }
        if (potenciaOK <= 0) {
            throw new InstantiationException(ErroresPosibles.POTENC_INSUF);
        }
        this.superficiePlaca = superficieOK;
        this.precioPlaca = precioOK;
        this.potenciaPlaca = potenciaOK;
    }

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
