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
public class Numeros {

    private final int[][] matrizNumeros = new int[3][3];

    public Numeros() {
        //Crea la matriz para las transiciones 
        // 0 -> Signo "+"
        // 1 -> Signo "-"
        // 2 -> Cualquier numero
        matrizNumeros[0][0] = 1;
        matrizNumeros[0][1] = 1;
        matrizNumeros[0][2] = 2;
        matrizNumeros[1][0] = -1;
        matrizNumeros[1][1] = -1;
        matrizNumeros[1][2] = 2;
        matrizNumeros[2][0] = -1;
        matrizNumeros[2][1] = -1;
        matrizNumeros[2][2] = 2;
    }

    //Verifica su alfabeto
    public int analizarAlfabeto(char caracter) {
        int respuesta = -1;
        if (caracter == '+') {
            respuesta = 0;
        } else if (caracter == '-') {
            respuesta = 1;
        } else if (Character.isDigit(caracter)) {
            respuesta = 2;
        }
        return respuesta;
    }

    //Controla las transicion de la palabra
    public int controlarTransiciones(int estadoActual, int caracterIngresado) {
        int estadoSiguiente = -1;
        if (caracterIngresado >= 0 && caracterIngresado <= 2) {
            estadoSiguiente = matrizNumeros[estadoActual][caracterIngresado];
        }
        return estadoSiguiente;
    }

    //Determina si es un tpken de tipo entero o decimal o no lo es
    public void determinarToken(String palabra, int linea, int columna) {
        int posicionPalabra = 0;
        String palabraError = "";
        int estadoActual = 0;
        while (posicionPalabra < palabra.length()) {
            char temporal = palabra.charAt(posicionPalabra);
            palabraError += temporal;
            int estadoTemporal = controlarTransiciones(estadoActual, analizarAlfabeto(temporal));
            estadoActual = estadoTemporal;
            if (estadoActual == -1) {
                break;
            }
            posicionPalabra++;
        }
        if (estadoActual == 2) {
            AlmacenadorInfo.agregarInformacion("Entero", palabra, linea, columna);
        } else {
            if (posicionPalabra > 0) {
                AlmacenadorInfo.agregarInformacion("Error", palabra, linea, columna + posicionPalabra);
            }
        }
    }

}
