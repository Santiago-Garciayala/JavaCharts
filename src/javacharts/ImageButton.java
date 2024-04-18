/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacharts;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 *
 * @author santi
 */
public class ImageButton extends JButton{
    int value;
    
    public ImageButton(String filename, int value){
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        this.setIcon(icon);
        this.value = value;
    }
}
