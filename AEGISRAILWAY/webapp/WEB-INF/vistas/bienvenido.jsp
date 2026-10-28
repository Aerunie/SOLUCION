<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String usuario = (session != null && session.getAttribute("usuario") != null) ? (String) session.getAttribute("usuario") : "Invitado";
    String rol = (session != null && session.getAttribute("rol") != null) ? session.getAttribute("rol").toString().trim() : "Invitado";
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aegis ID - Menú Principal</title>
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&display=swap" rel="stylesheet"/>
    <style>html,body{margin:0;padding:0;}body{overscroll-behavior:none;}::-webkit-scrollbar{display:none;}</style>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
    tailwind.config={theme:{extend:{colors:{"on-surface":"#131b2e","primary":"#070235","secondary":"#4e45d5","surface-container":"#eaedff","surface-container-high":"#e2e7ff","tertiary-container":"#2a006b","surface":"#faf8ff","surface-container-low":"#f2f3ff","surface-container-lowest":"#ffffff","on-surface-variant":"#47464f","on-primary":"#ffffff","primary-container":"#1e1b4b","secondary-container":"#6860ef","on-secondary-container":"#fffbff","secondary-fixed":"#e3dfff","outline":"#787680","error":"#ba1a1a","surface-variant":"#dae2fd","outline-variant":"#c8c5d0"},fontFamily:{"sans":["Plus Jakarta Sans"]}}}};
    </script>
