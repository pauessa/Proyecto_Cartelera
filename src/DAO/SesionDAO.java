/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cine;
import modelo.Pelicula;
import modelo.Sesion;

/**
 *
 * @author Pauessa
 */
public class SesionDAO {

    public void actualiza(Connection con, Sesion sesion) throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE sesion SET horas_Sesion=?, dia_Sesio=?,id_Cine=?,id_Pelicula=? WHERE id_Sesion=?");
            stmt.setString(1, sesion.getHoras());
            stmt.setString(2, sesion.getDia());
            stmt.setInt(3, sesion.getIdCine());
            stmt.setInt(4, sesion.getIdPelicula());
            stmt.setInt(5, sesion.getId());
          

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Problemas al actualizar la pelicula " + ex.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }

    public void elimina(Connection con, Sesion sesion) throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM sesion WHERE id_Sesion=?");
            stmt.setInt(1, sesion.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("problemas al insertar la sesion " + ex.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void inserta(Connection con, Sesion sesion) throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO sesion (horas_Sesion,dia_Sesion,id_Cine,id_Pelicula) VALUES(?,?,?,?)");
           stmt.setString(1, sesion.getHoras());
            stmt.setString(2, sesion.getDia());
            stmt.setInt(3, sesion.getIdCine());
            stmt.setInt(4, sesion.getIdPelicula());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("problemas al insertar la sesion " + ex.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

}
