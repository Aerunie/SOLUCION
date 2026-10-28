package aegisid.modelo;

public class UsuarioFactory {
    public static Usuario crearUsuario(int id, String correo, String clave, String rol, String estado, 
                                       String nombres, String apellidos, String dni, String telefono, 
                                       int intentosFallidos, boolean bloqueado) {
        return new Usuario(id, correo, clave, rol, estado, nombres, apellidos, dni, telefono, intentosFallidos, bloqueado);
    }
}