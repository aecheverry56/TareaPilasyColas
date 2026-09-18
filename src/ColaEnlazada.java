import java.util.Iterator;
import java.util.NoSuchElementException;

/** Ejercicio 1: TAD Cola con las operaciones adicionales solicitadas. */
public class ColaEnlazada<T> implements Iterable<T> {
    private static class NodoColaEnlazada<E> { E dato; NodoColaEnlazada<E> siguiente; NodoColaEnlazada(E d) { dato = d; } }
    private NodoColaEnlazada<T> primero, ultimo;
    private int tamano;

    public void agregar(T elemento) {
        NodoColaEnlazada<T> nuevo = new NodoColaEnlazada<>(elemento);
        if (estaVacia()) primero = nuevo; else ultimo.siguiente = nuevo;
        ultimo = nuevo; tamano++;
    }
    public T eliminar() {
        if (estaVacia()) throw new NoSuchElementException("La cola esta vacia.");
        T dato = primero.dato; primero = primero.siguiente; tamano--;
        if (primero == null) ultimo = null;
        return dato;
    }
    public boolean estaVacia() { return tamano == 0; }
    public int tamano() { return tamano; }
    public T ultimoElemento() {
        if (estaVacia()) throw new NoSuchElementException("La cola esta vacia.");
        return ultimo.dato;
    }
    public ColaEnlazada<T> alReves() {
        java.util.Stack<T> pila = new java.util.Stack<>();
        for (T e : this) pila.push(e);
        ColaEnlazada<T> inversa = new ColaEnlazada<>();
        while (!pila.empty()) inversa.agregar(pila.pop());
        return inversa;
    }
    /** Agrega al final los elementos de otra cola, sin modificarla. */
    public void concatenar(ColaEnlazada<T> otra) { for (T e : otra) agregar(e); }
    public static <E> ColaEnlazada<E> intercalar(ColaEnlazada<E> una, ColaEnlazada<E> otra) {
        Iterator<E> a = una.iterator(), b = otra.iterator(); ColaEnlazada<E> resultado = new ColaEnlazada<>();
        while (a.hasNext() || b.hasNext()) { if (a.hasNext()) resultado.agregar(a.next()); if (b.hasNext()) resultado.agregar(b.next()); }
        return resultado;
    }
    public Iterator<T> iterator() {
        return new Iterator<>() { NodoColaEnlazada<T> actual = primero;
            public boolean hasNext() { return actual != null; }
            public T next() { if (!hasNext()) throw new NoSuchElementException(); T d = actual.dato; actual = actual.siguiente; return d; }
        };
    }
    public String toString() { StringBuilder s = new StringBuilder("["); for (T e : this) { if (s.length() > 1) s.append(", "); s.append(e); } return s.append(']').toString(); }
    public static void main(String[] args) { ColaEnlazada<String> cola = new ColaEnlazada<>(); for (String x : new String[]{"A","B","C","D"}) cola.agregar(x); System.out.println("Cola: " + cola + ", ultimo: " + cola.ultimoElemento()); System.out.println("Inversa: " + cola.alReves()); }
}
