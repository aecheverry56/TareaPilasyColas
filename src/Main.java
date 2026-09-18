import java.util.List;
import java.util.Scanner;

/** Punto de entrada y coordinador de los ejercicios de pilas y colas. */
public final class Main {
    private record OpcionMenu(int numero, String descripcion, Runnable ejecutar) { }

    private static final List<OpcionMenu> OPCIONES = List.of(
            new OpcionMenu(1, "Pilas · Frases palindromas", () -> VerificadorPalindromos.main(new String[0])),
            new OpcionMenu(2, "Pilas · Ordenar tomos", () -> OrganizadorTomos.main(new String[0])),
            new OpcionMenu(3, "Pilas · Expresion infija a postfija", () -> ConversorInfijaPostfija.main(new String[0])),
            new OpcionMenu(4, "Pilas · Suma y resta de enteros grandes", () -> CalculadoraEnterosGrandes.main(new String[0])),
            new OpcionMenu(5, "Pilas · Edicion de una linea", () -> EditorLinea.main(new String[0])),
            new OpcionMenu(6, "Colas · Cola enlazada", () -> ColaEnlazada.main(new String[0])),
            new OpcionMenu(7, "Colas · Cola circular reutilizable", () -> ColaCircularReutilizable.main(new String[0])),
            new OpcionMenu(8, "Colas · Bicola", () -> Bicola.main(new String[0])),
            new OpcionMenu(9, "Colas · Atencion de cine", () -> ColaPrioritariaCine.main(new String[0])),
            new OpcionMenu(10, "Colas · Asignacion de consolas", () -> AsignadorConsolas.main(new String[0])),
            new OpcionMenu(11, "Colas · Asignacion de mesas", () -> AsignadorMesas.main(new String[0]))
    );

    private Main() { }

    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            int opcion;
            do {
                mostrarMenu();
                opcion = leerOpcion(entrada);
                ejecutarOpcion(opcion);
            } while (opcion != 0);
        }
        System.out.println("Programa finalizado.");
    }

    private static void mostrarMenu() {
        System.out.println("\n========== TAREA: PILAS Y COLAS ==========");
        OPCIONES.forEach(opcion -> System.out.printf("%2d. %s%n", opcion.numero(), opcion.descripcion()));
        System.out.println(" 0. Salir");
    }

    private static int leerOpcion(Scanner entrada) {
        while (true) {
            System.out.print("Seleccione una opcion: ");
            String valor = entrada.nextLine().trim();
            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException error) {
                System.out.println("Ingrese un numero de opcion valido.");
            }
        }
    }

    private static void ejecutarOpcion(int numero) {
        if (numero == 0) return;
        for (OpcionMenu opcion : OPCIONES) {
            if (opcion.numero() == numero) {
                opcion.ejecutar().run();
                System.out.println();
                return;
            }
        }
        System.out.println("Opcion no disponible.");
    }
}
