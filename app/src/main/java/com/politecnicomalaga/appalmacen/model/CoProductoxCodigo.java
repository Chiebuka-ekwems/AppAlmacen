package com.politecnicomalaga.appalmacen.model;
import java.util.Comparator;


/**
 * Write a description of class CoProductoxCodigo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CoProductoxCodigo implements Comparator<Producto>
{
    @Override
    public int compare(Producto p1, Producto p2){
        return p1.getCodigoProducto().compareTo(p2.getCodigoProducto());
    }
}