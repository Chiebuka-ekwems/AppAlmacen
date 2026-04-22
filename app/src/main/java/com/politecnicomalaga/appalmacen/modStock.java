package com.politecnicomalaga.appalmacen;

import android.os.Bundle;
import android.service.controls.Control;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.politecnicomalaga.appalmacen.controller.Controlador;
import com.politecnicomalaga.appalmacen.controller.PantallaReaccionable;
import com.politecnicomalaga.appalmacen.model.Producto;

public class modStock extends AppCompatActivity implements PantallaReaccionable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mod_stock);
        Controlador.getSingleton(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private Producto miProducto;

    @Override
    public void reaccionar(String mensaje) {
        runOnUiThread(() -> {
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            if (mensaje.contains("éxito")) finish();
        });
    }

    public void buscarProducto (View view) {
        TextView tvcodigo = findViewById(R.id.etCodigo);
        String codigo = tvcodigo.getText().toString();

        miProducto = Controlador.getSingleton().getProductoPorCode(codigo);

        TextView stockActual = findViewById(R.id.tvStockActual2);
        if(miProducto != null){
            stockActual.setText(String.valueOf(miProducto.getStock()));
        } else {
            Toast.makeText(this, "Error: Producto no encontrado", Toast.LENGTH_SHORT).show();
            stockActual.setText("");
        }


    }

    public void modStock2 (View view) {

        if (miProducto == null) {
            Toast.makeText(this, "Por favor, busca un producto primero", Toast.LENGTH_SHORT).show();
            return;
        }


        TextView tvStock = findViewById(R.id.tvStockNuevo2);
        EditText etStock = findViewById(R.id.etStockMod);

        try {


            int stock = Integer.parseInt(etStock.getText().toString());

            int nuevoStock = miProducto.getStock() - stock;
            tvStock.setText(String.valueOf(nuevoStock));

            boolean exito = Controlador.getSingleton().updateStock(miProducto.getCodigoProducto(), stock, this);

            if (exito) {
                Toast.makeText(this, "Stock modificado con éxito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al modificar el stock", Toast.LENGTH_SHORT).show();
            }
        }catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, introduce un número válido para el stock", Toast.LENGTH_SHORT).show();
        }
    }


}