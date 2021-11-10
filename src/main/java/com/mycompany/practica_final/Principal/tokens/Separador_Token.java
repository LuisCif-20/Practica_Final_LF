/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica_final.Principal.tokens;

/**
 *
 * @author nroda
 */
public class Separador_Token {

    private int posicion = 0;
    private int conteoLinea = 1;
    int contadorColumna = 0;
    private int posicionEspacio = 0;
    private String textoParaAnalizar;
    Comentarios comentarios;
    Numeros enteros;
    Identificadores id;
    Reservadas rs;
    Literal lit;
    Simbolos simbolos;

    public Separador_Token(String textoParaAnalizar) {
        comentarios = new Comentarios();
        enteros = new Numeros();
        id = new Identificadores();
        lit = new Literal();
        rs = new Reservadas();
        simbolos = new Simbolos();
        this.textoParaAnalizar = textoParaAnalizar;
    }

    //Recorre el texto y lo separa con cada espacio
    public void separarTexto() {
        char templ = ' ';
        int LineaTemporal = conteoLinea;
        String palabraTemporal = "";
        boolean seguirLeyendo = true;
        boolean reiniciarContador = false;
        while (seguirLeyendo & posicion < textoParaAnalizar.length()) {
            contadorColumna++;
            char temporal = this.textoParaAnalizar.charAt(posicion);
            char temporal1 = ' ';
            if (posicion < textoParaAnalizar.length() - 1) {
                temporal1 = this.textoParaAnalizar.charAt(posicion + 1);
            }
            if (temporal == '"' & posicionEspacio == posicion) {
                boolean esLit = true;
                palabraTemporal += temporal;
                while (esLit & posicion < this.textoParaAnalizar.length()) {
                    posicion++;
                    temporal = this.textoParaAnalizar.charAt(posicion);
                    if (temporal != '"' & temporal != '\n') {
                        palabraTemporal += temporal;
                    } else {
                        if (temporal == '"') {
                            palabraTemporal += temporal;
                            posicionEspacio = posicion + 1;
                        }
                        seguirLeyendo = false;
                        esLit = false;
                    }
                }
            } else if (Character.isWhitespace(temporal)) {
                if (temporal == '\n') {
                    conteoLinea++;
                    reiniciarContador = true;
                }
                posicionEspacio = posicion + 1;
                seguirLeyendo = false;
            } else if (posicionEspacio == posicion & this.esCom(temporal, temporal1)) {
                palabraTemporal += temporal;
                while (seguirLeyendo & posicion < this.textoParaAnalizar.length()) {
                    posicion++;
                    char temp = this.textoParaAnalizar.charAt(posicion);
                    if (temp != '\n') {
                        palabraTemporal += temp;
                    } else {
                        posicion--;
                        seguirLeyendo = false;
                    }
                }
                seguirLeyendo = false;
            } else {
                if (posicionEspacio == this.posicion && simbolos.esSimbolo(temporal, LineaTemporal, contadorColumna)) {
                    posicionEspacio = posicion + 1;
                    seguirLeyendo = false;
                } else {
                    palabraTemporal += temporal;
                }
            }

            posicion++;
            templ = temporal;
        }

        if (posicion == this.textoParaAnalizar.length()) {
            contadorColumna++;
        }
        
        if (palabraTemporal != "") {
            comentarios.esComentario(palabraTemporal, LineaTemporal, contadorColumna);
            enteros.determinarToken(palabraTemporal, LineaTemporal, contadorColumna - palabraTemporal.length());
            boolean esrs = rs.esPalabraReservada(palabraTemporal, LineaTemporal, contadorColumna - palabraTemporal.length());
            if (!esrs) {
                id.esId(palabraTemporal, LineaTemporal, contadorColumna - palabraTemporal.length());
            }
            lit.esLiteral(palabraTemporal, LineaTemporal, contadorColumna);
        }
        
        if (templ == '"') {
                contadorColumna = contadorColumna + palabraTemporal.length()-1;
            }

        if (reiniciarContador) {
            contadorColumna = 0;
        }
    }

    //Valida si empieza como comentario
    public boolean esCom(char caracter, char caracter1) {
        boolean loEs = false;
        if (caracter == '/' & caracter1 == '/') {
            loEs = true;
        }
        return loEs;
    }
    //Getters y Setters

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getConteoLinea() {
        return conteoLinea;
    }

    public void setConteoLinea(int conteoLinea) {
        this.conteoLinea = conteoLinea;
    }

    public int getContadorColumna() {
        return contadorColumna;
    }

    public void setContadorColumna(int contadorColumna) {
        this.contadorColumna = contadorColumna;
    }

    public int getPosicionEspacio() {
        return posicionEspacio;
    }

    public void setPosicionEspacio(int posicionEspacio) {
        this.posicionEspacio = posicionEspacio;
    }

    public String getTextoParaAnalizar() {
        return textoParaAnalizar;
    }

    public void setTextoParaAnalizar(String textoParaAnalizar) {
        this.textoParaAnalizar = textoParaAnalizar;
    }

}
