package aegisid.controlador;

import aegisid.modelo.Menu;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/bienvenido")
public class MenuServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession(false);
        String rolUsuario = (session != null && session.getAttribute("rol") != null) 
                            ? (String) session.getAttribute("rol") 
                            : "Administrador"; // Valor por defecto si no hay rol en sesión

        // Instanciamos el modelo para obtener las opciones filtradas de la base de datos
        Menu menuModelo = new Menu();
        List<Menu> listaOpciones = menuModelo.filtrarPorRol(rolUsuario);

        // Pasamos la lista como atributo a la vista
        req.setAttribute("listaOpciones", listaOpciones);

        // Redirigimos usando tu método centralizado
        ir(req, resp, "bienvenido");
    }
}