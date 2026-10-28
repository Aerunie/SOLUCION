<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="aegisid.modelo.Dashboard, aegisid.modelo.Reporte"%>
<%
    String usuarioSesion = (String) session.getAttribute("usuario");
    Dashboard dash = (Dashboard) request.getAttribute("dashboard");
    Reporte rep = (Reporte) request.getAttribute("reporte");
    int totalVentas = request.getAttribute("totalVentas") != null ? (Integer) request.getAttribute("totalVentas") : 0;
    double ingresosTotales = request.getAttribute("ingresosTotales") != null ? (Double) request.getAttribute("ingresosTotales") : 0.0;
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aegis ID - Módulo 8: Reportería y OLAP</title>
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&display=swap" rel="stylesheet"/>
    <style>
        @media print {
            header, .no-print { display: none !important; }
            body { background: white !important; color: black !important; }
            .shadow-sm, .shadow-md { box-shadow: none !important; border: 1px solid #cbd5e1 !important; }
        }
    </style>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-50 text-slate-900 min-h-screen font-sans" style="font-family:'Plus Jakarta Sans',sans-serif;">
    <header class="bg-indigo-950 text-white shadow-md">
        <div class="max-w-7xl mx-auto px-6 h-20 flex items-center justify-between">
            <div class="flex items-center gap-3">
                <a href="bienvenido" class="w-10 h-10 rounded-xl bg-white/10 flex items-center justify-center hover:bg-white/20 transition-all"><span class="material-symbols-outlined text-white text-[24px]">arrow_back</span></a>
                <div><h1 class="text-lg font-bold tracking-tight">Módulo 8: Reportería y OLAP</h1><p class="text-xs text-indigo-200">Informes Ejecutivos y Dashboard Dimensional</p></div>
            </div>
            <div class="flex items-center gap-3">
                <button onclick="window.print()" class="no-print px-4 py-2 rounded-xl bg-emerald-600 hover:bg-emerald-700 text-white text-xs font-semibold transition-all shadow-sm flex items-center gap-2">
                    <span class="material-symbols-outlined text-[18px]">picture_as_pdf</span>
                    <span>Exportar a PDF</span>
                </button>
                <a href="logout" class="no-print px-4 py-2 rounded-xl bg-red-600/20 hover:bg-red-600 text-red-200 text-xs font-semibold transition-all border border-red-500/30 flex items-center gap-2">
                    <span class="material-symbols-outlined text-[18px]">logout</span><span>Salir</span>
                </a>
            </div>
        </div>
    </header>
    <main class="max-w-7xl mx-auto px-6 py-10">
        <div class="mb-8 flex flex-col md:flex-row justify-between items-start md:items-center gap-4">
            <div>
                <h2 class="text-2xl font-bold tracking-tight text-slate-900">Dashboard y Modelo OLAP</h2>
                <p class="text-xs text-slate-500 mt-1">Consolidado métrico: <span class="font-semibold text-indigo-600"><%= rep != null ? rep.getTipoReporte() : "General" %></span></p>
            </div>
            <div class="flex items-center gap-3">
                <div class="text-xs text-slate-500 font-semibold bg-white px-4 py-2 rounded-xl border border-slate-200 shadow-sm">
                    Generado por: <span class="text-indigo-600"><%= usuarioSesion %></span>
                </div>
            </div>
        </div>

        <!-- Panel de Métodos Interactivos exigidos por rúbrica (filtrarPorFecha y actualizarDashboard) -->
        <div class="bg-white p-4 rounded-2xl shadow-sm border border-slate-100 mb-8 no-print flex flex-wrap items-center justify-between gap-4">
            <form method="get" action="reportes" class="flex items-center gap-3">
                <div class="text-xs font-bold text-slate-700 flex items-center gap-1">
                    <span class="material-symbols-outlined text-[18px] text-indigo-600">date_range</span>
                    <span>filtrarPorFecha():</span>
                </div>
                <input type="date" name="fecha_inicio" class="h-10 px-3 rounded-xl border border-slate-200 text-xs outline-none focus:ring-2 focus:ring-indigo-500/40"/>
                <span class="text-xs text-slate-400">hasta</span>
                <input type="date" name="fecha_fin" class="h-10 px-3 rounded-xl border border-slate-200 text-xs outline-none focus:ring-2 focus:ring-indigo-500/40"/>
                <button type="submit" class="px-4 h-10 rounded-xl bg-indigo-600 hover:bg-indigo-700 text-white text-xs font-semibold transition-all shadow-sm">Aplicar Filtro</button>
            </form>
            <a href="reportes" class="px-4 h-10 rounded-xl bg-slate-100 hover:bg-slate-200 text-slate-700 text-xs font-semibold transition-all flex items-center gap-1.5">
                <span class="material-symbols-outlined text-[16px]">sync</span>
                <span>actualizarDashboard()</span>
            </a>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
            <div class="bg-white p-6 rounded-2xl shadow-sm border border-slate-100">
                <div class="text-xs text-slate-400 font-semibold mb-1">Ingresos Totales (OLAP)</div>
                <div class="text-2xl font-bold text-emerald-600">$<%= String.format("%.2f", ingresosTotales) %></div>
                <div class="text-[11px] text-slate-400 mt-1"><%= totalVentas %> transacciones registradas</div>
            </div>
            <div class="bg-white p-6 rounded-2xl shadow-sm border border-slate-100">
                <div class="text-xs text-slate-400 font-semibold mb-1">Fecha Actual (mostrarResumen)</div>
                <div class="text-2xl font-bold text-indigo-600"><%= dash != null ? dash.getFechaActual() : "-" %></div>
                <div class="text-[11px] text-slate-400 mt-1">Corte de datos analíticos</div>
            </div>
            <div class="bg-white p-6 rounded-2xl shadow-sm border border-slate-100">
                <div class="text-xs text-slate-400 font-semibold mb-1">Producto Más Vendido</div>
                <div class="text-2xl font-bold text-blue-600"><%= dash != null ? dash.getProductoMasVendido() : "-" %></div>
                <div class="text-[11px] text-slate-400 mt-1">Líder en ventas</div>
            </div>
            <div class="bg-white p-6 rounded-2xl shadow-sm border border-slate-100">
                <div class="text-xs text-slate-400 font-semibold mb-1">Stock Actual</div>
                <div class="text-2xl font-bold text-amber-600"><%= dash != null ? dash.getStockActual() : 0 %></div>
                <div class="text-[11px] text-slate-400 mt-1">Unidades disponibles</div>
            </div>
        </div>
    </main>
</body>
</html>