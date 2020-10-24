package mx.unam.ciencias.edd;

/**
 * Clase para colas genéricas.
 */
public class Cola<T> extends MeteSaca<T> {

    /**
     * Regresa una representación en cadena de la cola.
     * @return una representación en cadena de la cola.
     */
    @Override public String toString() {
        if(rabo == null) return "";

        String toString = "";
        Nodo it = cabeza;

        while(it != null){
            toString += it.elemento.toString() + ",";
            it = it.siguiente;
        }

        return toString;
    }

    /**
     * Agrega un elemento al final de la cola.
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
        
        rabo.siguiente = ameter;
        rabo = ameter;
    }
}
