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
public class Reservadas {

    private String[] palabrasReservadas = new String[8];
    Identificadores id = new Identificadores();
    public Reservadas() {
        //Crea un array de palabras reservadas
        palabrasReservadas[0] = "ESCRIBIR";
        palabrasReservadas[1] = "FIN";
        palabrasReservadas[2] = "REPETIR";
        palabrasReservadas[3] = "INICIAR";
        palabrasReservadas[4] = "SI";
        palabrasReservadas[5] = "VERDADERO";
        palabrasReservadas[6] = "FALSO";
        palabrasReservadas[7] = "ENTONCES";
    }

    public boolean esPalabraReservada(String palabra, int linea, int columna) {
        boolean esReservada = false;
        for (int i = 0; i < palabrasReservadas.length; i++) {
            if (palabra.equals(palabrasReservadas[i])) {
                esReservada = true;
                break;
            }
        }
        if (esReservada) {
            AlmacenadorInfo.agregarInformacion("Palabra reservada", palabra, linea, columna);
        }
        return esReservada;
    }

}
