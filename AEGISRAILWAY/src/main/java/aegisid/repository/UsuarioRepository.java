package aegisid.repository;

import aegisid.config.DatabaseConnection;
import aegisid.modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    private static final String COLUMNAS = "id_usuario, correo, clave, rol, estado, nombres, apellidos, dni, telefono, intentos_fallidos, bloqueado";

    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT " + COLUMNAS + " FROM usuarios")) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public Usuario buscarPorId(int id) throws SQLException {
        return buscarUno("id_usuario = ?", id);
    }

    public Usuario buscarPorCorreo(String correo) throws SQLException {
        return buscarUno("correo = ?", correo);
    }

    private Usuario buscarUno(String where, Object valor) throws SQLException {
        String sql = "SELECT " + COLUMNAS + " FROM usuarios WHERE " + where;
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, valor);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public void crear(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuarios (correo, clave, rol, estado, nombres, apellidos, dni, telefono) VALUES (?, ?, ?, 'Activo', ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getCorreo());
            ps.setString(2, u.getClave());
            ps.setString(3, u.getRol());
            ps.setString(4, u.getNombres());
            ps.setString(5, u.getApellidos());
            ps.setString(6, u.getDni());
            ps.setString(7, u.getTelefono());
            ps.executeUpdate();
        }
    }

    public void actualizar(Usuario u) throws SQLException {
        boolean cambiaClave = u.getClave() != null && !u.getClave().trim().isEmpty();
        String sql = cambiaClave
                ? "UPDATE usuarios SET correo = ?, clave = ?, rol = ?, nombres = ?, apellidos = ?, dni = ?, telefono = ? WHERE id_usuario = ?"
                : "UPDATE usuarios SET correo = ?, rol = ?, nombres = ?, apellidos = ?, dni = ?, telefono = ? WHERE id_usuario = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            int i = 1;
            ps.setString(i++, u.getCorreo());
            if (cambiaClave) ps.setString(i++, u.getClave());
            ps.setString(i++, u.getRol());
            ps.setString(i++, u.getNombres());
            ps.setString(i++, u.getApellidos());
            ps.setString(i++, u.getDni());
            ps.setString(i++, u.getTelefono());
            ps.setInt(i, u.getId());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM usuarios WHERE id_usuario = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void resetearIntentos(String correo) throws SQLException {
        String sql = "UPDATE usuarios SET intentos_fallidos = 0, bloqueado = 0 WHERE correo = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.executeUpdate();
        }
    }

    public void registrarFallo(String correo, int intentos, boolean bloquear) throws SQLException {
        String sql = "UPDATE usuarios SET intentos_fallidos = ?, bloqueado = ? WHERE correo = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, intentos);
            ps.setBoolean(2, bloquear);
            ps.setString(3, correo);
            ps.executeUpdate();
        }
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getInt("id_usuario"), 
            rs.getString("correo"), 
            rs.getString("clave"),
            rs.getString("rol") != null ? rs.getString("rol") : "Administrador",
            rs.getString("estado") != null ? rs.getString("estado") : "Activo",
            rs.getString("nombres"),
            rs.getString("apellidos"),
            rs.getString("dni"),
            rs.getString("telefono"),
            rs.getInt("intentos_fallidos"), 
            rs.getBoolean("bloqueado")
        );
    }
}