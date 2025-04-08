/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.Compra;
import Util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell Notebook
 */
public class CompraDAO {
    public List<Compra> leerTodasCompras() throws SQLException {
        String sql = "SELECT * FROM Compras";
        List<Compra> compras = new ArrayList<>();

        try (Connection c = ConexionDB.getConnection();
             PreparedStatement stmt = c.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt("id_compra"));
                compra.setIdEmpleado(rs.getInt("id_empleado"));
                compra.setFechaCompra(rs.getDate("fecha_compra"));
                compra.setTotalCompra(rs.getFloat("total_compra"));
                compras.add(compra);
            }
        }
        return compras;
    }

    public static void main(String[] args) {
        try {
            CompraDAO dao = new CompraDAO();
            List<Compra> compras = dao.leerTodasCompras();
            System.out.println("Lista de compras:");
            for (Compra comp : compras) {
                System.out.println("ID: " + comp.getIdCompra() + 
                                 ", Empleado ID: " + comp.getIdEmpleado() + 
                                 ", Fecha: " + comp.getFechaCompra() + 
                                 ", Total: " + comp.getTotalCompra());
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

