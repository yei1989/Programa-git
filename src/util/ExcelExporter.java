/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;


import java.io.FileWriter;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author PORTATIL
 */
public class ExcelExporter {
    
      public static boolean exportToExcel(DefaultTableModel model, String nombreArchivo) {
        try (FileWriter fw = new FileWriter(nombreArchivo)) {

            // Escribir encabezados
            for (int c = 0; c < model.getColumnCount(); c++) {
                fw.write(model.getColumnName(c));
                if (c < model.getColumnCount() - 1) fw.write(",");
            }
            fw.write("\n");

            // Escribir filas
            for (int r = 0; r < model.getRowCount(); r++) {
                for (int c = 0; c < model.getColumnCount(); c++) {
                    Object value = model.getValueAt(r, c);
                    fw.write(value == null ? "" : value.toString());
                    if (c < model.getColumnCount() - 1) fw.write(",");
                }
                fw.write("\n");
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error exportando archivo: " + e.getMessage());
            return false;
        }
    }
 
    
    
}
