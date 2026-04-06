package com.politecnicomalaga.appalmacen.model;

//Clase producto

public class Producto implements Comparable<Producto>{
    //Relizar comparables con lo comentado
    private String codigoProducto; //a-z
    private String descripcion;
    private double precio; //de barato a caro
    private int stock; // de menor a mayor
    //Hacerlo en el controlador usando clases anonimas pero al reves
    
    private static final String CSV_FORMATO = "Clase;Código Producto;Descripción;Precio;Stock;Caducidad";

    // Constructor
    public Producto(String codigoProducto, String descripcion, double precio, int stock) {
        setCodigoProducto(codigoProducto); // Usamos el setter para validar
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters
    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        if (codigoProducto == null || codigoProducto.length() < 8 || codigoProducto.length() > 16) {
            throw new IllegalArgumentException("El código de producto debe ser alfanumérico y tener entre 8 y 16 caracteres.");
        }
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio>=0.0) this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void changeStock(int newStock) {
        if (this.stock + newStock < 0) return; //nunca tenemos stock negativo
        this.stock += newStock;  //si newStock es negativo, se quita al almacén unidades del producto
    }

    // Método para mostrar la información del producto. CSV Plus
    /*@Override
    public String toString() {
        return "Clase=Producto;" +
                "codigoProducto=" + codigoProducto + ";" +
                "descripcion=" + descripcion + ";" +
                "precio=" + precio + ";" +
                "stock=" + stock;
    }*/
    @Override
    public String toString() {
        return "Producto;" +
        codigoProducto + ";" +
        descripcion + ";" +
         precio + ";" 
         + stock;
    }
    
    @Override
    public int compareTo(Producto p){
        String misDatos = this.getDescripcion();
        String datosP = p.getDescripcion();
        
        return misDatos.compareTo(datosP);
    }
    
    public static String getCsvFormato(){
        return CSV_FORMATO;
    }
    
    public static Producto cargarDesdeCSVPlus(String data) {
        String codigoProducto = "";
        String descripcion = "";
        double precio = 0;
        int stock = 0;
        
        //Poner aquí tu código
        
        return new Producto(codigoProducto, descripcion, precio, stock);
        
    }
}
