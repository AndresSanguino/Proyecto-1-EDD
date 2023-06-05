/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import EDD.Grafos;
import Interfaz.Ventana1;
import Interfaz.Ventana1;
import archivos.Archivos;

/**
 *
 * @author tomas
 */
public class Proyecto {
    
    int valor1;
    int valor2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Grafos grafo = new Grafos(4);
//        
//        grafo.agregarArista(0, 2, 121, 909, 8);
//        grafo.agregarArista(0, 1, 121, 254, 7);
//        grafo.agregarArista(1, 2, 254, 909, 5);
//        grafo.agregarArista(1, 3, 121, 893, 1);
//        grafo.agregarArista(2, 3, 909, 893, 5);
//        grafo.agregarArista(893, 129, 3);
//        grafo.agregarArista(129, 512, 10);
//        grafo.agregarArista(512, 412, 2);
//        grafo.agregarArista(893, 412, 4);
//        grafo.agregarArista(412, 788, 7);
//        grafo.agregarArista(788, 239, 7);
//        grafo.agregarArista(788, 443, 11);
//        grafo.agregarArista(443, 239, 6);
//        grafo.agregarArista(239, 907, 3);
//        grafo.agregarArista(443, 907, 9);
//  
//        grafo.imprimirGrafo();

        
//        Archivos usuarios = new Archivos();
//
//        String usuario1 = "Pepe_Gónzales";
//        String usuario2 = "StephaniaCominos";
//        String usuario3 = "obiwan123";
//        
//        String valor1 = usuarios.buscarValorParaUsuario("textox.txt", usuario1);
//        if (!valor1.isEmpty()) {
//            System.out.println("El valor para el usuario @" + usuario1 + " es: " + valor1);
//        } else {
//            System.out.println("No se encontró información para el usuario @" + usuario1);
//        }
//        
//        String valor2 = usuarios.buscarValorParaUsuario("textox.txt", usuario2);
//        if (!valor2.isEmpty()) {
//            System.out.println("El valor para el usuario @" + usuario2 + " es: " + valor2);
//        } else {
//            System.out.println("No se encontró información para el usuario @" + usuario2);
//        }
//        
//        String valor3 = usuarios.buscarValorParaUsuario("C:\\Users\\tomas\\Desktop\\vera1\\textox.txt", usuario3);
//        if (!valor3.isEmpty()) {
//            System.out.println("El valor para el usuario @" + usuario3 + " es: " + valor3);
//        } else {
//            System.out.println("No se encontró información para el usuario @" + usuario3);
//        }
//
//        Grafos grafo = new Grafos(3);
//
//        grafo.agregarArista(1, 2, 7);
//        grafo.agregarArista(2, 3, 8);
//        grafo.agregarArista(3, 1, 5);
//
//        grafo.imprimirGrafo();
//        grafo.graficarGrafo(grafo);
//



        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana1().setVisible(true);
            }
        });
    }
}
