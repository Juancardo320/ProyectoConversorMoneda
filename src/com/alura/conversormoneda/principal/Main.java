package com.alura.conversormoneda.principal;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static String monedaInicial;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double cantidadMoneda;
        String tipoMoneda;
        Gson gson = new Gson();

        Set<Currency> listaMonedas = Currency.getAvailableCurrencies();
        outerLoop:
        while (true){
            System.out.println("""
                    *************************************************
                    
                    Sea bienvenido/a al Conversor de Moneda =]
                    
                    1) Nombre de la moneda inicial (codigo 3 letras)
                    2) Nombre de la moneda destino (codigo 3 letras)
                    3) Cantidad de dinero a convertir
          
                    ARS (Peso argentino)
                    BOB (Boliviano boliviano)
                    BRL (Real brasileño)
                    CLP (Peso chileno)
                    COP (Peso colombiano)
                    EUR (Euro)
                    GBP (Libra esterlina)
                    JPY (Yen japones)
                    MXN (Peso mexicano)
                    USD (Dolar americano)
                    UYU (Peso uruguayo)
                    
                    Para terminar el programa escriba "salir"
                    *************************************************
                    
                    Ingrese el nombre de la moneda inicial:
                    """);

            while (true) {
                monedaInicial = sc.nextLine().toUpperCase();
                String monedaABuscar = monedaInicial;
                if (listaMonedas.stream().anyMatch(currency -> currency.getCurrencyCode().equals(monedaABuscar))) {
                    break;
                } else if (monedaInicial.equalsIgnoreCase("salir")) {
                    System.out.println("Fin del Programa");
                    break outerLoop;
                } else {
                    System.out.println("Ingrese un codigo de divisa valido");
                }
            }
            while (true) {
                System.out.println("Ingrese el codigo de la moneda a la que se va a convertir la moneda de origen:");
                tipoMoneda = sc.nextLine().toUpperCase();
                String monedaUsuario = tipoMoneda;
                if (listaMonedas.stream().anyMatch(currency -> currency.getCurrencyCode().equals(monedaUsuario))) {
                    break;
                } else if (tipoMoneda.equalsIgnoreCase("salir")) {
                    System.out.println("Fin del Programa");
                    break outerLoop;
                } else {
                    System.out.println("Ingrese un codigo de moneda valido");
                }
            }
            while (true) {
                try {
                    System.out.println("Ingrese la cantidad de " + monedaInicial + " que desea convertir a " + tipoMoneda + " :");
                    String cantidadUsuario = sc.nextLine();
                    cantidadMoneda = Double.valueOf(cantidadUsuario);
                    if (cantidadUsuario.equalsIgnoreCase("Salir")) {
                        System.out.println("Fin del Programa");
                        break outerLoop;
                    }else {
                        cantidadMoneda = Double.valueOf(cantidadUsuario);
                    }
                    if (cantidadMoneda > -99999) {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ingrese un numero");

                }
            }
            ConsultaApi api = new ConsultaApi();
            String resultado = api.consultaApi();

            try {
                Monedas moneda = gson.fromJson(resultado, Monedas.class);
                double tipoDeCambio = moneda.conversion_rates().get(tipoMoneda);
                double conversion = cantidadMoneda * tipoDeCambio;
                DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
                String conversionDecimal = formatoDecimal.format(conversion);
                System.out.println("""
                        \n*************************************************\s
                        
                        \nCantidad inicil :\s""" + cantidadMoneda + " " + monedaInicial + """
                        \nTipo de Cambio: \s""" + tipoDeCambio + " " + tipoMoneda + "/" + monedaInicial + """
                        \nResultado:\s""" + conversionDecimal + " " + tipoMoneda + """
                        
                        \n*************************************************\s
                        """);
                try {
                    File file = new File("Registro-de-transacciones.txt");
                    Date fecha = new Date();
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaConFormato = formatoFecha.format(fecha);
                    FileWriter fileWriter = new FileWriter(file, true);
                    fileWriter.write("""
                            \nFecha de la transaccion:\s""" + fechaConFormato + """
                            \nCantidad inicial:\s""" + cantidadMoneda + " " + monedaInicial + """
                            \nResultado:\s""" + conversionDecimal + " " + tipoMoneda + """
                            \n**********\s
                            """);
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("""
                        ¿Desea cambiar otra moneda?")
                        → Si
                        → No
                        """);

                String otraOperacion = sc.nextLine().toLowerCase();
                while (true) {
                    if (otraOperacion.equals("no") || otraOperacion.equals("salir")) {
                        System.out.println("Fin del Programa");
                        break outerLoop;
                    } else if (otraOperacion.equals("si")) {
                        break;
                    } else {
                        System.out.println("Opcion no valida");
                        otraOperacion = sc.nextLine().toLowerCase();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}