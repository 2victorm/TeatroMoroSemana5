package com.mycompany.moro2semana5; 

import java.util.Scanner; 

public class Moro2Semana5 {

    // Clase interna para representar una entrada o ticket
    static class Ticket {
        String sector;           // Sector elegido por el usuario (Vip, Platea o General)
        int asiento;             // Número de asiento seleccionado (1 a 10)
        double precioFinal;      // Precio final después de aplicar descuento
        int edad;                // Edad del comprador
        double descuento;        // Porcentaje de descuento aplicado (0.10, 0.15)

        // Constructor de la clase Ticket
        public Ticket(String sector, int asiento, double precioFinal, int edad, double descuento) {
            this.sector = sector;
            this.asiento = asiento;
            this.precioFinal = precioFinal;
            this.edad = edad;
            this.descuento = descuento;
        }

        // Método que entrega una representación del ticket
        public String toString() {
            return "Sector: " + sector +
                   ", Asiento: " + asiento +
                   ", Precio Final: " + "$"+precioFinal +
                   ", Edad: " + edad +
                   ", Descuento aplicado: " + (int)(descuento * 100) + "%";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Objeto para leer datos desde consola
        int opcionMenu = 0; // Control del menú principal

        int sectores = 3;    // Número de sectores del teatro
        int asientos = 10;   // Cantidad de asientos por sector

        // Precios base por sector
        int precioVip = 30000;
        int precioPlatea = 20000;
        int precioGeneral = 10000;

        // Arreglos para controlar la ocupación de asientos en cada sector
        boolean[] sectorVip = new boolean[asientos];
        boolean[] sectorPlatea = new boolean[asientos];
        boolean[] sectorGeneral = new boolean[asientos];

        // Arreglo para almacenar los tickets comprados (máximo 100 entradas)
        final int maxTickets = 100;
        Ticket[] ticketsComprados = new Ticket[maxTickets];
        int cantidadTickets = 0; // Contador de entradas vendidas

        // Ciclo principal del menú
        do {
            // Mostrar menú principal
            System.out.println("##### Bienvenid@ a Teatro Moro ####");
            System.out.println("1. Comprar entrada");
            System.out.println("2. Información de entradas compradas");
            System.out.println("3. Promociones");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            opcionMenu = scanner.nextInt(); // Leer opción del usuario

            switch (opcionMenu) {
                case 1:
                    // Mostrar plano de ubicaciones disponibles
                    System.out.println("\nMapa de ubicaciones del Teatro Moro:");

                    // Recorrer cada sector para mostrar los asientos ocupados/libres
                    for (int s = 1; s <= sectores; s++) {
                        String nombreSector = "";        // Nombre del sector actual
                        boolean[] sectorActual = null;  // Arreglo actual de ocupación

                        // Determinar el sector
                        if (s == 1) {
                            nombreSector = "1- Vip    ";
                            sectorActual = sectorVip;
                        } else if (s == 2) {
                            nombreSector = "2- Platea ";
                            sectorActual = sectorPlatea;
                        } else {
                            nombreSector = "3- General";
                            sectorActual = sectorGeneral;
                        }

                        // Mostrar los asientos del sector
                        System.out.print(nombreSector + ": ");
                        for (int a = 0; a < asientos; a++) {
                            System.out.print(sectorActual[a] ? "[X]" : "[O]"); // [X] = ocupado, [O] = libre
                        }
                        System.out.println();
                    }

                    // Seleccionar sector
                    System.out.print("Selecciona un sector (1: Vip, 2: Platea, 3: General): ");
                    int selSector = scanner.nextInt();

                    // Validar sector
                    if (selSector < 1 || selSector > 3) {
                        System.out.println("Sector incorrecto.\n");
                        break;
                    }

                    // Seleccionar asiento
                    System.out.print("Seleccione un asiento (1 a " + asientos + "): ");
                    int selAsiento = scanner.nextInt();

                    // Validar asiento
                    if (selAsiento < 1 || selAsiento > asientos) {
                        System.out.println("Número de asiento incorrecto.\n");
                        break;
                    }

                    int indiceAsiento = selAsiento - 1; // Ajustar índice (arreglos comienzan en 0)

                    // Determinar el sector elegido
                    String nombreSector;
                    int precioBase = 0;
                    boolean[] sectorElegido = null;

                    switch (selSector) {
                        case 1:
                            nombreSector = "Vip";
                            precioBase = precioVip;
                            sectorElegido = sectorVip;
                            break;
                        case 2:
                            nombreSector = "Platea";
                            precioBase = precioPlatea;
                            sectorElegido = sectorPlatea;
                            break;
                        case 3:
                            nombreSector = "General";
                            precioBase = precioGeneral;
                            sectorElegido = sectorGeneral;
                            break;
                        default:
                            System.out.println("Sector incorrecto.\n");
                            continue;
                    }

                    // Verificar si el asiento ya está ocupado
                    if (sectorElegido[indiceAsiento]) {
                        System.out.println("El asiento está ocupado. Intenta de nuevo.\n");
                        break;
                    }

                    // Pedir edad del usuario para aplicar posibles descuentos
                    System.out.print("Ingresa tu edad: ");
                    int edad = scanner.nextInt();

                    // Determinar descuento según edad
                    double descuento = 0.0;
                    if (edad < 25) {
                        descuento = 0.10; // Estudiante
                    } else if (edad > 60) {
                        descuento = 0.15; // Tercera edad
                    }

                    // Calcular el precio final con descuento aplicado
                    double precioFinal = precioBase * (1 - descuento);

                    // Mostrar resumen del precio
                    System.out.println("El precio base en el sector " + nombreSector + " es: " + "$"+precioBase);
                    if (descuento > 0) {
                        System.out.println("Tienes un descuento del " + (int)(descuento * 100) + "%.");
                    }
                    System.out.println("El precio final es de: " + "$"+precioFinal);

                    // Confirmar la compra
                    System.out.print("¿Deseas confirmar tu compra? (1: Sí, 2: No): ");
                    int confirmar = scanner.nextInt();
                    if (confirmar == 1) {
                        sectorElegido[indiceAsiento] = true; // Marcar asiento como ocupado
                        System.out.println("Compra realizada exitosamente.\n");

                        if (cantidadTickets < maxTickets) {
                            // Registrar la entrada en el arreglo
                            ticketsComprados[cantidadTickets] = new Ticket(nombreSector, selAsiento, precioFinal, edad, descuento);
                            cantidadTickets++; // Aumentar el contador de entradas
                        } else {
                            System.out.println("Se ha alcanzado el número máximo de entradas.");
                        }
                    } else {
                        System.out.println("Compra cancelada.\n");
                    }
                    break;

                case 2:
                    // Mostrar entradas compradas
                    System.out.println("\n### Entradas Compradas ###");
                    if (cantidadTickets == 0) {
                        System.out.println("Aún no compras entradas.\n");
                    } else {
                        // Mostrar todas las entradas
                        for (int i = 0; i < cantidadTickets; i++) {
                            System.out.println((i + 1) + ". " + ticketsComprados[i]);
                        }

                        // Opción para eliminar una entrada
                        System.out.print("¿Deseas eliminar alguna entrada? (1: Sí, 2: No): ");
                        int opcEliminar = scanner.nextInt();
                        if (opcEliminar == 1) {
                            System.out.print("Ingresa el número de la entrada que deseas eliminar: ");
                            int numEliminar = scanner.nextInt();                     

                            // Validar número
                            if (numEliminar < 1 || numEliminar > cantidadTickets) {
                                System.out.println("Número de entrada inválido.\n");
                            } else {
                                int indiceEliminar = numEliminar - 1;
                                Ticket ticketEliminar = ticketsComprados[indiceEliminar];

                                // Dejar libre el asiento nuevamente según el sector del ticket
                                if (ticketEliminar.sector.equalsIgnoreCase("Vip")) {
                                    sectorVip[ticketEliminar.asiento - 1] = false;
                                } else if (ticketEliminar.sector.equalsIgnoreCase("Platea")) {
                                    sectorPlatea[ticketEliminar.asiento - 1] = false;
                                } else if (ticketEliminar.sector.equalsIgnoreCase("General")) {
                                    sectorGeneral[ticketEliminar.asiento - 1] = false;
                                }

                                // Eliminar entrada moviendo el resto hacia la izquierda
                                for (int i = indiceEliminar; i < cantidadTickets - 1; i++) {
                                    ticketsComprados[i] = ticketsComprados[i + 1];
                                }
                                ticketsComprados[cantidadTickets - 1] = null; // Limpiar última posición
                                cantidadTickets--; // Reducir contador
                                System.out.println("Entrada eliminada1.\n");
                            }
                        }
                    }
                    break;

                case 3:
                    // Mostrar promociones 
                    System.out.println("\n### Ofertas ###");
                    System.out.println("Descuento para estudiantes (menores de 25 años): 10%");
                    System.out.println("Descuento para tercera edad (mayores de 60 años): 15%\n");
                    break;

                case 4:
                    // Salir del programa
                    System.out.println("Vuelve pronto!");
                    break;

                default:
                    // En caso de opción incorrecta
                    System.out.println("Opción incorrecta. Inténtalo nuevamente.\n");
                    break;
            }

        } while (opcionMenu != 4); // Repetir el menú hasta que el usuario elija salir

        scanner.close(); 
    }
}
