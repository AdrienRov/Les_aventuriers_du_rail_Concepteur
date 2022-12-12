package src.ihm;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import src.Controleur;


public class Gui extends JFrame
{
    private Controleur ctrl;
    private PanelCarte panelCarte;
    private PanelForm panelForm;
    public Gui(Controleur ctrl,ImageIcon img)
    {
        this.ctrl = ctrl;
        this.setTitle("Les aventuriers du rail");
        
        this.panelCarte = new PanelCarte(this.ctrl, img);
        this.panelForm  = new PanelForm(this.ctrl);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(this.panelForm, BorderLayout.WEST);
        this.add(this.panelCarte, BorderLayout.CENTER);
        //resize la fenetre quand on agrandit la fenetre
        this.setVisible(true);
    }

    public void addCarte(String path)
    {
        this.panelCarte.addCarte(path);
    }
}
