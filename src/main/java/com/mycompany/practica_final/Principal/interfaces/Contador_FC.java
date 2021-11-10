/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica_final.Principal.interfaces;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author nroda
 */
public class Contador_FC extends JFrame {

    private JTextArea cuadro;
    private JLabel posicion;

    public Contador_FC(JTextArea cuadro, JLabel posicion) {
        this.cuadro = cuadro;
        this.posicion = posicion;
        cuadro.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                JTextArea editArea = (JTextArea) e.getSource();

                int linea = 1;
                int columna = 1;

                try {
                    int caretpos = editArea.getCaretPosition();
                    linea = editArea.getLineOfOffset(caretpos);
                    columna = caretpos - editArea.getLineStartOffset(linea)+1; //Agrefamos uno para que el contador inicie desde 0

                    // Ya que las líneas las cuenta desde la 0
                    linea += 1;
                } catch (Exception ex) {
                }

                // Actualizamos el estado
                actualizarEstado(linea, columna);
            }
        });
    }

    private void actualizarEstado(int linea, int columna) {
        posicion.setText("Linea: " + linea + ", Columna: " + columna);
    }

}
