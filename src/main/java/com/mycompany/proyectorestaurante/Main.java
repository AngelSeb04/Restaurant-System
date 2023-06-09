package com.mycompany.proyectorestaurante;

import com.mycompany.proyectorestaurante.Formularios.FormLoggin;
import java.io.File;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        
        
        String directorioActual = System.getProperty("user.dir");
        File carpeta = new File(directorioActual + File.separator + "archivos");
        if(!carpeta.exists()){
            boolean carpetaCreada = carpeta.mkdir();
            if (carpetaCreada) {
                System.out.println("Carpeta creada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo crear la carpeta.");
            }
        }

        try{
            File archivo1 = new File("archivos/menu.txt");
            if(!archivo1.exists()){
                archivo1.createNewFile();
            }
            
            File archivo2 = new File("archivos/pedidosActuales.txt");
            if(!archivo2.exists()){
                archivo2.createNewFile();
            }
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        FormLoggin loggin = new FormLoggin();
        loggin.setVisible(true);
    }
}
