package com.politecnicomalaga.appalmacen.controller;
import com.politecnicomalaga.appalmacen.dataservice.BBDDAccess;
import com.politecnicomalaga.appalmacen.model.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Controlador
{
    // instance variables
    //ProductosA son mis productos activos
    private List <Producto> misProductosA;
    
    //ProductosR son mis producots retirados
    private List <Producto> misProductosR;
    
    //Singleton
    //poner aquí
    private static Controlador singleton;
    
    /**
     * Constructor for objects of class Controlador
     */
    private Controlador()
    {
        BBDDAccess miBBDD = new BBDDAccess();
        List<Producto> listaDeProductosInicial = DataAccess.loadData();    
        //Poner código aquí para que la lista inicial de productos esté
        //siempre disponible cuando se arranca el programa.
        misProductosA = new ArrayList<Producto>();
        misProductosA.addAll(listaDeProductosInicial);
        misProductosR = new ArrayList<Producto>();

        //AHora cogeremos los productos desde una base de datos aun no esta el codigo hecho
        // misProductosA = miBBDD.listAll();

    }

    
    public static Controlador getSingleton()
    {
        // put your code here
        if(singleton==null) singleton = new Controlador();
        return singleton;
        // Modificado por Chiebuka return null;
    }
    
    //Crud Productos
    
    //Listar por Comparable Ascendente
    public String listarProductoComAsc(int opcion){
        String resultado="";
        List<Producto> misProductos = new ArrayList<Producto>();
        
        for (Producto p: misProductosA){
            misProductos.add(p);
        }
        
        for (Producto p: misProductosR){
            misProductos.add(p);
        }
        
        //Segun opcion
        switch (opcion){
            case 1:
                Collections.sort(misProductos, new CoProductoxCodigo());
                break;
            case 2:
                Collections.sort(misProductos, new CoProductoxPrecio());
                break;
            case 3:
                Collections.sort(misProductos, new CoProductoxStock());                
                break;
        }
        
        
        for (Producto p: misProductos){
            resultado += p.toString() + "\n";
        }
        
        return Producto.getCsvFormato() + "\n" + resultado;
    }

    //Listar productos con lambda
    public String listarProductoLambda(){
        String resultado="";
        /*for(Producto p : misProductosA){
            resultado += p.toString() + "\n";
        }*/
        Stream<Producto> miStream = misProductosA.stream();
        Stream <String> miListaDeStringCsv = miStream.map(producto -> producto.toString());
        resultado = miListaDeStringCsv.reduce("", (a,b) -> a + b);

        return resultado;
    }


    //Listar por Comparable Descendente
    public String listarProductoComDes(int opcion){
        String resultado="";
        List<Producto> misProductos = new ArrayList<Producto>();
        
        for (Producto p: misProductosA){
            misProductos.add(p);
        }
        
        for (Producto p: misProductosR){
            misProductos.add(p);
        }
        
        //Segun opcion
        switch (opcion){
            case 1:
                Collections.sort(misProductos, new CoProductoxCodigo(){
                    @Override
                    public int compare(Producto p1, Producto p2){
                        return p2.getCodigoProducto().compareTo(p1.getCodigoProducto());
                    }
                });
                break;
            case 2:
                Collections.sort(misProductos, new CoProductoxPrecio(){
                    @Override
                    public int compare(Producto p1,Producto p2){
                        return (int)(p2.getPrecio()-p1.getPrecio());
                    }
                });
                break;
            case 3:
                Collections.sort(misProductos, new CoProductoxStock(){
                    @Override
                    public int compare(Producto p1,Producto p2){
                        return (p2.getStock()-p1.getStock());
                    }
                });                
                break;
        }
        
        
        for (Producto p: misProductos){
            resultado += p.toString() + "\n";
        }
        
        return Producto.getCsvFormato() + "\n" + resultado;
    }
    
    public String listarProductoSpire(String fecha){
        String resultado="";
        List<Producto> misProductos = new ArrayList<Producto>();
        
        int fechaInt = Integer.parseInt(fecha);
        
        for (Producto p : misProductosA) {
            if (p instanceof ProductoPerecedero) {
                ProductoPerecedero pp = (ProductoPerecedero) p;
                if (Integer.parseInt(pp.getFechaCaducidad()) < fechaInt) {
                    misProductos.add(pp);
                }
            }
        }
        
        for (Producto p : misProductosR) {
            if (p instanceof ProductoPerecedero) {
                ProductoPerecedero pp = (ProductoPerecedero) p;
                if (Integer.parseInt(pp.getFechaCaducidad()) < fechaInt) {
                    misProductos.add(pp);
                }
            }
        }
        
        for (Producto p : misProductos) {
            resultado += p.toString() + "\n";
        }
        
        return Producto.getCsvFormato() + "\n" + resultado;
    }
    
    
    public String listarProductos(){
        String resultado="";
        List<Producto> misProductos = new ArrayList<Producto>();
        
        for (Producto p: misProductosA){
            misProductos.add(p);
        }
        
        for (Producto p: misProductosR){
            misProductos.add(p);
        }
        
        Collections.sort(misProductos);
        
        for (Producto p: misProductos){
            resultado += p.toString() + "\n";
        }

        //Tendre una cabecera estatica final con los datos de los productos
        //return Producto.getCsvFormato() + "\n" + resultado;

        return resultado;
    }
    
    public String listarProductosRe(){
        String resultado="";
        List<Producto> misProductos = new ArrayList<Producto>();
        
        for (Producto p: misProductosR){
            misProductos.add(p);
        }
        
        //No se pide que tambien se muestre por orden de descripcion
        //Collections.sort(misProductos);
        
        for (Producto p: misProductos){
            resultado += p.toString() + "\n";
        }

        return Producto.getCsvFormato() + "\n" + resultado;
    }
    
    //Listar productos sin Stock
    public String listarProductoSinSt(){
        String resultado="";
        List<Producto> misProductos = new ArrayList<Producto>();
        
        for (Producto p: misProductosA){
            if(p.getStock()==0){
                misProductos.add(p);
            }
            
        }
        
        for (Producto p: misProductosR){
            if(p.getStock()==0){
                misProductos.add(p);
            }
            
        }
        
        Collections.sort(misProductos);
        
        for (Producto p: misProductos){
            resultado += p.toString() + "\n";
        }
        
        return Producto.getCsvFormato() + "\n" + resultado;
    }
    
    //Listar productos por precio
    public String listarProductosPrecio(int minimo, int maximo){
        String resultado="";
        List<Producto> misProductos = new ArrayList<Producto>();
        
        for (Producto p: misProductosA){
            if(p.getPrecio()>=minimo &&  p.getPrecio()<=maximo){
                misProductos.add(p);
            }
            
        }
        
        for (Producto p: misProductosR){
            if(p.getPrecio()>=minimo &&  p.getPrecio()<=maximo){
                misProductos.add(p);
            }
            
        }
        
        Collections.sort(misProductos);
        
        for (Producto p: misProductos){
            resultado += p.toString() + "\n";
        }
        
        return Producto.getCsvFormato() + "\n" + resultado;
    }
    
    public boolean deleteProducto(String codigoP){
        Producto p = getProductoPorCode(codigoP);
        
        if (p==null) return false;
        
        for (Producto pro: misProductosA){
            if(pro.getCodigoProducto().equals(codigoP)){
                misProductosR.add(pro);
                misProductosA.remove(pro);
                return true;
            }
        }
        return false;
    }
    
    public boolean updateStock (String codigoP, int nStock){
        //Cambiar
        Producto p = getProductoPorCode(codigoP);
        if(p!=null){
           p.changeStock(nStock);
           return true;
        }
        return false;  
        
    }
    
    public Producto getProductoPorCode(String codigo){
        for (Producto p: misProductosA){
            if(p.getCodigoProducto().equals(codigo))return p;
        }
        return null;
    }
    
    
    public boolean addProductoN (String csv){
        //String codigoProducto, String descripcion, double precio, int stock
        //Pongo el try catch por si me pasan menos datos de los que son
        try{
            String[] datos = csv.split(";");
        
            Producto p = new Producto(datos[0],datos[1],Double.parseDouble(datos[2]),Integer.parseInt(datos[3]));
        
            for(Producto pro: misProductosA){
                if(pro.getCodigoProducto().equals(p.getCodigoProducto()))return false;
            }
        
            misProductosA.add(p);
            return true;
        } catch (Exception e){
            System.out.println("Error. Revisa el formato de los datos.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
        
    }

    public boolean addProductoN (Map<String, String> datos){

        BBDDAccess miBBDD = new BBDDAccess();

        //String codigoProducto, String descripcion, double precio, int stock
        //Pongo el try catch por si me pasan menos datos de los que son
        try{

            Producto p = new Producto(datos.get("codigo"),datos.get("descripcion"),Double.parseDouble(datos.get("precio")),Integer.parseInt(datos.get("stock")));
            miBBDD.addProducto(p);

            for(Producto pro: misProductosA){
                if(pro.getCodigoProducto().equals(p.getCodigoProducto()))return false;
            }

            misProductosA.add(p);
            return true;
        } catch (Exception e){
            System.out.println("Error. Revisa el formato de los datos.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }

    }
    
    public boolean addProductoP (String csv){
        //String codigoProducto, String descripcion, double precio, int stock, String fecha caducidad
        String[] datos = csv.split(";");
        
        ProductoPerecedero p = new ProductoPerecedero
        (datos[0],datos[1],Double.parseDouble(datos[2]),Integer.parseInt(datos[3]),datos[4]);
        
        for(Producto pro: misProductosA){
            if(pro.getCodigoProducto().equals(p.getCodigoProducto()))return false;
        }
        
        misProductosA.add(p);
        return true;
    }
}