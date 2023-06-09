package com.mycompany.proyectorestaurante;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Admin {
    private String usuario;
    private String contra;
    private Menu menu;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    
    
    
    public void agregarPlatoMenu(JTextField nombrePlato, JTextField precioPlato){
        try{
            File file = new File("archivos\\menu.txt");
            if(file.length() == 0){
                menu = new Menu();
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                menu = (Menu) ois.readObject();
            }
            else{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                menu = (Menu) ois.readObject();
            }
            menu.agregarPlato(nombrePlato.getText(), Double.parseDouble(precioPlato.getText()));
            
        } catch (IOException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
    }
    
    public void eliminarPlatoMenu(JTextField numeroPlato){
        try{
            File file = new File("archivos\\menu.txt");
            if(file.length() == 0){
                menu = new Menu();
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                menu = (Menu) ois.readObject();
            }
            else{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                menu = (Menu) ois.readObject();
            }
            menu.eliminarPlato(Integer.parseInt(numeroPlato.getText()));
            
        } catch (IOException | ClassNotFoundException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }
    
}
