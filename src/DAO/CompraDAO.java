/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Modelo.Compra;
import Util.ConnectionDB;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiantes
 */
public class CompraDAO {

    public void crearCompra(Compra compra) throws SQLException {
        String sql = """
        INSERT INTO Compras (
            id_empleado, 
            fecha_compra, 
            total_compra
        ) VALUES (?, ?, ?)""";

        try (Connection c = ConnectionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, compra.getIdEmpleado());
            stmt.setDate(2, new java.sql.Date(compra.getFechaCompra().getTime()));
            stmt.setFloat(3, compra.getTotalCompra());
            stmt.executeUpdate();
        }
    }

    public List<Compra> leerTodasCompras() throws SQLException {
        String sql = "SELECT * FROM Compras";
        List<Compra> compras = new ArrayList<>();

        try (Connection c = ConnectionDB.getConnection(); PreparedStatement stmt = c.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
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

// Métodos para Actualizar y Eliminar
// Método para actualizar una compra
public void actualizarCompra(Compra compra) throws SQLException {
    String sql = "UPDATE Compras SET id_empleado = ?, fecha_compra = ?, total_compra = ? WHERE id_compra = ?";
    
    try (Connection c = ConnectionDB.getConnection();
         PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setInt(1, compra.getIdEmpleado());
        stmt.setDate(2, new java.sql.Date(compra.getFechaCompra().getTime()));
        stmt.setFloat(3, compra.getTotalCompra());
        stmt.setInt(4, compra.getIdCompra());
        stmt.executeUpdate();
    }
}

// Método para eliminar una compra
public void eliminarCompra(int idCompra) throws SQLException {
    String sql = "DELETE FROM Compras WHERE id_compra = ?";
    
    try (Connection c = ConnectionDB.getConnection();
         PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setInt(1, idCompra);
        stmt.executeUpdate();
    }
}
    public static void main(String[] args) {
        try {
            // Actualizar una compra
        Compra compra = new Compra();
        compra.setIdCompra(1); // ID existente
        compra.setIdEmpleado(2);
        compra.setFechaCompra(new java.util.Date());
        compra.setTotalCompra(1500.50f);
        dao.actualizarCompra(compra);
        System.out.println("Compra actualizada.");
        
        // Eliminar una compra
        dao.eliminarCompra(2); // ID a eliminar
        System.out.println("Compra eliminada.");
        
        // Leer y mostrar todas las compras para verificar
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
            CompraDAO dao = new CompraDAO();
            List<Compra> compras = dao.leerTodasCompras();
            System.out.println("Lista de compras:");
            for (Compra comp : compras) {
                System.out.println("ID: " + comp.getIdCompra()
                        + ", Empleado ID: " + comp.getIdEmpleado()
                        + ", Fecha: " + comp.getFechaCompra()
                        + ", Total: " + comp.getTotalCompra());
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

