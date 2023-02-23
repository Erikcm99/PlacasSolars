/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.placassolarssl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Erik
 */
public class PlacasSolarsSL {

    private static ArrayList<Casa> casasClientes = new ArrayList<>();
    private static String[] listadoErrores = {
        /*0*/"ERROR: Número de paràmetres incorrecte.",
        /*1*/ "ERROR: Superficie incorrecta. Ha de ser més gran de 10.",
        /*2*/ "ERROR: Ja hi ha una casa registrada amb aquest nif",
        /*3*/ "ERROR: No hi ha cap casa registrada amb aquest nif.",
        /*4*/ "No hi ha cases registrades.",
        /*5*/ "ERROR: Número de paràmetres incorrecte.",
        /*6*/ "ERROR: Comanda incorrecta",
        /*7*/ "No té cap aparell elèctric registrat.",
        /*8*/ "No té plaques solars instal·lades."

    };

    private static void addCasaMain(String[] input) throws InstantiationException {
        if (input.length != 4) {
            estaEnLista(input[1]);
        } else {
            throw new InstantiationException(ErroresPosibles.PARAMETROS_INSUF + "\n" + ErroresPosibles.USO_ADDCASA);
        }
        Casa casa = new Casa(input[1], input[2], input[3]);
        casasClientes.add(casa);
        System.out.println("OK: Casa enregistrada");
    }

    private static void addPlacaMain(String[] input) throws InstantiationException {
        if (input.length != 5) {
            throw new InstantiationException(ErroresPosibles.PARAMETROS_INSUF + "\n" + ErroresPosibles.USO_ADDPLACA);
        }
        noHayCasas();
        getCasa(input[1]).addPlaca(input[2], input[3], input[4]);
        System.out.println("OK: Aparell afegit a la casa.");
    }

    public static void addElectroMain(String[] input) throws InstantiationException {
        if (input.length != 4) {
            throw new InstantiationException(ErroresPosibles.PARAMETROS_INSUF + "\n" + ErroresPosibles.USO_ADDELECTRO);
        }
        noHayCasas();

        getCasa(input[1]).addElectro(input[2], input[3]);

    }

    public static void onCasaMain(String[] input) throws InstantiationException {
        if (input.length != 2) {
            throw new InstantiationException(ErroresPosibles.PARAMETROS_INSUF + "\n" + ErroresPosibles.USO_ONCASA);
        }
        noHayCasas();
        getCasa(input[1]).onCasa();
    }

    public static void onElectroMain(String[] input) throws InstantiationException {
        if (input.length != 3) {
            throw new InstantiationException(ErroresPosibles.PARAMETROS_INSUF + "\n" + ErroresPosibles.USO_ONCASA);
        }
        noHayCasas();
        getCasa(input[1]).onElectro(input[2]);
    }

    public static void offElectroMain(String[] input) throws InstantiationException {
        if (input.length != 3) {
            throw new InstantiationException(ErroresPosibles.PARAMETROS_INSUF + "\n" + ErroresPosibles.USO_ONCASA);
        }
        noHayCasas();
        getCasa(input[1]).offElectro(input[2]);
    }

    public static void list() {
        if (!casasClientes.isEmpty()) {
            System.out.println("--- Endolls Solars, S.L. ---");
            System.out.println(
                    "Cases enregistrades: " + casasClientes.size());
            int contador = 1;
            for (Casa casa : casasClientes) {
                System.out.println(" ");
                System.out.println("Casa" + contador);
                contador++;
                System.out.println("Client: " + casa.getNif() + " - " + casa.getNombre());
                System.out.println("Superficie de teulada: " + casa.getSuperficieTejado());
                System.out.println("Superfície disponible: " + casa.superficieRestante());
                if (casa.isInterruptor()) {
                    System.out.println("Interruptor general: encès");
                } else {
                    System.out.println("Interruptor general: apagat");
                }
                if (!casa.getPlacasInstaladas().isEmpty()) {
                    System.out.println("Plaques solars instal·lades: " + casa.getPlacasInstaladas().size());
                } else {
                    System.out.println(listadoErrores[8]);
                }
                if (!casa.getElectros().isEmpty()) {
                    System.out.println("Aparells registrats: " + casa.getElectros().size());
                } else {
                    System.out.println(listadoErrores[7]);
                }
            }
        } else {
            System.out.println(listadoErrores[4]);
        }
    }

