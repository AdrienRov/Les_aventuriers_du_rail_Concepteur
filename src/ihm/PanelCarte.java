package src.ihm;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.Controleur;
import src.metier.Noeud;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

import java.awt.Color;


public class PanelCarte extends JPanel implements MouseListener, ActionListener
{
    //image de fond de la carte 
    private Image image  ;
    private Graphics2D g2d;
    private Graphics2D g2d2;
    private ArrayList<Noeud> allNoeud;
    private Controleur ctrl;
    private ArrayList<JButton> allBtnNoeud;

    public PanelCarte(Controleur ctrl)
    {
        //définir l'image de fond du panel 
        this.ctrl = ctrl;
        this.setLayout  (null);
        this.initNoeud();
        
        //adapter la taille de l'image au panel
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

    public void ajouteNoeud(Noeud n, String nom)
    {
        JButton btn = new JButton(nom);

        btn.setBounds(n.x(), n.y(), 50, 50);
        btn.setSize(20,20);
        btn.setBackground(Color.RED);
        this.add(btn);
        this.repaint();
        
    }

    public void initNoeud()
    {
        if(this.allNoeud != null)
        {
            for (Noeud noeud : allNoeud) 
            {
                this.ajouteNoeud(noeud, noeud.nomNoeud());
            }
        }
        
    }

    public void resizeImage(int width, int height)
    {
        this.image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        this.initNoeud();
    }
   

    @Override
    public void mouseClicked(MouseEvent e) {
        if (ctrl.getActiveNoeud())
        {
            
            String input = JOptionPane.showInputDialog("Nom du noeud");
            Noeud n = new Noeud(e.getX(), e.getY(),input);
            this.ctrl.ajouteNoeud(n);

            //ajoute un carré sur la carte
            this.ajouteNoeud(n, input);
            System.out.println("x: "+e.getX()+" y: "+e.getY());
            this.ctrl.setActiveNoeud(false);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        


        
    }


   

    
}
