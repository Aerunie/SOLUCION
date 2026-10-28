<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, aegisid.modelo.Producto"%>
<%
    String usuarioSesion = (String) session.getAttribute("usuario");
    String mensaje = request.getAttribute("mensaje") != null ? (String) request.getAttribute("mensaje") : "";
    String tipoMensaje = request.getAttribute("tipoMensaje") != null ? (String) request.getAttribute("tipoMensaje") : "";

    Producto edit = (Producto) request.getAttribute("productoEdit");
    boolean modoEdicion = (edit != null);
    String editId        = modoEdicion ? String.valueOf(edit.getId()) : "";
    String editNombre    = modoEdicion ? edit.getNombre() : "";
    String editDesc      = modoEdicion ? edit.getDescripcion() : "";
    String editCategoria = modoEdicion ? edit.getCategoria() : "";
    String editPrecio    = modoEdicion ? String.valueOf(edit.getPrecio()) : "";
    String editStock     = modoEdicion ? String.valueOf(edit.getStock()) : "";

    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aegis ID - Inventario y Productos</title>
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&display=swap" rel="stylesheet"/>
    <style>html,body{margin:0;padding:0;}body{overscroll-behavior:none;}::-webkit-scrollbar{display:none;}</style>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
    tailwind.config={theme:{extend:{colors:{"on-surface":"#131b2e","primary":"#070235","secondary":"#4e45d5","surface-container":"#eaedff","surface":"#faf8ff","surface-container-low":"#f2f3ff","error":"#ba1a1a"}}}}
    </script>
