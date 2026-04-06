package com.politecnicomalaga.appalmacen.model;
import java.util.Comparator;


/**
 * Write a description of class CoProductoxPrecio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CoProductoxPrecio implements Comparator<Producto>
{
    //En este metodo se pierden los decimales y puede hacer que no se ordene bien
    /*
    @Override
    public int compare(Producto p1,Producto p2){
        return (int)(p1.getPrecio()-p2.getPrecio());
    }*/
    @Override
    public int compare(Producto p1, Producto p2){
        return Double.compare(p1.getPrecio(), p2.getPrecio());
    }
}