</head>
<body class="bg-surface text-on-surface min-h-screen font-sans" style="font-family:'Plus Jakarta Sans',sans-serif;">

    <!-- Barra Superior -->
    <header class="bg-primary text-white shadow-md">
        <div class="max-w-7xl mx-auto px-6 h-20 flex items-center justify-between">
            <div class="flex items-center gap-3">
                <div class="w-10 h-10 rounded-xl bg-white/10 flex items-center justify-center">
                    <span class="material-symbols-outlined text-white text-[24px]">shield_lock</span>
                </div>
                <div>
                    <h1 class="text-lg font-bold tracking-tight">Aegis ID Enterprise</h1>
                    <p class="text-xs text-indigo-200">Panel de Control • Rol: <span class="font-semibold text-white"><%= rol %></span></p>
                </div>
            </div>
            
            <div class="flex items-center gap-4">
                <div class="text-right hidden sm:block">
                    <div class="text-xs text-indigo-200">Sesión activa como:</div>
                    <div class="text-sm font-semibold text-white"><%= usuario %></div>
                </div>
                <a href="logout" class="px-4 py-2 rounded-xl bg-red-600/20 hover:bg-red-600 text-red-200 hover:text-white text-xs font-semibold transition-all border border-red-500/30 flex items-center gap-2">
                    <span class="material-symbols-outlined text-[18px]">logout</span>
                    <span>Cerrar Sesión</span>
                </a>
            </div>
        </div>
    </header>

    <!-- Contenido Principal -->
    <main class="max-w-7xl mx-auto px-6 py-10">
        <div class="mb-8">
            <h2 class="text-3xl font-bold tracking-tight text-on-surface">Menú Principal</h2>
            <p class="text-sm text-on-surface-variant mt-1">Selecciona el módulo del sistema al que deseas acceder según tus privilegios autorizados.</p>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">

            <!-- 1. GESTIÓN DE USUARIOS (Solo Administrador) -->
            <% if ("Administrador".equalsIgnoreCase(rol)) { %>
            <div class="bg-white p-6 rounded-2xl shadow-sm border border-slate-100 hover:shadow-md transition-all flex flex-col justify-between">
                <div>
                    <div class="w-12 h-12 rounded-xl bg-indigo-50 text-secondary flex items-center justify-center mb-4">
                        <span class="material-symbols-outlined text-[26px]">group</span>
                    </div>
                    <h3 class="text-lg font-bold text-slate-900 mb-1">Gestión de Usuarios</h3>
                    <p class="text-xs text-slate-500 leading-relaxed">Administración de cuentas de acceso, roles, permisos y estados.</p>
                </div>
                <div class="mt-6 pt-4 border-t border-slate-100 flex items-center justify-between">
                    <span class="text-[11px] font-semibold text-emerald-600 bg-emerald-50 px-2.5 py-1 rounded-full">Activo</span>
                    <a href="usuarios" class="text-xs font-bold text-secondary hover:underline flex items-center gap-1">
                        <span>Acceder</span>
                        <span class="material-symbols-outlined text-[16px]">arrow_forward</span>
                    </a>
                </div>
            </div>
            <% } %>

            <!-- 2. GESTIÓN DE CLIENTES (Admin y Operador) -->
            <% if ("Administrador".equalsIgnoreCase(rol) || "Operador".equalsIgnoreCase(rol)) { %>
            <div class="bg-white p-6 rounded-2xl shadow-sm border border-slate-100 hover:shadow-md transition-all flex flex-col justify-between">
                <div>
                    <div class="w-12 h-12 rounded-xl bg-indigo-50 text-secondary flex items-center justify-center mb-4">
                        <span class="material-symbols-outlined text-[26px]">badge</span>
                    </div>
                    <h3 class="text-lg font-bold text-slate-900 mb-1">Gestión de Clientes</h3>
                    <p class="text-xs text-slate-500 leading-relaxed">Registro, actualización y control de cartera de clientes.</p>
                </div>
                <div class="mt-6 pt-4 border-t border-slate-100 flex items-center justify-between">
                    <span class="text-[11px] font-semibold text-emerald-600 bg-emerald-50 px-2.5 py-1 rounded-full">Activo</span>
                    <a href="clientes" class="text-xs font-bold text-secondary hover:underline flex items-center gap-1">
                        <span>Acceder</span>
                        <span class="material-symbols-outlined text-[16px]">arrow_forward</span>
                    </a>
                </div>
            </div>
            <% } %>

            <!-- 3. GESTIÓN DE PROVEEDORES (Admin y Operador) -->
            <% if ("Administrador".equalsIgnoreCase(rol) || "Operador".equalsIgnoreCase(rol)) { %>
            <div class="bg-white p-6 rounded-2xl shadow-sm border border-slate-100 hover:shadow-md transition-all flex flex-col justify-between">
                <div>
                    <div class="w-12 h-12 rounded-xl bg-indigo-50 text-secondary flex items-center justify-center mb-4">
                        <span class="material-symbols-outlined text-[26px]">local_shipping</span>
                    </div>
                    <h3 class="text-lg font-bold text-slate-900 mb-1">Gestión de Proveedores</h3>
                    <p class="text-xs text-slate-500 leading-relaxed">Control de proveedores comerciales y catálogos de compras.</p>
                </div>
                <div class="mt-6 pt-4 border-t border-slate-100 flex items-center justify-between">
                    <span class="text-[11px] font-semibold text-emerald-600 bg-emerald-50 px-2.5 py-1 rounded-full">Activo</span>
                    <a href="proveedores" class="text-xs font-bold text-secondary hover:underline flex items-center gap-1">
                        <span>Acceder</span>
                        <span class="material-symbols-outlined text-[16px]">arrow_forward</span>
                    </a>
                </div>
            </div>
            <% } %>

            <!-- 4. MERCADERÍA Y PRODUCTOS (Admin, Operador, Cliente e Invitado) -->
            <% if ("Administrador".equalsIgnoreCase(rol) || "Operador".equalsIgnoreCase(rol) || "Cliente".equalsIgnoreCase(rol) || "CLIENTE".equalsIgnoreCase(rol) || "Invitado".equalsIgnoreCase(rol)) { %>
            <div class="bg-white p-6 rounded-2xl shadow-sm border border-slate-100 hover:shadow-md transition-all flex flex-col justify-between">
                <div>
                    <div class="w-12 h-12 rounded-xl bg-indigo-50 text-secondary flex items-center justify-center mb-4">
                        <span class="material-symbols-outlined text-[26px]">inventory_2</span>
                    </div>
                    <h3 class="text-lg font-bold text-slate-900 mb-1">Mercadería y Productos</h3>
                    <p class="text-xs text-slate-500 leading-relaxed">Control de inventario, stock disponible y precios unitarios.</p>
                </div>
                <div class="mt-6 pt-4 border-t border-slate-100 flex items-center justify-between">
                    <span class="text-[11px] font-semibold text-emerald-600 bg-emerald-50 px-2.5 py-1 rounded-full">Activo</span>
                    <a href="productos" class="text-xs font-bold text-secondary hover:underline flex items-center gap-1">
                        <span>Acceder</span>
                        <span class="material-symbols-outlined text-[16px]">arrow_forward</span>
                    </a>
                </div>
            </div>
            <% } %>

            <!-- 5. MÓDULO DE VENTAS (Admin, Operador y Cliente) -->
            <% if ("Administrador".equalsIgnoreCase(rol) || "Operador".equalsIgnoreCase(rol) || "Cliente".equalsIgnoreCase(rol) || "CLIENTE".equalsIgnoreCase(rol)) { %>
            <div class="bg-white p-6 rounded-2xl shadow-sm border border-slate-100 hover:shadow-md transition-all flex flex-col justify-between">
                <div>
                    <div class="w-12 h-12 rounded-xl bg-indigo-50 text-secondary flex items-center justify-center mb-4">
                        <span class="material-symbols-outlined text-[26px]">point_of_sale</span>
                    </div>
                    <h3 class="text-lg font-bold text-slate-900 mb-1">Módulo de Ventas</h3>
                    <p class="text-xs text-slate-500 leading-relaxed">Registro y procesamiento de operaciones de venta y comprobantes.</p>
                </div>
                <div class="mt-6 pt-4 border-t border-slate-100 flex items-center justify-between">
                    <span class="text-[11px] font-semibold text-emerald-600 bg-emerald-50 px-2.5 py-1 rounded-full">Activo</span>
                    <a href="ventas" class="text-xs font-bold text-secondary hover:underline flex items-center gap-1">
                        <span>Acceder</span>
                        <span class="material-symbols-outlined text-[16px]">arrow_forward</span>
                    </a>
                </div>
            </div>
            <% } %>

            <!-- 6. REPORTERÍA Y OLAP (Admin y Operador) -->
            <% if ("Administrador".equalsIgnoreCase(rol) || "Operador".equalsIgnoreCase(rol)) { %>
            <div class="bg-white p-6 rounded-2xl shadow-sm border border-slate-100 hover:shadow-md transition-all flex flex-col justify-between">
                <div>
                    <div class="w-12 h-12 rounded-xl bg-indigo-50 text-secondary flex items-center justify-center mb-4">
                        <span class="material-symbols-outlined text-[26px]">analytics</span>
                    </div>
                    <h3 class="text-lg font-bold text-slate-900 mb-1">Reportería y OLAP</h3>
                    <p class="text-xs text-slate-500 leading-relaxed">Generación de informes ejecutivos de actividades y ventas.</p>
                </div>
                <div class="mt-6 pt-4 border-t border-slate-100 flex items-center justify-between">
                    <span class="text-[11px] font-semibold text-emerald-600 bg-emerald-50 px-2.5 py-1 rounded-full">Activo</span>
                    <a href="olap" class="text-xs font-bold text-secondary hover:underline flex items-center gap-1">
                        <span>Acceder</span>
                        <span class="material-symbols-outlined text-[16px]">arrow_forward</span>
                    </a>
                </div>
            </div>
            <% } %>

        </div>
    </main>

</body>
</html>