/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Pauessa
 */
public class Sesion {
    private int id,idCine,idPelicula;
    private String horas;
    private String dia;

    public Sesion(int idCine, int idPelicula, String horas, String dia) {
        this.idCine = idCine;
        this.idPelicula = idPelicula;
        this.horas = horas;
        this.dia = dia;
    }

   

    public Sesion() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCine() {
        return idCine;
    }

    public void setIdCine(int idCine) {
        this.idCine = idCine;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "Sesion{" + "horas=" + horas + ", dia=" + dia + '}';
    }
    

}
