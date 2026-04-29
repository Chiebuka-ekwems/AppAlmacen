// Archivo: webapp/js/app.js

function insertarProducto() {
    // Recoger los valores del formulario
    const codigo = document.getElementById("addCodigo").value;
    const desc = document.getElementById("addDesc").value;
    const precio = document.getElementById("addPrecio").value;
    const stock = document.getElementById("addStock").value;
    const fechaCaducidad = document.getElementById("fechaCaducidad").value;

    // Validación básica
    if (!codigo || !desc || !precio || !stock) {
        alert("Por favor, rellena todos los campos obligatorios.");
        return;
    }

    // Construir la URL con los parámetros
    let url = `insertar?codigo=${encodeURIComponent(codigo)}&descripcion=${encodeURIComponent(desc)}&precio=${encodeURIComponent(precio)}&stock=${encodeURIComponent(stock)}`;

    // Si se ha introducido fecha, la añadimos a la URL para que se guarde como producto perecedero
    if (fechaCaducidad) {
        url += `&fechaCaducidad=${encodeURIComponent(fechaCaducidad)}`;
    }

    console.log("Enviando petición a:", url);

    // Hacer la petición GET al servlet
    fetch(url)
        .then(response => {
            if (!response.ok) throw new Error("Error en la respuesta del servidor");
            return response.json();
        })
        .then(data => {
            console.log("Respuesta del servidor:", data);
            alert("Producto añadido correctamente.");

            // Recargar la tabla para mostrar el nuevo producto
            cargarProductos();

            // Limpiar el formulario
            document.getElementById("formAdd").reset();
        })
        .catch(error => {
            console.error('Error al insertar:', error);
            alert("Hubo un error al añadir el producto.");
        });


}

// Esperar a que el documento HTML cargue por completo
document.addEventListener("DOMContentLoaded", function() {

    // Buscar el botón por su ID y decirle que escuche el evento 'click'
    const btnAnadir = document.getElementById("btnAnadir");

    if (btnAnadir) {
        btnAnadir.addEventListener("click", insertarProducto);
    }

});


function cargarProductos() {
    console.log("Cargando productos...");
    fetch('listar')
        .then(response => {
            if (!response.ok) throw new Error("Error en la respuesta del servidor");
            return response.json();
        })
        .then(data => {
            renderizarTabla(data);
        })
        .catch(error => {
            console.error('Error:', error);
            alert("No se pudo conectar con el servidor para listar los productos.");
        });
}

function renderizarTabla(productos) {
    const tbody = document.querySelector("#tablaProductos tbody");
    tbody.innerHTML = "";

    console.log("Datos crudos recibidos desde el Servlet:", productos);

    productos.forEach(p => {
        const tr = document.createElement("tr");

        let precioFormateado = (p.precio !== undefined && p.precio !== null)
            ? p.precio + " €"
            : "-";

        // Como esto ya es un .js puro, usamos ${...} normal sin la \
        tr.innerHTML = `
            <td>${p.codigoProducto || "-"}</td>
            <td>${p.descripcion || "-"}</td>
            <td>${precioFormateado}</td>
            <td>${p.stock !== undefined ? p.stock : "-"}</td>
            <td>${p.fechaCaducidad || "-"}</td>
        `;
        tbody.appendChild(tr);
    });
}

function actualizarStock() {
    const codigo = document.getElementById("modCodigo").value;
    const cantidad = document.getElementById("modCantidad").value;

    if (!codigo || !cantidad) {
        alert("Por favor, rellena el código y la cantidad.");
        return;
    }

    console.log(`Petición para actualizar stock: Cod=${codigo}, Cant=${cantidad}`);
    alert("Lógica de envío de datos preparada. Asegúrate de tener el Servlet de modificación creado en el backend.");
}

// Carga automática al abrir la página
window.onload = cargarProductos;