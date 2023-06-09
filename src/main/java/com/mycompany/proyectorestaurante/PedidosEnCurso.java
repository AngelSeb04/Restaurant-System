package com.mycompany.proyectorestaurante;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PedidosEnCurso implements Serializable{
    private ArrayList<Pedido> pedidosActuales = new ArrayList<>();
    private static File archivo = new File("archivos\\pedidosActuales.txt");
    
    public PedidosEnCurso(){
        if(!archivo.exists()){
            try{
                archivo.createNewFile();
            } catch (IOException e){
                JOptionPane.showMessageDialog(null, "Error: " + e.toString());
            }
           
        }
        try {
            if (archivo.length() == 0) {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
                oos.writeObject(this);
                oos.close();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }
    
    
    
    public static PedidosEnCurso cargarPedidosEnCurso() {
        PedidosEnCurso pedidos = null;
        
        try {
            if (archivo.length() == 0) {
                pedidos = new PedidosEnCurso();
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
                oos.writeObject(pedidos);
                oos.close();
            } else {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
                pedidos = (PedidosEnCurso) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return pedidos;
    }
    
    public ArrayList<Pedido> getPedidosActuales() {
        return pedidosActuales;
    }

    public void setPedidosActuales(ArrayList<Pedido> pedidosActuales) {
        this.pedidosActuales = pedidosActuales;
    }
    
     public void guardarPedidosEnCurso() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }
    
    public void agregarPedido(Pedido pedido) throws FileNotFoundException, IOException{
        pedidosActuales.add(pedido);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
        oos.writeObject(this);
        oos.close();
    }
    
}
