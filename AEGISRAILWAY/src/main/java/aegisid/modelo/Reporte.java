package aegisid.modelo;

public class Reporte {
    private int idReporte;
    private String tipoReporte;
    private String fechaInicio;
    private String fechaFin;
    private String resultado;
    
    // Campos métricos de compatibilidad
    private int totalUsuarios, totalClientes, totalProveedores, totalProductos, totalVentas;
    private double ingresosTotales;

    // Constructor para el reporte analítico general
    public Reporte(int idReporte, String tipoReporte, String fechaInicio, String fechaFin, String resultado) {
        this.idReporte = idReporte;
        this.tipoReporte = tipoReporte;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.resultado = resultado;
    }

    // Constructor métrico original
    public Reporte(int totalUsuarios, int totalClientes, int totalProveedores,
                   int totalProductos, int totalVentas, double ingresosTotales) {
        this.totalUsuarios = totalUsuarios; 
        this.totalClientes = totalClientes;
        this.totalProveedores = totalProveedores; 
        this.totalProductos = totalProductos;
        this.totalVentas = totalVentas; 
        this.ingresosTotales = ingresosTotales;
    }

    public void generarReporteVentas() {
        this.tipoReporte = "Reporte de Ventas";
        this.resultado = "Datos de ventas procesados exitosamente.";
    }

    public void generarReporteStock() {
        this.tipoReporte = "Reporte de Stock";
        this.resultado = "Inventario actualizado y verificado.";
    }

    public void generarReporteIngresos() {
        this.tipoReporte = "Reporte de Ingresos";
        this.resultado = "Consolidado financiero generado.";
    }

    public void exportarPDF() {
        // Lógica exclusiva para exportar a PDF
    }

    public int getIdReporte() { return idReporte; }
    public String getTipoReporte() { return tipoReporte; }
    public String getFechaInicio() { return fechaInicio; }
    public String getFechaFin() { return fechaFin; }
    public String getResultado() { return resultado; }

    public int getTotalUsuarios() { return totalUsuarios; }
    public int getTotalClientes() { return totalClientes; }
    public int getTotalProveedores() { return totalProveedores; }
    public int getTotalProductos() { return totalProductos; }
    public int getTotalVentas() { return totalVentas; }
    public double getIngresosTotales() { return ingresosTotales; }
}