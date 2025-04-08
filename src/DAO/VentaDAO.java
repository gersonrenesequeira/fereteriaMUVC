/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.Venta;
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
   public class VentaDAO {
    public List<Venta> leerTodasVentas() throws SQLException {
        String sql = "SELECT * FROM Ventas";
        List<Venta> ventas = new ArrayList<>();

        try (Connection c = ConexionDB.getConnection();
             PreparedStatement stmt = c.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdCliente(rs.getInt("id_cliente"));
                venta.setIdEmpleado(rs.getInt("id_empleado"));
                venta.setFechaVenta(rs.getTimestamp("fecha_venta"));
                venta.setTotalVenta(rs.getFloat("total_venta"));
                ventas.add(venta);
            }
        }
        return ventas;
    }

   public static void main(String[] args) {
        try {
            VentaDAO dao = new VentaDAO();
            List<Venta> ventas = dao.leerTodasVentas();
            System.out.println("Lista de ventas:");
            for (Venta ven : ventas) {
                System.out.println("Cliente ID: " + ven.getIdCliente() + 
                                 ", Empleado ID: " + ven.getIdEmpleado() + 
                                 ", Fecha: " + ven.getFechaVenta() + 
                                 ", Total: " + ven.getTotalVenta());
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
