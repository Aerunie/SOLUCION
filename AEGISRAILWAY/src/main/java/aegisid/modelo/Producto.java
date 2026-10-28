package aegisid.modelo;

public class Producto {
    private final int id, stock;
    private final String nombre, descripcion, categoria, estado;
    private final double precio;

    public Producto(int id, String nombre, String descripcion, double precio, int stock, String categoria, String estado) {
        this.id = id; 
        this.nombre = nombre; 
        this.descripcion = descripcion;
        this.categoria = categoria; 
        this.precio = precio; 
        this.stock = stock; 
        this.estado = estado;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getEstado() { return estado; }
}