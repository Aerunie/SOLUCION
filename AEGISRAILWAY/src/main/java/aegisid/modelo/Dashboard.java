package aegisid.modelo;

public class Dashboard {
    private String fechaActual;
    private double totalVentasDia;
    private String productoMasVendido;
    private int stockActual;

    public Dashboard(String fechaActual, double totalVentasDia, String productoMasVendido, int stockActual) {
        this.fechaActual = fechaActual;
        this.totalVentasDia = totalVentasDia;
        this.productoMasVendido = productoMasVendido;
        this.stockActual = stockActual;
    }

    // Métodos exigidos por la rúbrica
    public void mostrarResumen() {
        // Lógica para mostrar métricas generales
    }

    public void actualizarDashboard() {
        // Refrescar datos en tiempo real
    }

    public void filtrarPorFecha(String fechaInicio, String fechaFin) {
        // Filtrar métricas OLAP
    }

    public String getFechaActual() { return fechaActual; }
    public double getTotalVentasDia() { return totalVentasDia; }
    public String getProductoMasVendido() { return productoMasVendido; }
    public int getStockActual() { return stockActual; }
}