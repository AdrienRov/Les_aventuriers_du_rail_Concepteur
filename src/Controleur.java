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
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;


public class Controleur 
{
    private Gui gui;
    private List<Joueur> joueurs;
    private List<CartesVoitures> cartesVoiture;
    private List<CarteObjectif> cartesObjectif;
    private List<Noeud> noeuds;
    private Iterator<Noeud> itNoeud ;
    private List<Arete> aretes;
    private String []cheminImage;
    private int widthPanelCarte;
    private int numPanel = 0;
    private int nbJoueur;
    private int nbWagons;
    private int nbWagonsFin;
    private int nbPoint1;
    private int nbPoint2;
    private int nbPoint3;
    private int nbPoint4;
    private int nbPoint5;
    private int nbPoint6;
    private int nbJoueurDoublesVoies;

    public Controleur()
    {
        //importer la carte
        this.gui = new Gui(this);
        this.afficherCarte("images/ajouter_carte.png");
        this.cartesObjectif = new ArrayList<CarteObjectif>();
        this.cartesVoiture  = new ArrayList<CartesVoitures>();
        this.noeuds         = new ArrayList<Noeud>();
        this.aretes         = new ArrayList<Arete>();
        this.cheminImage = new String[8];
    }

    public void afficherCarte(String path)
    {
        this.gui.addCarte(path);
    }

    public void resizeGui(int width, int height)
    {
        this.gui.setSize(width+200, height);
    }
    public void setWidthPanelCarte(int width)
    {
        this.widthPanelCarte = width;
    }
    
    public int getWidthPanelCarte()
    {
        return this.widthPanelCarte;
    }
    

    public void ajouteCarteVoiture(CartesVoitures carte)
    {
        this.cartesVoiture.add(carte);
    }

    public void ajouteCarteObjectif(CarteObjectif carte)
    {
        this.cartesObjectif.add(carte);
        this.gui.refreshTabCarteObjectif();
    }

    public void ajouteNoeud(Noeud noeud)
    {
        this.noeuds.add(noeud);
        this.gui.refreshTabNoeud();
    }

    public void ajouteArete(Arete arete)
    {
        this.aretes.add(arete);
        this.gui.refreshTabTrajet();
    }

    public boolean getActiveNoeud()
    {
        return gui.getActiveNoeud();
    }

    public void setActiveNoeud(boolean active)
    {
        gui.AjouteNoeud();
    }

    public boolean getActiveTrajet()
    {
        return gui.getActiveTrajet();
    }
    public List<Arete> getAllTrajets()
    {
        return this.aretes;
    }
    public List<Noeud> getAllNoeuds()
    {
        return this.noeuds;
    }

    public List<CarteObjectif> getAllCartesObjectif()
    {
        return this.cartesObjectif;
    }

    public void setActiveTrajet(boolean active)
    {
        gui.activeAjouteTrajet(active);
    }

    public void notification(String message)
    {
        gui.notification(message);
    }

    public void supprimerNoeud(Noeud noeud) 
    {
        if(noeud == null) {
            throw new IllegalArgumentException("Le noeud n'existe pas.");
        }

        int i = 0;
        ArrayList<Integer> alIndice = new ArrayList<Integer>();
        for (Arete ar : aretes) 
        {
            if (ar.getNoeudDepart() == noeud || ar.getNoeudarrive() == noeud)
            {
                alIndice.add(i);
            }
            i ++;
        }
        int ecart = 0;
        for (int cpt : alIndice) 
        {
            aretes.remove(cpt - ecart);
            ecart ++;
        }
        this.noeuds.remove(noeud);
    }

    //supprimer un noeud
    /* 
    public void supprimerNoeud(Noeud noeud)
    {
        System.out.println("Noeud supprimé");
        
        this.itNoeud        = this.noeuds.iterator();
        while(this.itNoeud.hasNext())
        {
            Noeud n = this.itNoeud.next();
            if(n.equals(noeud))
            {
                this.itNoeud.remove();
            }
        }
        
        this.gui.refreshTabNoeud();
    }
    */
    

    public int getEtatSelectionNoeud(){return this.gui.getEtatSelectionNoeud();}

