/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Pauessa
 */
public class Cine {
    private String nombre;
    private String direcion;
    private String municipio;
    private ArrayList<Pelicula> peliculas=new ArrayList<Pelicula>();;
   


    public Cine(String nombre, String direcion, String municipio) {
        this.nombre = nombre;
        this.direcion = direcion;
        this.municipio = municipio;

    }

    public Cine() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public String toString() {
        return "Cine{" + "nombre=" + nombre + ", direcion=" + direcion + ", municipio=" + municipio + ", peliculas=" + peliculas + '}';
    }

    
   
   
}
