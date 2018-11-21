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

/**
 *
 * @author Pauessa
 */
public class CineDAO {

    public void actualiza(Connection con, Cine ci) throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cine SET nombre_Cine=?, direcion_Cine=?,municipio_Cine=? WHERE id_Cine=?");
            stmt.setString(1, ci.getNombre());
            stmt.setString(2, ci.getDirecion());
            stmt.setString(3, ci.getMunicipio());
            stmt.setInt(4, ci.getId_cine());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Problemas al actualizar el cine " + ex.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }

    public void elimina(Connection con, Cine ci) throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM cine WHERE id_Cine=?");
            stmt.setInt(1, ci.getId_cine());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("problemas al insertar el cine " + ex.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void inserta(Connection con, Cine ci) throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cine (nombre_Cine, direcion_Cine,municipio_Cine) VALUES(?,?,?)");
            stmt.setString(1, ci.getNombre());
            stmt.setString(2, ci.getDirecion());
            stmt.setString(3, ci.getMunicipio());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("problemas al insertar el cine " + ex.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

}
