package com.mycompany.proyectorestaurante;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class Menu implements Serializable {

    private Map<String, Double> platos;
    private static File archivo = new File("archivos\\menu.txt");

    public static Menu establecerMenu() {
        Menu menu = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
            menu = (Menu) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }

        return menu;
    }

    public Menu() throws IOException {
        platos = new LinkedHashMap<>();

        try {
            if (archivo.length() == 0) {
                agregarPlato("Chaufa", 15);
                agregarPlato("Seco de carne", 15);
                agregarPlato("Arroz con Pollo", 14);
                agregarPlato("Aji de Gallina", 15);
                agregarPlato("Carapulcra", 14);
                agregarPlato("Lomo saltado", 18);
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
                oos.writeObject(this);
                oos.close();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }

    }

    public void guardarMenu() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }

    public File getArchivo() {
        return archivo;
    }

    public Map<String, Double> getPlatos() {
        return platos;

    }

    public void setPlatos(Map<String, Double> platos) {
        this.platos = platos;
    }

    public void agregarPlato(String plato, double precio) throws FileNotFoundException, IOException {
        platos.put(plato, precio);
        guardarMenu();
    }

    public void eliminarPlato(int numeroPlato) {

        try {

            boolean aux = false;
            String plato = "";
            int i = 1;
            for (Map.Entry<String, Double> entry : platos.entrySet()) {
                if (i == numeroPlato) {
                    aux = true;
                    plato = entry.getKey();
                }
                i++;
            }

            if (aux == false) {
                JOptionPane.showMessageDialog(null, "Error: No se pudo encontrar el plato");
            } else {
                platos.remove(plato);
                guardarMenu();
                JOptionPane.showMessageDialog(null, "Plato eliminado exitosamente");

            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int maxLength = 0;

        // Determinar la longitud máxima del nombre del plato
        for (String plato : platos.keySet()) {
            maxLength = Math.max(maxLength, plato.length());
        }

        // Construir la salida formateada
        int i = 1;
        for (Map.Entry<String, Double> entry : platos.entrySet()) {
            String plato = entry.getKey();
            double precio = entry.getValue();
            String puntos = generateDots(maxLength - plato.length() + 5); // +5 para el espacio y los puntos
            sb.append(i).append(" ").append(plato).append(puntos).append(precio).append("<br>");
            i++;
        }

        return "<html><pre>             MENU   <br>" + sb.toString() + "</pre></html>";
    }

// Generar una cadena de puntos con la longitud especificada
    private String generateDots(int length) {
        StringBuilder dots = new StringBuilder();
        for (int i = 0; i < length; i++) {
            dots.append(".");
        }
        return dots.toString();
    }

}
