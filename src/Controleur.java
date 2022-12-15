package src;

import src.ihm.Gui;
import src.metier.Arete;
import src.metier.CarteObjectif;
import src.metier.CartesVoitures;
import src.metier.Joueur;
import src.metier.Noeud;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
    private int nbJoueur;
    private int nbWagons;
    private int nbWagonsFin;
    private int nbPoint1;

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
        System.out.println("Noeud supprimé");
    }
    

    public int getEtatSelectionNoeud(){return this.gui.getEtatSelectionNoeud();}

    public void refreshFrame()
    {
        this.gui.refresh();
    }
    //Modifier le numero du panel actuel
    public void setEtatPanel(int etat)
    {
        this.numPanel = etat;
    }
    //Obtenir le numero du panel actuel
    public int getEtatPanel()
    {
        return this.numPanel;
    }

    public void getParametre(boolean etat)
    {
        this.gui.getParametre(etat);
    }
    public void genererXml()
    {
        try

        {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("FichierSortie.xml"), "UTF-8"));

            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            pw.println("<jeu>");
            pw.println("\t<listeNoeuds>");
            for (Noeud n : noeuds) {
                pw.println("\t\t<noeud nom=\"" + n.getNom() + "\">");
                pw.println("\t\t\t<coordonees x=\"" + n.x() + "\" y=\"" + n.y() + "\"/>");
                pw.println("\t\t</noeud>");
            }
            pw.println("\t</listeNoeuds>");
            pw.println("\t<listeArete>");
            for (Arete arete : aretes) {
                pw.println("\t\t<arete>");
                pw.println("\t\t\t<noeudDepart nom=\"" + arete.getNoeudDepart().getNom() + "\"/>");
                pw.println("\t\t\t<noeudArrive nom=\"" + arete.getNoeudarrive().getNom() + "\"/>");
                pw.println("\t\t\t<coordonneesDepart x=\"" + arete.getN2x() + "\" y=\"" + arete.getN2y() + "\"/>");
                pw.println("\t\t\t<coordonneesArrive x=\"" + arete.getN1x() + "\" y=\"" + arete.getN1y() + "\"/>");
                pw.println("\t\t\t<nbWagon=\"" + arete.getNbVoiture() + "\"/>");
                pw.println("\t\t\t<couleur=\"" + arete.getCouleur() + "\"/>");
                pw.println("\t\t</arete>");
            }
            pw.println("\t</listeArete>");
            pw.println("</jeu>");

            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setNombreJoueur(int nombre)
    {
        this.nbJoueur = nombre;   
    }

    public void setNombreWagon(int nombre)
    {
        this.nbWagons = nombre;
    }

    public void setNombreWagonFin(int nombre)
    {
        this.nbWagonsFin = nombre;
    }

    public void setNombrePoint1(int nombre)
    {
        this.nbPoint1 = nombre;
    }

    public static void main(String[] args) 
    {
        new Controleur();
    }
}

