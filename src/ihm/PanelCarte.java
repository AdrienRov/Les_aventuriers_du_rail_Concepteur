package src.ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Font;
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
import src.metier.CarteObjectif;
import src.metier.Noeud;

public class PanelCarte extends JPanel implements MouseListener, ActionListener, MouseMotionListener
{
    //image de fond de la carte 
    private Image image;
    private Graphics2D g2d;
    private Graphics2D g2d2;
    
    private List<Noeud> allNoeud;
    private List<Arete> allTrajets;
    private List<CarteObjectif> allObjectif;
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
        this.allObjectif = new ArrayList<CarteObjectif>();
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
        
        // set la police des noms des noeuds avec le getPolice du controleur en le décodant avec la fonction decode de Font pour remettre en Font le String
        
        this.allTrajets = this.ctrl.getAllTrajets();
        this.allNoeud = this.ctrl.getAllNoeuds();

        if (this.allTrajets != null) {
            for (Arete arete : this.allTrajets) 
            {

                int x1 = arete.getNoeudDepart().x();
                int y1 = arete.getNoeudDepart().y();
                int x2 = arete.getNoeudarrive().x();
                int y2 = arete.getNoeudarrive().y();

                // Calculer l'angle entre les deux noeuds du premier trajet
                double angle = Math.atan2(y2 - y1, x2 - x1);
                double angle2;
                int distanceAuBord;
                // Décaler l'angle de 90 degrés pour obtenir l'angle de décalage sur l'axe y
                if (arete.getSensUnique()) 
                {
                    angle2 = angle;
                    distanceAuBord = 0;
                } else {
                    angle2 = angle + Math.PI / 2;
                    distanceAuBord = 15;

                }

                // Décaler les coordonnées des noeuds du second trajet en utilisant l'angle
                // calculé précédemment
                int x1_2 = x1 + (int) (distanceAuBord * Math.cos(angle2));
                int y1_2 = y1 + (int) (distanceAuBord * Math.sin(angle2));
                int x2_2 = x2 + (int) (distanceAuBord * Math.cos(angle2));
                int y2_2 = y2 + (int) (distanceAuBord * Math.sin(angle2));

                // Dessiner le second trajet en utilisant les coordonnées décalées
                
                if(arete.getCouleur() == Color.BLACK)
                    g2d.setColor(Color.WHITE);
                else   
                    g2d.setColor(Color.BLACK);

                
                g2d.setStroke(new BasicStroke(15));
                g2d.drawLine(x1_2 +15, y1_2 + 15, x2_2 + 15, y2_2 + 15);
                
                // Calculer la distance entre les noeuds du second trajet
                int distance = (int) Math.sqrt(Math.pow(x1_2 - x2_2, 2) + Math.pow(y1_2 - y2_2, 2));
                int distanceEntreVoiture = distance / (arete.getNbVoiture());

                // Placer les voitures sur le second trajet
                for (int i = 1; i < arete.getNbVoiture() + 1; i++) 
                {
                    int xVoiture = (int) (x1_2 + ((i-1) * distanceEntreVoiture + 10) * Math.cos(angle));
                    int yVoiture = (int) (y1_2 + ((i-1) * distanceEntreVoiture + 10) * Math.sin(angle));

                    int xVoiture2 = (int) (x1_2 + (i * distanceEntreVoiture - 7 ) * Math.cos(angle));
                    int yVoiture2 = (int) (y1_2 + (i * distanceEntreVoiture - 7 ) * Math.sin(angle));
                    g2d.setColor(arete.getCouleur());
                    g2d.setStroke(new BasicStroke(10));
                    g2d.drawLine(xVoiture + 15, yVoiture + 15, xVoiture2 +15, yVoiture2 + 15);
                    
                }
            }

            
             // dessiner les nom des noeuds
            for (Noeud noeud : this.allNoeud) {
                
                g2d.setStroke(new BasicStroke(4));
                // compter le nombre de lettre dans le nom du noeud
                int nbLettre = noeud.getNom().length();
                // dessiner un carre blanc pour surligner le nom du noeud
                g2d.setColor(Color.WHITE);
                g2d.fillRect(noeud.x() - 35, noeud.y() - 45, nbLettre * 13, 20);

                g2d.setColor(Color.BLACK);

                Font font = null;
                font = Font.decode(this.ctrl.getPolice());
                System.out.println("DECODE FONT ------- " + font);
                g2d.setFont(font);

                g2d.drawString(noeud.getNom(), noeud.x() - 30, noeud.y() - 30);

                //dessiner les noeuds
                g2d.setColor(Color.ORANGE);
                g2d.fillOval(noeud.x(), noeud.y(), 35, 35);

                //dessiner un cercke noir autour du noeud
                g2d.setColor(Color.BLACK);
                g2d.drawOval(noeud.x(), noeud.y(), 35, 35);
            }
        }
    }
    public void addCarte(String path) 
    {
        this.image = new ImageIcon(path).getImage();
        this.setSize(this.image.getWidth(null), this.image.getHeight(null));
        
        this.ctrl.resizeGui(this.image.getWidth(null), this.image.getHeight(null));
        this.repaint();
        this.ctrl.setWidthPanelCarte(this.image.getWidth(null));
        this.ctrl.resizeParametre(this.getWidth(), this.getHeight());
    }

    public void ajouteNoeud(int x, int y)
    {
        String input = JOptionPane.showInputDialog("Nom du noeud");

        if(input == null)return;
 
        Noeud n = new Noeud(x, y,input);
        // dessiner le nom du noeud au dessus de 5px
        this.ctrl.ajouteNoeud(n);
        //this.add(btn);
        
        this.repaint();
    }

    public void ajouterTrajet(Noeud noeudDepart, Noeud noeudArrivee)
    {
        System.out.println("ajouter trajet");
        System.out.println(noeudDepart.x() + " " + noeudDepart.y());
        System.out.println(noeudArrivee.x() + " " + noeudArrivee.y());

        // Verifier si le trajet existe deja dans la liste des trajets
        for(Arete arete : this.allTrajets)
        {
            if(arete.getNoeudDepart().equals(noeudDepart) && arete.getNoeudarrive().equals(noeudArrivee))
            {
                JOptionPane.showMessageDialog(null, "Le trajet est deja existant");
                return;
            }
        }

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
        Arete arete = new Arete(noeudDepart, noeudArrivee, Integer.parseInt((String) listValeur.getSelectedItem()), colors[listCouleur.getSelectedIndex()], this.ctrl.verifAreteInvers(noeudDepart, noeudArrivee));
    
        this.ctrl.ajouteArete(arete);
        this.repaint();

    }

    public void ajouterObjectif(Noeud noeudDepart, Noeud noeudArrivee)
    {

        String input = JOptionPane.showInputDialog("Nombre de point ");

        // recuperer le nombre de point
        CarteObjectif carteObjectif = new CarteObjectif(noeudDepart, noeudArrivee, Integer.parseInt(input));
        this.ctrl.ajouteCarteObjectif(carteObjectif);

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
   
    private Noeud selectedNoeud;

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        this.etat = this.ctrl.getEtatPanel();
        System.out.println("ETAT : " + this.etat);
        if(this.etat == 1)
        {
            System.out.println("modifier noeud");
            this.allNoeud =  this.ctrl.getAllNoeuds();
            for(int i = 0; i < this.allNoeud.size(); i++)
            {
                int lx = this.allNoeud.get(i).x();
                int ly = this.allNoeud.get(i).y();
                if (lx <= e.getX() && e.getX() <= lx + 35 && ly <= e.getY() && e.getY() <= ly + 35)
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
                        this.ctrl.supprimerNoeud(this.allNoeud.get(i));
                    }
                    // recuperer la valeur de la liste déroulante
                    
                    this.repaint();
                    this.ctrl.refreshFrame();
                    return;
                }
            }
            System.out.println("ajouter noeud");
            this.ajouteNoeud(e.getX(), e.getY());
            this.ctrl.notification("Vous avez ajouté une ville");
            this.repaint();
        }

        if(this.etat == 2)
        {
            System.out.println("active trajet");
            allNoeud =  this.ctrl.getAllNoeuds();
            if(cpt == 0)
            {
                for(Noeud n : allNoeud)
                {
                    int lx = n.x();
                    int ly = n.y();
                    if (lx <= e.getX() && e.getX() <= lx + 35 && ly <= e.getY() && e.getY() <= ly + 35)
                    {
                        noeudDepart = n;
                    }
                }
                cpt++;
                this.ctrl.notification("Selectionner le noeud d'arrivée");
            }
            else if(cpt == 1 )
            {
                System.out.println("cpt = 1");
                for(Noeud n : allNoeud)
                {
                    int lx = n.x();
                    int ly = n.y();
                    if (lx <= e.getX() && e.getX() <= lx + 35 && ly <= e.getY() && e.getY() <= ly + 35)
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
                }
                cpt = 0;
            }
        }

            if(this.etat == 3)
            {
                System.out.println("active Objectif");
                allNoeud =  this.ctrl.getAllNoeuds();
                if(cpt == 0)
                {
                    for(Noeud n : allNoeud)
                    {
                        int lx = n.x();
                        int ly = n.y();
                        if (lx <= e.getX() && e.getX() <= lx + 35 && ly <= e.getY() && e.getY() <= ly + 35)
                        {
                            noeudDepart = n;
                        }
                    }
                    cpt++;
                    this.ctrl.notification("Selectionner le noeud d'arrivée");
                }
                else if(cpt == 1 )
                {
                    System.out.println("cpt = 1");
                    for(Noeud n : allNoeud)
                    {
                        int lx = n.x();
                        int ly = n.y();
                        if (lx <= e.getX() && e.getX() <= lx + 35 && ly <= e.getY() && e.getY() <= ly + 35)
                        {
                            if(n != noeudDepart)
                            {
                                noeudArrivee = n;
                                this.ajouterObjectif(noeudDepart, noeudArrivee);
                                this.ctrl.notification("Vous avez ajouté un objectif");
                        }
                        else 
                        {
                            this.ctrl.notification("Vous ne pouvez pas selectionner deux fois la même ville ");
                            return;
                        }
                    }
                }
                cpt = 0;
                }
            }
        }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        for(Noeud noeud : this.allNoeud)
        {
            int lx = noeud.x();
            int ly = noeud.y();
            if (lx <= e.getX() && e.getX() <= lx + 35 && ly <= e.getY() && e.getY() <= ly + 35)
            {
                this.selectedNoeud = noeud;
                System.out.println("selected noeud");
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        selectedNoeud = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        // TODO Auto-generated method stub
        if (this.selectedNoeud != null)
        {
            this.selectedNoeud.setX(e.getX() );
            this.selectedNoeud.setY(e.getY());
            this.ctrl.refreshFrame();
            this.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
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
    }
}