package aegisid.modelo;

import java.util.List;

public class Venta {
    private final int idVenta;
    private final String fecha;
    private double total;
    private final String tipoComprobante;
    private final String estado;
    private final int idCliente;
    private String nombreCliente; // Para mostrarlo en la vista
    private List<DetalleVenta> detalles;

    public Venta(int idVenta, String fecha, double total, String tipoComprobante, String estado, int idCliente) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.total = total;
        this.tipoComprobante = tipoComprobante;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    // Métodos exigidos por la rúbrica
    public double calcularTotal(List<DetalleVenta> listaDetalles) {
        double suma = 0;
        for (DetalleVenta d : listaDetalles) {
            suma += d.getSubtotal();
        }
        this.total = suma;
        return this.total;
    }

    public String generarComprobante() {
        return tipoComprobante + " N° 001-" + String.format("%05d", idVenta);
    }

    public int getId() { return idVenta; }
    public int getIdVenta() { return idVenta; }
    public String getFecha() { return fecha; }
    public double getTotal() { return total; }
    public String getTipoComprobante() { return tipoComprobante; }
    public String getEstado() { return estado; }
    public int getIdCliente() { return idCliente; }
    
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public List<DetalleVenta> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVenta> detalles) { this.detalles = detalles; }
}