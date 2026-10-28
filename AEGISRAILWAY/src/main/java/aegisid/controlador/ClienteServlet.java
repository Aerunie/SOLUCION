package aegisid.controlador;

import aegisid.modelo.Cliente;
import aegisid.repository.ClienteRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/clientes")
public class ClienteServlet extends BaseServlet {

    private final ClienteRepository repo = new ClienteRepository();

    private String obtenerRol(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return (session != null && session.getAttribute("rol") != null) ? session.getAttribute("rol").toString() : "";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String accion = req.getParameter("accion");
            String rol = obtenerRol(req);

            if ("preeditar".equals(accion)) {
                if ("Administrador".equals(rol) || "Operador".equals(rol)) {
                    req.setAttribute("clienteEdit", repo.buscarPorId(Integer.parseInt(req.getParameter("id"))));
                } else {
                    mensaje(req, "Acceso denegado: No tienes permisos para editar clientes.", "error");
                }
            } else if ("eliminar".equals(accion)) {
                if ("Administrador".equals(rol)) {
                    repo.eliminarCliente(Integer.parseInt(req.getParameter("id")));
                    mensaje(req, "Cliente eliminado correctamente.", "success");
                } else {
                    mensaje(req, "Acceso denegado: Solo el Administrador puede eliminar registros.", "error");
                }
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
            String rol = obtenerRol(req);

            if ("crear".equals(accion)) {
                if ("Administrador".equals(rol) || "Operador".equals(rol)) {
                    repo.registrarCliente(leer(req, 0));
                    mensaje(req, "Cliente registrado exitosamente.", "success");
                } else {
                    mensaje(req, "Acceso denegado: No tienes permisos para registrar clientes.", "error");
                }
            } else if ("actualizar".equals(accion)) {
                if ("Administrador".equals(rol) || "Operador".equals(rol)) {
                    repo.actualizarCliente(leer(req, Integer.parseInt(req.getParameter("id_cliente"))));
                    mensaje(req, "Cliente actualizado correctamente.", "success");
                } else {
                    mensaje(req, "Acceso denegado: No tienes permisos para actualizar clientes.", "error");
                }
            }
        } catch (Exception e) {
            mensaje(req, "Error en base de datos: " + e.getMessage(), "error");
        }
        mostrar(req, resp);
    }

    private Cliente leer(HttpServletRequest req, int id) {
        return new Cliente(
            id, 
            req.getParameter("nombre"), 
            req.getParameter("apellido"), 
            req.getParameter("dni_ruc"), 
            req.getParameter("correo"),
            req.getParameter("telefono"), 
            req.getParameter("direccion"), 
            "Activo"
        );
    }

    private void mostrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("clientes", repo.listarClientes());
        } catch (Exception e) {
            mensaje(req, "Error al cargar listado: " + e.getMessage(), "error");
            req.setAttribute("clientes", new ArrayList<Cliente>());
        }
        ir(req, resp, "clientes");
    }
}