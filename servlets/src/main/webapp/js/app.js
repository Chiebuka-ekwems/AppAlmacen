// Archivo: webapp/js/app.js

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