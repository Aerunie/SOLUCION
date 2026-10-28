package aegisid.modelo;

public class Cliente implements Cloneable {
    private final int id;
    private final String nombre, apellido, dniRuc, correo, telefono, direccion, estado;

    public Cliente(int id, String nombre, String apellido, String dniRuc, String correo, String telefono, String direccion, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dniRuc = dniRuc;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
    }

    // Patrón Prototype: Permite clonar instancias del cliente
    public Cliente clonar() {
        return new Cliente(this.id, this.nombre, this.apellido, this.dniRuc, this.correo, this.telefono, this.direccion, this.estado);
    }

    @Override
    public Cliente clone() {
        return this.clonar();
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDniRuc() { return dniRuc; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
    public String getEstado() { return estado; }
}