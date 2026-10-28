package aegisid.repository;

import aegisid.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthRepository {

    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE correo = ?";
        
        try (Connection con = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar correo: " + e.getMessage());
        }
        return false;
    }

    public boolean registrar(String nombre, String correo, String clave) {
        if (existeCorreo(correo)) {
            return false;
        }

        // Incluimos username y rol que son obligatorios (No NULL) en tu tabla
        String sql = "INSERT INTO usuarios (nombres, correo, clave, username, rol, intentos_fallidos, bloqueado) VALUES (?, ?, ?, ?, ?, 0, 0)";
        
        try (Connection con = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, clave); 
            ps.setString(4, correo); // Usamos el correo como username temporal
            ps.setString(5, "CLIENTE"); // Rol por defecto
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("ERROR SQL EN REGISTRO: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}