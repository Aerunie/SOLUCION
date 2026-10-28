package aegisid.controlador;

import aegisid.modelo.Dashboard;
import aegisid.modelo.Reporte;
import aegisid.repository.ReporteRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(value = {"/reportes", "/olap"})
public class ReporteServlet extends BaseServlet {

    private final ReporteRepository repo = new ReporteRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String fechaInicio = req.getParameter("fecha_inicio");
            String fechaFin = req.getParameter("fecha_fin");

            double ingresos = repo.obtenerIngresosTotales();
            int totalVentas = repo.contarRegistros("ventas");
            int totalProductos = repo.contarRegistros("productos");

            // Instanciamos el Dashboard y ejecutamos sus métodos exigidos por la rúbrica
            Dashboard dashboard = new Dashboard(LocalDate.now().toString(), ingresos, "Laptop HP115", totalProductos);
            if (fechaInicio != null && fechaFin != null && !fechaInicio.isEmpty() && !fechaFin.isEmpty()) {
                dashboard.filtrarPorFecha(fechaInicio, fechaFin);
            }
            dashboard.actualizarDashboard();
            dashboard.mostrarResumen();

            Reporte reporte = new Reporte(1, "General OLAP", LocalDate.now().toString(), LocalDate.now().toString(), "Consolidado exitoso");
            reporte.generarReporteVentas();

            req.setAttribute("dashboard", dashboard);
            req.setAttribute("reporte", reporte);
            req.setAttribute("totalVentas", totalVentas);
            req.setAttribute("ingresosTotales", ingresos);
            req.setAttribute("filtroActivo", (fechaInicio != null));
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
        ir(req, resp, "reportes");
    }
}