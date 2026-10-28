-- Para MySQL (Railway)
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
INSERT IGNORE INTO usuarios (username, password) VALUES ('admin', '1234');
