package src.ihm;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.ObjectInputStream.GetField;
import java.awt.Toolkit;
import java.awt.Dimension;

import src.Controleur;


public class Gui extends JFrame 
{
    private Controleur ctrl;
    private PanelCarte panelCarte;
    private PanelForm panelForm;
    private PanelParametre panelParametre;
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
        this.panelNotification = new JPanel();
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                panelCarte.resizeImage(panelCarte.getWidth(), panelCarte.getHeight());
            }
        });
        
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

    public void activeAjouteNoeud(boolean active)
    {
        this.activeNoeud = active ;
    
    }

    public boolean getActiveTrajet()
    {
        return this.activeTrajet;
    }
    public void refresh()
    {
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
            this.add(this.panelCarte, BorderLayout.CENTER);
        }
        this.repaint();
        this.revalidate();
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
