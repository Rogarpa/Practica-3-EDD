package mx.unam.ciencias.edd;

import java.util.Comparator;

import javax.lang.model.element.Element;

/**
 * Clase para ordenar y buscar arreglos genéricos.
 */
public class Arreglos {

    /* Constructor privado para evitar instanciación. */
    private Arreglos() {}

    /**
     * Ordena el arreglo recibido usando QickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordenar el arreglo.
     */
    public static <T> void
    quickSort(T[] arreglo, Comparator<T> comparador) {
        quickSort(arreglo, 0, arreglo.length - 1, comparador);         
    }

    private static <T> void quickSort(T[] arreglo, int a, int b, Comparator<T> comparador){
        if(b <= a) return;
        int i = a +1;
        int j = b;
        while(i < j){
            if(comparador.compare(arreglo[i], arreglo[a]) > 0 
            && comparador.compare(arreglo[j], arreglo[a]) <= 0){
                intercambia(arreglo, i, j);
                i++;
                j--;
            }
            else if(comparador.compare(arreglo[i], arreglo[a]) <= 0) i++;
            else j--;
        }
        if(comparador.compare(arreglo[i], arreglo[a]) > 0 ) i--;
        intercambia(arreglo, a, i);
        quickSort(arreglo, a, i - 1, comparador); 
        quickSort(arreglo, i + 1, b, comparador);
    }

    private static <T> void intercambia(T[] arreglo, int primeroAIntercambiar, int segundoAIntercambiar){
        T aux = arreglo[primeroAIntercambiar];
        arreglo[primeroAIntercambiar] = arreglo[segundoAIntercambiar];
        arreglo[segundoAIntercambiar] = aux;
    }

    /**
     * Ordena el arreglo recibido usando QickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    quickSort(T[] arreglo) {
        quickSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordernar el arreglo.
     */
    public static <T> void
    selectionSort(T[] arreglo, Comparator<T> comparador) {
        int n = arreglo.length;
        for(int i = 0; i < n-1; i++){
            int m = i;
            for(int j = i+1; j < n; j++){
                if(comparador.compare(arreglo[j], arreglo[m]) < 0) m = j;
            }
            intercambia(arreglo, m, i);
        }
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    selectionSort(T[] arreglo) {
        selectionSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo dónde buscar.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador para hacer la búsqueda.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T> int
    busquedaBinaria(T[] arreglo, T elemento, Comparator<T> comparador) {
        return busquedaBinaria(arreglo, 0, arreglo.length-1, elemento, comparador);
    }

    private static <T> int busquedaBinaria (T[] arreglo, int a, int b, T elemento, Comparator<T> comparador){
        if(b < a) return -1;
        int divisor = a + ((b-a) >> 1);
        if(comparador.compare(arreglo[divisor], elemento) == 0) return divisor;
        else if(comparador.compare(elemento, arreglo[divisor]) < 0) return busquedaBinaria(arreglo, a, divisor-1, elemento, comparador);
        else return busquedaBinaria(arreglo, divisor + 1, b, elemento, comparador);    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     * @param elemento el elemento a buscar.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T extends Comparable<T>> int
    busquedaBinaria(T[] arreglo, T elemento) {
        return busquedaBinaria(arreglo, elemento, (a, b) -> a.compareTo(b));
    }
}
