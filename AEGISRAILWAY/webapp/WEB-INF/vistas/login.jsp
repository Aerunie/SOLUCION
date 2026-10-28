<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mensaje = request.getAttribute("mensaje") != null ? (String) request.getAttribute("mensaje") : "";
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8"/>
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<title>Aegis ID - Iniciar Sesión</title>
<link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&display=swap" rel="stylesheet"/>
<style>html,body{margin:0;padding:0;}body{overscroll-behavior:none;}::-webkit-scrollbar{display:none;}</style>
<script src="https://cdn.tailwindcss.com"></script>
<script>
tailwind.config={theme:{extend:{colors:{"on-surface":"#131b2e","primary":"#070235","secondary":"#4e45d5","surface-container":"#eaedff","surface-container-high":"#e2e7ff","tertiary-container":"#2a006b","surface":"#faf8ff","surface-container-low":"#f2f3ff","surface-container-lowest":"#ffffff","on-surface-variant":"#47464f","on-primary":"#ffffff","primary-container":"#1e1b4b","secondary-container":"#6860ef","on-secondary-container":"#fffbff","secondary-fixed":"#e3dfff","outline":"#787680","error":"#ba1a1a","surface-variant":"#dae2fd","outline-variant":"#c8c5d0"},fontFamily:{"sans":["Plus Jakarta Sans"]}}}};
</script>
</head>
<body class="bg-surface text-on-surface min-h-screen antialiased font-sans" style="font-family:'Plus Jakarta Sans',sans-serif;">
<div class="min-h-screen w-full flex flex-col lg:flex-row bg-surface">

  <!-- Panel izquierdo -->
  <div class="relative w-full lg:w-5/12 xl:w-1/2 bg-gradient-to-br from-primary via-primary-container to-tertiary-container text-white p-8 md:p-14 lg:p-16 flex flex-col justify-between overflow-hidden shadow-xl">
    <div class="absolute -top-32 -left-32 w-96 h-96 rounded-full bg-secondary-container/20 blur-3xl pointer-events-none"></div>
    <div class="absolute -bottom-40 -right-20 w-96 h-96 rounded-full bg-secondary/25 blur-3xl pointer-events-none"></div>

    <div class="relative z-10">
      <div class="flex items-center gap-3.5 mb-10">
        <div class="w-12 h-12 rounded-xl bg-white/10 backdrop-blur-md p-1.5 shadow-md flex items-center justify-center">
          <span class="material-symbols-outlined text-white text-[28px]">shield_lock</span>
        </div>
        <div>
          <div class="flex items-center gap-2">
            <span class="text-lg font-semibold tracking-tight text-white">Aegis ID</span>
            <span class="px-2 py-0.5 rounded-full bg-secondary-container text-on-secondary-container text-[11px] font-semibold uppercase tracking-wider">Enterprise</span>
          </div>
          <p class="text-xs text-surface-container-high/80">Plataforma Unificada de Identidad</p>
        </div>
      </div>

      <div class="max-w-xl space-y-4">
        <div class="inline-flex items-center gap-2 px-3 py-1.5 rounded-full bg-white/10 backdrop-blur-md text-surface-container-high text-xs font-semibold">
          <span class="w-2 h-2 rounded-full bg-emerald-400 animate-pulse"></span>
          Protocolo de Cero Confianza (Zero-Trust v4.2)
        </div>
        <h1 class="text-4xl font-bold leading-tight tracking-tight text-white">
          Seguridad y control en un solo lugar
        </h1>
        <p class="text-base leading-relaxed text-surface-container-high/90">
          Accede a tu plataforma empresarial con encriptación de grado bancario, autenticación multifactor y protección biométrica avanzada.
        </p>
      </div>
    </div>

    <div class="relative z-10 my-10 space-y-4">
      <div class="grid grid-cols-2 sm:grid-cols-3 gap-3">
        <div class="bg-white/10 backdrop-blur-sm rounded-xl p-4 shadow-sm">
          <div class="text-xl font-bold text-white">99.99%</div>
          <div class="text-[11px] font-semibold text-surface-container-high/75">Disponibilidad SLA</div>
        </div>
        <div class="bg-white/10 backdrop-blur-sm rounded-xl p-4 shadow-sm">
          <div class="text-xl font-bold text-white">ISO 27001</div>
          <div class="text-[11px] font-semibold text-surface-container-high/75">SOC2 Tipo II Compliant</div>
        </div>
        <div class="col-span-2 sm:col-span-1 bg-white/10 backdrop-blur-sm rounded-xl p-4 shadow-sm">
          <div class="text-xl font-bold text-white">+50K</div>
          <div class="text-[11px] font-semibold text-surface-container-high/75">Equipos protegidos</div>
        </div>
      </div>

      <div class="bg-white/15 backdrop-blur-md rounded-xl p-4 shadow-md flex items-start gap-3.5">
        <div class="w-10 h-10 rounded-full bg-secondary-fixed flex-shrink-0 flex items-center justify-center">
          <span class="material-symbols-outlined text-secondary text-[22px]">person</span>
        </div>
        <div class="min-w-0 flex-1">
          <p class="text-xs italic text-white leading-snug">
            "La mejor experiencia de autenticación y gestión de accesos que hemos implementado en la compañía."
          </p>
          <div class="mt-1 text-[11px]">
            <span class="font-semibold text-secondary-fixed">Elena Morales</span>
            <span class="text-surface-container-high/70"> • Directora de Ciberseguridad, GlobalFin</span>
          </div>
        </div>
      </div>
    </div>

    <div class="relative z-10 pt-4 flex flex-col sm:flex-row sm:items-center justify-between gap-3 text-surface-container-high/80 text-xs font-semibold">
      <div class="flex items-center gap-2">
        <span class="h-2 w-2 rounded-full bg-emerald-400"></span>
        <span class="text-white">Todos los sistemas operacionales</span>
      </div>
      <div class="font-normal text-surface-container-high/70">© 2026 Aegis ID Corporation</div>
    </div>
  </div>

  <!-- Panel derecho: formulario -->
  <div class="w-full lg:w-7/12 xl:w-1/2 flex items-center justify-center p-6 sm:p-10 md:p-14 lg:p-16 bg-surface">
    <div class="w-full max-w-[480px] py-4">

      <div class="mb-8">
        <h2 class="text-3xl font-bold tracking-tight text-on-surface">Bienvenido de nuevo</h2>
        <p class="text-sm text-on-surface-variant mt-1.5">Ingresa tus credenciales autorizadas para acceder a la consola corporativa.</p>
      </div>

      <!-- FORMULARIO DE ACCESO APUNTA AL SERVLET -->
      <form method="post" action="<%=request.getContextPath()%>/login" class="space-y-4" id="loginForm">

        <% if (!mensaje.isEmpty()) { 
     boolean esExito = mensaje.toLowerCase().contains("exitoso");
     String bgColor = esExito ? "background:#d1e7dd;color:#0f5132;" : "background:#ffdad6;color:#93000a;";
%>
<div class="p-3 rounded-lg text-sm font-semibold text-center" style="<%= bgColor %>">
  <%= mensaje %>
</div>
<% } %>

        <div>
          <label class="text-xs text-on-surface font-semibold flex items-center gap-1 mb-1.5" for="email">
            Correo Electrónico Corporativo <span class="text-error font-bold">*</span>
          </label>
          <div class="relative flex items-center rounded-xl bg-white shadow-sm focus-within:shadow-md focus-within:ring-2 focus-within:ring-secondary/40">
            <span class="material-symbols-outlined text-on-surface-variant text-[20px] ml-4 pointer-events-none">mail</span>
            <input class="w-full h-[52px] bg-transparent pl-3 pr-4 text-on-surface placeholder:text-outline text-sm outline-none"
                   id="email" name="correo" placeholder="tu.nombre@empresa.com" required type="email"/>
          </div>
        </div>

        <div>
          <div class="flex items-center justify-between mb-1.5">
            <label class="text-xs text-on-surface font-semibold flex items-center gap-1" for="password">
              Contraseña <span class="text-error font-bold">*</span>
            </label>
            <!-- ENLACE CORREGIDO AL SERVLET DE RECUPERACIÓN -->
            <a class="text-xs font-semibold text-secondary hover:underline" href="<%=request.getContextPath()%>/recuperar">¿Olvidaste tu contraseña?</a>
          </div>
          <div class="relative flex items-center rounded-xl bg-white shadow-sm focus-within:shadow-md focus-within:ring-2 focus-within:ring-secondary/40">
            <span class="material-symbols-outlined text-on-surface-variant text-[20px] ml-4 pointer-events-none">lock</span>
            <input class="w-full h-[52px] bg-transparent pl-3 pr-12 text-on-surface placeholder:text-outline text-sm outline-none"
                   id="password" name="clave" placeholder="••••••••••••" required type="password"/>
            <button type="button" aria-label="Mostrar u ocultar contraseña"
                    class="absolute right-3.5 p-1 rounded-lg text-on-surface-variant hover:text-on-surface hover:bg-surface-container-low"
                    onclick="togglePasswordVisibility()">
              <span class="material-symbols-outlined text-[20px] align-middle" id="passwordEyeIcon">visibility</span>
            </button>
          </div>
        </div>

        <div class="pt-2">
          <button type="submit" id="submitLoginBtn"
                  class="w-full h-[52px] rounded-xl bg-gradient-to-r from-primary-container via-secondary to-primary-container text-white text-sm font-semibold shadow-md hover:shadow-lg hover:brightness-105 active:scale-[0.99] transition-all flex items-center justify-center gap-2.5">
            <span>Iniciar Sesión</span>
            <span class="material-symbols-outlined text-[20px]">arrow_forward</span>
          </button>
        </div>
      </form>

      <!-- ENLACE CORREGIDO AL SERVLET DE REGISTRO -->
      <div class="mt-8 text-center">
        <p class="text-sm text-on-surface-variant">
          ¿Aún no tienes cuenta institucional?
          <a class="font-bold text-secondary hover:underline ml-1" href="<%=request.getContextPath()%>/registro">Regístrate gratis</a>
        </p>
      </div>

      <div class="mt-10 pt-6 flex flex-wrap items-center justify-between gap-4 text-xs text-on-surface-variant">
        <div class="flex flex-wrap items-center gap-4">
          <a class="hover:underline" href="#ayuda">Centro de Ayuda</a>
          <span class="text-outline-variant">•</span>
          <a class="hover:underline" href="#privacidad">Privacidad</a>
          <span class="text-outline-variant">•</span>
          <a class="hover:underline" href="#terminos">Términos</a>
        </div>
        <div class="inline-flex items-center gap-1.5 px-2.5 py-1 rounded-lg bg-surface-container-high text-[11px] font-semibold text-on-surface">
          <span class="material-symbols-outlined text-[16px]">language</span>
          <span>Español (ES)</span>
        </div>
      </div>

    </div>
  </div>
</div>

<script>
  function togglePasswordVisibility() {
    var input = document.getElementById('password');
    var icon = document.getElementById('passwordEyeIcon');
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