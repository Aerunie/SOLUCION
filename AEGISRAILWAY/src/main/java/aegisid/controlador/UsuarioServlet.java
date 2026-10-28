package aegisid.controlador;

import aegisid.modelo.Usuario;
import aegisid.repository.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/usuarios")
public class UsuarioServlet extends BaseServlet {

    private final UsuarioRepository repo = new UsuarioRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String accion = req.getParameter("accion");
            if ("preeditar".equals(accion)) {
                req.setAttribute("usuarioEdit", repo.buscarPorId(Integer.parseInt(req.getParameter("id"))));
            } else if ("eliminar".equals(accion)) {
                repo.eliminar(Integer.parseInt(req.getParameter("id")));
                mensaje(req, "Usuario eliminado correctamente.", "success");
            }
        } catch (Exception e) {
            mensaje(req, "Error en base de datos: " + e.getMessage(), "error");
        }
        mostrar(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            String accion = req.getParameter("accion");
            if ("crear".equals(accion)) {
                repo.crear(leer(req, 0));
                mensaje(req, "Usuario registrado exitosamente.", "success");
            } else if ("actualizar".equals(accion)) {
                repo.actualizar(leer(req, Integer.parseInt(req.getParameter("id_usuario"))));
                mensaje(req, "Usuario actualizado correctamente.", "success");
            }
        } catch (Exception e) {
            mensaje(req, "Error en base de datos: " + e.getMessage(), "error");
        }
        mostrar(req, resp);
    }

    private Usuario leer(HttpServletRequest req, int id) {
        return new Usuario(
            id, 
            req.getParameter("correo"), 
            req.getParameter("clave"),
            req.getParameter("rol"), 
            "Activo", 
            req.getParameter("nombres"),
            req.getParameter("apellidos"),
            req.getParameter("dni"),
            req.getParameter("telefono"),
            0, 
            false
        );
    }

    private void mostrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("usuarios", repo.listarTodos());
        } catch (Exception e) {
            mensaje(req, "Error al cargar listado: " + e.getMessage(), "error");
            req.setAttribute("usuarios", new ArrayList<Usuario>());
        }
        ir(req, resp, "usuarios");
    }
}