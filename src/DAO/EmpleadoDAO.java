/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.Empleado;
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
   public class EmpleadoDAO {
    public List<Empleado> leerTodosEmpleados() throws SQLException {
        String sql = "SELECT * FROM Empleados";
        List<Empleado> empleados = new ArrayList<>();

        try (Connection c = ConexionDB.getConnection();
             PreparedStatement stmt = c.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("id_empleado"));
                empleado.setPrimerNombre(rs.getString("primer_nombre"));
                empleado.setSegundoNombre(rs.getString("segundo_nombre"));
                empleado.setPrimerApellido(rs.getString("primer_apellido"));
                empleado.setSegundoApellido(rs.getString("segundo_apellido"));
                empleado.setCelular(rs.getString("celular"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setFechaContratacion(rs.getDate("fecha_contratacion"));
                empleados.add(empleado);
            }
        }
        return empleados;
    }
    
   public static void main(String[] args) {
        try {
            EmpleadoDAO dao = new EmpleadoDAO();
            List<Empleado> empleados = dao.leerTodosEmpleados();
            System.out.println("Lista de empleados:");
            for (Empleado emp : empleados) {
                System.out.println("ID: " + emp.getIdEmpleado() + 
                                 ", Nombre: " + emp.getPrimerNombre() + " " + emp.getSegundoNombre() + 
                                 " " + emp.getPrimerApellido() + " " + emp.getSegundoApellido() + 
                                 ", Celular: " + emp.getCelular() + 
                                 ", Cargo: " + emp.getCargo() + 
                                 ", Fecha Contratación: " + emp.getFechaContratacion());
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
