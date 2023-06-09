package com.mycompany.proyectorestaurante;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Reloj {
  
    private String formattedDate = "";
    
  public String getHora(){
       Timer timer = new Timer();
       
      TimerTask task = new TimerTask() {
         
        @Override
        public void run() {
            // Obtener la fecha y hora actual
            Date date = new Date();

            // Formatear la fecha y hora en el formato deseado
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formattedDate = sdf.format(date);

            // Imprimir la fecha y hora actual
            
            
        }
        
    };
      timer.scheduleAtFixedRate (task, 0, 1000);
      
      return formattedDate;
  }
  

    // Programar la tarea para que se ejecute cada segundo (1000 milisegundos)
   
}
