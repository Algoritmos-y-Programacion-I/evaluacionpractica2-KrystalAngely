package ui;

import java.util.Scanner;
import model.Controller;
import model.Project;

public class Executable {

    private Controller control;
    private Scanner reader;

    public Executable() {
        control = new Controller();
        reader = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Executable exe = new Executable();
        exe.menu();
    }

    /** 
     * Descripcion: Despliega el menu principal de funcionalidades al usuario
     * pre: El sistema debe estar inicializado.
     * pos: Se ejecuta la opción seleccionada por el usuario.
     */
    public void menu() {
        boolean flag = true;
        do {
            int option = -1;
            do {
                try {
                    System.out.println("\nBienvenido a Icesi Sostenible!");
                    System.out.println("\nMENU PRINCIPAL");
                    System.out.println("----------------------");
                    System.out.println("1) Registrar un Proyecto en un Pilar");
                    System.out.println("2) Consultar Proyectos por Pilar");
                    System.out.println("0) Salir");
                    option = reader.nextInt();
                } catch (Exception e) {
                    System.out.println("Entrada inválida, intente nuevamente.");
                    reader.nextLine(); // Limpiar el buffer
                }
            } while (option < 0 || option > 2); // Validación del rango de opciones

            switch (option) {
                case 1:
                    registerProject();
                    break;
                case 2:
                    showProjectsByPillar();
                    break;
                case 0:
                    System.out.println("Gracias por usar nuestros servicios. Adios!");
                    flag = false;
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
                    break;
            }
        } while (flag);
    }

    /** 
     * Descripcion: Solicita al usuario la información necesaria para registrar un Proyecto
     * en un Pilar en el sistema.
     * pre: El sistema debe estar inicializado.
     * pos: El proyecto es registrado si se cumplen las condiciones.
     */
    public void registerProject() {
        System.out.println("\nREGISTRAR PROYECTO");
        System.out.println("Proporcione el pilar al que quiere agregar el proyecto"+"\n" +
            "1. BIODIVERSIDAD" + "\n" + "2. AGUA" + "\n" + "3. TRATAMIENTO DE BASURAS " + "\n" + "4. ENERGIA");
        int type = reader.nextInt();
        reader.nextLine(); // Limpieza de buffer

        System.out.println("Proporcione el ID del proyecto");
        String id = reader.nextLine();

        System.out.println("Proporcione el nombre del proyecto");
        String name = reader.nextLine();
        
        System.out.println("Proporcione una descripción del proyecto");
        String description = reader.nextLine();

        System.out.println("Proporcione el estado del proyecto (activo o inactivo)");
        String status = reader.nextLine();

        boolean statusInfo = status.equalsIgnoreCase("activo");

        boolean resultado = control.registerProjectInPillar(id, name, description, type, statusInfo);

        if (resultado) {
            System.out.println("Proyecto registrado exitosamente");
        } else {
            System.out.println("Error: memoria llena o datos inválidos.");
        }
    }

    /** 
     * Descripcion: Muestra al usuario los Proyectos registrados en un Pilar específico.
     * pre: El sistema debe estar cargado con al menos un proyecto.
     * pos: Se muestran los proyectos registrados en el Pilar seleccionado.
     */
    public void showProjectsByPillar() {
        System.out.println("Seleccione un pilar: ");
        String list = control.getPillarList();
        System.out.println(list);
        System.out.print("Ingrese el nombre del pilar: ");
        reader.nextLine();
        String pickedPillar = reader.nextLine();

        

        //System.out.println("MI NOMBRE ES "+ pickedPillar);


        String result = control.queryProjectsByPillar(pickedPillar);
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("No se encontraron proyectos en el pilar seleccionado.");
        }
    }
}

