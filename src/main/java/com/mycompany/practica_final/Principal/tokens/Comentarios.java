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
public class Comentarios {

    private int[][] matrizComentarios = new int[3][2];

    public Comentarios() {
        //matriz que manejara las transiciones
        //0 -> /
        //1 -> Cualquier caracter
        matrizComentarios[0][0] = 1;
        matrizComentarios[0][1] = -1;
        matrizComentarios[1][0] = 2;
        matrizComentarios[1][1] = -1;
        matrizComentarios[2][0] = 2;
        matrizComentarios[2][1] = 2;

    }

    //Verifica su alfabeto
    public int analizarAlfabeto(char caracter) {
        int respuesta = -1;
        if (caracter == '/') {
            respuesta = 0;
        } else {
            respuesta = 1;
        }
        return respuesta;
    }

    //Controla las transiciones en la matriz
    public int controlarTransiciones(int estadoActual, int caracterIngresado) {
        int siguienteEstado = -1;
        if (caracterIngresado >= 0 && caracterIngresado <= 1) {
            siguienteEstado = matrizComentarios[estadoActual][caracterIngresado];
        }
        return siguienteEstado;
    }
    
    //Determina si es un identificador o no
    public void esComentario(String palabra, int linea, int columna) {
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
        if (estadoActual == 2) {
            AlmacenadorInfo.agregarInformacion("Comentario", palabra, linea, columna);
        } else {
            if (posicionPalabra > 0) {
                AlmacenadorInfo.agregarInformacion("Error", palabra, linea, columna + posicionPalabra);
            }
        }

    }

}
