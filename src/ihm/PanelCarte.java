package src.ihm;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.Controleur;
import src.metier.Arete;
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
    private ArrayList<Arete> allTrajets;
    private Controleur ctrl;
    private ArrayList<JButton> allBtnNoeud;

    private int cpt = 0;
    private Noeud noeudDepart;
    private Noeud noeudArrivee;

    public PanelCarte(Controleur ctrl)
    {
        //définir l'image de fond du panel 
        this.ctrl = ctrl;
        this.allTrajets = new ArrayList<Arete>();
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
        this.allTrajets = this.ctrl.getAllTrajets();
        this.allNoeud = this.ctrl.getAllNoeuds();
        
        if(this.allTrajets != null)
        {
            for(Arete arete : this.allTrajets)
            {
                g2d.setColor(arete.getCouleur());
                g2d.setStroke(new BasicStroke(5));
                g2d.drawLine(arete.getNoeudDepart().x(), arete.getNoeudDepart().y(), arete.getNoeudarrive().x(), arete.getNoeudarrive().y());
            }
            // dessiner les nom des noeuds
            for(Noeud noeud : this.allNoeud)
            {
                g2d.setColor(Color.BLACK);
                g2d.drawString(noeud.nomNoeud(), noeud.x()-10, noeud.y()-20);
            }
        }
        
        
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
        JButton btn = new JButton("");
       
        btn.setBounds(x, y, 50, 50);
        btn.setSize(20,20);
        btn.setBackground(Color.RED);
       
        Noeud n = new Noeud(x+10, y+10,input, btn);
        // dessiner le nom du noeud au dessus de 5px
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

        Object[] choixValeur = {"1", "2", "3", "4", "5", "6"};
        Object[] choixCouleur = {Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.BLACK, Color.WHITE, Color.GRAY};

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1,2,2));
        
        JComboBox listValeur = new JComboBox(choixValeur);
        JComboBox listCouleur = new JComboBox(choixCouleur);

        JLabel labelValeur = new JLabel("Nombre de voiture :");
        JLabel labelCouleur = new JLabel("Couleur de la section :");
            
        listValeur.setSelectedIndex(0);
        listCouleur.setSelectedIndex(0);
        panel.add(labelValeur);
        panel.add(listValeur);
        panel.add(labelCouleur);
        panel.add(listCouleur);

        JOptionPane.showMessageDialog(null, panel, "Trajet", JOptionPane.PLAIN_MESSAGE);
        // recuperer la valeur de la liste déroulante
        Arete arete = new Arete(noeudDepart, noeudArrivee, Integer.parseInt((String) listValeur.getSelectedItem()),(Color) listCouleur.getSelectedItem());
    
        this.ctrl.ajouteArete(arete);
        this.repaint();

    }



    public void resizeImage(int width, int height)
    {
        this.image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

    }

    public int getEtatSelectionNoeud(){return this.cpt;}
   

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
                this.ctrl.notification("Selectionner le noeud d'arrivée");
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
