package aegisid.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.Statement;

@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement st = conn.createStatement()) {

            st.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios ("
                + "id_usuario INT AUTO_INCREMENT PRIMARY KEY, "
                + "correo VARCHAR(100) NOT NULL UNIQUE, "
                + "clave VARCHAR(100) NOT NULL, "
                + "username VARCHAR(100) NULL, "
                + "rol VARCHAR(50) DEFAULT 'Administrador', "
                + "estado VARCHAR(15) DEFAULT 'Activo', "
                + "nombres VARCHAR(100) NULL, "
                + "apellidos VARCHAR(100) NULL, "
                + "dni VARCHAR(20) NULL, "
                + "telefono VARCHAR(20) NULL, "
                + "intentos_fallidos INT DEFAULT 0, "
                + "bloqueado BOOLEAN DEFAULT FALSE)");

            st.executeUpdate("INSERT IGNORE INTO usuarios (correo, clave, username, rol, estado, nombres, intentos_fallidos, bloqueado) "
                + "VALUES ('admin@correo.com', '1234', 'admin@correo.com', 'Administrador', 'Activo', 'Administrador', 0, FALSE)");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS clientes ("
                + "id_cliente INT AUTO_INCREMENT PRIMARY KEY, "
                + "nombre VARCHAR(100) NOT NULL, "
                + "apellido VARCHAR(100) NULL, "
                + "dni_ruc VARCHAR(20) NULL, "
                + "correo VARCHAR(100) NULL, "
                + "telefono VARCHAR(20) NULL, "
                + "direccion VARCHAR(200) NULL, "
                + "estado VARCHAR(15) DEFAULT 'Activo')");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS proveedores ("
                + "id_proveedor INT AUTO_INCREMENT PRIMARY KEY, "
                + "razon_social VARCHAR(150) NOT NULL, "
                + "ruc VARCHAR(20) NULL, "
                + "telefono VARCHAR(20) NULL, "
                + "direccion VARCHAR(200) NULL, "
                + "correo VARCHAR(100) NULL, "
                + "estado VARCHAR(15) DEFAULT 'Activo')");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS productos ("
                + "id_producto INT AUTO_INCREMENT PRIMARY KEY, "
                + "nombre VARCHAR(150) NOT NULL, "
                + "descripcion VARCHAR(255) NULL, "
                + "precio DECIMAL(10,2) NOT NULL, "
                + "stock INT NOT NULL DEFAULT 0, "
                + "categoria VARCHAR(100) NULL, "
                + "estado VARCHAR(15) DEFAULT 'Disponible')");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS ventas ("
                + "id_venta INT AUTO_INCREMENT PRIMARY KEY, "
                + "fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                + "total DECIMAL(10,2) NOT NULL, "
                + "tipo_comprobante VARCHAR(50) NULL, "
                + "estado VARCHAR(20) DEFAULT 'Registrada', "
                + "id_cliente INT NOT NULL)");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS detalle_venta ("
                + "id_detalle INT AUTO_INCREMENT PRIMARY KEY, "
                + "id_venta INT NOT NULL, "
                + "id_producto INT NOT NULL, "
                + "cantidad INT NOT NULL, "
                + "precio_unitario DECIMAL(10,2) NOT NULL, "
                + "subtotal DECIMAL(10,2) NOT NULL)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
