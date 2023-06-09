package com.mycompany.proyectorestaurante;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pedido implements Serializable {

    private Map<String, Integer> platos;
    private Date fecha;
    private String resumenPedido;
    private String nombreCliente;
    private double precioTotal;

    public Pedido(Map<String, Integer> platos, Date fecha, String resumenPedido, String nombreCliente, double precioTotal) {
        this.platos = platos;
        this.fecha = fecha;
        this.resumenPedido = resumenPedido;
        this.nombreCliente = nombreCliente;
        this.precioTotal = precioTotal;
    }

    public Pedido() {
        platos = new LinkedHashMap();
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getResumenPedido() {
        return resumenPedido;
    }

    public void setResumenPedido(String resumenPedido) {
        this.resumenPedido = resumenPedido;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Map<String, Integer> getPlatos() {
        return platos;
    }

    public void setPlatos(Map platos) {
        this.platos = platos;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String textoPlatos() {
        
        StringBuilder sb = new StringBuilder();
        int maxLength = 0;
        
        for (String plato : platos.keySet()) {
            maxLength = Math.max(maxLength, plato.length());
        }

        
        for (Map.Entry<String, Integer> entry : platos.entrySet()) {
            String plato = entry.getKey();
            int cantidad = entry.getValue();
            String puntos = generateDots(maxLength - plato.length() + 5);
            sb.append(plato).append(puntos).append(cantidad).append("\n");
        }
        
        return sb.toString(); 
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
        for (Map.Entry<String, Integer> entry : platos.entrySet()) {
            String plato = entry.getKey();
            int precio = entry.getValue();
            String puntos = generateDots(maxLength - plato.length() + 5); // +5 para el espacio y los puntos
            sb.append(plato).append(puntos).append(precio).append("<br>");
            i++;
        }

        return "<html><pre>" + sb.toString() + "</pre></html>";
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
