/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica_final.Principal.interfaces;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author nroda
 */
public class Copiar_Pegar extends JFrame implements ClipboardOwner {

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void copiarTexto(String copiar){
        StringSelection copy = new StringSelection(copiar);
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        cb.setContents(copy, null);
    }
    
    public String pegarTexto(){
        String resultado = "";
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable textoPegar = cb.getContents(null);
        if (textoPegar.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                resultado = (String)textoPegar.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException ex) {
                Logger.getLogger(Copiar_Pegar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Copiar_Pegar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }
    
}
