/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.TransformerException;
import modelo.Cartelera;
import modelo.Cine;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Pauessa
 */
public class CtrlCartelera extends CtrlDom {
    
    static final String ET_CARTELERA = "cartelera";
    File file = null;
    Cartelera cartelera = null;
    
    public CtrlCartelera() {
        this.cartelera = new Cartelera();
    }
    
    public CtrlCartelera(Cartelera cartelera) {
        this.cartelera = cartelera;
    }
    
    public CtrlCartelera(Cartelera cartelera, File f) {
        this.cartelera = cartelera;
        this.file = f;
    }
    
    public void recuperar() throws ParserConfigurationException, SAXException, IOException {
        Document doc = null;
        doc = deXmlaDoc(file);
    }
    
    public Document recuperar(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
        Document doc = null;
        doc = deXmlaDoc(xmlFile);
        return doc;
    }
    
    public Cartelera llegir(Document doc) {
        Element laCar = doc.getDocumentElement();
        NodeList listaCines = laCar.getChildNodes();
        cartelera.clear();
        for (int i = 0; i < listaCines.getLength(); i++) {
            if (listaCines.item(i).getNodeType() == Node.ELEMENT_NODE) {
                cartelera.add(CtrlCine.llegirCine((Element) listaCines.item(i)));
                
            }
        }
        return cartelera;
    }
    
    public void escriure(Document doc) {
        
        Element arrelCartelera = doc.createElement(ET_CARTELERA);
        for (Cine c : cartelera) {       
            CtrlCine.escriure(c, arrelCartelera, doc);
        }
         doc.appendChild(arrelCartelera);
    }
    
    public void emmagatzemar() throws ParserConfigurationException, TransformerException {
        Document doc = null;
        doc = deXmlaDoc();
        escriure(doc);
        deDocaXml(doc, file);
    }
    
    public void emmagatzemar(Document doc, File file) throws TransformerException {
        deDocaXml(doc, file);
    }
    
    public File getFile() {
        return file;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public Cartelera getcartelera() {
        return cartelera;
    }
    
    public void setbk(Cartelera cartelera) {
        this.cartelera = cartelera;
    }
    
}
