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
public class Cartelera extends ArrayList<Cine> {

    public ArrayList<Cine> getCines() {
        return this;
    }

    @Override
    public String toString() {
        String result = "+";
        for (int i = 0; i < this.getCines().size(); i++) {
            result += "\n" + this.getCines().get(i).toString();
        }
        return result;

    }

    
}
