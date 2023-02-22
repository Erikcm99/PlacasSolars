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
   /*0*/ "ERROR: Número de paràmetres incorrecte.",
   /*1*/ "ERROR: Superficie incorrecta. Ha de ser més gran de 10.",
   /*2*/ "ERROR: Ja hi ha una casa registrada amb aquest nif",
   /*3*/ "ERROR: No hi ha cap casa registrada amb aquest nif.",
   /*4*/ "No hi ha cases registrades.",
   /*5*/ "ERROR: Número de paràmetres incorrecte.",
   /*6*/ "ERROR: Comanda incorrecta",
   /*7*/ "No té cap aparell elèctric registrat.",
   /*8*/ "No té plaques solars instal·lades."
        

    };

    private static void addCasaMain(String nif, String nom, int superficie) {
        if (superficie >= 10) {
            Casa casa = new Casa(nif, nom, superficie);
            if (!estaEnLista(nif)) {
                casasClientes.add(casa);
                System.out.println(casa.getListadoErrores()[0] + "Casa enregistrada");
            } else {
                System.out.println(listadoErrores[2]);
            }
        } else {
            System.out.println(listadoErrores[1]);
        }
    }

    private static void addPlacaMain(String nif, int superficie, float precio, int potencia) {
        if (!casasClientes.isEmpty()) {
            if (estaEnLista(nif)) {
                int numeroSalida = getCasa(nif).addPlaca(superficie, precio, potencia);
                if (numeroSalida == 0) {
                    System.out.println(getCasa(nif).getListadoErrores()[numeroSalida] + "Placa afegida a la casa.");
                } else if (numeroSalida == 4) {
                    System.out.println(getCasa(nif).getListadoErrores()[numeroSalida] + "(" + getCasa(nif).superficieRestante() + "m2)");
                }
            } else {
                System.out.println(listadoErrores[3]);
            }
        } else {
            System.out.println(listadoErrores[4]);

        }
    }

    public static void addElectroMain(String nif, String descripcion, int potencia) {
        if (!casasClientes.isEmpty()) {
            if (estaEnLista(nif)) {
                int numeroSalida = getCasa(nif).addElectro(descripcion, potencia);
                if (numeroSalida == 0) {
                    System.out.println(getCasa(nif).getListadoErrores()[numeroSalida] + "Aparell afegit a la casa.");
                } else {
                    System.out.println(getCasa(nif).getListadoErrores()[numeroSalida]);
                }
            } else {
                System.out.println(listadoErrores[3]);
            }
        } else {
            System.out.println(listadoErrores[4]);
        }
    }

    public static void onCasaMain(String nif) {
        if (!casasClientes.isEmpty()) {
            if (estaEnLista(nif)) {
                int numeroSalida = getCasa(nif).onCasa();
                if (numeroSalida == 0) {
                    System.out.println(getCasa(nif).getListadoErrores()[numeroSalida] + "Interruptor general activat.");
                } else {
                    System.out.println(getCasa(nif).getListadoErrores()[numeroSalida]);
                }
            } else {
                System.out.println(listadoErrores[3]);
            }
        } else {
            System.out.println(listadoErrores[4]);
        }

    }

    public static void onElectroMain(String nif, String desc) {
        if (!casasClientes.isEmpty()) {
            if (estaEnLista(nif)) {
                int numeroSalida = getCasa(nif).onElectro(desc);
                if (numeroSalida == 0) {
                    System.out.println(getCasa(nif).getListadoErrores()[numeroSalida] + "Aparell encès.");
                } else {
                    System.out.println(getCasa(nif).getListadoErrores()[numeroSalida]);
                }
            } else {
                System.out.println(listadoErrores[3]);
            }
        } else {
            System.out.println(listadoErrores[4]);
        }
    }

    public static void offElectroMain(String nif, String desc) {
        if (!casasClientes.isEmpty()) {
            if (estaEnLista(nif)) {
                int numeroSalida = getCasa(nif).offElectro(desc);
                if (numeroSalida == 0) {
                    System.out.println(getCasa(nif).getListadoErrores()[numeroSalida] + "Aparell apagat");
                } else {
                    System.out.println(getCasa(nif).getListadoErrores()[numeroSalida]);
                }
            } else {
                System.out.println(listadoErrores[3]);
            }
        } else {
            System.out.println(listadoErrores[4]);
        }
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

    public static boolean estaEnLista(String nif) {
        for (Casa casa : casasClientes) {
            if (casa.getNif().equalsIgnoreCase(nif)) {
                return true;
            }
        }
        return false;
    }

    public static Casa getCasa(String nif) {
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
                    if (input.length == 4) {
                        addCasaMain(input[1], input[2], Integer.parseInt(input[3]));
                    } else {
                        System.out.println(listadoErrores[5]);
                        System.out.println("Ús: addCasa [nif] [nom] [superficie]");
                    }
                    break;
                case "addplaca":
                    if (input.length == 5) {
                        addPlacaMain(input[1], Integer.parseInt(input[2]), (Float.parseFloat(input[3])), Integer.parseInt(input[4]));
                    } else {
                        System.out.println(listadoErrores[5]);
                        System.out.println("Ús: addPlaca [nif] [superficie] [preu] [potencia]");
                    }
                    break;
                case "addaparell":
                    if (input.length == 4) {
                        addElectroMain(input[1], input[2], Integer.parseInt(input[3]));
                    } else {
                        System.out.println(listadoErrores[5]);
                        System.out.println("Uso: addAparell [nif] [descripció] [potencia]");
                    }
                    break;
                case "oncasa":
                    if (input.length == 2) {
                        onCasaMain(input[1]);
                    } else {
                        System.out.println(listadoErrores[5]);
                        System.out.println("Uso: onCasa [nif]");
                    }
                    break;
                case "onaparell":
                    if (input.length == 3) {
                        onElectroMain(input[1], input[2]);
                    } else {
                        System.out.println(listadoErrores[5]);
                        System.out.println("Ús: onAparell [nif] [descripció aparell]");
                    }
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
