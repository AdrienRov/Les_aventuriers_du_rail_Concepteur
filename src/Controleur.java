package src;

import src.ihm.Gui;
import src.metier.Arete;
import src.metier.CarteObjectif;
import src.metier.CartesVoitures;
import src.metier.Joueur;
import src.metier.Noeud;
import java.awt.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;


public class Controleur 
{
    private Gui gui;
    private List<Joueur>            joueurs;
    private List<CartesVoitures>    cartesVoiture;
    private List<CarteObjectif>     cartesObjectif;
    private List<Noeud>             noeuds;
    private Iterator<Noeud>         itNoeud ;
    private List<Arete>             aretes;
    private List<String>            listeImages;
    private String[]                cheminImage;
    private String[]                nomImage;
    private int                  couleurNoeud;
    private int     widthPanelCarte;
    private int     numPanel = 0;
    private int     nbJoueurMin;
    private int     nbJoueurMax;
    private int     pion;
    private int     nbWagons;
    private int     nbWagonsFin;
    private int     nbPoint1;
    private int     nbPoint2;
    private int     nbPoint3;
    private int     nbPoint4;
    private int     nbPoint5;
    private int     nbPoint6;
    private int     nbCarteJoeur;
    private int     nbJoueurDoublesVoies;
    private int     nbWagonCouleur;
    private int     nbJoker;
    private String  nomPolice;

    public Controleur()
    {
        //importer la carte
        this.gui = new Gui(this);
        this.afficherCarte("images/ajouter_carte.png");
        this.cartesObjectif = new ArrayList<CarteObjectif>();
        this.cartesVoiture  = new ArrayList<CartesVoitures>();
        this.noeuds         = new ArrayList<Noeud>();
        this.aretes         = new ArrayList<Arete>();
        this.listeImages    = new ArrayList<String>();
        this.cheminImage    = new String[11];
        this.nomImage       = new String[11];
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
        if(noeud == null) 
        {
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
            pw.println("<root>");
            pw.println("\t<listeNoeuds>");
            for (Noeud n : noeuds) 
            {
                pw.println("\t\t<noeud nom=\"" + n.getNom() + "\">");
                pw.println("\t\t\t<coordonnees x=\"" + n.x() + "\" y=\"" + n.y() + "\"/>");
                pw.println("\t\t\t<coordonneesTexte x=\"" + n.xText() + "\" y=\"" + n.yText() + "\"/>");
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
                pw.println("\t\t\t<nbWagon nb=\"" + arete.getNbVoiture() + "\"/>");
                pw.println("\t\t\t<couleur couleur=\"" + arete.getCouleur().getRGB() + "\"/>");
                pw.println("\t\t\t<sens sens=\"" + arete.getSensUnique() + "\"/>");
                pw.println("\t\t</arete>");
            }
            pw.println("\t</listeArete>");
            pw.println("\t<listeParametres>");
            pw.println("\t\t<parametre>");
            pw.println("\t\t\t<nbJoueurMin nb=\"" + this.nbJoueurMin + "\"/>");
            pw.println("\t\t\t<nbJoueurMax nb=\"" + this.nbJoueurMax + "\"/>");
            pw.println("\t\t\t<nbPion nb=\"" + this.pion + "\"/>");
            pw.println("\t\t\t<nbWagonFin nb=\"" + this.nbWagonsFin + "\"/>");
            pw.println("\t\t\t<nbPoint1 nb=\"" + this.nbPoint1 + "\"/>");
            pw.println("\t\t\t<nbPoint2 nb=\"" + this.nbPoint2 + "\"/>");
            pw.println("\t\t\t<nbPoint3 nb=\"" + this.nbPoint3 + "\"/>");
            pw.println("\t\t\t<nbPoint4 nb=\"" + this.nbPoint4 + "\"/>");
            pw.println("\t\t\t<nbPoint5 nb=\"" + this.nbPoint5 + "\"/>");
            pw.println("\t\t\t<nbPoint6 nb=\"" + this.nbPoint6 + "\"/>");
            pw.println("\t\t\t<nbCarteJoueur nb=\"" + this.nbCarteJoeur + "\"/>");
            pw.println("\t\t\t<nbJoueurDoublesVoies nb=\"" + this.nbJoueurDoublesVoies + "\"/>");
            pw.println("\t\t\t<nbWagonCouleur nb=\"" + this.nbWagonCouleur + "\"/>");
            pw.println("\t\t\t<nbJoker nb=\"" + this.nbJoker + "\"/>");
            pw.println("\t\t</parametre>");
            pw.println("\t</listeParametres>");
            pw.println("\t<couleurNoeud>");
            pw.println("\t\t\t<couleur couleur=\"" + this.couleurNoeud + "\"/>");
            pw.println("\t</couleurNoeud>");
            pw.println("\t<listeImage>");
            for(int i = 0; i < this.cheminImage.length; i++)
            {
                pw.println("\t\t<image idImage=\"" + this.cheminImage[i] + "\" nom=\""+ this.nomImage[i] +"\" />");
            }
            pw.println("\t</listeImage>");
            pw.println("\t<listeCarteObjectif>");
            for (CarteObjectif co : cartesObjectif) 
            {
                pw.println("\t\t<CarteObjectif>");
                pw.println("\t\t\t<noeudDepart nom=\"" + co.getNoeud1().getNom() + "\"/>");
                pw.println("\t\t\t<noeudArrive nom=\"" + co.getNoeud2().getNom() + "\"/>");
                pw.println("\t\t\t<point pts=\"" + co.getScore() + "\"/>");
                pw.println("\t\t</CarteObjectif>");
            }
            pw.println("\t</listeCarteObjectif>");
            pw.println("</root>");
            pw.close();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public void setCouleur(Color couleur)
    {
        this.couleurNoeud = couleur.getRGB();
    }

    public void setNbJoueurMin(int nombre)
    {
        this.nbJoueurMin = nombre;   
    }

    public void setNbJoueurMax(int nombre)
    {
        this.nbJoueurMax = nombre;   
    }

    public void setNbPion(int nombre)
    {
        this.pion = nombre;
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

    public void setNbCarteJoueur(int nombre)
    {
        this.nbCarteJoeur = nombre;
    }

    public void setNbJoueurDoublesVoies(int nombre)
    {
        this.nbJoueurDoublesVoies = nombre;
    }

    public void setNbWagonCouleur(int nombre)
    {
        this.nbWagonCouleur = nombre;
    }

    public void setNbJoker(int nombre)
    {
        this.nbJoker = nombre;
    }

    public void setCheminImage(int i, String chemin, String nomImage)
    {
        this.cheminImage[i] = chemin;
        this.nomImage[i] = nomImage;
    }

    public boolean verifDouble(Noeud depart, Noeud arrive) 
    {
        for (Arete a : aretes) {
            if (a.getNoeudDepart() == arrive && a.getNoeudarrive() == depart || a.getNoeudDepart() == depart && a.getNoeudarrive() == arrive) 
            {
                return false;
            }
        }
        return true;
    }

    public void setPolice(String police) 
    {
        this.nomPolice = police;
        this.gui.refresh();

    }
    public String getPolice()
    {
        return this.nomPolice;
    }

    public void getCouleur(boolean etat)
    {
        this.gui.getCouleur(etat);
    }

    public String fileToString(File file) {
        try {
            FileInputStream r = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            r.read(bytes);
            r.close();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) 
    {
        new Controleur();
    }
}

