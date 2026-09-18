import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/** Ejercicio 9: asignacion de mesas por primera disponible o mejor ajuste. */
public class AsignadorMesas {
    record MesaRestaurante(String codigo, int capacidad) { }
    record ReservacionMesa(String nombre, int comensales) { }
    record AsignacionMesa(String nombre, String mesa) { }
    public static List<AsignacionMesa> asignar(ArrayDeque<MesaRestaurante> mesas, ArrayDeque<ReservacionMesa> reservas, boolean optimizar) {
        List<AsignacionMesa> resultado=new ArrayList<>();
        while(!reservas.isEmpty()) { ReservacionMesa r=reservas.removeFirst(); MesaRestaurante elegida=null;
            if(optimizar) elegida=mesas.stream().filter(m->m.capacidad()>=r.comensales()).min(Comparator.comparingInt(MesaRestaurante::capacidad)).orElse(null);
            else for(MesaRestaurante m:mesas) if(m.capacidad()>=r.comensales()){elegida=m;break;}
            if(elegida!=null){mesas.remove(elegida);resultado.add(new AsignacionMesa(r.nombre(),elegida.codigo()));} else resultado.add(new AsignacionMesa(r.nombre(),"Sin mesa disponible")); }
        return resultado;
    }
    public static void main(String[] args) { Scanner e=new Scanner(System.in); ArrayDeque<MesaRestaurante> mesas=new ArrayDeque<>();ArrayDeque<ReservacionMesa> reservas=new ArrayDeque<>();System.out.print("Numero de mesas: ");int n=e.nextInt();e.nextLine();for(int i=0;i<n;i++){System.out.print("Codigo: ");String c=e.nextLine();System.out.print("Capacidad: ");mesas.addLast(new MesaRestaurante(c,e.nextInt()));e.nextLine();}System.out.print("Reservaciones: ");n=e.nextInt();e.nextLine();for(int i=0;i<n;i++){System.out.print("Nombre: ");String nombre=e.nextLine();System.out.print("Comensales: ");reservas.addLast(new ReservacionMesa(nombre,e.nextInt()));e.nextLine();}System.out.print("Optimizar ocupacion (s/n): ");boolean o=e.nextLine().equalsIgnoreCase("s");for(AsignacionMesa a:asignar(mesas,reservas,o))System.out.println(a.nombre()+" -> "+a.mesa()); }
}
