# Desarrollo Web Integrado - Sección 40860
## Docente: Omar Wilton SAAVEDRA SALAZAR
<center>
<img src="https://i.ibb.co/VjJkQwz/Captura-de-pantalla-2025-01-07-154518.png" style="width: 100% ; aspect-ratio:16/9">
</center>

# Sistema de Gestión de Ventas UTP Technology

Este es un proyecto full stack desarrollado con **Spring Boot** en el backend, **Angular 20** en el frontend y **MySQL** como base de datos. El sistema permite gestionar productos, clientes, usuarios, pedidos y comprobantes.

## 📌 Tecnologías utilizadas

- **Frontend:** Angular 20, TypeScript,
- **Backend:** Spring Boot, Spring Data JPA, Spring Security, JWT
- **Base de datos:** MySQL
- **Otros:** REST API, Maven

## 🧩 Estructura de la base de datos

La base de datos `utp_bd` contiene las siguientes tablas:

- `rol`: Roles de usuario (Administrador, Vendedor, Cliente)
- `usuarios`: Usuarios con clave encriptada
- `clientes`: Información de clientes vinculados a usuarios
- `productos`: Lista de productos con imagen, precio y stock
- `pedidos`: Registra compras realizadas
- `detalles_pedido`: Detalles de cada producto dentro de un pedido
- `comprobantes`: Documento generado por cada pedido (boleta/factura)

> El script `ScriptUtpTechnology.sql` crea e inserta datos de prueba automáticamente.

## 🚀 Cómo ejecutar el proyecto

### 1. Base de datos

- Crear una base de datos MySQL ejecutando el script:


ScriptUtpTechnology.sql


<center>
<img src="https://img001.prntscr.com/file/img001/wiUNYs6QSBGwD0eriFXKxg.png" style="width: 100% ; aspect-ratio:16/9">
</center>
