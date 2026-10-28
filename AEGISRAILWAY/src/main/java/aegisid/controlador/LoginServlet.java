package aegisid.controlador;

import aegisid.modelo.Usuario;
import aegisid.repository.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends BaseServlet {

    private static final int MAX_INTENTOS = 3;
    private final UsuarioRepository repo = new UsuarioRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ir(req, resp, "login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String correo = req.getParameter("correo") != null ? req.getParameter("correo").trim() : "";
        String clave = req.getParameter("clave") != null ? req.getParameter("clave").trim() : "";
        String mensaje;

        try {
            Usuario u = repo.buscarPorCorreo(correo);

            if (u == null) {
                mensaje = "El correo ingresado no se encuentra registrado.";
            } else if (u.isBloqueado()) {
                mensaje = "Tu cuenta está BLOQUEADA por superar los 3 intentos fallidos.";
            } else if (u.getClave().equals(clave)) {
                repo.resetearIntentos(correo);
                
                // GUARDAMOS EL CORREO Y EL ROL EN LA SESIÓN
                HttpSession session = req.getSession();
                session.setAttribute("usuario", correo);
                session.setAttribute("rol", u.getRol()); // <-- ¡Obligatorio para que funcionen los permisos por rol!

                resp.sendRedirect(req.getContextPath() + "/bienvenido");
                return;
            } else {
                int intentos = u.getIntentosFallidos() + 1;
                boolean bloquear = intentos >= MAX_INTENTOS;
                repo.registrarFallo(correo, intentos, bloquear);
                mensaje = bloquear
                    ? "Contraseña incorrecta. Has superado los 3 intentos. Tu cuenta ha sido BLOQUEADA."
                    : "Correo o contraseña incorrectos. Intentos fallidos: " + intentos + " de 3.";
            }
        } catch (Exception e) {
            mensaje = "Error: " + e.getMessage();
        }

        req.setAttribute("mensaje", mensaje);
        ir(req, resp, "login");
    }
}