</head>
<body class="bg-surface text-on-surface min-h-screen font-sans" style="font-family:'Plus Jakarta Sans',sans-serif;">
    <header class="bg-primary text-white shadow-md">
        <div class="max-w-7xl mx-auto px-6 h-20 flex items-center justify-between">
            <div class="flex items-center gap-3">
                <a href="bienvenido" class="w-10 h-10 rounded-xl bg-white/10 flex items-center justify-center hover:bg-white/20 transition-all"><span class="material-symbols-outlined text-white text-[24px]">arrow_back</span></a>
                <div><h1 class="text-lg font-bold tracking-tight">Módulo 6: Inventario y Productos</h1><p class="text-xs text-indigo-200">Control de stock y mercadería</p></div>
            </div>
            <a href="logout" class="px-4 py-2 rounded-xl bg-red-600/20 hover:bg-red-600 text-red-200 hover:text-white text-xs font-semibold transition-all border border-red-500/30 flex items-center gap-2">
                <span class="material-symbols-outlined text-[18px]">logout</span>
                <span>Salir</span>
            </a>
        </div>
    </header>
    <main class="max-w-7xl mx-auto px-6 py-10">
        <% if (!mensaje.isEmpty()) { %><div class="mb-6 p-4 rounded-xl text-sm font-semibold text-center <%= "error".equals(tipoMensaje) ? "bg-red-100 text-red-800 border border-red-300" : "bg-emerald-50 text-emerald-800 border border-emerald-200" %>"><%= mensaje %></div><% } %>
        <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
            <div class="bg-white p-6 rounded-2xl shadow-sm border border-slate-100 h-fit">
                <h2 class="text-xl font-bold text-slate-900 mb-1"><%= modoEdicion ? "Modificar Producto" : "Nuevo Producto" %></h2>
                <p class="text-xs text-slate-500 mb-6"><%= modoEdicion ? "Actualiza la información del ítem." : "Añade nuevos productos al catálogo." %></p>
                <form method="post" action="productos" class="space-y-4">
                    <input type="hidden" name="accion" value="<%= modoEdicion ? "actualizar" : "crear" %>">
                    <% if (modoEdicion) { %><input type="hidden" name="id_producto" value="<%= editId %>"><% } %>
                    <div><label class="text-xs text-slate-700 font-semibold block mb-1.5">Nombre del Producto</label><input type="text" name="nombre" required value="<%= editNombre %>" placeholder="Ej. Laptop HP 15" class="w-full h-[46px] px-4 rounded-xl border border-slate-200 text-sm outline-none focus:ring-2 focus:ring-secondary/40"/></div>
                    <div><label class="text-xs text-slate-700 font-semibold block mb-1.5">Descripción</label><input type="text" name="descripcion" value="<%= editDesc %>" placeholder="Detalles del producto" class="w-full h-[46px] px-4 rounded-xl border border-slate-200 text-sm outline-none focus:ring-2 focus:ring-secondary/40"/></div>
                    <div><label class="text-xs text-slate-700 font-semibold block mb-1.5">Categoría</label><input type="text" name="categoria" required value="<%= editCategoria %>" placeholder="Ej. Equipos de Cómputo" class="w-full h-[46px] px-4 rounded-xl border border-slate-200 text-sm outline-none focus:ring-2 focus:ring-secondary/40"/></div>
                    <div class="grid grid-cols-2 gap-3">
                        <div><label class="text-xs text-slate-700 font-semibold block mb-1.5">Precio Unitario</label><input type="number" step="0.01" name="precio" required value="<%= editPrecio %>" placeholder="0.00" class="w-full h-[46px] px-4 rounded-xl border border-slate-200 text-sm outline-none focus:ring-2 focus:ring-secondary/40"/></div>
                        <div><label class="text-xs text-slate-700 font-semibold block mb-1.5">Stock</label><input type="number" name="stock" required value="<%= editStock %>" placeholder="0" class="w-full h-[46px] px-4 rounded-xl border border-slate-200 text-sm outline-none focus:ring-2 focus:ring-secondary/40"/></div>
                    </div>
                    <div class="flex gap-3 pt-2">
                        <button type="submit" class="flex-1 h-[48px] rounded-xl bg-secondary hover:bg-indigo-700 text-white font-semibold text-sm shadow-md transition-all flex items-center justify-center gap-2">
                            <span class="material-symbols-outlined text-[20px]"><%= modoEdicion ? "save" : "add_box" %></span>
                            <span><%= modoEdicion ? "Actualizar Producto" : "Guardar Producto" %></span>
                        </button>
                        <% if (modoEdicion) { %><a href="productos" class="px-4 h-[48px] rounded-xl bg-slate-100 hover:bg-slate-200 text-slate-700 font-semibold text-sm transition-all flex items-center justify-center">Cancelar</a><% } %>
                    </div>
                </form>
            </div>
            <div class="lg:col-span-2 bg-white p-6 rounded-2xl shadow-sm border border-slate-100">
                <h2 class="text-xl font-bold text-slate-900 mb-1">Inventario de Productos</h2>
                <p class="text-xs text-slate-500 mb-6">Listado general de mercadería y stock disponible.</p>
                <div class="overflow-x-auto">
                    <table class="w-full text-left border-collapse">
                        <thead>
                            <tr class="border-b border-slate-100 text-xs text-slate-400 uppercase tracking-wider">
                                <th class="py-3 px-4 font-semibold">ID</th>
                                <th class="py-3 px-4 font-semibold">PRODUCTO / DESC</th>
                                <th class="py-3 px-4 font-semibold">CATEGORÍA</th>
                                <th class="py-3 px-4 font-semibold">PRECIO / STOCK</th>
                                <th class="py-3 px-4 font-semibold text-center">ACCIONES</th>
                            </tr>
                        </thead>
                        <tbody class="text-sm divide-y divide-slate-100">
                            <%
                                if (productos != null) {
                                    for (Producto p : productos) {
                                        String idPr   = String.valueOf(p.getId());
                                        String nomPr  = p.getNombre();
                                        String desPr  = p.getDescripcion();
                                        String catPr  = p.getCategoria();
                                        String prePr  = String.format("%.2f", p.getPrecio());
                                        String stkPr  = String.valueOf(p.getStock());
                            %>
                            <tr class="hover:bg-slate-50/50">
                                <td class="py-3.5 px-4 text-slate-600 font-medium">#<%= idPr %></td>
                                <td class="py-3.5 px-4">
                                    <div class="font-semibold text-slate-900"><%= nomPr %></div>
                                    <div class="text-xs text-slate-400"><%= desPr.isEmpty() ? "Sin descripción" : desPr %></div>
                                </td>
                                <td class="py-3.5 px-4"><span class="px-2.5 py-1 rounded-full bg-blue-50 text-blue-600 text-xs font-semibold"><%= catPr %></span></td>
                                <td class="py-3.5 px-4 text-xs">
                                    <div class="font-semibold text-slate-900">$ <%= prePr %></div>
                                    <div class="text-emerald-600 font-medium">Stock: <%= stkPr %></div>
                                </td>
                                <td class="py-3.5 px-4 text-center">
                                    <div class="flex items-center justify-center gap-2">
                                        <a href="productos?accion=preeditar&id=<%= idPr %>" class="inline-flex items-center justify-center w-8 h-8 rounded-lg bg-indigo-50 text-secondary hover:bg-indigo-100 transition-all" title="Modificar"><span class="material-symbols-outlined text-[18px]">edit</span></a>
                                        <a href="productos?accion=eliminar&id=<%= idPr %>" onclick="return confirm('¿Estás seguro de eliminar este producto?');" class="inline-flex items-center justify-center w-8 h-8 rounded-lg bg-red-50 text-red-600 hover:bg-red-100 transition-all" title="Eliminar"><span class="material-symbols-outlined text-[18px]">delete</span></a>
                                    </div>
                                </td>
                            </tr>
                            <% 
                                    }
                                } 
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>
</body>
</html>