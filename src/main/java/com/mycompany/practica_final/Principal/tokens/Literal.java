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
public class Literal {
    
    private int[][] matrizLiteral = new int[4][5];

    public Literal() {
        //Matriz de transiciones para un literal
        //0 -> '"'
        //1 -> Cualquier letra
        //2 -> Cualquier digito
        //3 -> Cualquier Simbolo
        //4 -> Espacio en blanco
        matrizLiteral[0][0] = 1;
        matrizLiteral[0][1] = -1;
        matrizLiteral[0][2] = -1;
        matrizLiteral[0][3] = -1;
        matrizLiteral[0][4] = -1;
        matrizLiteral[1][0] = -1;
        matrizLiteral[1][1] = 2;
        matrizLiteral[1][2] = 2;
        matrizLiteral[1][3] = 2;
        matrizLiteral[1][4] = 2;
        matrizLiteral[2][0] = 3;
        matrizLiteral[2][1] = 2;
        matrizLiteral[2][2] = 2;
        matrizLiteral[2][3] = 2;
        matrizLiteral[2][4] = 2;
        matrizLiteral[3][0] = -1;
        matrizLiteral[3][1] = -1;
        matrizLiteral[3][2] = -1;
        matrizLiteral[3][3] = -1;
        matrizLiteral[3][4] = -1;
    }
    
    public boolean esSimbolo(char caracter){
        boolean esSim = false;
        if (caracter == '+' | caracter == '-') {
            esSim = true;
        } else if (caracter == '*' | caracter == '/') {
            esSim = true;
        } else if (caracter == '%') {
            esSim = true;
        } else  if (caracter == '.' | caracter == ',') {
            esSim = true;
        } else if (caracter == ':' | caracter == ';') {
            esSim = true;
        } else if (caracter == '{' | caracter == '}') {
            esSim = true;
        } else if (caracter == '(' | caracter == ')') {
            esSim = true;
        } else if (caracter == '[' | caracter == ']') {
            esSim = true;
        } else if (caracter == '\'' | caracter == '<') {
            esSim = true;
        } else if (caracter == '>' | caracter == '=') {
            esSim = true;
        }
        return esSim;
    }
    

    //Verifica el alfabeto
    public int analizarAlfabeto(char caracterIngresado) {
        int respuesta = -1;
        if (caracterIngresado == '"') {
            respuesta = 0;
        } else if (Character.isLetter(caracterIngresado)) {
            respuesta = 1;
        } else if (Character.isDigit(caracterIngresado)) {
            respuesta = 2;
        } else if (this.esSimbolo(caracterIngresado)) {
            respuesta = 3;
        } else if (Character.isSpaceChar(caracterIngresado)) {
            respuesta = 4;
        }
        return respuesta;
    }
    
     //Controla las transiciones en la matriz
    public int controlarTransiciones(int estadoActual, int caracterIngresado) {
        int siguienteEstado = -1;
        if (caracterIngresado >= 0 && caracterIngresado <= 4) {
            siguienteEstado = matrizLiteral[estadoActual][caracterIngresado];
        }
        return siguienteEstado;
    }
    
    
    //Determina si es un identificador o no
    public void esLiteral(String palabra, int linea, int columna) {
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
        if (estadoActual == 3) {
            AlmacenadorInfo.agregarInformacion("Literal", this.quitarComillas(palabra), linea, columna);
        } else {
            if (posicionPalabra > 0) {
                AlmacenadorInfo.agregarInformacion("Error", palabra, linea, columna);
            }
        }
        
    }
    
    public String quitarComillas(String palabra){
        String resultado = "";
        for (int i = 1; i < palabra.length()-1; i++) {
            char temp = palabra.charAt(i);
            resultado += temp;
        }
        return resultado;
    }
    
}
