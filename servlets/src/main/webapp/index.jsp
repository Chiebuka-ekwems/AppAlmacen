<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>App Almacén - Inicio</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .main-container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 350px;
            text-align: center;
        }
        h1 {
            color: #333333;
            font-size: 24px;
            margin-bottom: 30px;
        }
        .android-btn {
            display: block;
            width: 100%;
            box-sizing: border-box;
            padding: 14px;
            margin-bottom: 15px;
            background-color: #6200EA; /* Color primario estilo Android Material Design */
            color: #ffffff;
            text-decoration: none;
            font-size: 16px;
            font-weight: bold;
            border-radius: 6px;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .android-btn:hover {
            background-color: #3700B3; /* Color oscuro al pasar el ratón */
        }
    </style>
</head>
<body>

<div class="main-container">
    <h1>Gestión de Almacen</h1>

    <a href="addProducto" class="android-btn">Añadir Producto</a>
    <a href="addProductoPe" class="android-btn">Añadir Producto Perecedero</a>
    <a href="listarProductos" class="android-btn">Listar Productos</a>
    <a href="modStock" class="android-btn">Modificar Stock</a>
    <a href="retirarProducto" class="android-btn">Retirar Producto</a>
</div>

</body>
</html>

