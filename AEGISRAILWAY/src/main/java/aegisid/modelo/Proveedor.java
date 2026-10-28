package aegisid.modelo;

public class Proveedor {
    private final int id;
    private final String razonSocial, ruc, telefono, direccion, correo, estado;

    public Proveedor(int id, String razonSocial, String ruc, String telefono, String direccion, String correo, String estado) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.estado = estado;
    }

    public int getId() { return id; }
    public String getRazonSocial() { return razonSocial; }
    public String getRuc() { return ruc; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
    public String getCorreo() { return correo; }
    public String getEstado() { return estado; }
}