package src.ihm;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
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
    private boolean activeNoeud = false;
   
    public Gui(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setTitle("Les aventuriers du rail");
        //positionner la fenetre au centre de l'écran
        this.panelCarte = new PanelCarte(this.ctrl);
        this.panelForm  = new PanelForm(this.ctrl);
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

    
}
