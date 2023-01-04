package src.ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

import java.awt.event.*;

import src.Controleur;



public class PanelCouleur extends JPanel implements ActionListener
{
    private Controleur ctrl;
    private JPanel panelCentre;
    private JPanel panelSud;
    private JLabel labelTitre;
    private JButton btnSauvegarder;
    public JButton btnRouge;
    public JButton btnJaune;
    public JButton btnOrange;
    public JButton btnVert;
    public JButton btnBleu;
    public JButton btnViolet;
    public JButton btnNoir;
    public JButton btnGris;
    public JButton btnRose;
    public JButton btnMarron;
    public JButton btnCyan;
    public JButton btnVertFonce;
    public static Color couleurNoeud;


    public PanelCouleur(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(35,31,32));

        this.btnRouge = new JButton();
        this.btnJaune = new JButton();
        this.btnOrange = new JButton();
        this.btnVert = new JButton();
        this.btnBleu = new JButton();
        this.btnViolet = new JButton();
        this.btnNoir = new JButton();
        this.btnGris = new JButton();
        this.btnRose = new JButton();
        this.btnMarron = new JButton();
        this.btnCyan = new JButton();
        this.btnVertFonce = new JButton();
        this.btnSauvegarder = new JButton("Sauvegarder");

        this.btnRouge.setBackground(Color.RED);
        this.btnJaune.setBackground(Color.YELLOW);
        this.btnOrange.setBackground(Color.ORANGE);
        this.btnVert.setBackground(Color.GREEN);
        this.btnBleu.setBackground(Color.BLUE);
        this.btnViolet.setBackground(Color.MAGENTA);
        this.btnNoir.setBackground(Color.BLACK);
        this.btnGris.setBackground(Color.GRAY);
        this.btnRose.setBackground(Color.PINK);
        this.btnMarron.setBackground(new Color(139,69,19));
        this.btnCyan.setBackground(Color.CYAN);
        this.btnVertFonce.setBackground(new Color(0,100,0));

        this.panelCentre = new JPanel();
        this.panelSud = new JPanel();
        this.panelSud.setBackground(new Color(35,31,32));
        
        this.panelCentre.setLayout(new GridLayout(6,2));

        labelTitre = new JLabel("Couleur des noeuds");

        this.panelCentre.setBackground(new Color(35,31,32));
   
        this.labelTitre.setHorizontalAlignment(JLabel.CENTER);

        this.labelTitre.setForeground(Color.WHITE);
        this.labelTitre.setFont(new Font("Arial", Font.BOLD, 20));
        
        this.add(this.labelTitre, BorderLayout.NORTH);   

        this.panelCentre.add(this.btnRouge);
        this.panelCentre.add(this.btnJaune);
        this.panelCentre.add(this.btnOrange);
        this.panelCentre.add(this.btnVert);
        this.panelCentre.add(this.btnBleu);
        this.panelCentre.add(this.btnViolet);
        this.panelCentre.add(this.btnNoir);
        this.panelCentre.add(this.btnGris);
        this.panelCentre.add(this.btnRose);
        this.panelCentre.add(this.btnMarron);
        this.panelCentre.add(this.btnCyan);
        this.panelCentre.add(this.btnVertFonce);

        this.btnRouge.addActionListener(this);
        this.btnJaune.addActionListener(this);
        this.btnOrange.addActionListener(this);
        this.btnVert.addActionListener(this);
        this.btnBleu.addActionListener(this);
        this.btnViolet.addActionListener(this);
        this.btnNoir.addActionListener(this);
        this.btnGris.addActionListener(this);
        this.btnRose.addActionListener(this);
        this.btnMarron.addActionListener(this);
        this.btnCyan.addActionListener(this);
        this.btnVertFonce.addActionListener(this);
        this.btnSauvegarder.addActionListener(this);

        this.add(this.panelCentre, BorderLayout.CENTER);

        this.add(this.panelSud, BorderLayout.SOUTH);
        this.panelSud.add(this.btnSauvegarder);
        this.btnSauvegarder.setPreferredSize(new Dimension(200, 30));

    }

    public void  initCouleur(int largeur , int hauteur) 
    {
        this.setSize(largeur, hauteur);
        System.out.println("Largeur :  "+ largeur);
        System.out.println("Hauteur :  "+ hauteur);
        int x = 50;
        int ecart = largeur/2 + 50;
        int y = 0;
        int police = 12;
        if(largeur > 600 && hauteur > 600)
        {
            police = 15;
            x = largeur/5;
            ecart = largeur/2 + 20;
        }
    }

    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnSauvegarder)
        {
            this.ctrl.getCouleur(false);
        }
        if(e.getSource() == this.btnRouge)     {couleurNoeud = Color.RED;}
        if(e.getSource() == this.btnJaune)     {couleurNoeud = Color.YELLOW;}
        if(e.getSource() == this.btnOrange)    {couleurNoeud = Color.ORANGE;}
        if(e.getSource() == this.btnVert)      {couleurNoeud = Color.GREEN;}
        if(e.getSource() == this.btnBleu)      {couleurNoeud = Color.BLUE;}
        if(e.getSource() == this.btnViolet)    {couleurNoeud = Color.MAGENTA;}
        if(e.getSource() == this.btnNoir)      {couleurNoeud = Color.BLACK;}
        if(e.getSource() == this.btnGris)      {couleurNoeud = Color.GRAY;}
        if(e.getSource() == this.btnRose)      {couleurNoeud = Color.PINK;}
        if(e.getSource() == this.btnMarron)    {couleurNoeud = new Color(139,69,19);}
        if(e.getSource() == this.btnCyan)      {couleurNoeud = Color.CYAN;}
        if(e.getSource() == this.btnVertFonce) {couleurNoeud = new Color(0,100,0);}
    }
}