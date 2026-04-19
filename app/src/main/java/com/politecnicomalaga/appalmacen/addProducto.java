package com.politecnicomalaga.appalmacen;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.politecnicomalaga.appalmacen.controller.Controlador;

import com.politecnicomalaga.appalmacen.controller.PantallaReaccionable;

public class addProducto extends AppCompatActivity implements PantallaReaccionable {

    private EditText etCodigo, etDescripcion, etPrecio, etStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_producto);
        Controlador.getSingleton(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void reaccionar(String mensaje) {
        runOnUiThread(() -> {
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            if (mensaje.contains("éxito")) finish();
        });
    }


    // Este es el método que llama el botón "Crear producto" (android:onClick="crearProducto")
    public void crearProducto(View view) {

        // Inicializamos las referencias a los componentes de la vista
        etCodigo = findViewById(R.id.etCodigo);
        etDescripcion = findViewById(R.id.etDescripcion);
        etPrecio = findViewById(R.id.etPrecio);
        etStock = findViewById(R.id.etStock);


        String codigo = etCodigo.getText().toString();
        String desc = etDescripcion.getText().toString();
        String precio = etPrecio.getText().toString();
        String stock = etStock.getText().toString();

        // Validamos que no haya campos vacíos
        if (codigo.isEmpty() || desc.isEmpty() || precio.isEmpty() || stock.isEmpty()) {
            Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonObject jsonProducto = new JsonObject();
        jsonProducto.addProperty("codigoProducto", codigo);
        jsonProducto.addProperty("descripcion", desc);
        jsonProducto.addProperty("precio", precio);
        jsonProducto.addProperty("stock", stock);

        String jsonFinal = jsonProducto.toString();



        //Formato mapa
        /*Map<String, String> datos = new HashMap<>();
        datos.put("codigo", codigo);
        datos.put("descripcion", desc);
        datos.put("precio", precio);
        datos.put("stock", stock);*/

        // Formateamos el string CSV como lo espera tu Controlador.addProductoN
        //String csv = codigo + ";" + desc + ";" + precio + ";" + stock;

        // Llamamos al controlador
        boolean exito = Controlador.getSingleton().addProductoN(jsonFinal);

        if (exito) {
            Toast.makeText(this, "Producto añadido con éxito", Toast.LENGTH_SHORT).show();
            finish(); // Cerramos la actividad y volvemos atrás
            //Actualizar la vista
        } else {
            Toast.makeText(this, "Error: El código ya existe o el formato es incorrecto", Toast.LENGTH_LONG).show();
        }
    }
}