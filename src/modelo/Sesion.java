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

    private String horas;
    private String dia;

    public Sesion(String horas, String dia) {
        this.horas = horas;
        this.dia = dia;
    }

    public Sesion() {

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
