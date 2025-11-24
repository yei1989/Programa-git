/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 *
 * @author PORTATIL
 */
public class ImagenUtil {
    
    public static byte[] convertirImagenABytes(ImageIcon icon) {

        try {
            Image img = icon.getImage();

            BufferedImage bImage = new BufferedImage(
                    img.getWidth(null),
                    img.getHeight(null),
                    BufferedImage.TYPE_INT_RGB
            );

            Graphics2D g = bImage.createGraphics();
            g.drawImage(img, 0, 0, null);
            g.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", baos);

            return baos.toByteArray();

        } catch (IOException e) {
            System.out.println("Error convirtiendo imagen: " + e.getMessage());
            return null;
        }
    }
    
}
