package com.politecnicomalaga.appalmacen.model;


/**
 * Write a description of class ProductoPerecedero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ProductoPerecedero extends Producto
{
    private String fechaCaducidad;
    
    //private static final String CSV_FORMATO = "Fecha caducidad";
    
    public ProductoPerecedero(String codigoProducto, String descripcion, double precio, int stock, String fechaCaducidad){
        super(codigoProducto,descripcion,precio,stock);
        this.fechaCaducidad=fechaCaducidad;
    }
    
    //Getters
    public String getFechaCaducidad(){
        return fechaCaducidad;
    }
    
    //Setters
    public void setFechaCaducidad(String fechaCaducidad){
        this.fechaCaducidad=fechaCaducidad;
    }
    
    //Metodos
    @Override
    public String toString(){
        return super.toString() + ";" +
        this.fechaCaducidad;
    }
    
    /*public static String getCsvFormato(){
        return Producto.getCsvFormato() + ";" + ProductoPerecedero.CSV_FORMATO;
    }*/
}
