import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Ejercicio 8: asigna consolas a las tiendas respetando el orden de solicitudes. */
public class AsignadorConsolas {
    record ConsolaInventario(String codigo, String descripcion) { }
    record SolicitudTienda(String tienda, int cantidad) { }
    record AsignacionConsolas(String tienda, List<String> codigos) { }
    public static List<AsignacionConsolas> asignar(ArrayDeque<ConsolaInventario> inventario, ArrayDeque<SolicitudTienda> solicitudes) {
        List<AsignacionConsolas> resultado = new ArrayList<>();
        while (!solicitudes.isEmpty()) { SolicitudTienda s=solicitudes.removeFirst(); List<String> codigos=new ArrayList<>();
            for(int i=0;i<s.cantidad() && !inventario.isEmpty();i++) codigos.add(inventario.removeFirst().codigo());
            resultado.add(new AsignacionConsolas(s.tienda(), codigos)); }
        return resultado;
    }
    public static void main(String[] args) { Scanner e=new Scanner(System.in); ArrayDeque<ConsolaInventario> inventario=new ArrayDeque<>(); ArrayDeque<SolicitudTienda> solicitudes=new ArrayDeque<>(); System.out.print("Consolas disponibles: "); int n=e.nextInt(); e.nextLine(); for(int i=0;i<n;i++){System.out.print("Codigo: ");String c=e.nextLine();System.out.print("Descripcion: ");inventario.addLast(new ConsolaInventario(c,e.nextLine()));} System.out.print("Solicitudes: ");n=e.nextInt();e.nextLine();for(int i=0;i<n;i++){System.out.print("Tienda: ");String t=e.nextLine();System.out.print("Cantidad: ");solicitudes.addLast(new SolicitudTienda(t,e.nextInt()));e.nextLine();} for(AsignacionConsolas a:asignar(inventario,solicitudes))System.out.println(a.tienda()+": "+a.codigos()); }
}
