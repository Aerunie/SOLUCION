package aegisid.controlador;

import aegisid.modelo.Producto;
import aegisid.repository.ProductoRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/productos")
public class ProductoServlet extends BaseServlet {

    private final ProductoRepository repo = new ProductoRepository();

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
                    req.setAttribute("productoEdit", repo.buscarPorId(Integer.parseInt(req.getParameter("id"))));
                } else {
                    mensaje(req, "Acceso denegado: No tienes permisos para editar productos.", "error");
                }
            } else if ("eliminar".equals(accion)) {
                if ("Administrador".equals(rol)) {
                    repo.eliminarProducto(Integer.parseInt(req.getParameter("id")));
                    mensaje(req, "Producto eliminado correctamente.", "success");
                } else {
                    mensaje(req, "Acceso denegado: Solo el Administrador puede eliminar productos.", "error");
                }
            }
        } catch (Exception e) {
            mensaje(req, "Error: " + e.getMessage(), "error");
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
                    repo.registrarProducto(leer(req, 0));
                    mensaje(req, "Producto registrado exitosamente.", "success");
                } else {
                    mensaje(req, "Acceso denegado: No tienes permisos para registrar productos.", "error");
                }
            } else if ("actualizar".equals(accion)) {
                if ("Administrador".equals(rol) || "Operador".equals(rol)) {
                    repo.actualizarProducto(leer(req, Integer.parseInt(req.getParameter("id_producto"))));
                    mensaje(req, "Producto actualizado correctamente.", "success");
                } else {
                    mensaje(req, "Acceso denegado: No tienes permisos para actualizar productos.", "error");
                }
            }
        } catch (Exception e) {
            mensaje(req, "Error: " + e.getMessage(), "error");
        }
        mostrar(req, resp);
    }

    private Producto leer(HttpServletRequest req, int id) {
        return new Producto(
            id, 
            req.getParameter("nombre"), 
            req.getParameter("descripcion"),
            Double.parseDouble(req.getParameter("precio")),
            Integer.parseInt(req.getParameter("stock")),
            req.getParameter("categoria"), 
            "Disponible"
        );
    }

    private void mostrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("productos", repo.listarProductos());
        } catch (Exception e) {
            mensaje(req, "Error al cargar inventario: " + e.getMessage(), "error");
            req.setAttribute("productos", new ArrayList<Producto>());
        }
        ir(req, resp, "productos");
    }
}