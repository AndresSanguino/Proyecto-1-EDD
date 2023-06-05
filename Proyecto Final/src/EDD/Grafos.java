/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFrame;


/**
 *
 * @author tomas
 * @param <T>
 */
public class Grafos <T> {
    
    NodoVertice<T> listaVertices;
    int numVertices;
    Lista[] listaAdyacencia;
    
    public Grafos(int numVertices) {
        this.listaVertices = null;
        this.numVertices= numVertices;
        this.listaAdyacencia = new Lista[numVertices];
        
        for (int i = 0; i < numVertices; i++) {
            this.listaAdyacencia[i] = new Lista();
        }
    }
    
    public void agregarArista(T valorOrigen, T valorDestino, int peso, int i) {

         if (i < numVertices){
            this.listaAdyacencia[i].agregar(valorOrigen, valorDestino, peso);
         }
    }
    
    public class NodoVertice<T> {
        public T data;
        public NodoVertice<T> siguiente;
        public NodoAdyacencia<T> listaAdyacencia;
        private Point posicion; // posición del vértice en el plano

        public NodoVertice(T data) {
            this.data = data;
            this.siguiente = null;
            this.listaAdyacencia = null;
            this.posicion = null;
        }
        
        public void asignarPosicion(Nodo actualNodo, int x, int y) {
            actualNodo.posicion = new Point(x, y);
        }
        
        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public NodoVertice<T> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(NodoVertice<T> siguiente) {
            this.siguiente = siguiente;
        }

        public NodoAdyacencia<T> getListaAdyacencia() {
            return listaAdyacencia;
        }

        public void setListaAdyacencia(NodoAdyacencia<T> listaAdyacencia) {
            this.listaAdyacencia = listaAdyacencia;
        }

        public Point getPosicion() {
            return posicion;
        }

        public void setPosicion(Point posicion) {
            this.posicion = posicion;
        }
    }

    public class NodoAdyacencia<T> {
        public NodoVertice<T> cabeza;
        public NodoArista<T> listaArista;
        public NodoAdyacencia<T> siguiente;

        public NodoAdyacencia(NodoVertice<T> cabeza) {
            this.cabeza = cabeza;
            this.listaArista = null;
            this.siguiente = null;
        }
    }

    public class NodoArista<T> {
        public NodoVertice<T> origen;
        public NodoVertice<T> destino;
        public int peso;
        public NodoArista<T> siguiente;

        public NodoArista(NodoVertice<T> origen, NodoVertice<T> destino, int peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
            this.siguiente = null;
        }
    }
    
    public int indiceDe(T vertice) {
        NodoVertice<T> actual = listaVertices;
        int indice = 0;

        while (actual != null && !actual.data.equals(vertice)) {
            actual = actual.siguiente;
            indice++;
        }

        return (actual == null) ? -1 : indice;
    }

    public void agregarVertice(T data) {
        NodoVertice<T> nuevo = new NodoVertice<T>(data);

        if (indiceDe(data) != -1) {
            System.out.println("El vértice ya existe en el grafo.");
            return;
        }

        nuevo.siguiente = listaVertices;
        listaVertices = nuevo;
        numVertices++;
    }

    public NodoVertice<T> obtenerVertice(int indice) {
        NodoVertice<T> actual = listaVertices;
        int contador = 0;

        while (actual != null && contador < indice) {
            actual = actual.siguiente;
            contador++;
        }

        return actual;
    }

    public void imprimirGrafo() {
        for (int i = 0; i < numVertices; i++) {
            Nodo actualNodo = listaAdyacencia[i].cabeza;
            while (actualNodo != null) {
                System.out.println("Vértice " + actualNodo.data + " <——> Vértice "
                    + actualNodo.dato + ", por la arista " + actualNodo.peso + ".");
                actualNodo = actualNodo.next;
            }
        } System.out.println();
    }   

