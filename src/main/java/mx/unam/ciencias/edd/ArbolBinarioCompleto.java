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
            Vertice aux = cola.saca();
            if(aux.izquierdo != null) cola.mete(aux.izquierdo);
            if(aux.derecho != null) cola.mete(aux.derecho);
            return aux.elemento;
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
                elementos++;
        
                if(raiz == null) {
                    raiz = aAgregar;
                    return;
                }
        
                int x, y, intervalo;
                Vertice padreUltimo;
                y = altura();
                intervalo = 1;
                
                for(int altura = y; altura > 0; altura--) intervalo = intervalo<<1;
                x = elementos - intervalo >> 1;
                
                if((x % 2) == 0) padreUltimo = buscaCoordenado(x/2, y-1);
                else padreUltimo = buscaCoordenado((x-1)/2, y-1);
                
                if((x % 2) == 0){
                    padreUltimo.izquierdo = aAgregar;
                    aAgregar.padre = padreUltimo.izquierdo;
                }else{
                    padreUltimo.derecho = aAgregar;
                    aAgregar.padre = padreUltimo.derecho;
                }
        
            }
        
            private Vertice buscaCoordenado(int x, int y){
                Vertice vaux = raiz;
                int intervalo = 1;
                while(y-- > 0){
                    if(x <= intervalo) {
                        vaux = vaux.izquierdo;
                        intervalo = intervalo >> 1;
                    }else{
                        vaux = vaux.derecho;
                        intervalo += intervalo >> 1; 
                    }
                }
                return vaux;
            }
        
    /**
     * Elimina un elemento del árbol. El elemento a eliminar cambia lugares con
     * el último elemento del árbol al recorrerlo por BFS, y entonces es
     * eliminado.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        if(elemento == null) return;

        Vertice encontrado = vertice(super.busca(elemento));
        if(encontrado == null) return;
        elementos--;

        if(elementos == 0) {
            raiz = null;
            return;
        }

        int x, y, altura, intervalo;
        y = altura = altura();
        intervalo = 1;
        
        while(altura-- > 0) intervalo = intervalo<<1;
        x = elementos - intervalo >> 1; 

        Vertice aCambiar = buscaCoordenado(x, y);
        T aux;
        aux = aCambiar.elemento;
        aCambiar.elemento = encontrado.elemento;
        encontrado.elemento = aux;

        if(aCambiar.padre.izquierdo == aCambiar) aCambiar.padre.izquierdo = null;
        else aCambiar.padre.derecho = null;
    }

    /**
     * Regresa la altura del árbol. La altura de un árbol binario completo
     * siempre es ⌊log<sub>2</sub><em>n</em>⌋.
     * @return la altura del árbol.
     */
    @Override public int altura() {
        int altura = 0;
        int n = elementos;
        while(n != 0 || n != 1){
            n = n>>1;
            altura++;
        }
        return altura;    }

    /**
     * Realiza un recorrido BFS en el árbol, ejecutando la acción recibida en
     * cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void bfs(AccionVerticeArbolBinario<T> accion) {
        Cola<Vertice> cola = new Cola<Vertice>();
        if(raiz == null) return;
        cola.mete(raiz);
        Vertice aux;
        while(! cola.esVacia()){
            aux = cola.saca();
            accion.actua(aux);
            if(aux.izquierdo != null) cola.mete(aux.izquierdo);
            if(aux.derecho != null) cola.mete(aux.derecho);
        }
    }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden BFS.
     * @return un iterador para iterar el árbol.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }
}
