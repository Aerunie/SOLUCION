<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%
    request.setCharacterEncoding("UTF-8");

    String usuario = request.getParameter("txtUsuario");
    if (usuario == null) usuario = request.getParameter("username");
    if (usuario == null) usuario = request.getParameter("usuario");

    String clave = request.getParameter("txtPassword");
    if (clave == null) clave = request.getParameter("txtClave");
    if (clave == null) clave = request.getParameter("password");
    if (clave == null) clave = request.getParameter("clave");

    if (usuario != null) usuario = usuario.trim();
    if (clave != null) clave = clave.trim();

    boolean autenticado = false;

    // Configuración por variables de entorno (Railway)
    String dbUrl = System.getenv("DB_URL");
    String dbUser = System.getenv("DB_USER");
    String dbPass = System.getenv("DB_PASSWORD");
    boolean demo = "true".equalsIgnoreCase(System.getenv("DEMO_LOGIN"));

    boolean dbOk = false;
    if (usuario != null && clave != null && dbUrl != null && !dbUrl.isEmpty()) {
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             PreparedStatement ps = con.prepareStatement("SELECT password FROM usuarios WHERE username = ?")) {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String passwordDb = rs.getString("password");
                    if (passwordDb != null && passwordDb.trim().equals(clave)) {
                        autenticado = true;
                    }
                }
            }
            dbOk = true;
        } catch (Exception e) {
            application.log("Error de BD en login: " + e.getMessage());
        }
    }

    // Respaldo admin/1234 solo si DEMO_LOGIN=true y la BD no respondió
    if (!autenticado && !dbOk && demo && "admin".equals(usuario) && "1234".equals(clave)) {
        autenticado = true;
    }

    if (autenticado) {
        session.setAttribute("usuario", usuario);
        session.setAttribute("usuarioLogueado", usuario);
        String safe = usuario.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>AegisID</title></head>");
        out.println("<body style='font-family: Arial, sans-serif; background-color: #f4f6f9; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0;'>");
        out.println("<div style='background: white; padding: 40px; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); text-align: center;'>");
        out.println("<h1 style='color: #2e7d32;'>¡Acceso Correcto!</h1>");
        out.println("<p style='font-size: 18px;'>Bienvenido al sistema, <strong>" + safe + "</strong>.</p>");
        out.println("<p><a href='menuPrincipal.jsp'>Ir al panel principal</a></p>");
        out.println("</div></body></html>");
    } else {
        out.println("<div style='font-family: Arial; padding: 20px; color: red; text-align: center; margin-top: 50px;'>");
        out.println("<h2>Usuario o contraseña incorrectos.</h2>");
        out.println("</div>");
    }
%>
