package com.mycompany.proyectorestaurante;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class Repositorio implements Serializable{
    private Map<String, ArrayList<Pedido>> repositorioPedidos;
    private static File archivo = new File("archivos\\repositorioPedidos.txt");
    
    public Repositorio(){
        repositorioPedidos = new LinkedHashMap<>();
    }

    public Map<String, ArrayList<Pedido>> getRepositorioPedidos() {
        return repositorioPedidos;
    }

    public void setRepositorioPedidos(Map<String, ArrayList<Pedido>> repositorioPedidos) {
        this.repositorioPedidos = repositorioPedidos;
    }

    public static File getArchivo() {
        return archivo;
    }

    public static void setArchivo(File archivo) {
        Repositorio.archivo = archivo;
    }

    
    
    public static Repositorio cargarRepositorio() {
        Repositorio repositorio = null;
        
        try {
            if (archivo.length() == 0) {
                repositorio = new Repositorio();
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
                oos.writeObject(repositorio);
                oos.close();
            } else {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
                repositorio = (Repositorio) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return repositorio;
    }
    
    public void guardarRepositorio(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            oos.writeObject(this);
            oos.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }
    

    public void registrarPedido(Pedido pedido, Date dia){
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formato.format(dia);
        
        if (repositorioPedidos.containsKey(fechaFormateada)) {
            // Si existe, agregamos el pedido a la lista existente
            repositorioPedidos.get(fechaFormateada).add(pedido);
        } else {
            // Si no existe, creamos una nueva lista y agregamos el pedido
            ArrayList<Pedido> pedidos = new ArrayList<>();
            pedidos.add(pedido);
            repositorioPedidos.put(fechaFormateada, pedidos);
        }
        guardarRepositorio();
    }
    
    public String imprimirPedidosPorDia(){
        String texto = "";
        for(Map.Entry<String, ArrayList<Pedido>> entry : repositorioPedidos.entrySet()){
            String dia = entry.getKey();
            ArrayList pedidos = entry.getValue();
            
            texto = "Fecha: " + dia + "\n" +
                    "Numero de pedidos registrados: " + pedidos.size() + "\n" +
                    "----------------------------------------\n";
            
            for (int i = 0; i < pedidos.size(); i++) {
                Pedido pedido = (Pedido) pedidos.get(i);
                
                texto += "Hora del pedido: " + pedido.getFecha() + "\n" +
                        "CLIENTE: " + pedido.getNombreCliente() + "\n" +
                        pedido.textoPlatos() + "\n" +
                        "-----------------------------------------\n";
             
            }
           
        }
        
        return texto;
    }
    
    
}
