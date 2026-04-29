<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>App Almacén - Panel Web</title>
    <link rel="stylesheet" href="css/style.css">
    <script defer src="js/app.js"></script>
</head>
<body>

<h1>App Almacén</h1>

<div class="grid-container">
    <div class="card">
        <h2>Añadir Producto</h2>
        <form id="formAdd">
            <div class="form-group"><label for="addCodigo">Código:</label><input type="text" id="addCodigo" required></div>
            <div class="form-group"><label for="addDesc">Descripción:</label><input type="text" id="addDesc" required></div>
            <div class="form-group"><label for="addPrecio">Precio:</label><input type="number" id="addPrecio" step="0.01" required></div>
            <div class="form-group"><label for="addStock">Stock:</label><input type="number" id="addStock" required></div>
            <div class="form-group"><label for="addFechaCad">Fecha de Caducidad (Opcional):</label><input type="date" id="addFechaCad"></div>

            <button type="button" id="btnAnadir">Añadir Producto</button>
        </form>
    </div>

    <div class="card">
        <h2>Modificar Stock</h2>
        <form id="formMod">
            <div class="form-group"><label for="modCodigo">Código del Producto:</label><input type="text" id="modCodigo" required></div>
            <div class="form-group"><label for="modCantidad">Cantidad a sumar/restar:</label><input type="number" id="modCantidad" required></div>
            <button type="button" onclick="actualizarStock()">Actualizar Stock</button>
        </form>
    </div>

    <div class="card">
        <h2>Retirar Producto</h2>
        <form id="formDel">
            <div class="form-group"><label for="delCodigo">Código del Producto:</label><input type="text" id="delCodigo" required></div>
            <button type="button" class="btn-danger">Eliminar Producto</button>
        </form>
    </div>

    <div class="card table-container">
        <h2>Inventario Actual <button type="button" onclick="cargarProductos()" style="width: auto; padding: 5px 15px; float: right; margin: 0;">↻ Actualizar Lista</button></h2>
        <table id="tablaProductos">
            <thead>
            <tr>
                <th>Código</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>Stock</th>
                <th>Caducidad</th>
            </tr>
            </thead>
            <tbody>
                </tbody>
        </table>
    </div>
</div>

</body>
</html>