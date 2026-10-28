package aegisid.controlador;

import aegisid.modelo.Cliente;
import aegisid.modelo.DetalleVenta;
import aegisid.modelo.Producto;
import aegisid.modelo.Venta;
import aegisid.repository.ClienteRepository;
import aegisid.repository.ProductoRepository;
import aegisid.repository.VentaRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ventas")
public class VentaServlet extends BaseServlet {

    private final VentaRepository repo = new VentaRepository();
    private final ProductoRepository productoRepo = new ProductoRepository();
    private final ClienteRepository clienteRepo = new ClienteRepository();

    private String obtenerRol(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return (session != null && session.getAttribute("rol") != null) ? session.getAttribute("rol").toString() : "";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String rol = obtenerRol(req);
            if ("anular".equals(req.getParameter("accion"))) {
                if ("Administrador".equals(rol)) {
                    repo.anularVenta(Integer.parseInt(req.getParameter("id")));
                    mensaje(req, "Venta anulada correctamente.", "success");
                } else {
                    mensaje(req, "Acceso denegado: Solo el Administrador puede anular ventas.", "error");
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
            String rol = obtenerRol(req);
            if ("registrar".equals(req.getParameter("accion"))) {
                if ("Administrador".equals(rol) || "Operador".equals(rol)) {
                    int idCliente = Integer.parseInt(req.getParameter("id_cliente"));
                    String tipoComprobante = req.getParameter("tipo_comprobante");
                    
                    String[] idsProductos = req.getParameterValues("id_productos");
                    String[] cantidades = req.getParameterValues("cantidades");
                    String[] precios = req.getParameterValues("precios_unitarios");

                    if (idsProductos == null || idsProductos.length == 0) {
                        mensaje(req, "El carrito de compras está vacío.", "error");
                        mostrar(req, resp);
                        return;
                    }

                    List<DetalleVenta> listaDetalles = new ArrayList<>();
                    double totalGeneral = 0;

                    for (int i = 0; i < idsProductos.length; i++) {
                        int idProducto = Integer.parseInt(idsProductos[i]);
                        int cantidad = Integer.parseInt(cantidades[i]);
                        double precioUnitario = Double.parseDouble(precios[i]);
                        double subtotal = cantidad * precioUnitario;

                        totalGeneral += subtotal;
                        listaDetalles.add(new DetalleVenta(0, 0, idProducto, "", cantidad, precioUnitario));
                    }

                    Venta nuevaVenta = new Venta(0, null, totalGeneral, tipoComprobante, "Registrada", idCliente);
                    repo.registrarVentaConMultiplesDetalles(nuevaVenta, listaDetalles);
                    
                    mensaje(req, "Venta y detalles registrados correctamente.", "success");
                } else {
                    mensaje(req, "Acceso denegado: No tienes permisos para registrar ventas.", "error");
                }
            }
        } catch (Exception e) {
            mensaje(req, "Error al registrar venta: " + e.getMessage(), "error");
        }
        mostrar(req, resp);
    }

    private void mostrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("ventas", repo.listarVentas());
            req.setAttribute("productos", productoRepo.listarProductos());
            req.setAttribute("clientes", clienteRepo.listarClientes());
        } catch (Exception e) {
            mensaje(req, "Error al cargar datos: " + e.getMessage(), "error");
            req.setAttribute("ventas", new ArrayList<>());
            req.setAttribute("productos", new ArrayList<>());
            req.setAttribute("clientes", new ArrayList<>());
        }
        ir(req, resp, "ventas");
    }
}