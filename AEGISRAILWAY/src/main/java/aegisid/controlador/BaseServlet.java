package aegisid.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    protected void mensaje(HttpServletRequest req, String texto, String tipo) {
        req.setAttribute("mensaje", texto);
        req.setAttribute("tipoMensaje", tipo);
    }

    protected void ir(HttpServletRequest req, HttpServletResponse resp, String vista)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/vistas/" + vista + ".jsp").forward(req, resp);
    }
}