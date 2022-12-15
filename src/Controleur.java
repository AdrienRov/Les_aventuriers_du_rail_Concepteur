package src;

import src.ihm.Gui;
import src.metier.Arete;
import src.metier.CarteObjectif;
import src.metier.CartesVoitures;
import src.metier.Joueur;
import src.metier.Noeud;

import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Controleur 
{
    private Gui gui;
    private ArrayList<Joueur> joueurs;
    private ArrayList<CartesVoitures> cartesVoiture;
    private ArrayList<CarteObjectif> cartesObjectif;
    private ArrayList<Noeud> noeuds;
    private ArrayList<Arete> aretes;
    private int numPanel = 0;

    public Controleur()
    {
        //importer la carte
        this.gui = new Gui(this);
        this.afficherCarte("src/images/ajouter_carte.png");
        cartesObjectif = new ArrayList<CarteObjectif>();
        cartesVoiture  = new ArrayList<CartesVoitures>();
        noeuds         = new ArrayList<Noeud>();
        aretes         = new ArrayList<Arete>();

    }

    public void afficherCarte(String path)
    {
        this.gui.addCarte(path);
    }

    public void resizeGui(int width, int height)
    {
        this.gui.setSize(width+200, height);
    }

    public void ajouteCarteVoiture(CartesVoitures carte)
    {
        this.cartesVoiture.add(carte);
    }

    public void ajouteCarteObjectif(CarteObjectif carte)
    {
        this.cartesObjectif.add(carte);
    }

    public void ajouteNoeud(Noeud noeud)
    {
        this.noeuds.add(noeud);
    }

    public void ajouteArete(Arete arete)
    {
        this.aretes.add(arete);
    }

    public boolean getActiveNoeud()
    {
        return gui.getActiveNoeud();
    }

    public void setActiveNoeud(boolean active)
    {
        gui.activeAjouteNoeud(active);
    }

    public boolean getActiveTrajet()
    {
        return gui.getActiveTrajet();
    }
    public ArrayList<Arete> getAllTrajets()
    {
        return this.aretes;
    }
    public ArrayList<Noeud> getAllNoeuds()
    {
        return this.noeuds;
    }

    public void setActiveTrajet(boolean active)
    {
        gui.activeAjouteTrajet(active);
    }

    public void notification(String message)
    {
        gui.notification(message);
    }


    public ArrayList<Noeud> getListeNoeud()
    {
        return this.noeuds;
    }

    //supprimer un noeud
    public void supprimerNoeud(Noeud noeud)
    {
        this.noeuds.remove(noeud);
    }
    

    public int getEtatSelectionNoeud(){return this.gui.getEtatSelectionNoeud();}

    public void refreshFrame()
    {
        this.gui.refresh();
    }

    public void setEtatPanel(int etat)
    {
        this.gui.setEtatPanel(etat);
    }

    public void getParametre(boolean etat)
    {
        this.gui.getParametre(etat);
    }

    public static void main(String[] args) 
    {
        new Controleur();
    }
}

