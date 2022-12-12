package src.ihm;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import src.Controleur;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class PanelCarte extends JPanel
{
    //image de fond de la carte
    
    private Image image ;
    private Graphics2D g2d;
    private Controleur ctrl;

    public PanelCarte(Controleur ctrl, ImageIcon img)
    {
        //définir l'image de fond du panel 
        this.ctrl = ctrl;
        this.setLayout  (null);
        this.image = img.getImage();
        this.setFocusable(true);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }

    public void addCarte(String path) 
    {
        this.image = new ImageIcon(path).getImage();
        this.setSize(this.image.getWidth(null), this.image.getHeight(null));
        this.ctrl.resizeGui(this.image.getWidth(null), this.image.getHeight(null));
        this.repaint();
    }

    
}
