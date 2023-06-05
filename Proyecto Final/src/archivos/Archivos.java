
package archivos;

import EDD.Grafos;
import EDD.Usuario;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
        
public class Archivos {
    
    
    public String leerArchivo(String direccion) {
        StringBuilder texto = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(direccion))) {
                String linea;
                while ((linea = bf.readLine()) != null) {
                    texto.append(linea);
                }
        } catch (IOException e) {
            System.err.println("No se encontró archivo");
        }
        return texto.toString();
    }
        
    public String buscarDato(String direccion, String buscado) {
        String resultado = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(direccion));
            String linea = reader.readLine();
            while (linea != null) {
                if (linea.contains(buscado)) {
                    resultado += linea + "\n";
                }
                linea = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + direccion);
            e.printStackTrace();
        }
        return resultado;
    }

    public static String buscarValorParaUsuario(String direccion, String usuario) {
        
        String valor = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(direccion));
            String linea = reader.readLine();
            while (linea != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2 && partes[1].trim().equals("@" + usuario)) {
                    try {
                    valor = Integer.parseInt(partes[0].trim()) + "";
                    } catch (NumberFormatException e) {
                        System.out.println("El valor para el usuario @" + usuario + " no es un número válido.");
                    }
                    break;
                }
                linea = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return valor;
    }

    public static boolean agregarDato(String direccion, String dato) {
        boolean resultado = false;
        try {
            FileWriter writer = new FileWriter(direccion, true);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(dato);
            buffer.newLine();
            buffer.close();
            writer.close();
            resultado = true;
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al añadir el dato al archivo: " + e.getMessage());
        }
        return resultado;
    }
    
    public static void agregarUsuario(String id, String user) {
        String rutaArchivo = "test\\txt\\textox.txt";

        try {
            FileReader fr = new FileReader(rutaArchivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            int posicionUltimoUsuario = -1;
            int indice = 0;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("907, @Presentesiempre")) {
                    posicionUltimoUsuario = indice + 1;
                }
                indice++;
            }

            if (posicionUltimoUsuario != -1) {
                fr = new FileReader(rutaArchivo);
                br = new BufferedReader(fr);
                BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo + ".temp"));
                indice = 0;
                while ((linea = br.readLine()) != null) {
                    bw.write(linea);
                    bw.newLine();
                    if (indice == posicionUltimoUsuario - 1) {
                        bw.write(id + ", " + user);
                        bw.newLine();
                    }
                    indice++;
                }
                bw.close();
                br.close();
                fr.close();

                fr = new FileReader(rutaArchivo + ".temp");
                br = new BufferedReader(fr);
                BufferedWriter bw2 = new BufferedWriter(new FileWriter(rutaArchivo));
                while ((linea = br.readLine()) != null) {
                    bw2.write(linea);
                    bw2.newLine();
                }
                bw2.close();
                br.close();
                fr.close();

                new File(rutaArchivo + ".temp").delete();
            } else {
                System.err.println("Error al agregar el nuevo usuario: no se encontró la posición adecuada.");
            }

        } catch (IOException e) {
            System.err.println("Error al leer o escribir en el archivo: " + e.getMessage());
        }
    }

    public static boolean existeUsuario(String usuario, String archivo) {
        try {
            File archivoTxt = new File(archivo);
            Scanner scanner = new Scanner(archivoTxt);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                if (linea.contains(usuario)) {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static int generarIDUnico() {
        Random rand = new Random();
        int id = rand.nextInt(999) + 1;
        boolean idRepetido = false;
        String nombreArchivo = "test\\txt\\textox.txt";
        File file = new File(nombreArchivo);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                if (linea.contains(String.valueOf(id))) {
                    idRepetido = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al leer el archivo: " + e.getMessage());
            return -1;
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        while (idRepetido) {
            id = rand.nextInt(999) + 1;
            scanner = null;
            try {
                scanner = new Scanner(file);
                idRepetido = false;
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    if (linea.contains(String.valueOf(id))) {
                        idRepetido =true;
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Ha ocurrido un error al leer el archivo: " + e.getMessage());
                return -1;
            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }
        }
        return id;
    }  
    
    public static boolean verificar(String id, String usuario, String archivo) {
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(", ");
                if (partes.length == 2 && partes[0].equals(id) && partes[1].equals(usuario)) {
                    encontrado = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encontrado;
    }
    
    public static void agregarRelacion(String relacionesFilePath, int usuario1, int usuario2, int peso) {
        try {
            // Leemos el archivo de relaciones y almacenamos su contenido en una variable
            File relacionesFile = new File(relacionesFilePath);
            BufferedReader reader = new BufferedReader(new FileReader(relacionesFile));
            String line;
            StringBuilder relacionesContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                relacionesContent.append(line).append("\n");
            }
            reader.close();

            // Agregamos la nueva relación al contenido
            String nuevaRelacion = usuario1 + ", " + usuario2 + ", " + peso;
            relacionesContent.append(nuevaRelacion).append("\n");

            // Guardamos el nuevo contenido en el archivo de relaciones
            BufferedWriter writer = new BufferedWriter(new FileWriter(relacionesFile));
            writer.write(relacionesContent.toString());
            writer.close();

            System.out.println("La relación " + nuevaRelacion + " se ha agregado exitosamente al archivo " + relacionesFilePath);
        } catch (IOException e) {
            System.out.println("Error al agregar la relación " + usuario1 + ", " + usuario2 + ", " + peso + " al archivo " + relacionesFilePath + ": " + e.getMessage());
        }
    }
    
    public static String concatenarUsuarios(String archivoUsuarios) {
        StringBuilder usuariosConcatenados = new StringBuilder();

        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivoUsuarios));
            String linea;

            // Agregar un indicador para saber cuando empezar a agregar usuarios
            boolean agregarUsuarios = false;

            while ((linea = lector.readLine()) != null) {
                if (linea.equals("Usuarios")) {
                    agregarUsuarios = true;
                } else if (linea.equals("Relaciones")) {
                    agregarUsuarios = false;
                } else if (agregarUsuarios) {
                    // Solo agregar líneas que contienen nombres de usuario
                    if (linea.contains(",")) {
                        String[] partes = linea.split(",");
                        String id = partes[0].trim();
                        String usuario = partes[1].trim();
                        usuariosConcatenados.append(id).append(", ").append(usuario).append("\n");
                    }
                }
            }

            lector.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios: " + e.getMessage());
            return "";
        }

        return usuariosConcatenados.toString();
    }
   
}