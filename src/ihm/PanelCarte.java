package src.ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.Controleur;
import src.metier.Arete;
import src.metier.Noeud;

public class PanelCarte extends JPanel implements MouseListener, ActionListener, MouseMotionListener
{
    //image de fond de la carte 
    private Image image;
    private Graphics2D g2d;
    private Graphics2D g2d2;
    
    private List<Noeud> allNoeud;
    private List<Arete> allTrajets;
    private Controleur ctrl;
    private List<JButton> allBtnNoeud;

    private int cpt  = 0;
    private int etat = 0;
    private Noeud noeudDepart;
    private Noeud noeudArrivee;
    private int xDifference;
    private int yDifference;
    private boolean isDragged = false;

    public PanelCarte(Controleur ctrl)
    {
        //définir l'image de fond du panel 
        this.ctrl = ctrl;
        this.allTrajets = new ArrayList<Arete>();
        this.allNoeud   = new ArrayList<Noeud>();
        this.image = new ImageIcon("").getImage();
        this.setLayout  (null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        //adapter la taille de l'image au panel
        this.setFocusable(true);
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
                g2d.drawString(noeud.getNom(), noeud.x()-5, noeud.y()-5);
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

        if(input == null)return;
        
        JButton btn = new JButton("");
       
        btn.setBounds(x, y, 50, 50);
        btn.setSize(20,20);
        btn.setBackground(Color.RED);
       
        Noeud n = new Noeud(x, y,input, btn);
        // dessiner le nom du noeud au dessus de 5px
        this.ctrl.ajouteNoeud(n);
        this.add(btn);
        btn.addActionListener(this);
        btn.addMouseListener(this);
        btn.addMouseMotionListener(this);
        this.repaint();
    }

    public void ajouterTrajet(Noeud noeudDepart, Noeud noeudArrivee)
    {
        System.out.println("ajouter trajet");
        System.out.println(noeudDepart.x() + " " + noeudDepart.y());
        System.out.println(noeudArrivee.x() + " " + noeudArrivee.y());

        Object[] choixValeur = {"1", "2", "3", "4", "5", "6"};
        Object[] choixCouleur = {"Vert", "Rouge", "Bleu", "Jaune", "Orange", "Noir", "Blanc", "Gris"};

        Color[ ] colors = {Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.BLACK, Color.WHITE, Color.GRAY};

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
        Arete arete = new Arete(noeudDepart, noeudArrivee, Integer.parseInt((String) listValeur.getSelectedItem()), colors[listCouleur.getSelectedIndex()]);
    
        this.ctrl.ajouteArete(arete);
        this.repaint();

    }
    public void refreshNoeuds()
    {
        this.allNoeud = this.ctrl.getAllNoeuds();
        //supprimer tout les boutons du panel
        
        this.removeAll();
        //ajouter les boutons
        for(Noeud noeud : this.allNoeud)
        {
            JButton btn = noeud.getButton();
            btn.setSize(20,20);
            btn.setBackground(Color.RED);
            btn.addActionListener(this);
            btn.addMouseListener(this);
            btn.addMouseMotionListener(this);
            this.add(btn);
        }
        this.repaint();
        this.revalidate();
        System.out.println("refresh Carte");
    }



    public void resizeImage(int width, int height)
    {
        this.image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

    }
    public void setEtatPanel(int etat)
    {
        this.etat = etat;
    }

    public int getEtatSelectionNoeud(){return this.cpt;}
   

    @Override
    public void mouseClicked(MouseEvent e) {
        if ( ctrl.getEtatPanel()==1)
        {
            this.ajouteNoeud(e.getX(), e.getY());
            this.ctrl.notification("Vous avez ajouté une ville");
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        if(this.ctrl.getEtatPanel() == 1 && e.getSource() instanceof JButton && isDragged == true)
        {
            for(int i = 0; i<this.ctrl.getAllNoeuds().size();i++)
            {
                if(this.ctrl.getAllNoeuds().get(i).getButton() == e.getSource())
                {
                    this.ctrl.getAllNoeuds().get(i).setX(e.getX()+xDifference);
                    this.ctrl.getAllNoeuds().get(i).setY(e.getY()+yDifference);
                    this.ctrl.getAllNoeuds().get(i).getButton().setLocation(e.getX()+xDifference, e.getY()+yDifference);                                                                                                                                                                                                                                                                                                                                                  
                    System.out.println("Noeud modifié x:" + e.getX()+xDifference + " y:" + e.getY()+yDifference);
                    this.ctrl.notification("Vous avez déplacé une ville");
                }
            }
            System.out.println("Sortie du bouton");
            this.ctrl.refreshFrame();
            this.refreshNoeuds();
            isDragged = false;
           
        }
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
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        if(this.ctrl.getEtatPanel() == 1 && e.getSource() instanceof JButton)
        {
            System.out.println("Modif btn");            
            isDragged = true;
            System.out.println("xNew: "+xDifference+" yNew: "+yDifference);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == this)
        {
            xDifference = e.getX();
            yDifference = e.getY();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        System.out.println("action");
        
        if(this.ctrl.getEtatPanel()==2)
        {
            System.out.println("active trajet");
            allNoeud =  this.ctrl.getAllNoeuds();
            if(cpt == 0)
            {
                for(Noeud n : allNoeud)
                    if(n.getButton()  == e.getSource())
                        noeudDepart = n;
                cpt++;
                this.ctrl.notification("Selectionner le noeud d'arrivée");
            }
            else if(cpt == 1 )
            {
                System.out.println("cpt = 1");
                for(Noeud n : allNoeud)
                    if(n.getButton() == e.getSource())
                    {
                        if(n != noeudDepart)
                        {
                            noeudArrivee = n;
                            this.ajouterTrajet(noeudDepart, noeudArrivee);
                            this.ctrl.setActiveTrajet(false);
                            this.ctrl.notification("Vous avez ajouté un trajet");
                        }
                        else 
                        {
                            this.ctrl.notification("Vous ne pouvez pas selectionner deux fois la même ville ");
                            return;
                        }

                    }
                cpt = 0;
            }
        }
        else 
        {
            //modifier le noeud quand on clique dessus pop up pour modifier le nom ou supprimer le noeud 
           
            System.out.println("modifier noeud");
            this.allNoeud =  this.ctrl.getAllNoeuds();
            for(int i = 0; i < allNoeud.size(); i++)
            {
                if(this.allNoeud.get(i).getButton() == e.getSource())
                {
                    System.out.println("noeud trouvé");
                    
                    //pop up  pour modifier le nom avec un zone de texte ou une case à coché pour supprimer le noeud
                    Object[] choix = {"Modifier le nom", "Supprimer le noeud"};
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridLayout(1,1,2,2));
                    
                    // textfield pour modifier le nom
                    JTextField text = new JTextField();
                    text.setText(this.allNoeud.get(i).getNom());
                    
                    // checkbox pour supprimer le noeud
                    JCheckBox check = new JCheckBox("Supprimer le noeud");
                    panel.add(text);
                    panel.add(check);
                    JOptionPane.showMessageDialog(null, panel, "Modifier le noeud", JOptionPane.PLAIN_MESSAGE);
                    String input = text.getText();

                    this.allNoeud.get(i).setNom(input);

                    // suppirmer le noeud si la case est coché et enlever le bouton du panel
                    if(check.isSelected())
                    {
                        this.remove(this.allNoeud.get(i).getButton());
                        this.ctrl.supprimerNoeud(this.allNoeud.get(i));
                    }
                    // recuperer la valeur de la liste déroulante
                    
                    this.repaint();
                }
            }
        }
    }

    
    
}
