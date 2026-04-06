package com.politecnicomalaga.appalmacen.model;
import java.util.Comparator;


/**
 * Write a description of class CoProductoxStock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CoProductoxStock implements Comparator<Producto>
{
    //De menor a mayor
    @Override
    public int compare(Producto p1,Producto p2){
        return (p1.getStock()-p2.getStock());
    }
    
    //Forma mas segura
    /*
    @Override
    public int compare(Producto p1, Producto p2) {
        return Integer.compare(p1.getStock(), p2.getStock());
    }   
    */
}