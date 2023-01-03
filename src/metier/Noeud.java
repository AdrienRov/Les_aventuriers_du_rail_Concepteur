package src.metier;

import javax.swing.JButton;

public class Noeud{
    // getter et setter nom et x et y et button to string
    private int x;
    private int y;
    private JButton button;
    private String nom;

    public Noeud(int x, int y, String nom, JButton button) {
        this.x = x;
        this.y = y;
        this.button = button;
        this.nom = nom;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public JButton getButton() {
        return button;
    }

    public String getNom() {
        return nom;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Noeud [button=" + button + ", nom=" + nom + ", x=" + x + ", y=" + y + "]";
    }



    
}