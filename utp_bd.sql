-- Creación bd
CREATE DATABASE bd_utp;

-- Uso de la base de datos
USE bd_utp;

-- Creación de la tabla Categorias
CREATE TABLE categorias (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(30) UNIQUE NOT NULL
);

-- Creación de la tabla Productos
CREATE TABLE productos (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  imagen VARCHAR(200) NOT NULL,
  precio DECIMAL(10, 2) NOT NULL,
  stock INT NOT NULL,
  categoria_id INT NOT NULL
);

-- Creación de la tabla Pedidos
CREATE TABLE pedidos (
  id INT PRIMARY KEY AUTO_INCREMENT,
  fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
  total DECIMAL(10, 2)
);

-- Creación de la tabla intermedia DetallePedido
CREATE TABLE detalle_pedido (
  id INT PRIMARY KEY AUTO_INCREMENT,
  pedido_id INT NOT NULL,
  producto_id INT NOT NULL,
  cantidad INT NOT NULL,
  precio_unitario DECIMAL(10, 2) NOT NULL,

  FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
  FOREIGN KEY (producto_id) REFERENCES productos(id)
);

-- Agregar la clave foránea a la tabla productos
ALTER TABLE productos
ADD CONSTRAINT fk_categoria
FOREIGN KEY (categoria_id) REFERENCES categorias(id);

-- ================================== INSERCIONES ======================================--

-- Inserciones en la tabla Productos
INSERT INTO productos (id, nombre, imagen, precio, stock, categoria_id) VALUES
(1, 'Audifonos', 'https://images.pexels.com/photos/16688488/pexels-photo-16688488/free-photo-of-blanco-y-negro-escritorio-ordenador-portatil-oficina.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 30.00, 10, 1),
(2, 'Disco Duro Externo', 'https://images.pexels.com/photos/20076003/pexels-photo-20076003/free-photo-of-cable-tecnologia-portatil-electronica.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 200.00, 10, 2),
(3, 'Camara Pro', 'https://images.pexels.com/photos/11002709/pexels-photo-11002709.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 400.00, 10, 3),
(4, 'Auriculares', 'https://images.pexels.com/photos/3921803/pexels-photo-3921803.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 40.00, 10, 1),
(5, 'Siri', 'https://images.pexels.com/photos/1034653/pexels-photo-1034653.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 150.00, 10, 4),
(6, 'Tarjeta de Video', 'https://images.pexels.com/photos/4581903/pexels-photo-4581903.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 300.00, 10, 4),
(7, 'Celular', 'https://images.pexels.com/photos/10343717/pexels-photo-10343717.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 800.00, 10, 4),
(8, 'Laptop', 'https://images.pexels.com/photos/577210/pexels-photo-577210.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 3200.00, 10, 4);

-- FORMATO IMAGEN:
UPDATE productos SET imagen = REPLACE(REPLACE(imagen, '\\u0026', '&'), '\\u003e', '>');

-- Inserciones en la tabla Categorias
INSERT INTO categorias (nombre) VALUES
('Audifonos'),
('Almacenamiento'),
('Camaras'),
('Componentes');
