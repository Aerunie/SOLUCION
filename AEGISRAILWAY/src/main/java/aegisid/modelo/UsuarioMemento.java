package aegisid.modelo;

public class UsuarioMemento {
    private final String correo;
    private final String rol;
    private final String estado;

    public UsuarioMemento(String correo, String rol, String estado) {
        this.correo = correo;
        this.rol = rol;
        this.estado = estado;
    }

    public String getCorreo() { return correo; }
    public String getRol() { return rol; }
    public String getEstado() { return estado; }
}