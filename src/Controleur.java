package src;

import src.ihm.Gui;

import javax.swing.ImageIcon;


public class Controleur 
{
    private Gui gui;

    public Controleur()
    {
        //importer la carte
        ImageIcon img = new ImageIcon("./src/images/ajouter_carte.png");
        this.gui = new Gui(this, img);
        this.gui.setSize(img.getIconWidth()+200, img.getIconHeight());

    }

    public void afficherCarte(String path)
    {
        this.gui.addCarte(path);
    }

    public void resizeGui(int width, int height)
    {
        this.gui.setSize(width+200, height);
    }

    public static void main(String[] args) 
    {
        new Controleur();
    }
}
