/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

/**
 *
 * @author tomas
 * @param <T>
 */
public class Lista <T> {
    Nodo cabeza;
    T dato;
    
    public Lista() {
        this.dato = dato;
        this.cabeza = null;
    }
    
    public void agregar(T data, T dato, int peso) {
        Nodo nuevoNodo = new Nodo(data, dato, peso);
        nuevoNodo.next = cabeza;
        cabeza = nuevoNodo;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }
}
    
