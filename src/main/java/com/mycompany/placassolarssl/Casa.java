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
    // Estas dos arrayList almacenan todos las placas y electrodomésticos de la 
    // casa 
    private ArrayList<Placa> placasInstaladas = new ArrayList<>();
    private ArrayList<Electrodomestico> electros = new ArrayList<>();

    // En el propio constructor reviso que la superficie sea mayor que 10 y si 
    // es el caso, instancia el objeto Casa
    public Casa(String nif, String nombre, int superficie) throws InstantiationException, ErrorValorException {
        if (superficie < 10) {
            throw new ErrorValorException(ErrorValorException.SUPERF_TEJADO10);
        }
        this.nif = nif.toUpperCase();
        this.nombre = nombre;
        this.superficieTejado = superficie;
        this.interruptor = true;
    }

    // Este método solo crea y añade la placa si tiene espacio suficiente.
    public void addPlaca(int superficie, double precio, int potencia) throws InstantiationException, ErrorValorException {
        if (superficie > superficieRestante()) {
            throw new ErrorValorException(ErrorValorException.ESPACIO_TEJADO_INSUF);
        }
        Placa placa = new Placa(superficie, precio, potencia);
        placasInstaladas.add(placa);
    }

    // Este método solo crea y añade el electrodoméstico si no ha sido registrado
    // anteriormente, es decir no hay ninguno más con la misma descripción
    public void addElectro(String descripcion, int consumo) throws InstantiationException, ErrorDeListaException, ErrorValorException {
        electroRegistrado(descripcion);
        Electrodomestico electro = new Electrodomestico(descripcion, consumo);
        electros.add(electro);
    }

    // Si el interruptor de la casa ya está encendido, lanza una excepción, de lo
    // contrario, lo enciende
    public void onCasa() throws InstantiationException, ElectricException {
        if (this.interruptor) {
            throw new ElectricException(ElectricException.CASA_YA_ON);
        } else {
            this.interruptor = true;
        }
    }

    // Si el interruptor general de la casa esta apagado no se puede encender el electrodomestico
    // y si al encender el electrodoméstico se supera la potencia de las placas, 
    // caen los plomos de la casa
    public void onElectro(String desc) throws InstantiationException, ErrorDeListaException, ElectricException {
        if (!this.interruptor) {
            throw new ElectricException(ElectricException.CASA_APAGADA);
        }

        getElectro(desc).setInterruptor(true);
        if (consumoElectros() > potenciaPlacas()) {
            this.interruptor = false;
            for (Electrodomestico ele : electros) {
                ele.setInterruptor(false);
            }
            throw new ElectricException(ElectricException.PLOMOS_DOWN);
        }
    }
    // Si el electrodomestico ya esta apagado lanza la excepción que le corresponde
    public void offElectro(String desc) throws InstantiationException, ErrorDeListaException, ElectricException {
        if (getElectro(desc).isInterruptor() == false) {
            throw new ElectricException(ElectricException.ELECTRO_YA_APAGADO);
        }
        getElectro(desc).setInterruptor(false);
    }
    // Esta funcion suma todos la potencia que prestan las placas
    public int potenciaPlacas() {
        int total = 0;
        for (Placa placa : placasInstaladas) {
            total += placa.getPotenciaPlaca();
        }
        return total;
    }
    // Esta funcion devuelve una lista de electrodomesticos encendidos
    public ArrayList<String> listaElectrosOn() {
        ArrayList<String> listaElectros = new ArrayList<>();
        for (Electrodomestico electro : electros) {
            if (electro.isInterruptor()) {
                listaElectros.add(electro.getDescripcion());
            }
        }
        return listaElectros;
    }
    // Aquí se suman los consumos de todos los electrodomesticos encendidos
    public int consumoElectros() {
        int total = 0;
        for (Electrodomestico electro : electros) {
            if (electro.isInterruptor()) {
                total += electro.getConsumo();
            }
        }
        return total;
    }
    // Aqui se suma el tamaño que ocupan las placas
    public int tamañoPlacas() {
        int total = 0;
        for (Placa placa : placasInstaladas) {
            total += placa.getSuperficiePlaca();
        }
        return total;
    }
    //Aqui se suma el precio total de las placas instaladas(inversión)
    public double precioPlacas() {
        double total = 0;
        for (Placa placa : placasInstaladas) {
            total += placa.getPrecioPlaca();
        }
        return total;
    }
    // Con este calculo sabemos cuanta superficie restante de tejado queda
    public int superficieRestante() {
        return superficieTejado - tamañoPlacas();
    }
    // Esta funcion es para comprobar si un electro ya esta registrado y si es el caso
    // lanzar la excepcion correspondiente
    public void electroRegistrado(String desc) throws InstantiationException, ErrorDeListaException {
        for (Electrodomestico electro : electros) {
            if (electro.getDescripcion().equalsIgnoreCase(desc)) {
                throw new ErrorDeListaException(ErrorDeListaException.ELECTRO_REGISTRADO);
            }
        }
    }
    //getters

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
    // Primero se comprueba si hay electros en la lista y luego devuelve el que se haya solicitado
    public Electrodomestico getElectro(String desc) throws InstantiationException, ErrorDeListaException {
        noHayElectros();
        for (Electrodomestico electro : electros) {
            if (electro.getDescripcion().equalsIgnoreCase(desc)) {
                return electro;
            }
        }
        throw new ErrorDeListaException(ErrorDeListaException.ELECTRO_NO_REGISTRADO);
    }
    // Si la lista de electrodomesticos esta vacía lanza una excepción
    public void noHayElectros() throws InstantiationException, ErrorDeListaException {
        if (electros.isEmpty()) {
            throw new ErrorDeListaException(ErrorDeListaException.ELECTRO_NO_REGISTRADO);
        }
    }

    public ArrayList<Placa> getPlacasInstaladas() {
        return placasInstaladas;
    }

    public ArrayList<Electrodomestico> getElectros() {
        return electros;
    }
}
