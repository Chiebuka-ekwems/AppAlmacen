package com.politecnicomalaga.appalmacen.dataservice;

import com.politecnicomalaga.appalmacen.model.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BBDDAccess implements DataService{
    @Override
    public boolean addProducto(Producto p) {
        //Vamos a intentar conectar con la BBDD

        //Mandar un insert

        return false;
    }

    @Override
    public List<Producto> listAll() {
        List<Producto> lista = new ArrayList<>();

        //Vamos a intentar conectar con la BBDD


        //Pedimos un select


        //COmo un select nos devuelve muchas lineas(cada linea un producto) necesitamos un bucle


        return lista;
    }
}
