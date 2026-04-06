package com.politecnicomalaga.appalmacen;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent; 
import android.view.View;
import android.widget.TextView;

import com.politecnicomalaga.appalmacen.controller.Controlador;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    
    public void AddProducto (View view) {
        Intent intent = new Intent(this, addProducto.class);
        startActivity(intent);
    }

    public void AddProductoP (View view){
        Intent intent = new Intent(this, addProductoPe.class);
        startActivity(intent);

    }

    public void ListProducts (View view){
        Intent intent = new Intent(this, listarProductos.class);
        startActivity(intent);

    }

    public void ModStock (View view){
        Intent intent = new Intent(this, modStock.class);
        startActivity(intent);

    }


}