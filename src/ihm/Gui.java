package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.Controleur;
import src.metier.Noeud;

public class Gui extends JFrame {
    private Controleur        ctrl;
    private PanelCarte        panelCarte;
    private PanelForm         panelForm;
    private PanelParametre    panelParametre;
    private PanelParamSuivant panelParamSuivant;
    private PanelCouleur      panelCouleur;
    private JPanel            panelNotification;
    private boolean           activeNoeud = false;
    private boolean           activeTrajet = false;
    private int               etat = 0;

    public Gui(Controleur ctrl) {
        this.ctrl = ctrl;
        this.setTitle("Les aventuriers du rail");
        // positionner la fenetre au centre de l'écran
        this.panelCarte        = new PanelCarte(this.ctrl);
        this.panelForm         = new PanelForm(this.ctrl);
        this.panelParametre    = new PanelParametre(this.ctrl);
        this.panelParamSuivant = new PanelParamSuivant(this.ctrl);
        this.panelNotification = new JPanel();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(this.panelForm, BorderLayout.WEST);
        this.add(this.panelCarte, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        // rendre la fenetre visible
        this.setVisible(true);
    }

    public void addCarte(String path) 
    {
        this.panelCarte.addCarte(path);
        this.setLocationRelativeTo(null);
    }

    public int getWidthPanelCarte() 
    {
        return this.panelCarte.getWidth();
    }

    public boolean getActiveNoeud()
    {
        return this.activeNoeud;
    }

    public void AjouteNoeud() 
    {
        this.panelCarte.ajouteNoeud(this.panelCarte.getWidth() / 2, this.panelCarte.getHeight() / 2);
    }

    public boolean getActiveTrajet() 
    {
        return this.activeTrajet;
    }

    public void refresh() 
    {
        this.refreshTabNoeud();
        this.repaint();
        this.revalidate();
    }

    public void refreshNomNoeud() 
    {
        this.panelForm.repaint();
    }

    public void getParametre(boolean active) 
    {
        if (active == true) {
            this.remove(this.panelCarte);
            this.remove(this.panelParamSuivant);
            this.panelParametre = new PanelParametre(this.ctrl);
            this.panelParametre.initParametre(this.panelCarte.getWidth(), this.panelCarte.getHeight());
            this.add(this.panelParametre, BorderLayout.CENTER);
        }
        if (active == false) {
            this.remove(this.panelParametre);
            this.remove(this.panelParamSuivant);
            this.add(this.panelCarte, BorderLayout.CENTER);
        }
        this.repaint();
        this.revalidate();
    }

    public void getParametreSuivant() {
        this.remove(this.panelParametre);
        this.add(this.panelParamSuivant, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }

    public void refreshTabNoeud() 
    {
        this.panelForm.refreshTabNoeud();
        this.panelForm.initPanel(1);
    }

    public void refreshTabCarteObjectif() 
    {
        this.panelForm.refreshTableObjectif();
        this.panelForm.initPanel(3);
    }

    public void resizeParametre(int width, int height) {
        this.panelParametre.setSize(width, height);
        this.revalidate();
        this.repaint();
    }

    public void refreshTabTrajet() 
    {
        this.panelForm.refreshTabTrajet();
        this.panelForm.initPanel(2);
    }

    public void getCouleur(boolean active)
    {
        if(active == true)
        {
            this.remove(this.panelCarte);
            this.panelCouleur = new PanelCouleur(this.ctrl);
            this.panelCouleur.initCouleur(this.panelCarte.getWidth(), this.panelCarte.getHeight());
            System.out.println("width : " + this.panelCarte.getWidth() + " height : " + this.panelCarte.getHeight());
            this.add(this.panelCouleur, BorderLayout.CENTER);
        }
        if(active == false)
        {
            this.remove(this.panelCouleur);
            this.add(this.panelCarte, BorderLayout.CENTER);
        }
        this.repaint();
        this.revalidate();
    }

    public int getEtatSelectionNoeud() 
    {
        return this.panelCarte.getEtatSelectionNoeud();
    }

    public void activeAjouteTrajet(boolean active) 
    {
        this.activeTrajet = active;
    }

    public void notification(String message) 
    {
        this.panelNotification.removeAll();
        this.panelNotification.repaint();
        JLabel label = new JLabel(message);
        this.panelNotification.add(label);
        this.panelNotification.setBackground(Color.GRAY);
        this.add(this.panelNotification, BorderLayout.NORTH);
        this.validate();
    }
}
