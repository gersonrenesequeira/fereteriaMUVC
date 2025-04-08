/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Modelo.DetalleVenta;
import Util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Dell Notebook
 */
   public class DetalleVentaDAO {
    public List<DetalleVenta> leerTodosDetallesVenta() throws SQLException {
        String sql = "SELECT * FROM Detalles_Ventas";
        List<DetalleVenta> detalles = new ArrayList<>();

        try (Connection c = ConexionDB.getConnection();
             PreparedStatement stmt = c.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdDetalleVenta(rs.getInt("id_detalle_venta"));
                detalle.setIdVenta(rs.getInt("id_venta"));
                detalle.setIdProducto(rs.getInt("id_producto"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecioUnitario(rs.getFloat("precio_unitario"));
                detalles.add(detalle);
            }
        }
        return detalles;
    }

    public static void main(String[] args) {
        try {
            DetalleVentaDAO dao = new DetalleVentaDAO();
            List<DetalleVenta> detalles = dao.leerTodosDetallesVenta();
            System.out.println("Lista de detalles de venta:");
            for (DetalleVenta det : detalles) {
                System.out.println("ID: " + det.getIdDetalleVenta() + 
                                 ", Venta ID: " + det.getIdVenta() + 
                                 ", Producto ID: " + det.getIdProducto() + 
                                 ", Cantidad: " + det.getCantidad() + 
                                 ", Precio Unitario: " + det.getPrecioUnitario());
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
