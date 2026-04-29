package com.politecnicomalaga.appalmacen.view;
import java.util.Scanner;
import com.politecnicomalaga.appalmacen.controller.*;


/**
 * Descripción del examen:
 * 
 * Hay que terminar un prototipo para gestión del stock de productos de un almacén
 * El proyecto tiene una serie de clases incompletas añadidas, y tendrás que crear también 
 * una clase ProductoPerecedero que será un producto que tendrá fecha de caducidad (AAAAMMDD)
 * Tu objetivo: implementar el prototipo hasta hacer funcionales todas las opciones del menú, 
 * teniendo en cuenta que:
 *  - El controlador tendrá una colección List de productos, donde estarán todos los productos
 *  activos
 *  - El controlador tendrá otra colección List de productos con los productos retirados (por 
 *  obsoletos, caducados, o lo que sea)
 *  - Cuando añadimos un producto puede ser normal o perecedero
 *  - Cuando modificamos el stock de un producto, se solicita el código del producto y el stock
 *  - Cuando queremos retirar un producto, pedimos su código
 *  - Cuando queremos mostrar productos entre dos precios pedimos el mínimo y el máximo precio
 *  que usaremos para obtener de la lista de productos activos, los que cumplan con el filtro
 *  - En las demás opciones realizamos lo solicitado sin necesidad de obtener ningún dato por 
 *  teclado
 *  - El controlador es de tipo Singleton
 *  - La vista NO usa objetos ni clases del modelo. El proyecto es MVC con capas puras
 *  - Hay que implementar los listados como tablas, con una cabecera y los datos en forma de filas
 *  pero sin tener la información de los atributos. Ejemplo:
 *  Clase,Código Producto,Descripción,Precio,Stock,Caducidad
    Producto,TECL5678X,Teclado mecánico RGB con switches rojos,89.99,45
    Producto,RATN9012K,Ratón inalámbrico ergonómico con 5 botones,34.50,67
    Producto,AURC3456L,Auriculares inalámbricos con cancelación de ruido,129.99,23
    Producto,WEBC7890P,Webcam Full HD 1080p con micrófono integrado,59.90,32
    Producto,HUBB2345M,Hub USB 3.0 de 4 puertos con alimentación,24.75,56
    Producto,INK55665F,Toner b/w genérico HP 8750,79.99,18,20260713
    
    - En el ejemplo anterior, la última línea es una producto perecedero
    
 *  - Podéis reutilizar código del Taller mecánico, el que queráis
 *  - El examen dura unas 2 horas y media (se cerrará la entrega a las 10:55)
 *  
 *  - Antes de esa hora, hay que entregar el proyecto en ZIP en la classroom
 *  - Después, se puede seguir trabajando en el proyecto, que completo, se defenderá
 *  a la vuelta de semana blanca.
 *  
 *  Evaluación: 
 *  1ª Oportunidad: ZIP entregado en la classroom
 *  2ª Oportunidad: Repositorio depués de semana blanca
 *  
 *  Cada opción de menú: 1.25 puntos. Total: 10p. Aprobar: 5p.
 *  
 */
public class Main
{
    

