package com.politecnicomalaga.appalmacen.dataservice;

import com.politecnicomalaga.appalmacen.model.Producto;
import com.politecnicomalaga.appalmacen.model.ProductoPerecedero;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BBDDAccess {

    //método para obtener los productos. Se conecta y ejecuta el select
    //No sabe se "vive" en una aplicación Java tradicional, en un ExecuteService de Android,
    //en un servlet... Simplemente "pide" a la BBDD la ejecución de SQL y obtiene los datos
    //para convertirlo en objetos del modelo.




    public interface OnBBDDCallback {
        void onSuccess(List<Producto> data);
        void onError(String error);
    }

    //método para obtener los productos. Se conecta y ejecuta el select
    public List<Producto> listarTodos() throws SQLException,ClassNotFoundException {

        Connection conn = null;
        List<Producto> listaResultado = new ArrayList<>();

        conn = ConexionDB.getConnection();

        // Productos normales
        String sql = "SELECT codigo, descripcion, precio, stock from Productos";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            listaResultado.add(new Producto(rs.getString("codigo"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")));
        }


        // Productos Perecederos
        sql = "SELECT codigo, descripcion, precio, stock, fecha_caducidad from Productos";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            listaResultado.add(new ProductoPerecedero(rs.getString("codigo"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getString("fecha_caducidad")
            ));
        }
        if (rs!=null) rs.close();
        if (stmt!=null) stmt.close();
        if (conn != null) conn.close();

        return listaResultado;

    }

    public List<Producto> buscarPorCodigo(String codigo) throws SQLException,ClassNotFoundException {

        Connection conn = null;
        List<Producto> listaResultado = new ArrayList<>();

        conn = ConexionDB.getConnection();

        // Productos normales
        String sql = "SELECT codigo, descripcion, precio, stock from Productos where codigo LIKE ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,"%"+codigo+"%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            listaResultado.add(new Producto(rs.getString("codigo"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")));
        }


        // Productos Perecederos
        sql = "SELECT codigo, descripcion, precio, stock, fecha_caducidad from Productos where codigo LIKE ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1,"%"+codigo+"%");
        rs = stmt.executeQuery();
        while (rs.next()) {
            listaResultado.add(new ProductoPerecedero(rs.getString("codigo"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getString("fecha_caducidad")
            ));
        }
        if (rs!=null) rs.close();
        if (stmt!=null) stmt.close();
        if (conn != null) conn.close();

        return listaResultado;

    }





    //Esta función es la que implementa realmente el acceso y el insert
    public void insertarProducto(Producto p) throws SQLException,ClassNotFoundException {

        PreparedStatement pstmt = null;
        Connection conn = ConexionDB.getConnection();

        String tabla = "Productos";
        String values = " (codigo, descripcion, precio, stock) VALUES (?, ?, ?, ?)";
        if (p instanceof ProductoPerecedero) {
            tabla = "ProductosPerecederos";
            values = " (codigo, descripcion, precio, stock, fecha) VALUES (?, ?, ?, ?, ?)";
        }
        String sql = "INSERT INTO " + tabla + values;


        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, p.getCodigoProducto());
        pstmt.setString(2, p.getDescripcion());
        pstmt.setDouble(3, p.getPrecio());
        pstmt.setInt(4, p.getStock());

        if (p instanceof ProductoPerecedero) {
            pstmt.setString(5,((ProductoPerecedero)p).getFechaCaducidad());
        }

        pstmt.executeUpdate();
    }


    /*
    public void insertarProductoP(final String codigo, final String descripcion,
                                 final double precio, final int stock,
                                 final OnBBDDCallback callback) {
        //El código a ejecutar, se lo pasamos al sistema con una Lambda
        executorService.execute(() -> {
            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
                // Cargar el driver (necesario en algunas versiones de Android)
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASS);
                String sql = "INSERT INTO Productos"

                        + " (codigo, descripcion, precio, stock) VALUES (?, ?, ?, ?)";

                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, codigo);
                pstmt.setString(2, descripcion);
                pstmt.setDouble(3, precio);
                pstmt.setInt(4, stock);

                pstmt.executeUpdate();
                // Si todo sale bien, notificamos al hilo principal
                if (callback != null) callback.onSuccess(null);
            } catch (Exception e) {
                if (callback != null) callback.onError(e.getMessage());
            } finally {
                try { if (pstmt != null) pstmt.close(); } catch (SQLException
                        ignored) {}
                try { if (conn != null) conn.close(); } catch (SQLException
                        ignored) {}
            }
        });
    }
    */

}
