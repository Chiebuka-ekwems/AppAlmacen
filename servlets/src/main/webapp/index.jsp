<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>App Almacén - Panel Web</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f5f5f5; margin: 0; padding: 20px; }
        h1 { text-align: center; color: #333; }
        .grid-container { display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 20px; max-width: 1200px; margin: 0 auto; }
        .card { background-color: white; padding: 20px; border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
        .card h2 { margin-top: 0; font-size: 1.2em; border-bottom: 2px solid #5BE761; padding-bottom: 10px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; font-size: 0.9em; }
        input[type="text"], input[type="number"] { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 5px; }
        button { width: 100%; padding: 10px; background-color: #5BE761; color: black; border: none; border-radius: 5px; font-weight: bold; cursor: pointer; transition: background-color 0.3s; margin-top: 10px; }
        button:hover { background-color: #45c94b; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; font-size: 0.9em; }
        th { background-color: #5BE761; color: black; }
        .table-container { grid-column: 1 / -1; } /* Hace que la tabla ocupe todo el ancho inferior */
    </style>
</head>
<body>

<h1>📦 Gestión de Almacén</h1>

<div class="grid-container">

    <div class="card">
        <h2>Añadir Producto</h2>
        <form id="formAdd" onsubmit="addProducto(event)">
            <div class="form-group"><label>Código:</label><input type="text" id="addCodigo" required></div>
            <div class="form-group"><label>Descripción:</label><input type="text" id="addDesc" required></div>
            <div class="form-group"><label>Precio:</label><input type="number" id="addPrecio" step="0.01" required></div>
            <div class="form-group"><label>Stock Inicial:</label><input type="number" id="addStock" required></div>
            <div class="form-group">
                <label><input type="checkbox" id="chkPerecedero" onchange="toggleFecha()"> Es Perecedero</label>
            </div>
            <div class="form-group" id="divFecha" style="display:none;">
                <label>Fecha Caducidad (AAAAMMDD):</label><input type="text" id="addFecha">
            </div>
            <button type="submit">Añadir</button>
        </form>
    </div>

    <div class="card">
        <h2>Modificar Stock</h2>
        <form id="formMod" onsubmit="modStock(event)">
            <div class="form-group"><label>Código del Producto:</label><input type="text" id="modCodigo" required></div>
            <div class="form-group">
                <label>Cantidad a sumar/restar (ej: 5 o -5):</label>
                <input type="number" id="modCantidad" required>
            </div>
            <button type="submit">Actualizar Stock</button>
        </form>
    </div>

    <div class="card">
        <h2>Retirar/Eliminar Producto</h2>
        <form id="formDel" onsubmit="delProducto(event)">
            <div class="form-group"><label>Código del Producto:</label><input type="text" id="delCodigo" required></div>
            <button type="submit" style="background-color: #ff4c4c; color: white;">Eliminar</button>
        </form>
    </div>

    <div class="card table-container">
        <h2>Inventario Actual <button style="width: auto; padding: 5px 15px; float: right; margin: 0;" onclick="cargarProductos()">↻ Actualizar</button></h2>
        <table id="tablaProductos">
            <thead>
            <tr>
                <th>Código</th>
                <th>Descripción</th>
                <th>Precio (€)</th>
                <th>Stock</th>
                <th>Caducidad</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

</div>

<script>
    // --- UTILIDADES DE INTERFAZ ---
    function toggleFecha() {
        const chk = document.getElementById("chkPerecedero");
        const div = document.getElementById("divFecha");
        div.style.display = chk.checked ? "block" : "none";
    }

    // --- CONEXIÓN CON EL BACKEND (AJAX/FETCH) ---
    // ⚠️ NOTA: Deberás cambiar las URLs ('/api/productos') por la ruta real de tus Servlets.

    // 1. Cargar Productos
    function cargarProductos() {
        // Ejemplo de llamada a tu Servlet

        fetch('listar')
            .then(response => response.json())
            .then(data => {
                renderizarTabla(data);
            });


        // Datos de prueba (MOCK) para que veas cómo queda visualmente hasta que conectes el Servlet
        /*
        const datosPrueba = [
            { codigo: "TECL5678X", descripcion: "Teclado RGB", precio: 89.99, stock: 45, caducidad: "N/A" },
            { codigo: "LECHE123", descripcion: "Leche Entera", precio: 1.20, stock: 100, caducidad: "20241231" }
        ];
        renderizarTabla(datosPrueba);
        */
    }

    function renderizarTabla(productos) {
        const tbody = document.querySelector("#tablaProductos tbody");
        tbody.innerHTML = ""; // Limpiamos tabla
        productos.forEach(p => {
            const tr = document.createElement("tr");
            tr.innerHTML = `
                    <td>${p.codigo}</td>
                    <td>${p.descripcion}</td>
                    <td>${p.precio}</td>
                    <td>${p.stock}</td>
                    <td>${p.fecha_caducidad || "-"}</td>
                `;
            tbody.appendChild(tr);
        });
    }

    // 2. Añadir Producto
    function addProducto(event) {
        event.preventDefault(); // Evita que la página recargue

        const producto = {
            codigo: document.getElementById("addCodigo").value,
            descripcion: document.getElementById("addDesc").value,
            precio: parseFloat(document.getElementById("addPrecio").value),
            stock: parseInt(document.getElementById("addStock").value),
            esPerecedero: document.getElementById("chkPerecedero").checked,
            caducidad: document.getElementById("addFecha").value
        };

        console.log("Enviando a Servlet:", producto);
        alert("Lógica para añadir producto enviada (Ver consola)");

        // Aquí harías el fetch a tu Servlet de Añadir
        /*
        fetch('/AddProductoServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(producto)
        }).then(() => cargarProductos());
        */
    }

    // 3. Modificar Stock
    function modStock(event) {
        event.preventDefault();
        const codigo = document.getElementById("modCodigo").value;
        const cantidad = parseInt(document.getElementById("modCantidad").value);

        console.log(`Modificando stock de ${codigo}: sumar ${cantidad}`);
        alert(`Petición de stock enviada para ${codigo} (Ver consola)`);

        // Fetch a tu Servlet de Modificar Stock
        /*
        fetch('/ModStockServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `codigo=${codigo}&cantidad=${cantidad}`
            }).then(() => cargarProductos());
            */
    }

    // 4. Eliminar Producto
    function delProducto(event) {
        event.preventDefault();
        const codigo = document.getElementById("delCodigo").value;

        if(confirm(`¿Seguro que quieres retirar el producto ${codigo}?`)) {
            console.log(`Eliminando ${codigo}`);
            alert(`Petición de eliminación enviada para ${codigo}`);

            // Fetch a tu Servlet de Eliminar
            /*
            fetch('/DelProductoServlet', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: `codigo=${codigo}`
                }).then(() => cargarProductos());
                */
        }
    }

    // Cargar los productos al abrir la página
    window.onload = cargarProductos;
</script>
</body>
</html>


