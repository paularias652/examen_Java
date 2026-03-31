import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Mascota> mascotas = new ArrayList<>();

    public static void main(String[] args) {
        int opcion = 0;

        do {
            mostrarMenu();

            try {
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1:
                        registrarMascota();
                        break;
                    case 2:
                        mostrarMascotas();
                        break;
                    case 3:
                        ejecutarSonidoPorId();
                        break;
                    case 4:
                        buscarPorNombre();
                        break;
                    case 5:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un número válido.");
                sc.nextLine(); // limpiar entrada incorrecta
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }

            System.out.println();

        } while (opcion != 5);
    }

    public static void mostrarMenu() {
        System.out.println("===== SISTEMA DE GESTIÓN DE MASCOTAS =====");
        System.out.println("1. Registrar mascota");
        System.out.println("2. Mostrar mascotas");
        System.out.println("3. Ejecutar sonido de una mascota");
        System.out.println("4. Buscar mascota por nombre");
        System.out.println("5. Salir");
    }

    public static void registrarMascota() {
        try {
            System.out.println("Seleccione tipo de mascota:");
            System.out.println("1. Perro");
            System.out.println("2. Gato");
            System.out.println("3. Ave");
            System.out.print("Opción: ");
            int tipo = sc.nextInt();
            sc.nextLine();

            System.out.print("Ingrese id: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Ingrese nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Ingrese edad: ");
            int edad = sc.nextInt();
            sc.nextLine();

            if (id <= 0) {
                System.out.println("Error: el id debe ser mayor a 0.");
                return;
            }

            if (nombre == null || nombre.trim().isEmpty()) {
                System.out.println("Error: el nombre no puede estar vacío.");
                return;
            }

            if (edad <= 0) {
                System.out.println("Error: la edad debe ser mayor a 0.");
                return;
            }

            if (buscarPorId(id) != null) {
                System.out.println("Error: ya existe una mascota con ese id.");
                return;
            }

            Mascota nuevaMascota;

            switch (tipo) {
                case 1:
                    nuevaMascota = new Perro(id, nombre, edad);
                    break;
                case 2:
                    nuevaMascota = new Gato(id, nombre, edad);
                    break;
                case 3:
                    nuevaMascota = new Ave(id, nombre, edad);
                    break;
                default:
                    System.out.println("Tipo de mascota inválido.");
                    return;
            }

            mascotas.add(nuevaMascota);
            System.out.println("Mascota registrada correctamente.");

        } catch (InputMismatchException e) {
            System.out.println("Error: entrada inválida. Debe ingresar números donde corresponda.");
            sc.nextLine();
        }
    }

    public static void mostrarMascotas() {
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
            return;
        }

        System.out.println("===== LISTA DE MASCOTAS =====");
        for (Mascota mascota : mascotas) {
            mascota.mostrarInfo();
            System.out.println("Tipo: " + mascota.getClass().getSimpleName());
            System.out.println("Sonido: " + mascota.hacerSonido());
            System.out.println("-----------------------------");
        }
    }

    public static void ejecutarSonidoPorId() {
        try {
            System.out.print("Ingrese el id de la mascota: ");
            int id = sc.nextInt();
            sc.nextLine();

            Mascota mascota = buscarPorId(id);

            if (mascota != null) {
                System.out.println("Sonido: " + mascota.hacerSonido());
            } else {
                System.out.println("Mascota no encontrada");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: debe ingresar un id numérico.");
            sc.nextLine();
        }
    }

    public static void buscarPorNombre() {
        System.out.print("Ingrese el nombre de la mascota: ");
        String nombreBuscado = sc.nextLine();

        for (Mascota mascota : mascotas) {
            if (mascota.getNombre().equalsIgnoreCase(nombreBuscado)) {
                System.out.println("Mascota encontrada:");
                mascota.mostrarInfo();
                System.out.println("Tipo: " + mascota.getClass().getSimpleName());
                System.out.println("Sonido: " + mascota.hacerSonido());
                return;
            }
        }

        System.out.println("Mascota no encontrada");
    }

    public static Mascota buscarPorId(int id) {
        for (Mascota mascota : mascotas) {
            if (mascota.getId() == id) {
                return mascota;
            }
        }
        return null;
    }
}
