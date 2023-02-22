/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.placassolarssl;

import java.util.ArrayList;

/**
 *
 * @author Erik
 */
public class Casa {

    private String nif;
    private String nombre;
    private int superficieTejado;
    private boolean interruptor;
    private ArrayList<Placa> placasInstaladas = new ArrayList<>();
    private ArrayList<Electrodomestico> electros = new ArrayList<>();
    private String[] listadoErrores = {
        /*0*/ "OK: ",
        /*1*/ "ERROR: La superficie tiene que ser mayor que 0",
        /*2*/ "ERROR: El precio tiene que ser mauor que 0",
        /*3*/ "ERROR: La potencia tiene que ser mayor que 0",
        /*4*/ "ERROR: La placa que se ha tratado de asignar es más grande que la superficie de tejado restante",
        /*5*/ "ERROR: No se puede encender este electrodoméstico, el interruptor general de la casa está apagado",
        /*6*/ "ERROR: Han saltado los plomos. La casa ha quedado completamente apagada",
        /*7*/ "ERROR: No hay ningún electrodoméstico registrado con esa descripción en la casa",
        /*8*/ "ERROR: Electrodoméstico ya registrado",
        /*9*/ "ERROR: La casa ya tiene el interruptor encendido",
        /*10*/"ERROR: El electrodoméstico ya está apagado"};

    public Casa(String nif, String nombre, int superficie) {
        this.nif = nif;
        this.nombre = nombre;
        this.superficieTejado = superficie;
        this.interruptor = true;
    }

    public int addPlaca(int superficie, float precio, int potencia) {
        if (superficie <= 0) {
            return 1;
        }
        if (precio <= 0) {
            return 2;
        }
        if (potencia <= 0) {
            return 3;
        }
        if (superficie > superficieRestante()) {
            return 4;
        }
        Placa placa = new Placa(superficie, precio, potencia);
        placasInstaladas.add(placa);
        return 0;
    }

    public int addElectro(String descripcion, int potencia) {
        if (electroRegistrado(descripcion)) {
            return 8;
        }
        if (potencia < 0) {
            return 3;
        }
        Electrodomestico electro = new Electrodomestico(descripcion, potencia);
        electros.add(electro);
        return 0;
    }

    public int onCasa() {
        if (this.interruptor == true) {
            return 9;
        } else {
            this.interruptor = true;
            return 0;
        }
    }

    public int onElectro(String desc) {
        if (this.interruptor == false) {
            return 5;
        }
        if (!electroRegistrado(desc)) {
            return 7;
        }
        getElectro(desc).setInterruptor(true);
        if (consumoElectros() > potenciaPlacas()) {
            this.interruptor = false;
            for (Electrodomestico ele : electros) {
                ele.setInterruptor(false);
            }
            return 6;
        }
        return 0;
    }

    public int offElectro(String desc) {
        if (!electroRegistrado(desc)) {
            return 7;
        }
        if (getElectro(desc).isInterruptor() == false) {
            return 10;
        }
        getElectro(desc).setInterruptor(false);
        return 0;
    }

    public int potenciaPlacas() {
        int total = 0;
        for (Placa placa : placasInstaladas) {
            total += placa.getPotenciaPlaca();
        }
        return total;
    }
    public ArrayList<String> listaElectrosOn(){
        ArrayList<String> listaElectros = new ArrayList<>();
        for (Electrodomestico electro: electros){
            if (electro.isInterruptor()){
                listaElectros.add(electro.getDescripcion());
            }
        }
        return listaElectros;
    }

    public int consumoElectros() {
        int total = 0;
        for (Electrodomestico electro : electros) {
            if (electro.isInterruptor()) {
                total += electro.getConsumo();
            }
        }
        return total;
    }

    public int tamañoPlacas() {
        int total = 0;
        for (Placa placa : placasInstaladas) {
            total += placa.getSuperficiePlaca();
        }
        return total;
    }

    public float precioPlacas() {
        int total = 0;
        for (Placa placa : placasInstaladas) {
            total += placa.getPrecioPlaca();
        }
        return total;
    }

    public int superficieRestante() {
        return superficieTejado - tamañoPlacas();
    }

    public boolean electroRegistrado(String desc) {
        for (Electrodomestico electro : electros) {
            if (electro.getDescripcion().equalsIgnoreCase(desc)) {
                return true;
            }
        }
        return false;
    }

    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSuperficieTejado() {
        return superficieTejado;
    }

    public boolean isInterruptor() {
        return interruptor;
    }

    public Electrodomestico getElectro(String desc) {
        for (Electrodomestico electro : electros) {
            if (electro.getDescripcion().equalsIgnoreCase(desc)) {
                return electro;
            }
        }
        return electros.get(0);
    }

    public String[] getListadoErrores() {
        return listadoErrores;
    }

    public ArrayList<Placa> getPlacasInstaladas() {
        return placasInstaladas;
    }

    public ArrayList<Electrodomestico> getElectros() {
        return electros;
    }

}
