/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlCartelera;
import controlador.CtrlDom;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import modelo.Cartelera;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Pau
 */
public class ProyectoCartelera {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Scanner teclado = new Scanner(System.in);
        int op = 0;
        File f1 = new File("cine.xml");
        File f2 = new File("cine_final.xml");
        CtrlCartelera ctrlcarte = new CtrlCartelera();
        Document doc = null;
        Cartelera carte = null;
        CtrlDom ctrldom = new CtrlDom();        
        
        do {
            menu();
            op = teclado.nextInt();
            teclado.nextLine();
            switch (op) {
                case 1:
                    doc = ctrlcarte.recuperar(f1);
                    break;
                case 2:
                    carte = ctrlcarte.llegir(doc);
                    break;
                case 3:
                    System.out.println(carte);
                    break;
                case 4:
                    doc = ctrldom.deXmlaDoc();
                    ctrlcarte.escriure(doc);
                    break;
                case 5:
                    ctrlcarte.emmagatzemar(doc,f2);
                    break;
                
            }
        } while (op != 0);
        
    }
    
    public static void menu() {
        System.out.println("1.Recuperar");
        System.out.println("2.Leer");
        System.out.println("3.Mostrar");
        System.out.println("4.Escribir");
        System.out.println("5.Guardar");
        System.out.println("0.Salir");
        
    }
    
}


