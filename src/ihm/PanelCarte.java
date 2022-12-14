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
    private Image image;
    private Graphics2D g2d;
    private Graphics2D g2d2;
    
    private ArrayList<Noeud> allNoeud;
    private Controleur ctrl;
    private ArrayList<JButton> allBtnNoeud;

    private int cpt = 0;
    private Noeud noeudDepart;
    private Noeud noeudArrivee;

    public PanelCarte(Controleur ctrl)
    {
        //définir l'image de fond du panel 
        this.ctrl = ctrl;
        this.image = new ImageIcon("").getImage();
        this.setLayout  (null);
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

    public void ajouteNoeud(int x, int y)
    {
        String input = JOptionPane.showInputDialog("Nom du noeud");
        JButton btn = new JButton(input);
       
        btn.setBounds(x, y, 50, 50);
        btn.setSize(20,20);
        btn.setBackground(Color.RED);
       
        Noeud n = new Noeud(x, y,input, btn);
        
        this.ctrl.ajouteNoeud(n);
        this.add(btn);
        btn.addActionListener(this);
        this.repaint();
        
    }

    public void ajouterTrajet(Noeud noeudDepart, Noeud noeudArrivee)
    {
        System.out.println("ajouter trajet");
        System.out.println(noeudDepart.x() + " " + noeudDepart.y());
        System.out.println(noeudArrivee.x() + " " + noeudArrivee.y());
        // desinner un trai entre les deux noeuds
        g2d = (Graphics2D) this.getGraphics();
        // en noir et epais 
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(noeudDepart.x()+10, noeudDepart.y()+10, noeudArrivee.x()+10, noeudArrivee.y()+10);



    }



    public void resizeImage(int width, int height)
    {
        this.image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

    }
   

    @Override
    public void mouseClicked(MouseEvent e) {
        if (ctrl.getActiveNoeud())
        {
            System.out.println("click");
            
            this.ajouteNoeud(e.getX(), e.getY());
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
    public void actionPerformed(ActionEvent e) 
    {
        System.out.println("action");
        if(this.ctrl.getActiveTrajet())
        {
            System.out.println("active trajet");
            allNoeud =  this.ctrl.getListeNoeud();
            if(cpt == 0)
            {
                for(Noeud n : allNoeud)
                    if(n.btn() == e.getSource())
                        noeudDepart = n;
                cpt++;
                
            }
            else if(cpt == 1)
            {
                System.out.println("cpt = 1");
                for(Noeud n : allNoeud)
                    if(n.btn() == e.getSource())
                        noeudArrivee = n;
                this.ajouterTrajet(noeudDepart, noeudArrivee);
                this.ctrl.setActiveTrajet(false);
                cpt = 0;
                
            }
            
            
        }
       

        


        
    }
    
}
