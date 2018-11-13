/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.*;

import java.awt.Color;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Pauessa
 */
public class CtrlCine extends CtrlDom {

    private static final String ET_CINE = "cine";
    private static final String ET_PELICULA = "pelicula";
    private static final String ET_SINOPSIS = "sinopsis";
    private static final String ET_SECIONES = "sesiones";
    private static final String ET_SECION = "sesion";
    private static final String AT_MUNICIPIO = "municipio";
    private static final String AT_DIRECION = "direccion";
    private static final String AT_NOMBRE = "nombre";
    private static final String AT_DURACION = "duracion";
    private static final String AT_CATEGORIA = "categoria";
    private static final String AT_INTERPRETES = "interpretes";
    private static final String AT_DIRECTOR = "director";
    private static final String AT_TITULO = "titulo";
    private static final String AT_HORAS = "horas";
    private static final String AT_DIA = "dia";

    public static Cine llegirCine(Element eleCine) {
        Cine cine = new Cine();

        cine.setNombre(getAtributoEtiqueta(eleCine, AT_NOMBRE));
        cine.setDirecion(getAtributoEtiqueta(eleCine, AT_DIRECION));
        cine.setMunicipio(getAtributoEtiqueta(eleCine, AT_MUNICIPIO));

        cine.setPeliculas(leerPeliculas(eleCine));

        return cine;
    }

    private static ArrayList<Pelicula> leerPeliculas(Element eleCine) {
        NodeList nListaPelicuas = eleCine.getChildNodes();
        ArrayList<Pelicula> listaPeliculas = new ArrayList<Pelicula>();

        for (int i = 0; i < nListaPelicuas.getLength(); i++) {
            if (nListaPelicuas.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Pelicula p = new Pelicula();
                p.setTitulo(getAtributoEtiqueta((Element) nListaPelicuas.item(i), AT_TITULO));
                p.setDirector(getAtributoEtiqueta((Element) nListaPelicuas.item(i), AT_DIRECTOR));
                p.setInterpretes(getAtributoEtiqueta((Element) nListaPelicuas.item(i), AT_INTERPRETES));
                p.setCategoria(getAtributoEtiqueta((Element) nListaPelicuas.item(i), AT_CATEGORIA));
                p.setDuracion(getAtributoEtiqueta((Element) nListaPelicuas.item(i), AT_DURACION));
                p.setSinopsis(getValorEtiqueta(ET_SINOPSIS, (Element) nListaPelicuas.item(i)));

                p.setSesiones(leerSesiones(getElementEtiqueta(ET_SECIONES,(Element) nListaPelicuas.item(i))));

                listaPeliculas.add(p);
            }

        }

        return listaPeliculas;
    }

    private static ArrayList<Sesion> leerSesiones(Element eleSesiones) {
        NodeList nListaSesiones = eleSesiones.getChildNodes();
      
        ArrayList<Sesion> listaSesiones = new ArrayList<Sesion>();
        for (int i = 0; i < nListaSesiones.getLength(); i++) {
            if (nListaSesiones.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Sesion s = new Sesion();
                s.setDia(getAtributoEtiqueta((Element) nListaSesiones.item(i), AT_DIA));
                s.setHoras(getAtributoEtiqueta((Element) nListaSesiones.item(i), AT_HORAS));
               
                listaSesiones.add(s);
            }
        }
        return listaSesiones;
    }

    static void escriure(Cine c, Element arrelCartelera, Document doc) {
        Element eleCine = doc.createElement(ET_CINE);
        eleCine.setAttribute(AT_NOMBRE, c.getNombre());
        eleCine.setAttribute(AT_DIRECION, c.getDirecion());
        eleCine.setAttribute(AT_MUNICIPIO, c.getMunicipio());
        escriurePeliculas(c, eleCine, doc);

        arrelCartelera.appendChild(eleCine);
    }

    private static void escriurePeliculas(Cine c, Element eleCine, Document doc) {
        ArrayList<Pelicula> listaPeliculas = c.getPeliculas();

        for (Pelicula p : listaPeliculas) {
            Element elePelicula = doc.createElement(ET_PELICULA);
            elePelicula.setAttribute(AT_TITULO, p.getTitulo());
            elePelicula.setAttribute(AT_DIRECTOR, p.getDirector());
            elePelicula.setAttribute(AT_INTERPRETES, p.getInterpretes());
            elePelicula.setAttribute(AT_CATEGORIA, p.getCategoria());
            elePelicula.setAttribute(AT_DURACION, p.getDuracion());
            Element eleSinopsis = doc.createElement(ET_SINOPSIS);
            eleSinopsis.setTextContent(p.getSinopsis());
            elePelicula.appendChild(eleSinopsis);
            Element eleSesiones = doc.createElement(ET_SECIONES);
            escriureSesiones(p, eleSesiones, doc);
            elePelicula.appendChild(eleSesiones);
            eleCine.appendChild(elePelicula);
        }

    }

    private static void escriureSesiones(Pelicula p, Element eleSesiones, Document doc) {
        ArrayList<Sesion> listaSesiones = p.getSesiones();

        for (Sesion s : listaSesiones) {
            Element eleSesion = doc.createElement(ET_SECION);
            eleSesion.setAttribute(AT_DIA, s.getDia());
            eleSesion.setAttribute(AT_HORAS, s.getHoras());
            eleSesiones.appendChild(eleSesion);

        }

    }
}
