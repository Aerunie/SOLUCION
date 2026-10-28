package aegisid.controlador;

import aegisid.repository.AuthRepository;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistroServlet", urlPatterns = {"/registro"})
public class RegistroServlet extends BaseServlet {

    private AuthRepository authRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        this.authRepository = new AuthRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Carga el JSP /WEB-INF/vistas/registro.jsp usando BaseServlet
        ir(req, resp, "registro");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        String nombre = req.getParameter("nombre");
        String correo = req.getParameter("correo");
        String clave = req.getParameter("clave");

        boolean registrado = authRepository.registrar(nombre, correo, clave);

        if (registrado) {
            req.setAttribute("mensaje", "¡Registro exitoso! Ya puedes iniciar sesión.");
            ir(req, resp, "login");
        } else {
            req.setAttribute("error", "Ocurrió un error al registrar. El correo podría estar en uso.");
            ir(req, resp, "registro");
        }
    }
}