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

    // 
    private static ArrayList<Casa> casasClientes = new ArrayList<>();

    private static void addCasaMain(String[] input) throws InstantiationException, UsoIncorrectoException, ErrorDeListaException, ErrorValorException {
        if (input.length != 4) {
            throw new UsoIncorrectoException(UsoIncorrectoException.USO_ADDCASA);
        }
        yaEstaEnLista(input[1]);

        Casa casa = new Casa(input[1], input[2], Integer.parseInt(input[3]));
        casasClientes.add(casa);
        System.out.println("OK: Casa registrada");
    }

    private static void addPlacaMain(String[] input) throws InstantiationException, UsoIncorrectoException, ErrorDeListaException, ErrorValorException {
        if (input.length != 5) {
            throw new UsoIncorrectoException(UsoIncorrectoException.USO_ADDPLACA);

        }
        getCasa(input[1]).addPlaca(Integer.parseInt(input[2]), Double.parseDouble(input[3]), Integer.parseInt(input[4]));
        System.out.println("OK: Placa afegida a la casa.");
    }

    public static void addElectroMain(String[] input) throws InstantiationException, UsoIncorrectoException, ErrorDeListaException, ErrorValorException {
        if (input.length != 4) {
            throw new UsoIncorrectoException(UsoIncorrectoException.USO_ADDELECTRO);
        }
        getCasa(input[1]).addElectro(input[2], Integer.parseInt(input[3]));
        System.out.println("OK: Aparell afegit a la casa.");
    }

    public static void onCasaMain(String[] input) throws InstantiationException, UsoIncorrectoException, ErrorDeListaException, ElectricException {
        if (input.length != 2) {
            throw new UsoIncorrectoException(UsoIncorrectoException.USO_ONCASA);

        }
        getCasa(input[1]).onCasa();
        System.out.println("OK: Interruptor general activat.");
    }

    public static void onElectroMain(String[] input) throws InstantiationException, UsoIncorrectoException, ErrorDeListaException, ElectricException {
        if (input.length != 3) {
            throw new UsoIncorrectoException(UsoIncorrectoException.USO_ONELECTRO);

        }
        getCasa(input[1]).onElectro(input[2]);
        System.out.println("OK: Aparell encès.");
    }

    public static void offElectroMain(String[] input) throws InstantiationException, UsoIncorrectoException, ErrorDeListaException, ElectricException {
        if (input.length != 3) {
            throw new UsoIncorrectoException(UsoIncorrectoException.USO_OFFELECTRO);
        }
        getCasa(input[1]).offElectro(input[2]);
        System.out.println("OK: Aparell apagat.");

    }

    public static void list(String[] input) throws InstantiationException, UsoIncorrectoException, ErrorDeListaException {
        if (input.length != 1) {
            throw new UsoIncorrectoException(UsoIncorrectoException.USO_LIST);

        }
        noHayCasas();
        System.out.println("--- Endolls Solars, S.L. ---");
        System.out.println(
                "Cases enregistrades: " + casasClientes.size());
        int contador = 1;
        for (Casa casa : casasClientes) {
            System.out.println(" ");
            System.out.println("Casa " + contador);
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
                System.out.println("No té plaques solars instal·lades.");
            }
            if (!casa.getElectros().isEmpty()) {
                System.out.println("Aparells registrats: " + casa.getElectros().size());
            } else {
                System.out.println("No té cap aparell elèctric registrat.");
            }
        }
    }

    public static void info(String[] input) throws InstantiationException, UsoIncorrectoException, ErrorDeListaException {
        if (input.length != 2) {
            throw new UsoIncorrectoException(UsoIncorrectoException.USO_INFO);

        }
        noHayCasas();
        Casa casa = getCasa(input[1]);
        System.out.println("Client: " + casa.getNif() + " - " + casa.getNombre());
        System.out.println("Plaques solars instal·lades: " + casa.getPlacasInstaladas().size());
        System.out.println("Potencia total: " + casa.potenciaPlacas() + "W");
        System.out.println("Inversió total: " + casa.precioPlacas() + "€");
        System.out.println("Aparells registrats: " + casa.getElectros().size());
        System.out.println("Consum actual: " + casa.consumoElectros() + "W");

        if (!casa.getElectros().isEmpty()) {
            if (casa.consumoElectros() > 0) {
                System.out.println("Aparells encesos:");
                for (String electro : casa.listaElectrosOn()) {
                    System.out.println("\t- " + electro);
                }
            }
        }
    }

    public static void yaEstaEnLista(String nif) throws InstantiationException, ErrorDeListaException {
        for (Casa casa : casasClientes) {
            if (casa.getNif().equalsIgnoreCase(nif)) {
                throw new ErrorDeListaException(ErrorDeListaException.CASA_REGISTRADA);

            }
        }
    }

    public static Casa getCasa(String nif) throws InstantiationException, ErrorDeListaException {
        noHayCasas();
        for (Casa casa : casasClientes) {
            if (casa.getNif().equalsIgnoreCase(nif)) {
                return casa;
            }
        }
        throw new ErrorDeListaException(ErrorDeListaException.NO_EXISTE_CASA);

    }

    public static ArrayList<Casa> getCasasClientes() {
        return casasClientes;
    }

    public static void noHayCasas() throws InstantiationException, ErrorDeListaException {
        if (casasClientes.isEmpty()) {
            throw new ErrorDeListaException(ErrorDeListaException.NO_CASAS);

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        String comando;
        do {
            System.out.print("> ");
            String[] input = (consola.readLine()).split(" ");
            comando = input[0].toLowerCase();
            try {
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
                        offElectroMain(input);
                        break;
                    case "list":
                        list(input);
                        break;
                    case "info":
                        info(input);
                        break;
                    case "quit":
                        break;
                    default:
                        System.out.println(UsoIncorrectoException.COMANDA_INCORRECTA);
                        break;
                }
            } catch (InstantiationException | ErrorDeListaException | UsoIncorrectoException | ErrorValorException | ElectricException e) {
                System.out.println(e.getMessage());
            }
        } while (!comando.equalsIgnoreCase("quit"));
    }
}
