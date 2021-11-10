/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica_final.Principal.tokens;

import com.mycompany.practica_final.Principal.principal.AlmacenadorInfo;

/**
 *
 * @author nroda
 */
public class Identificadores {
    
    private int[][] matrizId = new int[3][4];

    public Identificadores() {
        //Creacion de matriz para realizar transiciones
        // 0 -> Guion Bajo
        // 1 -> Cualquier Letra
        // 2 -> Cualquier Numero
        // 3 -> Guion Medio
        matrizId[0][0] = 1;
        matrizId[0][1] = 1;
        matrizId[0][2] = -1;
        matrizId[0][3] = -1;
        matrizId[1][0] = 2;
        matrizId[1][1] = 2;
        matrizId[1][2] = 2;
        matrizId[1][3] = 2;
        matrizId[2][0] = 2;
        matrizId[2][1] = 2;
        matrizId[2][2] = 2;
        matrizId[2][3] = 2;
        
    }
    
    //Verifica el alfabeto
    public int analizarAlfabeto(char caracter) {
        int respuesta = -1;
        if (caracter == '_') {
            respuesta = 0;
        } else if (Character.isLetter(caracter)) {
            respuesta = 1;
        } else if (Character.isDigit(caracter)) {
            respuesta = 2;
        } else if (caracter == '-') {
            respuesta = 3;
        }
        return respuesta;
    }
    
    //Controla las transiciones en la matriz
    public int controlarTransiciones(int estadoActual, int caracterIngresado) {
        int siguienteEstado = -1;
        if (caracterIngresado >= 0 && caracterIngresado <= 3) {
            siguienteEstado = matrizId[estadoActual][caracterIngresado];
        }
        return siguienteEstado;
    }
    
    //Determina si es un identificador o no
    public void esId(String palabra, int linea, int columna) {
        int estadoActual = 0;
        int posicionPalabra = 0;
        while (posicionPalabra < palabra.length()) {
            char temporal = palabra.charAt(posicionPalabra);
            int estadoTemporal = controlarTransiciones(estadoActual, analizarAlfabeto(temporal));
            estadoActual = estadoTemporal;
            if (estadoActual == -1) {
                break;
            }
            posicionPalabra++;
        }
        if (estadoActual == 2 | estadoActual == 1) {
            AlmacenadorInfo.agregarInformacion("identificador", palabra, linea, columna);
        } else {
            if (posicionPalabra > 0) {
                AlmacenadorInfo.agregarInformacion("Error", palabra, linea, columna + posicionPalabra);
            }
        }

    }
    
}
