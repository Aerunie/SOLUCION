package aegisid.repository;

import aegisid.config.DatabaseConnection;
import aegisid.modelo.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorRepository {

    private static final String COLUMNAS = "id_proveedor, razon_social, ruc, telefono, direccion, correo, estado";

    public List<Proveedor> listarProveedores() throws SQLException {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT " + COLUMNAS + " FROM proveedores";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public Proveedor buscarPorId(int id) throws SQLException {
        String sql = "SELECT " + COLUMNAS + " FROM proveedores WHERE id_proveedor = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public void registrarProveedor(Proveedor p) throws SQLException {
        String sql = "INSERT INTO proveedores (razon_social, ruc, telefono, direccion, correo, estado) VALUES (?, ?, ?, ?, ?, 'Activo')";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getRazonSocial());
            ps.setString(2, p.getRuc());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getDireccion());
            ps.setString(5, p.getCorreo());
            ps.executeUpdate();
        }
    }

    public void actualizarProveedor(Proveedor p) throws SQLException {
        String sql = "UPDATE proveedores SET razon_social = ?, ruc = ?, telefono = ?, direccion = ?, correo = ? WHERE id_proveedor = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getRazonSocial());
            ps.setString(2, p.getRuc());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getDireccion());
            ps.setString(5, p.getCorreo());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
        }
    }

    public void eliminarProveedor(int id) throws SQLException {
        String sql = "DELETE FROM proveedores WHERE id_proveedor = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Proveedor mapear(ResultSet rs) throws SQLException {
        return new Proveedor(
            rs.getInt("id_proveedor"),
            nz(rs.getString("razon_social")),
            nz(rs.getString("ruc")),
            nz(rs.getString("telefono")),
            nz(rs.getString("direccion")),
            nz(rs.getString("correo")),
            rs.getString("estado") != null ? rs.getString("estado") : "Activo"
        );
    }

    private static String nz(String s) { return s != null ? s : ""; }
}