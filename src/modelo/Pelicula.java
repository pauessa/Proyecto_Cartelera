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
public class Pelicula {

    private String titulo;
    private String director;
    private String interpretes;
    private String categoria;
    private String duracion;
    private String sinopsis;
    private ArrayList<Sesion> sesiones=new ArrayList<Sesion>();

    public Pelicula(String titulo, String director, String interpretes, String categoria, String duracion) {
        this.titulo = titulo;
        this.director = director;
        this.interpretes = interpretes;
        this.categoria = categoria;
        this.duracion = duracion;
    }
    
    public Pelicula(){
        
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getInterpretes() {
        return interpretes;
    }

    public void setInterpretes(String interpretes) {
        this.interpretes = interpretes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public ArrayList<Sesion> getSesiones() {
        return sesiones;
    }

    public void setSesiones(ArrayList<Sesion> sesiones) {
        this.sesiones = sesiones;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "titulo=" + titulo + ", director=" + director + ", interpretes=" + interpretes + ", categoria=" + categoria + ", duracion=" + duracion + ", sinopsis=" + sinopsis + ", sesiones=" + sesiones + '}';
    }
    

}