    public void graficarGrafo(Grafos<T> grafo) {
        // Crea un objeto GrafoCanvas y lo agrega a un JFrame
        GrafoCanvas canvas = new GrafoCanvas(grafo);
        JFrame frame = new JFrame();
        frame.add(canvas);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
    public class GrafoCanvas<T> extends Canvas {
        private Grafos<T> grafo;
        private boolean[] verticesDibujados; // arreglo de booleanos para llevar registro de los vértices dibujados


        public GrafoCanvas(Grafos<T> grafo) {
            this.grafo = grafo;
            this.verticesDibujados = new boolean[grafo.numVertices];
        }

        
        @Override
        public void paint(Graphics g) {

            // Dibuja los vértices
            int numVertices = grafo.numVertices;
            int radio = 30;
            int centroX = getWidth() / 2;
            int centroY = getHeight() / 2;
            int anguloInicial = 0;
            int anguloTotal = 360;
            int anguloEntreVertices = anguloTotal / numVertices;
            int radioCirculoPrincipal = Math.min(getWidth(), getHeight()) / 2 - 50;

            for (int i = 0; i < grafo.numVertices; i++) {
                
                Nodo actualNodo = grafo.listaAdyacencia[i].cabeza;
                
                // verifica si el vértice ya ha sido dibujado antes
                if (verticesDibujados[i]) {
                    continue;
                } else{
                    int x, y;
                    // calcula su posición y lo dibuja
                    x = centroX + (int) (radioCirculoPrincipal * Math.cos(Math.toRadians(anguloInicial)));
                    y = centroY + (int) (radioCirculoPrincipal * Math.sin(Math.toRadians(anguloInicial)));

                    g.setColor(Color.WHITE);
                    g.fillOval(x - radio, y - radio, 2 * radio, 2 * radio);
                    g.setColor(Color.BLACK);
                    g.drawOval(x - radio, y - radio, 2 * radio, 2 * radio);
                    g.drawString((String) actualNodo.data, x - radio / 2, y + radio / 2);

                    // marca el vértice como dibujado en el arreglo de booleanos
                    verticesDibujados[i] = true;

                    // guarda su posición en el atributo correspondiente
                    actualNodo.posicion  = new Point(x, y);

                    // dibuja las aristas
                    int x1 = x, y1 = y;
                    int posicion = grafo.buscarPosicion((T) actualNodo.dato);
                    while (actualNodo != null) {
                        
                        if (verticesDibujados[posicion]) {
                            actualNodo = actualNodo.next;
                            continue;
                        } else{
                            int x2, y2;
                        
//                            Nodo destino = grafo.listaAdyacencia[i].cabeza;
                            x2 = centroX + (int) (radioCirculoPrincipal * Math.cos(Math.toRadians(anguloInicial + anguloEntreVertices * Integer.parseInt((String) actualNodo.dato))));
                            y2 = centroY + (int) (radioCirculoPrincipal * Math.sin(Math.toRadians(anguloInicial + anguloEntreVertices * Integer.parseInt((String) actualNodo.dato))));

//
                            g.setColor(Color.WHITE);
                            g.fillOval(x2 - radio, y2 - radio, 2 * radio, 2 * radio);
                            g.setColor(Color.BLACK);
                            g.drawOval(x2 - radio, y2 - radio, 2 * radio, 2 * radio);
                            g.drawString(actualNodo.dato.toString(),  x2 - radio / 2, y2 + radio / 2);

                            // Dibuja la arista
                            g.setColor(Color.RED);
                            g.drawLine(x1, y1, x2, y2);
                            g.drawString(Integer.toString(actualNodo.peso), (x1 + x2) / 2, (y1 + y2) / 2);

    //                        // marca el vértice destino como dibujado en el arreglo de booleanos
                            
                            verticesDibujados[posicion] = true;
    //
    //                        // guarda su posición en el atributo correspondiente
//                            actualNodo.next.posicion = new Point(x2, y2);
                            actualNodo = actualNodo.next;
                        }
                    }

                    // incrementa el ángulo para el siguiente vértice
                    anguloInicial += anguloEntreVertices;
                }
            }
        }
    }
                    
    public int buscarPosicion(T dato) {
        for (int i = 0; i < numVertices; i++) {
            Nodo actualNodo = listaAdyacencia[i].cabeza;
            while (actualNodo != null) {
                if (actualNodo.data.equals(dato)) {
                    return i;
                }
                actualNodo = actualNodo.next;
            }
        }
        return -1; // si no se encuentra el dato, retorna -1.
    }
    
    public static int contarLineasHastaRelaciones(String archivo) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;
            int contador = 0;
            while ((linea = lector.readLine()) != null) {
                contador++;
                if (linea.trim().equalsIgnoreCase("Relaciones")) {
                    lector.close();
                    return contador - 1;
                }
            }
            lector.close();
            return contador;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public void leerArchivo(String archivo) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("test\\txt\\textox.txt"));
        
        int numVertices = contarLineasHastaRelaciones("test\\txt\\textox.txt");
        Grafos<T> grafo = new Grafos<T>(numVertices);
        int i = 0;
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            String[] partes = linea.split(", ");
            if (partes.length == 3) {
                T usuario1 = (T) partes[0];
                T usuario2 = (T) partes[1];
                int peso = Integer.parseInt(partes[2]);
                grafo.agregarArista(usuario1, usuario2, peso, i);
                i = i + 1;
            }
        }
        grafo.imprimirGrafo();
        graficarGrafo(grafo);
    }
}
