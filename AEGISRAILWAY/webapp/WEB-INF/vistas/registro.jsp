<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mensaje = request.getAttribute("mensaje") != null ? (String) request.getAttribute("mensaje") : "";
    String error = request.getAttribute("error") != null ? (String) request.getAttribute("error") : "";
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8"/>
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<title>Aegis ID - Registro Institucional</title>
<link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&display=swap" rel="stylesheet"/>
<script src="https://cdn.tailwindcss.com"></script>
<script>
tailwind.config={theme:{extend:{colors:{"on-surface":"#131b2e","primary":"#070235","secondary":"#4e45d5","surface-container":"#eaedff","surface-container-high":"#e2e7ff","tertiary-container":"#2a006b","surface":"#faf8ff","surface-container-low":"#f2f3ff","surface-container-lowest":"#ffffff","on-surface-variant":"#47464f","on-primary":"#ffffff","primary-container":"#1e1b4b","secondary-container":"#6860ef","on-secondary-container":"#fffbff","secondary-fixed":"#e3dfff","outline":"#787680","error":"#ba1a1a","surface-variant":"#dae2fd","outline-variant":"#c8c5d0"},fontFamily:{"sans":["Plus Jakarta Sans"]}}}};
</script>
</head>
<body class="bg-surface text-on-surface min-h-screen antialiased font-sans" style="font-family:'Plus Jakarta Sans',sans-serif;">
<div class="min-h-screen w-full flex items-center justify-center p-6 bg-surface">
  <div class="w-full max-w-[500px] py-4 bg-white p-8 rounded-2xl shadow-xl border border-surface-container-high">

    <div class="mb-6">
      <div class="flex items-center gap-2 mb-2">
        <span class="material-symbols-outlined text-secondary text-[28px]">shield_lock</span>
        <span class="text-lg font-semibold tracking-tight text-on-surface">Aegis ID</span>
      </div>
      <h2 class="text-2xl font-bold tracking-tight text-on-surface">Crear Cuenta Institucional</h2>
      <p class="text-sm text-on-surface-variant mt-1">Completa los datos para registrar tu acceso en la plataforma.</p>
    </div>

    <!-- Mensajes de feedback -->
    <% if (!mensaje.isEmpty()) { %>
    <div class="mb-4 p-3 rounded-lg text-sm font-semibold text-center bg-emerald-100 text-emerald-800">
        <%= mensaje %>
    </div>
    <% } %>

    <% if (!error.isEmpty()) { %>
    <div class="mb-4 p-3 rounded-lg text-sm font-semibold text-center" style="background:#ffdad6;color:#93000a;">
        <%= error %>
    </div>
    <% } %>

    <!-- FORMULARIO DE REGISTRO QUE APUNTA AL SERVLET -->
    <form action="<%=request.getContextPath()%>/registro" method="POST" class="space-y-4">
      <div>
        <label class="text-xs text-on-surface font-semibold block mb-1.5" for="nombre">Nombre Completo <span class="text-error">*</span></label>
        <input class="w-full h-[48px] px-4 rounded-xl bg-surface-container-low border border-outline-variant text-sm outline-none focus:border-secondary" 
               id="nombre" name="nombre" placeholder="Ej. Carlos Pérez" required type="text"/>
      </div>

      <div>
        <label class="text-xs text-on-surface font-semibold block mb-1.5" for="correo">Correo Electrónico Corporativo <span class="text-error">*</span></label>
        <input class="w-full h-[48px] px-4 rounded-xl bg-surface-container-low border border-outline-variant text-sm outline-none focus:border-secondary" 
               id="correo" name="correo" placeholder="tu.nombre@empresa.com" required type="email"/>
      </div>

      <div>
        <label class="text-xs text-on-surface font-semibold block mb-1.5" for="clave">Contraseña <span class="text-error">*</span></label>
        <div class="relative flex items-center">
          <input class="w-full h-[48px] pl-4 pr-12 rounded-xl bg-surface-container-low border border-outline-variant text-sm outline-none focus:border-secondary" 
                 id="clave" name="clave" placeholder="••••••••••••" required type="password"/>
          <button type="button" class="absolute right-3.5 text-on-surface-variant hover:text-on-surface" onclick="togglePassword()">
            <span class="material-symbols-outlined text-[20px]" id="eyeIcon">visibility</span>
          </button>
        </div>
      </div>

      <button type="submit" class="w-full h-[50px] rounded-xl bg-secondary text-white text-sm font-semibold shadow-md hover:brightness-105 transition-all mt-2">
        Registrar Cuenta
      </button>
    </form>

    <div class="mt-6 text-center text-sm text-on-surface-variant">
      ¿Ya tienes una cuenta? 
      <a href="<%=request.getContextPath()%>/login" class="font-bold text-secondary hover:underline ml-1">Inicia sesión</a>
    </div>

  </div>
</div>

<script>
  function togglePassword() {
    var input = document.getElementById('clave');
    var icon = document.getElementById('eyeIcon');
    if (input.type === 'password') {
      input.type = 'text';
      icon.textContent = 'visibility_off';
    } else {
      input.type = 'password';
      icon.textContent = 'visibility';
    }
  }
</script>
</body>
</html>