    // Clase principal de la vista

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar=true;
        //Poner aquí el bucle para ejecutar mostrar menú, escoger opción y ejecutarla la opción
        //Hasta pulsar opción 0 que es salir
        do{
            mostrarMenu();
            int opcion = sc.nextInt();
            sc.nextLine();
            realizarOpcionMenu(sc,opcion);
            
            if (opcion==0) continuar=false;
        }while(continuar);
        
    }
    
    public static void mostrarMenu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("-                                                      -");
        System.out.println("- Menú del proyecto: Almacén de productos              -");
        System.out.println("-                                                      -");
        System.out.println("- 0. Salir                                             -");
        System.out.println("- 1. Añadir nuevo producto                             -");
        System.out.println("- 2. Añadir/quitar stock a un producto                 -");
        System.out.println("- 3. Listar todo (productos ordenados por descripción) -");
        System.out.println("- 4. Retirar un producto y su stock                    -");
        System.out.println("- 5. Mostrar productos con stock 0                     -");
        System.out.println("- 6. Mostrar productos caducados                       -");
        System.out.println("- 7. Mostrar productos entre dos precios               -");
        System.out.println("- 8. Mostrar productos retirados                       -");
        System.out.println("- 9. Mostrar productos de forma ascendente             -");
        System.out.println("- 10. Mostrar productos de forma descendiente          -");
        System.out.println("--------------------------------------------------------");
    }
    
    public static void realizarOpcionMenu(Scanner sc, int opcion){
        switch(opcion){
            case 0:
                print("Terminando proceso...");
                break;
            case 1:
                addProducto(sc);
                break;
            case 2:
                addStock(sc);                
                break;
            case 3:
                listarProductos(sc);
                break;
            case 4:
                deleteProducto(sc);
                break;
            case 5:
                listarProductoSinSt(sc);                
                break;
            case 6:
                listarProductoSpire(sc);
                break;
            case 7:
                listarProductosPrecio(sc);                
                break;
            case 8:
                listarProductosRe(sc);
                break;
            case 9:
                listarProductosAsc(sc);
                break;
            case 10:
                listarProductosDsc(sc);
                break;
        }
    }
    
    //Crud producto
    
    public static void listarProductosAsc (Scanner sc){
        print("1) Para listar por Codigo: ");
        print("2) Para listar por Precio: ");
        print("3) Para listar por Stock: ");
        int opcion = sc.nextInt();
        imprimirDatos(Controlador.getSingleton().listarProductoComAsc(opcion));
    }    
    
    public static void listarProductosDsc (Scanner sc){
        print("1) Para listar por Codigo: ");
        print("2) Para listar por Precio: ");
        print("3) Para listar por Stock: ");
        int opcion = sc.nextInt();
        imprimirDatos(Controlador.getSingleton().listarProductoComDes(opcion));
    }
    
    public static void listarProductoSpire(Scanner sc){
        print("Introduce una fecha | formato (YYYYMMDD): ");
        String fecha = sc.nextLine();
        imprimirDatos(Controlador.getSingleton().listarProductoSpire(fecha));        
    }
    
    public static void listarProductos (Scanner sc){
        imprimirDatos(Controlador.getSingleton().listarProductos());
        
    }
    
    public static void listarProductosRe(Scanner sc){
        imprimirDatos(Controlador.getSingleton().listarProductosRe());
        
    }
    public static void listarProductoSinSt(Scanner sc){
        imprimirDatos(Controlador.getSingleton().listarProductoSinSt());
        
    }
    
    public static void listarProductosPrecio(Scanner sc){
        print("Introduce el precio minimo: ");
        int minimo = sc.nextInt();
        print("Introduce el precio maximo: ");
        int maximo = sc.nextInt();
        
        imprimirDatos(Controlador.getSingleton().listarProductosPrecio(minimo,maximo));
        
    }
    
    public static void deleteProducto(Scanner sc){
        
        print("Introduce el codigo del producto que quieres modificar: \n");
        String codigoP = sc.nextLine();
        
        boolean retirado = Controlador.getSingleton().deleteProducto(codigoP);
        
        
        if (retirado) println("Producto eliminado con exito");
        else println("Error al eliminar producto");
    }
        
    
    
    public static void addStock (Scanner sc){
        //nStock nuevo stock
        
        //Pedimos codigo del producto
        print("Introduce el codigo del producto que quieres modificar: \n");
        String codigoP = sc.nextLine();
        
        println("Introduce la cantidad de stock que quieras añadir o quitar");
        print("Recuerda que para quitar tendras que poner (-) delante del número: \n");
        try{
            int nStock= sc.nextInt();
        
            boolean cambiado = Controlador.getSingleton().updateStock(codigoP,nStock);
        
            if (cambiado) println("Stock cambiado con exito");
            else println("Error al cambiar stock");
        }catch(Exception e){
            System.out.println("Has introducido un dato no válido.");
            System.out.println("Detalle del error: " + e.getMessage());
            sc.nextLine();
        }
        
    }
    
    
    public static void addProducto (Scanner sc){
        print("--Antes de añadir productos debe seleccionar que tipo de producto--\n");
        print("Pulse | 1 | para añadir un producto normal: \n");
        print("Pulse | 2 | para añadir producto perecedero: \n");
        int tipoP = sc.nextInt();
        sc.nextLine();
        switch(tipoP){
            case 1:
                addProductoN(sc);
                break;
            case 2:
                addProductoP(sc);
                break;
        }
    }
    
    public static void addProductoN (Scanner sc){
        //String codigoProducto, String descripcion, double precio, int stock
        println("Introduce el codigo de tu producto: ");
        String csv = sc.nextLine() + ";";
        println("Introduce la descripción del producto: ");
        csv += sc.nextLine() + ";";
        println("Introduce el precio del producto: ");
        csv += sc.nextDouble() + ";";
        println("Introduce la cantidad de stock del producto");
        csv+= sc.nextInt();
        //sc.nextLine();
        
        //boolean creado = Controlador.getSingleton().addProductoN(csv);
        //if(creado) println("Producto añadido con exito");
        //else println("Error al añadir producto");
    }
    
    public static void addProductoP (Scanner sc){
        //String codigoProducto, String descripcion, double precio, int stock, String fechaCaducidad
        println("Introduce el codigo de tu producto: ");
        String csv = sc.nextLine() + ";";
        println("Introduce la descripción del producto: ");
        csv += sc.nextLine() + ";";
        println("Introduce el precio del producto: ");
        csv += sc.nextDouble() + ";";
        println("Introduce la cantidad de stock del producto: ");
        csv+= sc.nextInt() + ";";
        sc.nextLine();
        println("Introduce la fecha de caducidad del producto: ");
        csv+=sc.nextLine();
        
        boolean creado = Controlador.getSingleton().addProductoP(csv);
        if(creado) println("Producto añadido con exito");
        else println("Error al añadir producto");
    }
    
    //Metodos
    public static void print(String texto){
        System.out.print(texto);
    }
    
    public static void println(String texto){
        System.out.println(texto);
    }
    
    public static void imprimirDatos(String datos){
        //Me pasan un paquete, global, con subpaquetes, apilados con \n
        //Cada paquetito se que es un String CSV dato1;dato2;dato3...
        String[] lineas = datos.split("\n");
        for(String l:lineas){
            imprimirColumnas(l);
        }
    }
    
    public static void imprimirColumnas(String linea){
        int anchoColumna=15;
        String[] columnas=linea.split(";");
        for(String col:columnas){
            //print(col+ " ");
            System.out.printf("| %-" + anchoColumna + "s ", col);
        }
        println("");
    }

 
}