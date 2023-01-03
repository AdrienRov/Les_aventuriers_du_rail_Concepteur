package src.metier;

import java.awt.Color;

public class Arete {
    // Attributs
    private int n1y;
    private int n1x;
    private int n2y;
    private int n2x;

    private Noeud noeudDepart;
    private Noeud noeudarrive;

    private Boolean sensUnique;

    private Color couleur;

    private int nbVoiture;

    public Arete(Noeud noeudDepart, Noeud noeudarrive, int nbVoiture, Color couleur, Boolean sensUnique) {
        this.noeudDepart = noeudDepart;
        this.noeudarrive = noeudarrive;

        this.nbVoiture = nbVoiture;
        this.n1x = noeudarrive.x();
        this.n1y = noeudarrive.y();
        this.n2x = noeudDepart.x();
        this.n2y = noeudDepart.y();
        this.sensUnique = sensUnique;
        this.couleur = couleur;

    }

    public int getN1x() {
        return n1x;
    }

    public int getN1y() {
        return n1y;
    }

    public int getN2x() {
        return n2x;
    }

    public int getN2y() {
        return n2y;
    }

    public int getNbVoiture() {
        return nbVoiture;
    }

    public Boolean getSensUnique() {
        return sensUnique;
    }

    public void setNbVoiture(int nbVoiture) {
        this.nbVoiture = nbVoiture;
    }

    public Noeud getNoeudDepart() {
        return noeudDepart;
    }

    public Noeud getNoeudarrive() {
        return noeudarrive;
    }

    public void setNoeudDepart(Noeud noeudDepart) {
        this.noeudDepart = noeudDepart;
    }

    public void setNoeudarrive(Noeud noeudarrive) {
        this.noeudarrive = noeudarrive;
    }

    public void setN1x(int n1x) {
        this.n1x = n1x;
    }

    public void setN1y(int n1y) {
        this.n1y = n1y;
    }

    public void setN2x(int n2x) {
        this.n2x = n2x;
    }

    public void setN2y(int n2y) {
        this.n2y = n2y;
    }

    public Color getCouleur() {
        return couleur;
    }

    public String toString() {
        return "Arete [n1x=" + n1x + ", n1y=" + n1y + ", n2x=" + n2x + ", n2y=" + n2y + ", nbVoiture=" + nbVoiture
                + "]";
    }

}
