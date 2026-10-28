package aegisid.modelo;

import aegisid.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    public List<Menu> obtenerOpcionesPorRol(String rolUsuario) {
        List<Menu> lista = new ArrayList<>();
        String sql = "SELECT id_menu, nombre_opcion, descripcion, url_enlace, rol_permitido FROM menu_opciones WHERE rol_permitido = ? OR rol_permitido = 'Todos'";
        
        try (Connection con = DatabaseConnection.getInstance().getConnection();
     PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, rolUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Menu m = new Menu(
                        rs.getInt("id_menu"),
                        rs.getString("nombre_opcion"),
                        rs.getString("descripcion"),
                        rs.getString("url_enlace"),
                        rs.getString("rol_permitido")
                    );
                    lista.add(m);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}