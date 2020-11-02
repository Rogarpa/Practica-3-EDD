package mx.unam.ciencias.edd;

import java.util.Iterator;

/**
 * <p>Clase para árboles binarios completos.</p>
 *
 * <p>Un árbol binario completo agrega y elimina elementos de tal forma que el
 * árbol siempre es lo más cercano posible a estar lleno.</p>
 */
public class ArbolBinarioCompleto<T> extends ArbolBinario<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Cola para recorrer los vértices en BFS. */
        private Cola<Vertice> cola;

        /* Inicializa al iterador. */
        public Iterador() {
            cola = new Cola<Vertice>();
            if(raiz != null) cola.mete(raiz);
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return !(cola.esVacia());
        }

        /* Regresa el siguiente elemento en orden BFS. */
        @Override public T next() {
            if(pila.esVacia()) throw new NoSuchElementException("Next a iterador sin elementos siguiente");
            Vertice aux = cola.saca();
            if(aux.izquierdo != null) cola.mete(aux.izquierdo);
            if(aux.derecho != null) cola.mete(aux.derecho);
            return aux;
        }
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinario}.
     */
    public ArbolBinarioCompleto() { super(); }

    /**
     * Construye un árbol binario completo a partir de una colección. El árbol
     * binario completo tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario completo.
     */
    public ArbolBinarioCompleto(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Agrega un elemento al árbol binario completo. El nuevo elemento se coloca
     * a la derecha del último nivel, o a la izquierda de un nuevo nivel.
     * @param elemento el elemento a agregar al árbol.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void agrega(T elemento) {
        //FEOOO y se puede sin realizar mults
        if(elemento == null) throw new IllegalArgumentException();
        Vertice aAgregar = new Vertice(elemento);
        elemento++;

        if(raiz == null) {
            raiz = aAgregar;
            return;
        }

        Vertice vaux = raiz;
        int x, y, altura, intervalo;
        y = altura = altura();
        intervalo = 1;
        
        while(altura-- > 0) intervalo = intervalo<<1;
        
        x = elementos - intervalo >> 1; 
        
        while(y-- > 1){
            if(x <= intervalo) {
                vaux = vaux.izquierdo;
                intervalo = intervalo >> 1;
            }else{
                vaux = vaux.derecho;
                intervalo += intervalo >> 1; 
            }
        }

        if((x % 2) == 0){
            vaux.izquierdo = aAgregar;
            ae    aAgregar.padre = vaux.izquierdo;
        }else{
            vaux.derecho = aAgregar;
            aAgregar.padre = vaux.derecho;
        }

    }

    private Vertice buscaCoordenado(int x, int y){

    }

    /**
     * Elimina un elemento del árbol. El elemento a eliminar cambia lugares con
     * el último elemento del árbol al recorrerlo por BFS, y entonces es
     * eliminado.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        VerticeArbolBinario<T> encontrado = super.busca(elemento);
        if(encontrado == null) return;
        elementos--;

        if(elementos == 0) raiz = null;

        

    }

    /**
     * Regresa la altura del árbol. La altura de un árbol binario completo
     * siempre es ⌊log<sub>2</sub><em>n</em>⌋.
     * @return la altura del árbol.
     */
    @Override public int altura() {
        return 0;
    }

    /**
     * Realiza un recorrido BFS en el árbol, ejecutando la acción recibida en
     * cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void bfs(AccionVerticeArbolBinario<T> accion) {
        // Aquí va su código.
    }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden BFS.
     * @return un iterador para iterar el árbol.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }
}
