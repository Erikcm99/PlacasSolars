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
        /*0*/"OK: ",
        /*1*/
        /*2*/ "ERROR: El precio tiene que ser mauor que 0",
        /*3*/ "ERROR: La potencia tiene que ser mayor que 0",
        /*4*/ "ERROR: La placa que se ha tratado de asignar es más grande que la superficie de tejado restante",
        /*5*/ "ERROR: No se puede encender este electrodoméstico, el interruptor general de la casa está apagado",
        /*6*/ "ERROR: Han saltado los plomos. La casa ha quedado completamente apagada",
        /*7*/ "ERROR: No hay ningún electrodoméstico registrado con esa descripción en la casa",
        /*8*/ "ERROR: Electrodoméstico ya registrado",
        /*9*/ "ERROR: La casa ya tiene el interruptor encendido",
        /*10*/ "ERROR: El electrodoméstico ya está apagado"};

    public Casa(String nif, String nombre, String superficie) throws InstantiationException {
        int superf = Integer.parseInt(superficie);
        if (superf < 10) {
            throw new InstantiationException(ErroresPosibles.SUPERF_TEJADO10);
        }
        this.nif = nif.toUpperCase();
        this.nombre = nombre;
        this.superficieTejado = superf;
        this.interruptor = true;
    }

    public void addPlaca(String superficie, String precio, String potencia) throws InstantiationException {
        int superficieOK = Integer.parseInt(superficie);
        if (superficieOK > superficieRestante()) {
            throw new InstantiationException(ErroresPosibles.ESPACIO_TEJADO_INSUF);
        }
        Placa placa = new Placa(superficie, precio, potencia);
        placasInstaladas.add(placa);
    }

    public void addElectro(String descripcion, String potencia) throws InstantiationException {
        electroRegistrado(descripcion);
        Electrodomestico electro = new Electrodomestico(descripcion, potencia);
        electros.add(electro);
    }

    public void onCasa() throws InstantiationException {
        if (this.interruptor == true) {
            throw new InstantiationException(ErroresPosibles.CASA_YA_ON);
        } else {
            this.interruptor = true;
        }
    }

    public void onElectro(String desc) throws InstantiationException {
        if (this.interruptor == false) {
            throw new InstantiationException(ErroresPosibles.ELECTRO_YA_APAGADO);
        }

        getElectro(desc).setInterruptor(true);
        if (consumoElectros() > potenciaPlacas()) {
            this.interruptor = false;
            for (Electrodomestico ele : electros) {
                ele.setInterruptor(false);
            }
            throw new InstantiationException(ErroresPosibles.PLOMOS_DOWN);
        }
    }

    public void offElectro(String desc) throws InstantiationException {
        if (getElectro(desc).isInterruptor() == false) {
            throw new InstantiationException(ErroresPosibles.ELECTRO_YA_APAGADO);
        }
        getElectro(desc).setInterruptor(false);
    }

    public int potenciaPlacas() {
        int total = 0;
        for (Placa placa : placasInstaladas) {
            total += placa.getPotenciaPlaca();
        }
        return total;
    }

    public ArrayList<String> listaElectrosOn() {
        ArrayList<String> listaElectros = new ArrayList<>();
        for (Electrodomestico electro : electros) {
            if (electro.isInterruptor()) {
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

    public void electroRegistrado(String desc) throws InstantiationException {
        for (Electrodomestico electro : electros) {
            if (electro.getDescripcion().equalsIgnoreCase(desc)) {
                throw new InstantiationException(ErroresPosibles.ELECTRO_REGISTRADO);
            }
        }
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

    public Electrodomestico getElectro(String desc) throws InstantiationException {
        noHayElectros();
        for (Electrodomestico electro : electros) {
            if (electro.getDescripcion().equalsIgnoreCase(desc)) {
                return electro;
            }
        }
        throw new InstantiationException(ErroresPosibles.ELECTRO_NO_REGISTRADO);
    }

    public void noHayElectros() throws InstantiationException {
        if (electros.isEmpty()) {
            throw new InstantiationException(ErroresPosibles.ELECTRO_NO_REGISTRADO);
        }
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
