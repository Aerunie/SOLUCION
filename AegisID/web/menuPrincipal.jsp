<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String usuario = (String) session.getAttribute("usuarioLogueado");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AegisID - Panel Principal</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body class="bg-slate-100 min-h-screen flex items-center justify-center font-sans">
        <div class="bg-white p-8 rounded-2xl shadow-xl max-w-md w-full text-center space-y-4">
            <h1 class="text-3xl font-bold text-slate-800">Panel Principal AegisID</h1>
            <p class="text-slate-600">Sesión iniciada con éxito como: <span class="font-semibold text-indigo-600"><%= usuario %></span></p>
            <div class="pt-4">
                <a href="login.jsp" class="inline-block bg-rose-600 hover:bg-rose-700 text-white font-medium px-5 py-2.5 rounded-xl transition-colors">Cerrar Sesión</a>
            </div>
        </div>
    </body>
</html>