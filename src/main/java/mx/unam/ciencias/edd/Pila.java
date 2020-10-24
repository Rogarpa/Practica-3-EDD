package mx.unam.ciencias.edd;

/**
 * Clase para pilas genéricas.
 */
public class Pila<T> extends MeteSaca<T> {

    /**
     * Regresa una representación en cadena de la pila.
     * @return una representación en cadena de la pila.
     */
    @Override public String toString() {
        if(rabo == null) return "";

        String toString = "";
        Nodo it = cabeza;

        while(it != null){
            toString += it.elemento.toString() + "\n";
            it = it.siguiente;
        }

        return toString;
    }

    /**
     * Agrega un elemento al tope de la pila.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void mete(T elemento) {
        if(elemento == null) throw new IllegalArgumentException();

        Nodo ameter = new Nodo(elemento);

        if(rabo == null){
            cabeza = rabo = ameter;
            return;
        }
        
        ameter.siguiente = cabeza;
        cabeza = ameter;
    }
}
