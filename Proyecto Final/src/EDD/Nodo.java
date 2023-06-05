/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import java.awt.Point;

/**
 *
 * @author tomas
 * @param <T>
 */
public class Nodo <T> {
    T data;
    T dato;
    int peso;
    Nodo next;
    int x;
    int y;
    boolean graficado;
    Point posicion;
    
    public Nodo(T data, T dato, int peso) {
        this.data = data;
        this.dato = dato;
        this.peso = peso;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(T peso) {
        this.peso = (int) peso;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }
}
 

