package src.ihm;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.Controleur;
import src.metier.Noeud;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;

import java.awt.*;

import java.awt.Color;


public class PanelCarte extends JPanel implements MouseListener
{
    //image de fond de la carte 
    private Image image ;
    private Graphics2D g2d;
    private Graphics2D g2d2;
 
    private Controleur ctrl;

    public PanelCarte(Controleur ctrl, ImageIcon img)
    {
        //définir l'image de fond du panel 
        this.ctrl = ctrl;
        this.setLayout  (null);
        this.image = img.getImage();
        this.setFocusable(true);
        this.addMouseListener(this);
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

    // focntion qui ajoute un noeud au dessus de la carte 
    public void ajouteNoeud(Graphics g,Noeud n)
    {

        super.paintComponent(g);
        g2d2 = (Graphics2D) g;
        g2d2.setStroke(new BasicStroke(3));
        g2d2.setColor(Color.RED);
        g2d2.fillRect(n.x(), n.y(), 10, 10);

        // écrit le nom au dessus du noeud
        g2d2.setColor(Color.BLACK);
        g2d2.drawString(n.nomNoeud(), n.x(), n.y());
        this.repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (ctrl.getActiveNoeud())
        {
            
            String input = JOptionPane.showInputDialog("Nom du noeud");
            Noeud n = new Noeud(e.getX(), e.getY(),input);
            this.ctrl.ajouteNoeud(n);
            this.ajouteNoeud(g2d, n);
            System.out.println("x: "+e.getX()+" y: "+e.getY());

            this.ctrl.setActiveNoeud();
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


   

    
}
