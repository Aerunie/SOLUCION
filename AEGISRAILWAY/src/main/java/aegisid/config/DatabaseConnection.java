package aegisid.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static DatabaseConnection getInstance() {
        return new DatabaseConnection();
    }

    private static String env(String nombre) {
        String v = System.getenv(nombre);
        return (v == null || v.trim().isEmpty()) ? null : v.trim();
    }

    public Connection getConnection() throws SQLException {
        // Opción 1: DB_URL / DB_USER / DB_PASSWORD
        String url = env("DB_URL");
        String user = env("DB_USER");
        String pass = env("DB_PASSWORD");

        // Opción 2: variables que entrega Railway MySQL
        if (url == null && env("MYSQLHOST") != null) {
            String puerto = env("MYSQLPORT") != null ? env("MYSQLPORT") : "3306";
            url = "jdbc:mysql://" + env("MYSQLHOST") + ":" + puerto + "/" + env("MYSQLDATABASE")
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=UTF-8";
            user = env("MYSQLUSER");
            pass = env("MYSQLPASSWORD");
        }

        if (url == null) {
            throw new SQLException("Falta configurar la base de datos (variables DB_URL, DB_USER y DB_PASSWORD).");
        }
        return DriverManager.getConnection(url, user, pass);
    }
}
