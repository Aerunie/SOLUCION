package aegisid.controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "VistasServlet", urlPatterns = {"/vista-registro", "/vista-recuperar"})
public class VistasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userPath = request.getServletPath();
        
        if (userPath.equals("/vista-registro")) {
            // Añadimos el "/" al inicio
            request.getRequestDispatcher("/vistas/registro.jsp").forward(request, response);
        } else if (userPath.equals("/vista-recuperar")) {
            // Añadimos el "/" al inicio
            request.getRequestDispatcher("/vistas/recuperar.jsp").forward(request, response);
        }
    }
}