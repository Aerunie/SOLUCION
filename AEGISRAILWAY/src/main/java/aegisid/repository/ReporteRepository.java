package aegisid.repository;

import aegisid.config.DatabaseConnection;
import java.sql.*;

public class ReporteRepository {

    public double obtenerIngresosTotales() throws SQLException {
        String sql = "SELECT COALESCE(SUM(total), 0) FROM ventas";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() ? rs.getDouble(1) : 0.0;
        }
    }

    public int contarRegistros(String tabla) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tabla;
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }
}