package aegisid.controlador;

import aegisid.modelo.Proveedor;
import aegisid.repository.ProveedorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/proveedores")
public class ProveedorServlet extends BaseServlet {

    private final ProveedorRepository repo = new ProveedorRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String accion = req.getParameter("accion");
            if ("preeditar".equals(accion)) {
                req.setAttribute("proveedorEdit", repo.buscarPorId(Integer.parseInt(req.getParameter("id"))));
            } else if ("eliminar".equals(accion)) {
                repo.eliminarProveedor(Integer.parseInt(req.getParameter("id")));
                mensaje(req, "Proveedor eliminado correctamente.", "success");
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
                repo.registrarProveedor(leer(req, 0));
                mensaje(req, "Proveedor registrado exitosamente.", "success");
            } else if ("actualizar".equals(accion)) {
                repo.actualizarProveedor(leer(req, Integer.parseInt(req.getParameter("id_proveedor"))));
                mensaje(req, "Proveedor actualizado correctamente.", "success");
            }
        } catch (Exception e) {
            mensaje(req, "Error en base de datos: " + e.getMessage(), "error");
        }
        mostrar(req, resp);
    }

    private Proveedor leer(HttpServletRequest req, int id) {
        return new Proveedor(
            id, 
            req.getParameter("razon_social"), 
            req.getParameter("ruc"), 
            req.getParameter("telefono"),
            req.getParameter("direccion"), 
            req.getParameter("correo"), 
            "Activo"
        );
    }

    private void mostrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("proveedores", repo.listarProveedores());
        } catch (Exception e) {
            mensaje(req, "Error al cargar listado: " + e.getMessage(), "error");
            req.setAttribute("proveedores", new ArrayList<Proveedor>());
        }
        ir(req, resp, "proveedores");
    }
}