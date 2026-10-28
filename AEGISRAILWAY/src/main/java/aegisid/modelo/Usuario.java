package aegisid.modelo;

public class Usuario {
    private final int id;
    private final String correo, clave, rol, estado, nombres, apellidos, dni, telefono;
    private final int intentosFallidos;
    private final boolean bloqueado;

    public Usuario(int id, String correo, String clave, String rol, String estado,
                   String nombres, String apellidos, String dni, String telefono,
                   int intentosFallidos, boolean bloqueado) {
        this.id = id; 
        this.correo = correo; 
        this.clave = clave; 
        this.rol = rol;
        this.estado = estado; 
        this.nombres = nombres; 
        this.apellidos = apellidos; 
        this.dni = dni; 
        this.telefono = telefono;
        this.intentosFallidos = intentosFallidos; 
        this.bloqueado = bloqueado;
    }

    public int getId() { return id; }
    public String getCorreo() { return correo; }
    public String getClave() { return clave; }
    public String getRol() { return rol; }
    public String getEstado() { return estado; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getDni() { return dni; }
    public String getTelefono() { return telefono; }
    public int getIntentosFallidos() { return intentosFallidos; }
    public boolean isBloqueado() { return bloqueado; }
}