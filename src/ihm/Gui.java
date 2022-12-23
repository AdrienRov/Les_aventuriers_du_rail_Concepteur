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


public class Gui extends JFrame 
{
    private Controleur ctrl;
    private PanelCarte panelCarte;
    private PanelForm panelForm;
    private PanelParametre panelParametre;
    private PanelParamSuivant panelParamSuivant;
    private JPanel panelNotification;
    private boolean activeNoeud = false;
    private boolean activeTrajet = false;
    private int etat = 0;

   
    public Gui(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setTitle("Les aventuriers du rail");
        //positionner la fenetre au centre de l'écran
        this.panelCarte = new PanelCarte(this.ctrl);
        this.panelForm  = new PanelForm(this.ctrl);
        this.panelParametre = new PanelParametre(this.ctrl);
        this.panelParamSuivant = new PanelParamSuivant(this.ctrl);
        this.panelNotification = new JPanel();
        this.setResizable(false);        
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(this.panelForm, BorderLayout.WEST);
        this.add(this.panelCarte, BorderLayout.CENTER);
       

        this.pack();
        this.setLocationRelativeTo(null);
        //rendre la fenetre visible 
        this.setVisible(true);
    }
    
    public void addCarte(String path)
    {
        this.panelCarte.addCarte(path);
        this.setLocationRelativeTo(null);
    }

    public boolean getActiveNoeud()
    {
        return this.activeNoeud;
    }

    public void AjouteNoeud()
    {
        this.panelCarte.ajouteNoeud(this.panelCarte.getWidth()/2, this.panelCarte.getHeight()/2);
    }

    public boolean getActiveTrajet()
    {
        return this.activeTrajet;
    }
    public void refresh()
    {
        panelCarte.refreshNoeuds();
        this.repaint();
        this.revalidate();
    }
    public void getParametre(boolean active)
    {
        if(active == true)
        {
            this.remove(this.panelCarte);
            this.add(this.panelParametre, BorderLayout.CENTER);
        }
        if(active == false)
        {
            this.remove(this.panelParametre);
            this.remove(this.panelParamSuivant);
            this.add(this.panelCarte, BorderLayout.CENTER);
        }
        this.repaint();
        this.revalidate();
    }
    public void getParametreSuivant()
    {
        this.remove(this.panelParametre);
        this.add(this.panelParamSuivant, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }

    public void afficherPanelParametre()
    {
        this.remove(this.panelParamSuivant);
        this.add(this.panelParametre, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }
    public void refreshTabNoeud()
    {
        System.out.println("refresh tab noeud");
        this.panelForm.refreshTabNoeud();
        this.panelForm.initPanel(1);
    }
    public void refreshTabTrajet()
    {
        System.out.println("refresh tab trajet");
        this.panelForm.refreshTabTrajet();
        this.panelForm.initPanel(2);
    }
   
    
    public int getEtatSelectionNoeud(){return this.panelCarte.getEtatSelectionNoeud();}

    public void activeAjouteTrajet(boolean active)
    {
        this.activeTrajet = active ;
    
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
