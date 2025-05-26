CREATE DATABASE IF NOT EXISTS utp_bd;
USE utp_bd;

-- Tabla de roles (opcional pero útil para distinguir entre cliente/admin)
CREATE TABLE rol (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL
);

-- Tabla de usuarios
CREATE TABLE usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255),
  correo VARCHAR(255),
  clave VARCHAR(255),
  rol_id INT,
  FOREIGN KEY (rol_id) REFERENCES rol(id)
);

-- Tabla de categorías
CREATE TABLE categoria (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255)
);

-- Tabla de productos
CREATE TABLE producto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255),
  imagen VARCHAR(255),
  precio DOUBLE,
  stock INT,
  categoria_id INT,
  FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

-- Tabla de pedidos (reemplaza a 'orden')
CREATE TABLE pedido (
  id INT AUTO_INCREMENT PRIMARY KEY,
  usuario_id INT,
  fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
  total DOUBLE,
  FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Detalles de cada pedido
CREATE TABLE detalle_pedido (
  id INT AUTO_INCREMENT PRIMARY KEY,
  pedido_id INT,
  producto_id INT,
  cantidad INT,
  precio_unitario DOUBLE,
  FOREIGN KEY (pedido_id) REFERENCES pedido(id),
  FOREIGN KEY (producto_id) REFERENCES producto(id)
);
