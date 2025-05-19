package ceballos_moises_proyectotienda;

import java.util.Scanner;

public class Ceballos_Moises_ProyectoTienda {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        boolean sistemaAbierto = true;
        boolean permitir_ventas = true;
        boolean permitir_compras = true;
        int dia = 1;

        // DINERO 
        double dineroCaja = 0;
        double dineroBanco = 0;

        // INVENTARIO 
        int stockAzucar = 0;
        int stockAvena = 0;
        int stockTrigo = 0;
        int stockMaiz = 0;

        // COSTOS ACUMULADOS 
        double costoTotalAzucar = 0;
        double costoTotalAvena = 0;
        double costoTotalTrigo = 0;
        double costoTotalMaiz = 0;

        // CONTADORES 
        int contador_de_ventas_general = 0;
        int contador_de_compras_general = 0;

        // ACUMULADORES 
        double volumen_total_compras = 0;
        double volumen_total_ventas = 0;
        double gananciasTotales = 0;

        // REGISTROS 
        double mayorVenta = 0;
        double mayorCompra = 0;
        int veces_vendidasAzucar = 0;
        int veces_vendidasAvena = 0;
        int veces_vendidasTrigo = 0;
        int veces_vendidasMaiz = 0;

        while (sistemaAbierto) {
            // MENU/INTERFAZ 
            int menu_opcion;
            int clienteIngresado;
            int proveedorIngresado;
            int codigoVender;
            int codigoComprar;
            int seleccion_SiNo;

            //  OPERACIONES ACTUALES 
            double cantidadKgAzucar = 0;
            double cantidadKgAvena = 0;
            double cantidadKgTrigo = 0;
            double cantidadKgMaiz = 0;
            double cantidad = 0;

            //  SUBTOTALES ACTUALES 
            double subtotal_ventas_general = 0;
            double subtotal_comprasAzucar = 0;
            double subtotal_comprasAvena = 0;
            double subtotal_comprasTrigo = 0;
            double subtotal_comprasMaiz = 0;
            double subtotal_ventasAzucar = 0;
            double subtotal_ventasAvena = 0;
            double subtotal_ventasTrigo = 0;
            double subtotal_ventasMaiz = 0;

            //  TRANSACCIÓN ACTUAL 
            double descuento = 0;
            double total_ventas_neto = 0;
            double valor_medio_compra = 0;
            double valor_medio_ventas = 0;

            //  DEPÓSITO BANCARIO 
            double deposito = 0;
            double cantidad_max = 0.6 * dineroCaja;

            // TEMPORALES 
            String productoEstrella = "";
            double dineroIngresado;

            //OPCIONES COMPRA Y VENTA 
            double precioAzucar = 30, precioAvena = 25, precioTrigo = 32, precioMaiz = 20;
            double precioAzucarC = 0, precioAvenaCB = 0, precioAvenaCC = 0, precioTrigoC = 0, precioMaizC = 0;
            double cantidadKg = 0;
            String nombreProducto1 = "Azucar", nombreProducto2 = "Avena", nombreProducto3 = "Trigo", nombreProducto4 = "Maiz";

            do { // Do de la Validacion de opcion valida en menu 

                System.out.println("\n======== DIA: " + dia + " ========");
                System.out.println("***Caja Registradora***");
                System.out.println("1.Abrir Caja");
                System.out.println("2.Ventas");
                System.out.println("3.Compras");
                System.out.println("4.Reportes");
                System.out.println("5.Cierre de Caja");
                System.out.println("6.Salir del sistema");
                System.out.print("Seleccione una opcion: ");

                menu_opcion = sc.nextInt();

                //ABRIR CAJA OPCION 1//
                if (menu_opcion == 1) {
                    if (permitir_ventas && permitir_compras) {

                        System.out.println("\nOpcion Seleccionada: ***Abrir Caja***");
                        System.out.println("Dinero Disponible en Caja: L." + String.format("%.2f", dineroCaja));

                        do { // VALIDA MONTOS Y CARGA DINERO EN CAJA
                            System.out.print("Ingrese Dinero a la Caja: L.");
                            dineroIngresado = sc.nextDouble();

                            if (dineroIngresado > 0) {
                                dineroCaja += dineroIngresado;
                                System.out.println("\nMENSAJE: SE HA INGRESADO EL MONTO CON EXITO.");
                                System.out.println("Dinero Disponible en Caja: L." + String.format("%.2f", dineroCaja));
                                System.out.println("Regresando al menu principal...");
                            } else {
                                System.out.println("MENSAJE: INGRESE MONTOS VALIDOS!");
                                System.out.println(" ");

                            }

                        } while (dineroIngresado <= 0);

                    } else { //UTILIZADO PARA PODER ABRIR LA CAJA SIN SOLICITAR DINERO DESPUES DEL CIERRE
                        System.out.println("Dinero Disponible en Caja: L." + String.format("%.2f", dineroCaja));
                        permitir_ventas = true; //BOOLEANS QUE PERMITEN LA VENTA Y COMPRA
                        permitir_compras = true;
                    }

                    // VENTAS OPCION 2//   
                } else if (menu_opcion == 2) {
                    if (permitir_ventas) {

                        if (stockAzucar > 0 || stockMaiz > 0 || stockAvena > 0 || stockTrigo > 0) { // FILTRO DE STOCK

                            System.out.println("\nOpcion Seleccionada: ***Ventas***");

                            contador_de_ventas_general += 0; //VARIABLE DE REGISTRO

                            total_ventas_neto = 0;
                            subtotal_ventas_general = 0;
                            subtotal_ventasAzucar = 0; //SIEMPRE QUE SE REGISTRE UNA NUEVA VENTA LOS SUBTOTALES SE DEBERAN REINICIAR
                            subtotal_ventasAvena = 0;
                            subtotal_ventasTrigo = 0;
                            subtotal_ventasMaiz = 0;

                            do {
                                System.out.print("Seleccione el tipo de Cliente 1.(A),2.(B),3.(C) : ");
                                clienteIngresado = sc.nextInt(); //ENTRADA DE TIPO DE CLIENTE

                                if (clienteIngresado == 1) {  //CLIENTE TIPO A

                                    //SUBTOTALES REINICIADOS
                                    cantidadKgAzucar = 0;
                                    cantidadKgAvena = 0;
                                    cantidadKgTrigo = 0;
                                    cantidadKgMaiz = 0;

                                    System.out.println("\nNOTA: LA TIENDA VENDE ESTOS SIGUIENTES PRODUCTOS POR KILOGRAMO: ");

                                    do {

                                        System.out.println("\n +-------------------------------------+  ");
                                        System.out.println(" |       Tipo de Cliente: A            |  ");
                                        System.out.println(" +-------------------------------------+  ");
                                        System.out.println(" | Codigo | Producto | Precio de Venta |  ");
                                        System.out.println(" +--------+----------+-----------------+  ");
                                        System.out.println(" |   1    | Azucar   | L. 30           |  ");
                                        System.out.println(" +--------+----------+-----------------+  ");
                                        System.out.println(" |   2    | Avena    | L. 25           |  ");
                                        System.out.println(" +--------+----------+-----------------+  ");
                                        System.out.println(" |   3    | Trigo    | L. 32           |  ");
                                        System.out.println(" +--------+----------+-----------------+  ");
                                        System.out.println(" |   4    | Maiz     | L. 20           |  ");
                                        System.out.println(" +--------+----------+-----------------+  ");

                                        System.out.println("\n===== STOCK =====");
                                        System.out.println("Stock Azucar: " + stockAzucar);
                                        System.out.println("Stock Avena: " + stockAvena);
                                        System.out.println("Stock Trigo: " + stockTrigo);
                                        System.out.println("Stock Maiz: " + stockMaiz);
                                        System.out.println("=================");

                                        do {
                                            System.out.print("\nIngrese el codigo de producto a vender: ");
                                            codigoVender = sc.nextInt();

                                            if (codigoVender == 1) { //AZUCAR AZUCAR AZUCAR AZUCAR //
                                                cantidad = -1;

                                                if (stockAzucar > 0) { //SI HAY STOCK DE AZUCAR SE PUEDE VENDER
                                                    if (cantidadKgAzucar > 0 && stockAzucar > 0) { // CONDICIONAL QUE CALCULA EL COSTO DE VENTA Y GANANCIAS
                                                        double costoVentaAzucar = cantidadKgAzucar * (costoTotalAzucar / stockAzucar);
                                                        gananciasTotales += (subtotal_ventasAzucar - costoVentaAzucar);
                                                    }
                                                    System.out.println("\n +-------------------------------------+  ");
                                                    System.out.println(" |       Seleccion de Cliente: A       |  ");
                                                    System.out.println(" +-------------------------------------+  ");
                                                    System.out.println(" | Codigo | Producto | Precio de Venta |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");
                                                    System.out.println(" |   1    | Azucar   | L. 30           |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");

                                                    while (cantidad <= 0) {
                                                        System.out.print("Ingrese la cantidad de kilogramos que desea comprar: ");
                                                        cantidad = sc.nextDouble();

                                                    }

                                                    if (stockAzucar >= cantidad) { // VALIDA SI SE PUEDE HACER LA COMPRA CON EL STOCK DISPONIBLE 
                                                        cantidadKgAzucar += cantidad;
                                                        veces_vendidasAzucar += cantidad; //ACUMULA CANTIDAD DE VECES VENDIDAS DE PRODUCTO PARA DETERMINAR EL PROD ESTRELLA
                                                        subtotal_ventasAzucar += (cantidad * precioAzucar);
                                                        stockAzucar -= cantidad;

                                                    } else {
                                                        System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                        break;
                                                    }

                                                } else {
                                                    System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                    break;
                                                }

                                            } else if (codigoVender == 2) { // AVENA AVENA AVENA AVENA //
                                                if (stockAvena > 0) {
                                                    if (cantidadKgAvena > 0 && stockAvena > 0) {
                                                        double costoVentaAvena = cantidadKgAvena * (costoTotalAvena / stockAvena);
                                                        gananciasTotales += (subtotal_ventasAvena - costoVentaAvena);
                                                    }
                                                    cantidad = -1;

                                                    System.out.println("\n +-------------------------------------+  ");
                                                    System.out.println(" |       Seleccion de Cliente: A       |  ");
                                                    System.out.println(" +-------------------------------------+  ");
                                                    System.out.println(" | Codigo | Producto | Precio de Venta |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");
                                                    System.out.println(" |   2    | Avena    | L. 25           |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");

                                                    while (cantidad <= 0) {
                                                        System.out.print("Ingrese la cantidad de kilogramos que desea comprar: ");
                                                        cantidad = sc.nextDouble();
                                                    }

                                                    if (stockAvena >= cantidad) {
                                                        cantidadKgAvena += cantidad;
                                                        veces_vendidasAvena += cantidad;
                                                        subtotal_ventasAvena += (cantidad * precioAvena);
                                                        stockAvena -= cantidad;

                                                    } else {
                                                        System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                        break;
                                                    }
                                                } else {
                                                    System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                    break;
                                                }

                                            } else if (codigoVender == 3) { // TRIGO TRIGO TRIGO TRIGO //
                                                if (stockTrigo > 0) {
                                                    if (cantidadKgTrigo > 0 && stockTrigo > 0) {
                                                        double costoVentaTrigo = cantidadKgTrigo * (costoTotalTrigo / stockTrigo);
                                                        gananciasTotales += (subtotal_ventasTrigo - costoVentaTrigo);
                                                    }
                                                    cantidad = -1;
                                                    System.out.println("\n +-------------------------------------+  ");
                                                    System.out.println(" |       Seleccion de Cliente: A       |  ");
                                                    System.out.println(" +-------------------------------------+  ");
                                                    System.out.println(" | Codigo | Producto | Precio de Venta |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");
                                                    System.out.println(" |   3    | Trigo    | L. 32           |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");

                                                    while (cantidad <= 0) {
                                                        System.out.print("Ingrese la cantidad de kilogramos que desea comprar: ");
                                                        cantidad = sc.nextDouble();
                                                    }

                                                    if (stockTrigo >= cantidad) {
                                                        cantidadKgTrigo += cantidad;
                                                        veces_vendidasTrigo += cantidad;
                                                        subtotal_ventasTrigo += (cantidad * precioTrigo);
                                                        stockTrigo -= cantidad;

                                                    } else {
                                                        System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                        break;
                                                    }
                                                } else {
                                                    System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                    break;
                                                }

                                            } else if (codigoVender == 4) { //MAIZ MAIZ MAIZ MAIZ//
                                                cantidad = -1;
                                                if (stockMaiz > 0) {
                                                    if (cantidadKgMaiz > 0 && stockMaiz > 0) {
                                                        double costoVentaMaiz = cantidadKgMaiz * (costoTotalMaiz / stockMaiz);
                                                        gananciasTotales += (subtotal_ventasMaiz - costoVentaMaiz);
                                                    }
                                                    System.out.println("\n +-------------------------------------+  ");
                                                    System.out.println(" |       Seleccion de Cliente: A       |  ");
                                                    System.out.println(" +-------------------------------------+  ");
                                                    System.out.println(" | Codigo | Producto | Precio de Venta |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");
                                                    System.out.println(" |   4    | Maiz     | L. 20           |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");

                                                    while (cantidad <= 0) {
                                                        System.out.print("Ingrese la cantidad de kilogramos que desea comprar: ");
                                                        cantidad = sc.nextDouble();
                                                    }

                                                    if (stockMaiz >= cantidad) {
                                                        cantidadKgMaiz += cantidad;
                                                        veces_vendidasMaiz += cantidad;
                                                        subtotal_ventasMaiz += (cantidad * precioMaiz);
                                                        stockMaiz -= cantidad;

                                                    } else {
                                                        System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                        break;
                                                    }

                                                } else {
                                                    System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                    break;
                                                }

                                            } else {
                                                System.out.println("MENSAJE: CODIGO DE PRODUCTO INVALIDO!");
                                            }

                                        } while (codigoVender != 1 && codigoVender != 2 && codigoVender != 3 && codigoVender != 4);

                                        do {
                                            System.out.print("Desea comprar otro producto? 1.Si 2.No: "); //RECOMPRA
                                            seleccion_SiNo = sc.nextInt();

                                            if (seleccion_SiNo != 1 && seleccion_SiNo != 2) { //VALIDACION DE RECOMPRA
                                                System.out.println("MENSAJE: INGRESE UNA OPCION VALIDA!");
                                            }
                                        } while (seleccion_SiNo != 1 && seleccion_SiNo != 2); //DO WHILE DE RECOMPRA

                                    } while (seleccion_SiNo == 1);
                                    //FACTURAS

                                    System.out.println("\n====== FACTURACION ======");

                                    if (cantidadKgAzucar > 0) {
                                        System.out.println("Nombre del producto: Azucar");
                                        System.out.println("Cantidad: " + cantidadKgAzucar + "kg");
                                        System.out.println("Precio Unitario: L. 30");
                                        System.out.println("Subtotal: L. " + subtotal_ventasAzucar);

                                    }

                                    if (cantidadKgAvena > 0) {
                                        System.out.println("\nNombre del producto: Avena");
                                        System.out.println("Cantidad: " + cantidadKgAvena + "kg");
                                        System.out.println("Precio Unitario: L. 25");
                                        System.out.println("Subtotal: L. " + subtotal_ventasAvena);

                                    }

                                    if (cantidadKgTrigo > 0) {

                                        System.out.println("\nNombre del producto: Trigo");
                                        System.out.println("Cantidad: " + cantidadKgTrigo + "kg");
                                        System.out.println("Precio Unitario: L. 32");
                                        System.out.println("Subtotal: L. " + subtotal_ventasTrigo);

                                    }

                                    if (cantidadKgMaiz > 0) {
                                        System.out.println("\nNombre del producto: Maiz");
                                        System.out.println("Cantidad: " + cantidadKgMaiz + "kg");
                                        System.out.println("Precio Unitario: L. 20");
                                        System.out.println("Subtotal: L. " + subtotal_ventasMaiz);

                                    }

                                    subtotal_ventas_general = subtotal_ventasAzucar + subtotal_ventasAvena + subtotal_ventasTrigo + subtotal_ventasMaiz;

                                    System.out.println("\nSubtotal total: L." + String.format("%.2f", subtotal_ventas_general));
                                    String texto_descuento = " %";
                                    double impuesto = 0.07;
                                    String texto_impuesto = "7%";

                                    //CALCULOS DE DESCUENTO E IMPUESTO
                                    if (subtotal_ventas_general > 5000) {
                                        descuento = subtotal_ventas_general * 0.1;
                                        total_ventas_neto = subtotal_ventas_general - descuento;
                                        impuesto = total_ventas_neto * 0.07;
                                        total_ventas_neto += impuesto;
                                        texto_descuento = "10%";
                                        volumen_total_ventas += total_ventas_neto;

                                    } else if (subtotal_ventas_general >= 1000) {
                                        descuento = subtotal_ventas_general * 0.05;
                                        total_ventas_neto = subtotal_ventas_general - descuento;
                                        impuesto = total_ventas_neto * 0.07;
                                        total_ventas_neto += impuesto;
                                        texto_descuento = "5%";
                                        volumen_total_ventas += total_ventas_neto;

                                    } else {
                                        total_ventas_neto = subtotal_ventas_general;
                                        impuesto = total_ventas_neto * 0.07;
                                        total_ventas_neto += impuesto;
                                        texto_descuento = "0%";
                                        volumen_total_ventas += total_ventas_neto;
                                    }

                                    if (total_ventas_neto > mayorVenta) {
                                        mayorVenta = total_ventas_neto;
                                    }

                                    System.out.println("Descuento Aplicado: " + texto_descuento);
                                    System.out.println("Descuento: L." + String.format("%.2f", descuento));
                                    System.out.println("Impuesto % : " + texto_impuesto);
                                    System.out.println("Impuesto: L." + String.format("%.2f", impuesto));
                                    System.out.println("Total neto: L." + String.format("%.2f", total_ventas_neto));

                                    System.out.println("=========================");

                                    dineroCaja += total_ventas_neto;
                                    System.out.println("\nMENSAJE: SE HA ACTUALIZADO DINERO EN CAJA!");
                                    System.out.println("Dinero Disponible en Caja: L." + String.format("%.2f", dineroCaja));

                                    contador_de_ventas_general += 1;

                                    System.out.println("Volviendo al menu principal...");
                                    break;

                                } else if (clienteIngresado == 2) { //CLIENTE B 
                                    cantidadKgTrigo = 0;
                                    cantidadKgAvena = 0;
                                    cantidadKgAzucar = 0;

                                    System.out.println("\nNOTA: LA TIENDA VENDE ESTOS SIGUIENTES PRODUCTOS POR KILOGRAMO: ");

                                    do {

                                        System.out.println("\n +-------------------------------------+  ");
                                        System.out.println(" |       Tipo de Cliente: B            |  ");
                                        System.out.println(" +-------------------------------------+  ");
                                        System.out.println(" | Codigo | Producto | Precio de Venta |  ");
                                        System.out.println(" +--------+----------+-----------------+  ");
                                        System.out.println(" |   1    | Azucar   | L. 30           |  ");
                                        System.out.println(" +--------+----------+-----------------+  ");
                                        System.out.println(" |   2    | Avena    | L. 25           |  ");
                                        System.out.println(" +--------+----------+-----------------+  ");
                                        System.out.println(" |   3    | Trigo    | L. 32           |  ");
                                        System.out.println(" +--------+----------+-----------------+  ");

                                        System.out.println("\n===== STOCK =====");
                                        System.out.println("Stock Azucar: " + stockAzucar);
                                        System.out.println("Stock Avena: " + stockAvena);
                                        System.out.println("Stock Trigo: " + stockTrigo);
                                        System.out.println("=================");

                                        do {
                                            System.out.print("Ingrese el codigo de producto a vender: ");
                                            codigoVender = sc.nextInt();

                                            if (codigoVender == 1) { //validacion de producto que solicita el cliente

                                                if (stockAzucar > 0) {
                                                    if (cantidadKgAzucar > 0 && stockAzucar > 0) {
                                                        double costoVentaAzucar = cantidadKgAzucar * (costoTotalAzucar / stockAzucar);
                                                        gananciasTotales += (subtotal_ventasAzucar - costoVentaAzucar);
                                                    }
                                                    cantidad = -1;
                                                    System.out.println("\n +-------------------------------------+  ");
                                                    System.out.println(" |       Seleccion de Cliente: B       |  ");
                                                    System.out.println(" +-------------------------------------+  ");
                                                    System.out.println(" | Codigo | Producto | Precio de Venta |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");
                                                    System.out.println(" |   1    | Azucar   | L. 30           |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");

                                                    while (cantidad <= 0) {
                                                        System.out.print("Ingrese la cantidad de kilogramos que desea comprar: ");
                                                        cantidad = sc.nextDouble();
                                                    }

                                                    if (stockAzucar >= cantidad) {
                                                        cantidadKgAzucar += cantidad;
                                                        veces_vendidasAzucar += cantidad;
                                                        subtotal_ventasAzucar += (cantidad * precioAzucar);
                                                        stockAzucar -= cantidad;
                                                    } else {
                                                        System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                        break;
                                                    }
                                                } else {
                                                    System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                    break;
                                                }

                                            } else if (codigoVender == 2) {
                                                if (stockAvena > 0) {
                                                    if (cantidadKgAvena > 0 && stockAvena > 0) {
                                                        double costoVentaAvena = cantidadKgAvena * (costoTotalAvena / stockAvena);
                                                        gananciasTotales += (subtotal_ventasAvena - costoVentaAvena);
                                                    }
                                                    cantidad = -1;
                                                    System.out.println("\n +-------------------------------------+  ");
                                                    System.out.println(" |       Seleccion de Cliente: B       |  ");
                                                    System.out.println(" +-------------------------------------+  ");
                                                    System.out.println(" | Codigo | Producto | Precio de Venta |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");
                                                    System.out.println(" |   2    | Avena    | L. 25           |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");

                                                    while (cantidad <= 0) {
                                                        System.out.print("Ingrese la cantidad de kilogramos que desea comprar");
                                                        cantidad = sc.nextDouble();
                                                    }

                                                    if (stockAvena >= cantidad) {
                                                        cantidadKgAvena += cantidad;
                                                        veces_vendidasAvena += cantidad;
                                                        subtotal_ventasAvena += (cantidad * precioAvena);
                                                        stockAvena -= cantidad;

                                                    } else {
                                                        System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                        break;
                                                    }
                                                } else {
                                                    System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                    break;
                                                }

                                            } else if (codigoVender == 3) {
                                                if (stockTrigo > 0) {
                                                    if (cantidadKgTrigo > 0 && stockTrigo > 0) {
                                                        double costoVentaTrigo = cantidadKgTrigo * (costoTotalTrigo / stockTrigo);
                                                        gananciasTotales += (subtotal_ventasTrigo - costoVentaTrigo);
                                                    }
                                                    cantidad = -1;
                                                    System.out.println("\n +-------------------------------------+  ");
                                                    System.out.println(" |       Seleccion de Cliente: B       |  ");
                                                    System.out.println(" +-------------------------------------+  ");
                                                    System.out.println(" | Codigo | Producto | Precio de Venta |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");
                                                    System.out.println(" |   3    | Trigo    | L. 32           |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");

                                                    while (cantidad <= 0) {
                                                        System.out.print("Ingrese la cantidad de kilogramos que desea comprar");
                                                        cantidad = sc.nextDouble();
                                                    }

                                                    if (stockTrigo >= cantidad) {
                                                        cantidadKgTrigo += cantidad;
                                                        veces_vendidasTrigo += cantidad;
                                                        subtotal_ventasTrigo += (cantidad * precioTrigo);
                                                        stockTrigo -= cantidad;
                                                    } else {
                                                        System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                        break;
                                                    }
                                                } else {
                                                    System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                    break;
                                                }

                                            } else {
                                                System.out.println("MENSAJE: CODIGO DE PRODUCTO INVALIDO");
                                            }

                                        } while (codigoVender != 1 && codigoVender != 2 && codigoVender != 3);

                                        do {
                                            System.out.print("Desea comprar otro producto? 1.Si 2.No: ");
                                            seleccion_SiNo = sc.nextInt();

                                            if (seleccion_SiNo != 1 && seleccion_SiNo != 2) {
                                                System.out.println("MENSAJE: INGRESE OPCION VALIDA");
                                            }
                                        } while (seleccion_SiNo != 1 && seleccion_SiNo != 2);

                                    } while (seleccion_SiNo == 1);

                                    System.out.println("\n====== FACTURACION ======");

                                    if (cantidadKgAzucar > 0) {
                                        System.out.println("Nombre del producto: Azucar");
                                        System.out.println("Cantidad: " + cantidadKgAzucar + "kg");
                                        System.out.println("Precio Unitario: L. 30");
                                        System.out.println("Subtotal: L. " + subtotal_ventasAzucar);

                                    }

                                    if (cantidadKgAvena > 0) {
                                        System.out.println("\nNombre del producto: Avena");
                                        System.out.println("Cantidad: " + cantidadKgAvena + "kg");
                                        System.out.println("Precio Unitario: L. 25");
                                        System.out.println("Subtotal: L. " + subtotal_ventasAvena);

                                    }

                                    if (cantidadKgTrigo > 0) {

                                        System.out.println("\nNombre del producto: Trigo");
                                        System.out.println("Cantidad: " + cantidadKgTrigo + "kg");
                                        System.out.println("Precio Unitario: L. 32");
                                        System.out.println("Subtotal: L. " + subtotal_ventasTrigo);

                                    }

                                    subtotal_ventas_general = subtotal_ventasAzucar + subtotal_ventasAvena + subtotal_ventasTrigo;
                                    System.out.println("\nSubtotal total: L." + String.format("%.2f", subtotal_ventas_general));
                                    String texto_descuento = " %";
                                    double impuesto = 0.07;
                                    String texto_impuesto = "7%";

                                    if (subtotal_ventas_general > 5000) {
                                        descuento = subtotal_ventas_general * 0.1;
                                        total_ventas_neto = subtotal_ventas_general - descuento;
                                        impuesto = total_ventas_neto * 0.07;
                                        total_ventas_neto += impuesto;
                                        texto_descuento = "10%";
                                        volumen_total_ventas += total_ventas_neto;

                                    } else if (subtotal_ventas_general >= 1000) {
                                        descuento = subtotal_ventas_general * 0.05;
                                        total_ventas_neto = subtotal_ventas_general - descuento;
                                        impuesto = total_ventas_neto * 0.07;
                                        total_ventas_neto += impuesto;
                                        texto_descuento = "5%";
                                        volumen_total_ventas += total_ventas_neto;

                                    } else {
                                        total_ventas_neto = subtotal_ventas_general;
                                        impuesto = total_ventas_neto * 0.07;
                                        total_ventas_neto += impuesto;
                                        texto_descuento = "0%";
                                        volumen_total_ventas += total_ventas_neto;
                                    }

                                    if (total_ventas_neto > mayorVenta) {
                                        mayorVenta = total_ventas_neto;
                                    }

                                    System.out.println("Descuento Aplicado: " + texto_descuento);
                                    System.out.println("Descuento: L." + String.format("%.2f", descuento));
                                    System.out.println("Impuesto %: " + texto_impuesto);
                                    System.out.println("Impuesto: L." + String.format("%.2f", impuesto));
                                    System.out.println("Total neto: L." + String.format("%.2f", total_ventas_neto));
                                    System.out.println("=========================");

                                    dineroCaja += total_ventas_neto;
                                    System.out.println("\nSe ha actualizado el dinero en caja");
                                    System.out.println("Dinero Disponible en Caja: L." + String.format("%.2f", dineroCaja));

                                    contador_de_ventas_general += 1;

                                    System.out.println("Volviendo al menu principal...");
                                    break;

                                } else if (clienteIngresado == 3) { // CLIENTE C 

                                    cantidadKgMaiz = 0;
                                    System.out.println("\nNOTA: LA TIENDA VENDE ESTOS SIGUIENTES PRODUCTOS POR KILOGRAMO: ");

                                    do {

                                        System.out.println("\n +-------------------------------------+  ");
                                        System.out.println(" |       Tipo de Cliente: C            |  ");
                                        System.out.println(" +-------------------------------------+  ");
                                        System.out.println(" | Codigo | Producto | Precio de Venta |  ");
                                        System.out.println(" +--------+----------+-----------------+  ");
                                        System.out.println(" |   4    | Maiz     | L. 20           |  ");
                                        System.out.println(" +--------+----------+-----------------+  ");

                                        System.out.println("\n===== STOCK =====");
                                        System.out.println("Stock Maiz:" + stockMaiz);
                                        System.out.println("=================");

                                        do {
                                            System.out.print("Ingrese el codigo de producto a vender: ");
                                            codigoVender = sc.nextInt();

                                            if (codigoVender == 4) {
                                                /// maiz
                                    if (stockMaiz > 0) {
                                                    if (cantidadKgMaiz > 0 && stockMaiz > 0) {
                                                        double costoVentaMaiz = cantidadKgMaiz * (costoTotalMaiz / stockMaiz);
                                                        gananciasTotales += (subtotal_ventasMaiz - costoVentaMaiz);
                                                    }
                                                    cantidad = -1;
                                                    System.out.println("\n +-------------------------------------+  ");
                                                    System.out.println(" |       Seleccion de Cliente: C       |  ");
                                                    System.out.println(" +-------------------------------------+  ");
                                                    System.out.println(" | Codigo | Producto | Precio de Venta |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");
                                                    System.out.println(" |   4    | Maiz     | L. 20           |  ");
                                                    System.out.println(" +--------+----------+-----------------+  ");

                                                    while (cantidad <= 0) {
                                                        System.out.print("Ingrese la cantidad de kilogramos que desea comprar: ");
                                                        cantidad = sc.nextDouble();
                                                    }

                                                    if (stockMaiz >= cantidad) {
                                                        cantidadKgMaiz += cantidad;
                                                        veces_vendidasMaiz += cantidad;
                                                        subtotal_ventasMaiz += (cantidad * precioMaiz);
                                                        stockMaiz -= cantidad;
                                                    } else {
                                                        System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                    }
                                                } else {
                                                    System.out.println("MENSAJE: STOCK INSUFICIENTE!");
                                                }
                                            } else {
                                                System.out.println("MENSAJE: CODIGO DE PRODUCTO INVALIDO");
                                            }

                                        } while (codigoVender != 4);

                                        do {
                                            System.out.print("Desea comprar otro producto? 1.Si 2.No: ");
                                            seleccion_SiNo = sc.nextInt();

                                            if (seleccion_SiNo != 1 && seleccion_SiNo != 2) {
                                                System.out.println("MENSAJE: INGRESE UNA OPCION VALIDA");
                                            }
                                        } while (seleccion_SiNo != 1 && seleccion_SiNo != 2);

                                    } while (seleccion_SiNo == 1);

                                    if (cantidadKgMaiz > 0) {
                                        System.out.println("\n====== FACTURACION ======");

                                        System.out.println("\nNombre del producto: Maiz");
                                        System.out.println("Cantidad: " + cantidadKgMaiz + "kg");
                                        System.out.println("Precio Unitario: L. 20");
                                        System.out.println("Subtotal: L. " + subtotal_ventasMaiz);

                                        subtotal_ventas_general = subtotal_ventasAzucar + subtotal_ventasAvena + subtotal_ventasTrigo + subtotal_ventasMaiz;
                                        System.out.println("\nSubtotal total: L." + String.format("%.2f", subtotal_ventas_general));
                                        String texto_descuento = " %";
                                        double impuesto = 0.07;
                                        String texto_impuesto = "7%";
                                        // CALCULO DE IMPUESTO/DESCUENTOS PREVIAMENTE EXPLICADOS
                                        if (subtotal_ventas_general > 5000) {
                                            descuento = subtotal_ventas_general * 0.1;
                                            total_ventas_neto = subtotal_ventas_general - descuento;
                                            impuesto = total_ventas_neto * 0.07;
                                            total_ventas_neto += impuesto;
                                            texto_descuento = "10%";
                                            volumen_total_ventas += total_ventas_neto;

                                        } else if (subtotal_ventas_general >= 1000) {
                                            descuento = subtotal_ventas_general * 0.05;
                                            total_ventas_neto = subtotal_ventas_general - descuento;
                                            impuesto = total_ventas_neto * 0.07;
                                            total_ventas_neto += impuesto;
                                            texto_descuento = "5%";
                                            volumen_total_ventas += total_ventas_neto;

                                        } else {
                                            total_ventas_neto = subtotal_ventas_general;
                                            impuesto = total_ventas_neto * 0.07;
                                            total_ventas_neto += impuesto;
                                            texto_descuento = "0%";
                                            volumen_total_ventas += total_ventas_neto;
                                        }

                                        if (total_ventas_neto > mayorVenta) {
                                            mayorVenta = total_ventas_neto;
                                        }

                                        System.out.println("Descuento Aplicado: " + texto_descuento);
                                        System.out.println("Descuento: L." + String.format("%.2f", descuento));
                                        System.out.println("Impuesto: " + texto_impuesto);
                                        System.out.println("Impuesto: L." + String.format("%.2f", impuesto));
                                        System.out.println("Total neto: L." + String.format("%.2f", total_ventas_neto));
                                        System.out.println("=========================");

                                        dineroCaja += total_ventas_neto;
                                        System.out.println("\nSe ha actualizado el dinero en caja");
                                        System.out.println("Dinero Disponible en Caja: L." + String.format("%.2f", dineroCaja));

                                        contador_de_ventas_general += 1;

                                    } else {
                                        System.out.println("Volviendo al menu principal...");
                                        break;
                                    }

                                } else {
                                    System.out.println("MENSAJE: INGRESE UN TIPO DE CLIENTE VALIDO");

                                }

                            } while (clienteIngresado != 1 && clienteIngresado != 2 && clienteIngresado != 3);
                        } else {
                            System.out.println("\nNO PUEDE INICIAR VENTAS SIN STOCK ");
                            continue;
                        }

                    } else {
                        System.out.println("\nMENSAJE: ABRIR CAJA PRIMERO");
                    }

                } else if (menu_opcion == 3) {
                    //COMPRAS COMPRAS COMPRAS COMPRAS COMPRAS
                    if (permitir_compras) {
                        if (dineroCaja > 0) {

                            System.out.println("\nOpcion Seleccionada: ***Compras***");
                            System.out.println("Dinero Disponible en Caja: L." + String.format("%.2f", dineroCaja));
                            do {

                                System.out.print("Ingrese el tipo de proveedor: 1.(A), 2.(B), 3.(C): ");
                                proveedorIngresado = sc.nextInt();

                                // solo puede comprar 1 producto por compra 
                                switch (proveedorIngresado) {
                                    case 1:  //Proveedor A 

                                        subtotal_comprasAzucar = 0;
                                        subtotal_comprasMaiz = 0;
                                        precioAzucarC = 25;
                                        precioMaizC = 18;

                                        System.out.println(" +--------------------------------------+  ");
                                        System.out.println(" |       Tipo de Proveedor: A           |  ");
                                        System.out.println(" +--------------------------------------+  ");
                                        System.out.println(" | Codigo | Producto | Precio de Compra |  ");
                                        System.out.println(" +--------+----------+------------------+  ");
                                        System.out.println(" |   1    | Azucar   | L. 25            |  ");
                                        System.out.println(" +--------+----------+------------------+  ");
                                        System.out.println(" |   4    | Maiz     | L. 18            |  ");
                                        System.out.println(" +--------+----------+------------------+  ");

                                        do {
                                            System.out.print("\nIngrese el codigo del producto a comprar: ");
                                            codigoComprar = sc.nextInt();

                                            switch (codigoComprar) { // SWITCH DE CODIGOCOMPRAR 
                                                case 1: //AZUCAR
                                                    cantidadKgAzucar = -1;
                                                    while (cantidadKgAzucar <= 0) {
                                                        System.out.println("Ingrese la cantidad de kilogramos que desea comprar: ");
                                                        cantidadKgAzucar = sc.nextDouble();
                                                    }

                                                    subtotal_comprasAzucar = cantidadKgAzucar * precioAzucarC;

                                                    if (dineroCaja >= subtotal_comprasAzucar) {
                                                        System.out.println("El subtotal de la compra es: L." + subtotal_comprasAzucar);
                                                        dineroCaja -= subtotal_comprasAzucar; // aqui ya se resta el subtotal del dinero en caja se efectua la compra

                                                        System.out.println("\n==== FACTURA ====");
                                                        System.out.println("Nombre del producto: Azucar");
                                                        System.out.println("Precio Unitario: L.25");
                                                        System.out.println("Cantidad: " + cantidadKgAzucar);
                                                        System.out.println("Total: L. " + String.format("%.2f", subtotal_comprasAzucar));
                                                        System.out.println("=================");
                                                        stockAzucar += cantidadKgAzucar; //ACTUALIZA EL STOCK 
                                                        costoTotalAzucar += subtotal_comprasAzucar;
                                                        System.out.println("\nMENSAJE: Se ha realizado la compra con exito, su dinero en caja es de L." + String.format("%.2f", dineroCaja));
                                                        System.out.println("Stock de Azucar: " + stockAzucar); // STOCK AZUCAR AZUCAR

                                                        contador_de_compras_general += 1;
                                                        volumen_total_compras += subtotal_comprasAzucar;

                                                        if (subtotal_comprasAzucar > mayorCompra) {
                                                            mayorCompra = subtotal_comprasAzucar;
                                                        }

                                                        break;

                                                    } else {
                                                        System.out.println("MENSAJE: FONDOS INSUFICIENTES PARA LA COMPRA");
                                                        break;
                                                    }

                                                case 4: //MAIZ
                                                    cantidadKgMaiz = -1;

                                                    while (cantidadKgMaiz <= 0) {
                                                        System.out.print("Ingrese la cantidad de kilogramos que desea comprar: ");
                                                        cantidadKgMaiz = sc.nextDouble();
                                                    }

                                                    subtotal_comprasMaiz = cantidadKgMaiz * precioMaizC;
                                                    //SALE FACTURA SI SE HACE LA COMPRA
                                                    if (dineroCaja >= subtotal_comprasMaiz) {
                                                        System.out.println("El subtotal de la compra es: L." + subtotal_comprasMaiz);
                                                        dineroCaja -= subtotal_comprasMaiz;

                                                        System.out.println("\n==== FACTURA ====");
                                                        System.out.println("Nombre del producto: Maiz");
                                                        System.out.println("Precio Unitario: L.18");
                                                        System.out.println("Cantidad: " + cantidadKgMaiz);
                                                        System.out.println("Total: L. " + String.format("%.2f", subtotal_comprasMaiz));
                                                        System.out.println("=================");
                                                        stockMaiz += cantidadKgMaiz; //act 
                                                        costoTotalMaiz += subtotal_comprasMaiz;
                                                        System.out.println("\nMENSAJE: Se ha realizado la compra con exito, su dinero en caja es de L." + String.format("%.2f", dineroCaja));
                                                        System.out.println("Stock Maiz: " + stockMaiz); //refleja

                                                        contador_de_compras_general += 1;
                                                        volumen_total_compras += subtotal_comprasMaiz;

                                                        if (subtotal_comprasMaiz > mayorCompra) {
                                                            mayorCompra = subtotal_comprasMaiz;
                                                        }

                                                        break;

                                                    } else {
                                                        System.out.println("MENSAJE: FONDOS INSUFICIENTES PARA LA COMPRA");
                                                        break;
                                                    }

                                                default:
                                                    System.out.println("MENSAJE: CODIGO INVALIDO");
                                                    break;

                                            }

                                        } while (codigoComprar != 1 && codigoComprar != 4);

                                        break;

                                    case 2: //Proveedor B // 

                                        precioAvenaCB = 20;
                                        precioTrigoC = 30;

                                        System.out.println(" +--------------------------------------+  ");
                                        System.out.println(" |       Tipo de Proveedor: B           |  ");
                                        System.out.println(" +--------------------------------------+  ");
                                        System.out.println(" | Codigo | Producto | Precio de Compra |  ");
                                        System.out.println(" +--------+----------+------------------+  ");
                                        System.out.println(" |   2    | Avena    | L. 20            |  ");
                                        System.out.println(" +--------+----------+------------------+  ");
                                        System.out.println(" |   3    | Trigo    | L. 30            |  ");
                                        System.out.println(" +--------+----------+------------------+  ");

                                        do {

                                            System.out.print("\nIngrese el codigo de producto a comprar: ");
                                            codigoComprar = sc.nextInt();

                                            switch (codigoComprar) {
                                                case 2: //AVENA
                                                    cantidadKgAvena = -1;
                                                    while (cantidadKgAvena <= 0) {
                                                        System.out.print("Ingrese la cantidad en kilogramos que desea: ");
                                                        cantidadKgAvena = sc.nextDouble();
                                                    }

                                                    subtotal_comprasAvena = cantidadKgAvena * precioAvenaCB;

                                                    if (dineroCaja >= subtotal_comprasAvena) {
                                                        System.out.println("El subtotal de la compra es: L." + String.format("%.2f", subtotal_comprasAvena));
                                                        System.out.println("\n==== FACTURA ====");
                                                        System.out.println("Nombre del Producto: Avena");
                                                        System.out.println("Precio Unitario: L.20");
                                                        System.out.println("Cantidad: " + cantidadKgAvena);
                                                        System.out.println("Total: L." + String.format("%.2f", subtotal_comprasAvena));
                                                        System.out.println("=================");
                                                        dineroCaja -= subtotal_comprasAvena;

                                                        System.out.println("\nMENSAJE: Se ha realizado la compra con exito, su dinero en caja es: L." + String.format("%.2f", dineroCaja));

                                                        stockAvena += cantidadKgAvena; // se actualizo el stock
                                                        costoTotalAvena += subtotal_comprasAvena;
                                                        System.out.println("Stock Avena " + stockAvena); //se refleja el stock

                                                        contador_de_compras_general += 1;
                                                        volumen_total_compras += subtotal_comprasAvena;

                                                        if (subtotal_comprasAvena > mayorCompra) {
                                                            mayorCompra = subtotal_comprasAvena;
                                                        }

                                                        break;

                                                    } else {
                                                        System.out.println("MENSAJE: FONDOS INSUFICIENTES PARA LA COMPRA");
                                                        break;
                                                    }

                                                case 3://TRIGO
                                                    cantidadKgTrigo = -1;

                                                    while (cantidadKgTrigo <= 0) {
                                                        System.out.print("Ingrese la cantidad en kilogramos que desea comprar: ");
                                                        cantidadKgTrigo = sc.nextDouble();
                                                    }

                                                    subtotal_comprasTrigo = cantidadKgTrigo * precioTrigoC;

                                                    if (dineroCaja >= subtotal_comprasTrigo) {
                                                        System.out.println("El subtotal de la compra es: L." + String.format("%.2f", subtotal_comprasTrigo));
                                                        System.out.println("\n==== FACTURA ====");
                                                        System.out.println("Nombre del Producto: Trigo");
                                                        System.out.println("Precio Unitario: L.30");
                                                        System.out.println("Cantidad: " + cantidadKgTrigo);
                                                        System.out.println("Total: L." + String.format("%.2f", subtotal_comprasTrigo));
                                                        System.out.println("=================");

                                                        dineroCaja -= subtotal_comprasTrigo;

                                                        System.out.println("\nMENSAJE: Se ha realizado la compra con exito, su dinero en caja es: L." + String.format("%.2f", dineroCaja));
                                                        stockTrigo += cantidadKgTrigo; //actualiza
                                                        costoTotalTrigo += subtotal_comprasTrigo;
                                                        System.out.println("Stock Trigo:" + stockTrigo); //refleja
                                                        contador_de_compras_general += 1;
                                                        volumen_total_compras += subtotal_comprasTrigo;

                                                        if (subtotal_comprasTrigo > mayorCompra) {
                                                            mayorCompra = subtotal_comprasTrigo;
                                                        }

                                                        break;

                                                    } else {
                                                        System.out.println("MENSAJE: FONDOS INSUFICIENTES PARA LA COMPRA");
                                                        break;
                                                    }

                                                default:
                                                    System.out.println("MENSAJE: CODIGO INVALIDO");
                                                    break;

                                            }

                                        } while (codigoComprar != 2 && codigoComprar != 3);

                                        break;

                                    case 3: //PROVEEDOR C 

                                        precioAvenaCC = 22;

                                        System.out.println(" +--------------------------------------+  ");
                                        System.out.println(" |       Tipo de Proveedor: C           |  ");
                                        System.out.println(" +--------------------------------------+  ");
                                        System.out.println(" | Codigo | Producto | Precio de Compra |  ");
                                        System.out.println(" +--------+----------+------------------+  ");
                                        System.out.println(" |   2    | Avena    | L. 22            |  ");
                                        System.out.println(" +--------+----------+------------------+  ");

                                        do {
                                            System.out.print("\nIngrese el codigo del producto a comprar: ");
                                            codigoComprar = sc.nextInt();

                                            switch (codigoComprar) {
                                                case 2:
                                                    cantidadKgAvena = -1;
                                                    while (cantidadKgAvena <= 0) {
                                                        System.out.print("Ingrese la cantidad en kilogramos que desea: ");
                                                        cantidadKgAvena = sc.nextDouble();
                                                    }

                                                    subtotal_comprasAvena = cantidadKgAvena * precioAvenaCC;

                                                    if (dineroCaja >= subtotal_comprasAvena) {
                                                        System.out.println("El subtotal de la compra es: L." + String.format("%.2f", subtotal_comprasAvena));

                                                        System.out.println("\n==== FACTURA ====");
                                                        System.out.println("Nombre del Producto: Avena");
                                                        System.out.println("Precio Unitario: L.22");
                                                        System.out.println("Cantidad: " + cantidadKgAvena);
                                                        System.out.println("Total: L." + String.format("%.2f", subtotal_comprasAvena));
                                                        System.out.println("=================");

                                                        dineroCaja -= subtotal_comprasAvena;

                                                        System.out.println("\nMENSAJE: Se ha realizado la compra con exito, su dinero en caja es: L." + String.format("%.2f", dineroCaja));
                                                        stockAvena += cantidadKgAvena; //ACT 
                                                        costoTotalAvena += subtotal_comprasAvena;
                                                        System.out.println("Stock Avena: " + stockAvena); //REFLEJA

                                                        contador_de_compras_general += 1;
                                                        volumen_total_compras += subtotal_comprasAvena;

                                                        if (subtotal_comprasAvena > mayorCompra) {
                                                            mayorCompra = subtotal_comprasAvena;
                                                        }

                                                        break;

                                                    } else {
                                                        System.out.println("MENSAJE: FONDOS INSUFICIENTES PARA LA COMPRA");
                                                        break;
                                                    }

                                                default:
                                                    System.out.println("CODIGO INVALIDO");
                                                    break;

                                            }

                                        } while (codigoComprar != 2);
                                        break;

                                    default:
                                        System.out.println("PROVEEDOR INVALIDO");
                                        break;

                                }

                            } while (proveedorIngresado != 1 && proveedorIngresado != 2 && proveedorIngresado != 3);
                        } else {
                            System.out.println("\nMENSAJE: NO PUEDE INCIAR COMPRAS SIN DINERO");
                            continue;
                        }

                    } else {
                        System.out.println("\nMENSAJE: ABRIR CAJA PRIMERO");
                    }

                } else if (menu_opcion == 4) { // REPORTES REPORTES REPORTES REPORTES
                    System.out.println("\n======================================");
                    System.out.println("          REPORTES DEL DIA " + dia);
                    System.out.println("======================================");

                    System.out.println("\na. Dinero actual en caja: L." + String.format("%.2f", dineroCaja));

                    System.out.println("\nb. Transacciones realizadas:");
                    System.out.println("   Compras: " + contador_de_compras_general);
                    System.out.println("   Ventas: " + contador_de_ventas_general);

                    System.out.println("\nc. Totales financieros:");
                    System.out.println("   Compras totales: L." + String.format("%.2f", volumen_total_compras));
                    System.out.println("   Ventas totales: L." + String.format("%.2f", volumen_total_ventas));

                    if (volumen_total_compras > 0) {
                        double gananciaNeta = volumen_total_ventas - volumen_total_compras;
                        double porcentajeGanancia = (gananciaNeta / volumen_total_compras) * 100;
                        System.out.println("   Ganancia neta: L." + String.format("%.2f", gananciaNeta));
                        System.out.println("   Margen de ganancia: " + String.format("%.2f", porcentajeGanancia) + "%");
                    } else if (volumen_total_ventas > 0) {
                        System.out.println("   Ganancia neta: L." + String.format("%.2f", volumen_total_ventas));
                        System.out.println("   Margen de ganancia: 100% (solo ventas)");
                    } else {
                        System.out.println("   No hay transacciones registradas");
                    }
                    System.out.println("\nd. Valores promedio:");
                    if (contador_de_compras_general >= 0) {
                        System.out.println("   Compra promedio: L."
                                + String.format("%.2f", volumen_total_compras / contador_de_compras_general));
                    } else {
                        System.out.println("   No hay compras registradas");
                    }

                    if (contador_de_ventas_general >= 0) {
                        System.out.println("   Venta promedio: L."
                                + String.format("%.2f", volumen_total_ventas / contador_de_ventas_general));
                    } else {
                        System.out.println("   No hay ventas registradas");
                    }

                    System.out.println("\ne. Operaciones destacadas:");
                    System.out.println("   Mayor venta: L." + String.format("%.2f", mayorVenta));
                    System.out.println("   Mayor compra: L." + String.format("%.2f", mayorCompra));

                    System.out.println("\nf. Producto mas vendido:");
                    String productoTop = "Ninguno";
                    
                    int maxVentas = 0;
                    
                    if (veces_vendidasAzucar > maxVentas) { //SACA EL MAXIMO DE VENTAS
                        maxVentas = veces_vendidasAzucar;
                    }
                    if (veces_vendidasAvena > maxVentas) {
                        maxVentas = veces_vendidasAvena;
                    }
                    if (veces_vendidasTrigo > maxVentas) {
                        maxVentas = veces_vendidasTrigo;
                    }
                    if (veces_vendidasMaiz > maxVentas) {
                        maxVentas = veces_vendidasMaiz;
                    }

                    // VERIFICA SI HAY PRODUCTOS VENDIDOS
                    if (maxVentas > 0) {
                        // CONTAR CUANTOS PRODUCTOS TRAE EL MAX
                        int contadorEmpates = 0;
                        if (veces_vendidasAzucar == maxVentas) {
                            contadorEmpates++;
                        }
                        if (veces_vendidasAvena == maxVentas) {
                            contadorEmpates++;
                        }
                        if (veces_vendidasTrigo == maxVentas) {
                            contadorEmpates++;
                        }
                        if (veces_vendidasMaiz == maxVentas) {
                            contadorEmpates++;
                        }

                        if (contadorEmpates == 1) {

                            if (veces_vendidasAzucar == maxVentas) {
                                System.out.println("   Azucar (" + maxVentas + " kg)");
                            } else if (veces_vendidasAvena == maxVentas) {
                                System.out.println("   Avena (" + maxVentas + " kg)");
                            } else if (veces_vendidasTrigo == maxVentas) {
                                System.out.println("   Trigo (" + maxVentas + " kg)");
                            } else {
                                System.out.println("   Maiz (" + maxVentas + " kg)");
                            }
                        } else {
                            // HAY EMPATES
                            System.out.println("   Empate entre:"); //IFS PORQUE AMBAS CONDICIONES SE PUEDEN CUMPLIR
                            if (veces_vendidasAzucar == maxVentas) {
                                System.out.println("   - Azucar (" + maxVentas + " kg)");
                            }
                            if (veces_vendidasAvena == maxVentas) {
                                System.out.println("   - Avena (" + maxVentas + " kg)");
                            }
                            if (veces_vendidasTrigo == maxVentas) {
                                System.out.println("   - Trigo (" + maxVentas + " kg)");
                            }
                            if (veces_vendidasMaiz == maxVentas) {
                                System.out.println("   - Maiz (" + maxVentas + " kg)");
                            }
                        }
                    } else {
                        System.out.println("   No se han realizado ventas");
                    }

                } else if (menu_opcion == 5) { //CIERRE DE CAJA
                    if (dineroCaja > 0) {
                        System.out.println("\nOpcion Seleccionada: ***Cierre de Caja***"); 

                        System.out.println("\n======== DIA: " + dia + " ========");
                        System.out.println("Dinero En Caja: L." + String.format("%.2f", dineroCaja));
                        do {
                            do {
                                cantidad_max = 0.6 * dineroCaja;
                                System.out.print("Cuanto dinero desea depositar? (MAXIMO ACEPTADO: L." + String.format("%.2f", cantidad_max) + " ):");
                                deposito = sc.nextDouble();

                                if (deposito <= 0) {
                                    System.out.println("MENSAJE: TRANSACCION RECHAZADA");
                                }
                            } while (deposito <= 0);
                            // SEMI APARTADO DE BANCO
                            if (deposito <= cantidad_max) {
                                System.out.println("SE HA GUARDADO EXITOSAMENTE SU DEPOSITO");
                                dineroBanco += deposito;
                                dineroCaja -= deposito;
                                System.out.println("DINERO EN BANCO: L." + String.format("%.2f", dineroBanco));
                            } else {
                                System.out.println("MENSAJE: TRANSACCION RECHAZADA");
                            }

                        } while (deposito > cantidad_max);

                        contador_de_ventas_general = 0;
                        contador_de_compras_general = 0;
                        volumen_total_compras = 0;
                        volumen_total_ventas = 0;
                        mayorVenta = 0;
                        mayorCompra = 0;
                        veces_vendidasAzucar = 0;
                        veces_vendidasAvena = 0;
                        veces_vendidasTrigo = 0;
                        veces_vendidasMaiz = 0;
                        gananciasTotales = 0;
                        costoTotalAzucar = 0;
                        costoTotalAvena = 0;
                        costoTotalTrigo = 0;
                        costoTotalMaiz = 0;
                        dia++;
                        //VARIABLES REINICIADAS PARA ASEGURAR UNA BUENA EJERCUCION DEL CODIGO

                        permitir_ventas = false; //BOLEANS IMPORTANTES PARA ITERACIONES DE DIAS
                        permitir_compras = false;
                    } else {
                        System.out.println("MENSAJE: NO TIENES DINERO EN CAJA");
                    }

                } else if (menu_opcion == 6) {
                    System.out.println("Saliendo del Sistema...");
                    sistemaAbierto = false;
                } else {
                    System.out.println("Selecciona una opcion valida del menu");

                }

            } while (menu_opcion != 6);//while de la validacion del valor valido menu
        }
    }

}