    public void refreshFrame()
    {
        this.gui.refresh();
        System.out.println("Refresh Frame");
    }
    //Modifier le numero du panel actuel
    public void setEtatPanel(int etat)
    {
        this.numPanel = etat;
    }

    public void resizeParametre(int width, int height)
    {
        this.gui.resizeParametre(width, height);
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

    public void getParametreSuivant()
    {
        this.gui.getParametreSuivant();
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
            for (Arete arete : aretes) 
            {
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
            pw.println("\t<listeParametres>");
            pw.println("\t\t<parametre>");
            pw.println("\t\t\t<nbJoueur=\"" + this.nbJoueur + "\"/>");
            pw.println("\t\t\t<nbWagon=\"" + this.nbWagons + "\"/>");
            pw.println("\t\t\t<nbWagonFin=\"" + this.nbWagonsFin + "\"/>");
            pw.println("\t\t\t<nbPoint1=\"" + this.nbPoint1 + "\"/>");
            pw.println("\t\t\t<nbPoint2=\"" + this.nbPoint2 + "\"/>");
            pw.println("\t\t\t<nbPoint3=\"" + this.nbPoint3 + "\"/>");
            pw.println("\t\t\t<nbPoint4=\"" + this.nbPoint4 + "\"/>");
            pw.println("\t\t\t<nbPoint5=\"" + this.nbPoint5 + "\"/>");
            pw.println("\t\t\t<nbPoint6=\"" + this.nbPoint6 + "\"/>");
            pw.println("\t\t\t<nbJoueurDoublesVoies=\"" + this.nbJoueurDoublesVoies + "\"/>");
            pw.println("\t\t</parametre>");
            pw.println("\t</listeParametres>");

            pw.println("\t\t<image>");
            pw.println("\t<listeImage>");
            for(int i = 0; i < this.cheminImage.length; i++)
            {
                pw.println("\t\t\t<image" + i + "=\"" + this.cheminImage[i] + "\"/>");
            }
            pw.println("\t</listeImage>");
            pw.println("\t\t</image>");

            pw.println("\t\t<CartesObjectif>");
            pw.println("\t<listeCarteObjectif>");
            for (CarteObjectif co : cartesObjectif) {
                pw.println("\t\t<CarteObjectif>");
                pw.println("\t\t\t<noeudDepart nom=\"" + co.getNoeud1().getNom() + "\"/>");
                pw.println("\t\t\t<noeudArrive nom=\"" + co.getNoeud2().getNom() + "\"/>");
                pw.println("\t\t\t<point=\"" + co.getScore() + "\"/>");
                pw.println("\t\t</CarteObjectif>");
            }
            pw.println("\t\t</CartesObjectif>");
            pw.println("\t</listeCarteObjectif>");
            pw.println("</jeu>");
            pw.close();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public void setNbJoueur(int nombre)
    {
        this.nbJoueur = nombre;   
    }

    public void setNbWagonJoueur(int nombre)
    {
        this.nbWagons = nombre;
    }

    public void setNbWagonFinPartie(int nombre)
    {
        this.nbWagonsFin = nombre;
    }

    public void setNbPoint1Wagon(int nombre)
    {
        this.nbPoint1 = nombre;
    }

    public void setNbPoint2Wagon(int nombre)
    {
        this.nbPoint2 = nombre;
    }

    public void setNbPoint3Wagon(int nombre)
    {
        this.nbPoint3 = nombre;
    }

    public void setNbPoint4Wagon(int nombre)
    {
        this.nbPoint4 = nombre;
    }

    public void setNbPoint5Wagon(int nombre)
    {
        this.nbPoint5 = nombre;
    }

    public void setNbPoint6Wagon(int nombre)
    {
        this.nbPoint6 = nombre;
    }

    public void setNbJoueurDoublesVoies(int nombre)
    {
        this.nbJoueurDoublesVoies = nombre;
    }

    public void setCheminImage(int i, String chemin)
    {
        this.cheminImage[i] = chemin;
    }

    public boolean verifAreteInvers(Noeud depart, Noeud arrive) {
        for (Arete a : aretes) {
            if (a.getNoeudDepart() == arrive && a.getNoeudarrive() == depart) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) 
    {
        new Controleur();
    }
}

