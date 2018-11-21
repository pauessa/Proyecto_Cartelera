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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cartelera;
import modelo.Cine;
import modelo.Pelicula;
import modelo.Sesion;
import vista.ProyectoCartelera;

/**
 *
 * @author Pauessa
 */
public class CarteleraDAO {

    public static void cargarJDBC(Cartelera carte) {
        Conexion_DB conexion_db = new Conexion_DB();
        System.out.println("abrir conexion");
        try {
            Connection con = conexion_db.AbrirConexion();

            System.out.println("conexion abierta");
            for (int j = 0; j < carte.getCines().size(); j++) {

                CineDAO cineDao = new CineDAO();
                Cine ci = new Cine(carte.getCines().get(j).getNombre(), carte.getCines().get(j).getDirecion(), carte.getCines().get(j).getMunicipio());
                cineDao.inserta(con, ci);

                ArrayList<Pelicula> listaPeliculas = carte.getCines().get(j).getPeliculas();

                for (int i = 0; i < listaPeliculas.size(); i++) {

                    PeliculaDAO peliDao = new PeliculaDAO();
                    Pelicula peli = new Pelicula(listaPeliculas.get(i).getTitulo(), listaPeliculas.get(i).getDirector(), listaPeliculas.get(i).getInterpretes(), listaPeliculas.get(i).getCategoria(), listaPeliculas.get(i).getDuracion(), listaPeliculas.get(i).getSinopsis());
                    peliDao.inserta(con, peli);

                    ArrayList<Sesion> listaSesiones = listaPeliculas.get(i).getSesiones();

                    for (int k = 0; k < listaSesiones.size(); k++) {

                        SesionDAO sesionDao = new SesionDAO();
                        Sesion sesion = new Sesion(j + 1, i + 1, listaSesiones.get(k).getHoras(), listaSesiones.get(k).getDia());
                        sesionDao.inserta(con, sesion);
                    }
                }

            }

            conexion_db.CerrarConexion(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoCartelera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoCartelera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cartelera cargarClases() {
        Cartelera c = new Cartelera();

        try {
            Conexion_DB conexion_db = new Conexion_DB();
            System.out.println("abrir conexion");
            Connection con = conexion_db.AbrirConexion();

            System.out.println("conexion abierta");
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement("SELECT * FROM cine");
                rs = stmt.executeQuery();
                while (rs.next()) {
                    Cine cine = new Cine();
                    obtenerCineFila(rs, cine);

                    stmt = con.prepareStatement("SELECT DISTINCT id_Pelicula from sesion where id_Cine=?");
                    stmt.setInt(1, cine.getId_cine());
                    ResultSet rs2 = stmt.executeQuery();

                    while (rs2.next()) {
                        Pelicula p = new Pelicula();
                        obtenerPeliId(rs2, p);
                        p=findByIdPelicula(con, p);
                        
                        stmt = con.prepareStatement("SELECT * from sesion where id_Cine=? and id_Pelicula=?");
                        stmt.setInt(1, cine.getId_cine());
                        stmt.setInt(2, p.getId());
                        ResultSet rs3 = stmt.executeQuery();
                         while (rs3.next()) {
                             Sesion s=new Sesion();
                             obtenerSesionFila(rs3,s);
                             p.getSesiones().add(s);
                         }
                        
                        
                        
                        cine.getPeliculas().add(p);
                    }

                    c.add(cine);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("problema al buscar por nombre " + ex.getMessage());
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            }

            conexion_db.CerrarConexion(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoCartelera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoCartelera.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    private void obtenerCineFila(ResultSet rs, Cine cine) throws Exception {
        cine.setId_cine(rs.getInt("id_Cine"));
        cine.setNombre(rs.getString("nombre_Cine"));
        cine.setDirecion(rs.getString("direcion_Cine"));
        cine.setMunicipio(rs.getString("municipio_Cine"));

    }

    private void obtenerPeliFila(ResultSet rs, Pelicula peli) throws Exception {
        peli.setId(rs.getInt("id_Pelicula"));
        peli.setTitulo(rs.getString("titulo_Pelicula"));
        peli.setDirector(rs.getString("director_Pelicula"));
        peli.setInterpretes(rs.getString("interprete_Pelicula"));
        peli.setCategoria(rs.getString("categoria_Pelicula"));
        peli.setDuracion(rs.getString("duracion_Pelicula"));
        peli.setSinopsis(rs.getString("sinopsis_Pelicula"));
    }
    
    
    private void obtenerPeliId(ResultSet rs, Pelicula peli) throws Exception {
        peli.setId(rs.getInt("id_Pelicula"));
        
    }
    
    public Pelicula findByIdPelicula(Connection con, Pelicula peli) throws Exception {
        //cliente = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM pelicula WHERE id_Pelicula=?");
            stmt.setInt(1, peli.getId());
            rs = stmt.executeQuery();

            while (rs.next()) {
                peli = new Pelicula();
                obtenerPeliFila(rs, peli);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("problema al buscar por titulo " + ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return peli;
    }

    private void obtenerSesionFila(ResultSet rs3, Sesion s) throws SQLException {
       s.setId(rs3.getInt("id_Sesion"));
        s.setDia(rs3.getString("dia_Sesion"));
        s.setHoras(rs3.getString("horas_Sesion"));
        s.setIdCine(rs3.getInt("id_Cine"));
        s.setIdPelicula(rs3.getInt("id_Pelicula"));
    }
    
    
}