    public static void info(String nif) {
        if (!casasClientes.isEmpty()) {
            if (estaEnLista(nif)) {
                Casa casa = getCasa(nif);
                System.out.println("Client: " + nif + " - " + casa.getNombre());
                if (!getCasa(nif).getPlacasInstaladas().isEmpty()) {
                    System.out.println("Plaques solars instal·lades: " + casa.getPlacasInstaladas().size());
                } else {
                    System.out.println(listadoErrores[8]);
                }
                System.out.println("Potencia total: " + casa.potenciaPlacas() + "W");
                System.out.println("Inversió total: " + casa.precioPlacas() + "€");
                if (!casa.getElectros().isEmpty()) {
                    System.out.println("Aparells registrats: " + casa.getElectros().size());
                } else {
                    System.out.println(listadoErrores[7]);
                }
                System.out.println("Consum actual: " + casa.consumoElectros() + "W");
                if (casa.consumoElectros() > 0) {
                    System.out.println("Aparells encesos:");
                    for (String electro : casa.listaElectrosOn()) {
                        System.out.println("\t- " + electro);
                    }
                }
            } else {
                System.out.println(listadoErrores[3]);
            }
        } else {
            System.out.println(listadoErrores[4]);
        }
    }

    public static boolean estaEnLista(String nif) throws InstantiationException {
        boolean encontrado = false;
        for (Casa casa : casasClientes) {
            if (casa.getNif().equalsIgnoreCase(nif)) {
                encontrado = true;
            }
        }
        if (!encontrado) {
            throw new InstantiationError(ErroresPosibles.CASA_REGISTRADA);
        }
        return encontrado;
    }

    public static void noHayCasas() throws InstantiationException {
        boolean hayCasa = false;
        if (!casasClientes.isEmpty()) {
            hayCasa = true;
        }
        if (!hayCasa) {
            throw new InstantiationException(ErroresPosibles.NO_CASAS);
        }
    }

    public static Casa getCasa(String nif) throws InstantiationException {
        if (!estaEnLista(nif)) {
            throw new InstantiationException(ErroresPosibles.NO_EXISTE_CASA);
        }
        for (Casa casa : casasClientes) {
            if (casa.getNif().equalsIgnoreCase(nif)) {
                return casa;
            }
        }
        return casasClientes.get(0);
    }

    public static ArrayList<Casa> getCasasClientes() {
        return casasClientes;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        String comando;
        do {
            System.out.print("> ");
            String[] input = (consola.readLine()).split(" ");
            comando = input[0].toLowerCase();

            switch (comando) {
                case "addcasa":
                    addCasaMain(input);
                    break;
                case "addplaca":
                    addPlacaMain(input);
                    break;
                case "addaparell":
                    addElectroMain(input);
                    break;
                case "oncasa":
                    onCasaMain(input);
                    break;
                case "onaparell":
                    onElectroMain(input);
                    break;
                case "offaparell":
                    if (input.length == 3) {
                        offElectroMain(input[1], input[2]);
                    } else {
                        System.out.println(listadoErrores[5]);
                        System.out.println("Uso: offAparell [nif] [descripció aparell]");
                    }
                    break;
                case "list":
                    if (input.length == 1) {
                        list();
                    } else {
                        System.out.println(listadoErrores[5]);
                        System.out.println("Ús: list");
                    }

                    break;
                case "info":
                    if (input.length == 2) {
                        info(input[1]);
                    } else {
                        System.out.println(listadoErrores[5]);
                        System.out.println("Ús: info [nif]");
                    }
                    break;
                default:
                    System.out.println(listadoErrores[6]);
                    break;
            }
        } while (!comando.equalsIgnoreCase("quit"));
    }

}
