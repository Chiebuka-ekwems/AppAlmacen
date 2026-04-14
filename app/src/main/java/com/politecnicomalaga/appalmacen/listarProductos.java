package com.politecnicomalaga.appalmacen;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.politecnicomalaga.appalmacen.controller.Controlador;
import com.politecnicomalaga.appalmacen.model.Producto;

import java.util.List;

public class listarProductos extends AppCompatActivity {

    private List<Producto> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar_productos);

        ListView productos = findViewById(R.id.lvProductos);
        lista = Controlador.getSingleton().getListaCompleta();

        // 2. Crear el Adaptador Personalizado
        ArrayAdapter<Producto> adapter = new ArrayAdapter<Producto>(this, R.layout.fila_producto, lista) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.fila_producto, parent, false);
                }

                TextView tvCod = convertView.findViewById(R.id.txtFilaCodigo);
                TextView tvDesc = convertView.findViewById(R.id.txtFilaDesc);
                TextView tvPrecio = convertView.findViewById(R.id.txtFilaPrecio);
                TextView tvStock = convertView.findViewById(R.id.txtFilaStock);

                Producto p = lista.get(position);

                tvCod.setText(p.getCodigoProducto());
                tvDesc.setText(p.getDescripcion());
                tvPrecio.setText(String.format("%.2f€", p.getPrecio()));
                tvStock.setText("Stock: " + p.getStock());

                return convertView;
            }
        };

        productos.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //He pasado este codigo al onCreate ya que No se va a llamar el metodo con un boton en ningun momento si no que se realizara el codigo de una
    /*public void listandoProductos (){
        TextView productos = findViewById(R.id.lvProductos);
        productos.setText(Controlador.getSingleton().listarProductos());

    }*/

    //Metodos para que la informacion de los productos salga de forma correcta por pantalla
    public String formatearDatosParaPantalla(String datos) {
        StringBuilder resultadoFinal = new StringBuilder();

        // 1. CREAMOS TU CABECERA FIJA PERSONALIZADA// Definimos los nombres que queremos ver
        String cabeceraManual = "CODIGO;DESCRIPCION;PRECIO;STOCK";

        // La formateamos y añadimos una línea decorativa
        resultadoFinal.append(formatearLinea(cabeceraManual)).append("\n");
        resultadoFinal.append("------------------------------------------------------------\n");

        // 2. AÑADIMOS LOS DATOS
        if (datos == null || datos.trim().isEmpty()) {
            resultadoFinal.append("No hay productos registrados.");
        } else {
            String[] lineas = datos.split("\n");
            for (String l : lineas) {
                if (!l.trim().isEmpty()) {
                    resultadoFinal.append(formatearLinea(l)).append("\n");
                }
            }
        }

        return resultadoFinal.toString();
    }

    private String formatearLinea(String linea) {
        String[] columnas = linea.split(";");
        StringBuilder lineaFormateada = new StringBuilder();

        // ESTOS ANCHOS DEBEN COINCIDIR CON TU CABECERA MANUAL
        // Ajusta los números según lo largo que sea cada campo
        int[] anchos = {10, 20, 10, 8};

        for (int i = 0; i < columnas.length; i++) {
            String texto = columnas[i];
            int anchoMax = (i < anchos.length) ? anchos[i] : 15;

            lineaFormateada.append("| ");
            lineaFormateada.append(texto);

            // Rellenar con espacios hasta completar el ancho de la columna
            int espacios = anchoMax - texto.length();
            for (int j = 0; j < espacios; j++) {
                lineaFormateada.append(" ");
            }
        }
        lineaFormateada.append("|");
        return lineaFormateada.toString();
    }
}