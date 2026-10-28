package aegisid.controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RecuperarServlet", urlPatterns = {"/recuperar"})
public class RecuperarServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Carga el JSP /WEB-INF/vistas/recuperar.jsp usando BaseServlet
        ir(req, resp, "recuperar");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        String correo = req.getParameter("correo");
        
        req.setAttribute("mensaje", "Si el correo está registrado, se han enviado las instrucciones a " + correo);
        ir(req, resp, "recuperar");
    }
}