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

/**
 *
 * @author Pauessa
 */
public class PeliculaDAO {

    public void actualiza(Connection con, Pelicula peli) throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE pelicula SET titulo_Pelicula=?, director_Pelicula=?,interprete_Pelicula=?,categoria_Pelicula=?,duracion_Pelicula=?,sinopsis_Pelicula=? WHERE id_Pelicula=?");
            stmt.setString(1, peli.getTitulo());
            stmt.setString(2, peli.getDirector());
            stmt.setString(3, peli.getInterpretes());
            stmt.setString(4, peli.getCategoria());
            stmt.setString(5, peli.getDuracion());
            stmt.setString(6, peli.getSinopsis());
            stmt.setInt(7, peli.getId());

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

    public void elimina(Connection con, Pelicula peli) throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM pelicula WHERE id_Pelicula=?");
            stmt.setInt(1, peli.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("problemas al insertar la pelicula " + ex.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void inserta(Connection con, Pelicula peli) throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO pelicula (titulo_Pelicula,director_Pelicula,interprete_Pelicula,categoria_Pelicula,duracion_Pelicula,sinopsis_Pelicula) VALUES(?,?,?,?,?,?)");
            stmt.setString(1, peli.getTitulo());
            stmt.setString(2, peli.getDirector());
            stmt.setString(3, peli.getInterpretes());
            stmt.setString(4, peli.getCategoria());
            stmt.setString(5, peli.getDuracion());
            stmt.setString(6, peli.getSinopsis());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("problemas al insertar la plicula " + ex.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

}
