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
        if(elemento == null) throw new IllegalArgumentException();
        
        Vertice h = nuevoVertice(elemento);
        
        
        elementos++;
        
        if(raiz == null){ 
            raiz = h;
            return;
        }else{
            
            Cola<Vertice> c = new Cola<Vertice>();
            Vertice v; 
            c.mete(raiz);
            while(!(c.esVacia()) ){
                v = c.saca();
                
                if(v.izquierdo == null){
                    v.izquierdo = h;
                    h.padre = v;
                    return;
                }
                if(v.derecho == null){
                    v.derecho = h;
                    h.padre = v;
                    return;    
                }
                
                c.mete(v.izquierdo);
                c.mete(v.derecho);
            }
        }
    }
    /**
     * Elimina un elemento del árbol. El elemento a eliminar cambia lugares con
     * el último elemento del árbol al recorrerlo por BFS, y entonces es
     * eliminado.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        if(elemento == null) return;
        
        Vertice h = vertice(busca(elemento)), aux = null;
        
        if(h == null) return;
        
        else{
            elementos--;
            if(elementos == 0){ 
                raiz = null;
                return;
            }
            
            int i = 0;
            
            Cola<Vertice> c = new Cola<Vertice>();
            Vertice v; 
            
            c.mete(raiz);
            
            while(!(c.esVacia())){
                v = c.saca();
                i++;
                if(i == elementos + 1) aux = v;
                
                if(v.izquierdo != null)    c.mete(v.izquierdo);
                if(v.derecho != null)    c.mete(v.derecho);
            }
            
            
            h.elemento = aux.elemento;
            if(aux.padre.derecho == null) aux.padre.izquierdo = null;
            else aux.padre.derecho = null;
            
        }
    }

    /**
     * Regresa la altura del árbol. La altura de un árbol binario completo
     * siempre es ⌊log<sub>2</sub><em>n</em>⌋.
     * @return la altura del árbol.
     */
    @Override public int altura() {
        int altura = 0;
        int n = elementos;
        while(! (n == 0 || n == 1)){
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
