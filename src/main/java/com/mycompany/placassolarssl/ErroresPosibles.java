/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.placassolarssl;

/**
 *
 * @author Erik
 */
public class ErroresPosibles {
    public static final String SUPERF_INSUF = "ERROR: Superfície incorrecta. Ha de ser més gran de 0.";
    public static final String PRECIO_INSUF = "ERROR: Preu incorrecte. Ha de ser més gran que 0.";
    public static final String POTENC_INSUF = "ERROR: Potència incorrecta. Ha de ser més gran de 0.";
    public static final String ESPACIO_TEJADO_INSUF = "ERROR: No hi ha espai disponible per a instal·lar aquesta placa.";
    public static final String PARAMETROS_INSUF = "ERROR: Número de paràmetres incorrecte.";
    public static final String SUPERF_TEJADO10 = "ERROR: Superficie incorrecta. Ha de ser més gran de 10.";
    public static final String USO_ADDPLACA = "Ús: addPlaca [nif] [superficie] [preu] [potencia]";
    public static final String USO_ADDCASA = "Ús: addCasa [nif] [nom] [superficie]";
    public static final String USO_ADDELECTRO = "Ús: addAparell [nif] [descripció] [potència]";
    public static final String USO_ONCASA = "Uso: onCasa [nif]";
    public static final String USO_ONELECTRO = "Ús: onAparell [nif] [descripció aparell]";
    public static final String USO_OFFELECTRO = "Ús: offAparell [nif] [descripció aparell]";
    public static final String USO_INFO = "Ús: info [nif]";
    public static final String USO_LIST = "Ús: list";
    public static final String NO_CASAS = "No hi ha cases registrades.";
    public static final String NO_EXISTE_CASA = "ERROR: No hi ha cap casa registrada amb aquest nif.";
    public static final String CASA_REGISTRADA = "ERROR: Ja hi ha una casa registrada amb aquest nif";
    public static final String CASA_YA_ON = "ERROR: La casa ja té l'interruptor encès.";
    public static final String CASA_APAGADA = "No es pot encendre l'aparell. L'interruptor general està apagat.";
    public static final String PLOMOS_DOWN = "ERROR: Han saltat els ploms. La casa ha quedat completament apagada.";
    public static final String ELECTRO_REGISTRADO = "ERROR: Ja existeix un aparell amb aquesta descripció a la casa indicada.";
    public static final String ELECTRO_YA_APAGADO = "ERROR: L'aparell ja està apagat.";
    public static final String ELECTRO_YA_ENCENDIDO = "ERROR: L'aparell ja està encès.";
    public static final String ELECTRO_NO_REGISTRADO = "ERROR: No hi ha cap aparell registrat amb aquesta descripció a la casa indicada.";
    public static final String COMANDA_INCORRECTA = "ERROR: Comanda incorrecta.";
}
