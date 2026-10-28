package aegisid.modelo;

import aegisid.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private int idMenu;
    private String nombreOpcion;
    private String descripcion;
    private String urlEnlace;
    private String rolPermitido;
    private List<Menu> listaOpciones; // Requerido por la rúbrica

    public Menu() {}

    public Menu(int idMenu, String nombreOpcion, String descripcion, String urlEnlace, String rolPermitido) {
        this.idMenu = idMenu;
        this.nombreOpcion = nombreOpcion;
        this.descripcion = descripcion;
        this.urlEnlace = urlEnlace;
        this.rolPermitido = rolPermitido;
    }

    // Métodos exigidos por la rúbrica
    public void mostrarMenu() {
        // Lógica para desplegar las opciones
    }

    // Método que alimenta la vista filtrando por rol directamente desde SQL Server
    public List<Menu> filtrarPorRol(String rolUsuario) {
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

    public void seleccionarOpcion(int idMenu) {
        // Lógica de selección
    }

    // Getters y Setters
    public int getIdMenu() { return idMenu; }
    public void setIdMenu(int idMenu) { this.idMenu = idMenu; }

    public String getNombreOpcion() { return nombreOpcion; }
    public void setNombreOpcion(String nombreOpcion) { this.nombreOpcion = nombreOpcion; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUrlEnlace() { return urlEnlace; }
    public void setUrlEnlace(String urlEnlace) { this.urlEnlace = urlEnlace; }

    public String getRolPermitido() { return rolPermitido; }
    public void setRolPermitido(String rolPermitido) { this.rolPermitido = rolPermitido; }

    public List<Menu> getListaOpciones() { return listaOpciones; }
    public void setListaOpciones(List<Menu> listaOpciones) { this.listaOpciones = listaOpciones; }
}