package src.metier;

import javax.swing.JButton;

public class Noeud
{
    // getter et setter nom et x et y et button to string
    private int     x;
    private int     y;
    private int     xText;
    private int     yText;
    private String  nom;

    public Noeud(int x, int y, String nom, int xText, int yText) 
    {
        this.x      = x;
        this.y      = y;
        this.xText  = xText;
        this.yText  = yText;
        this.nom = nom;
    }

    public int x() 
    {
        return x;
    }

    public int y() 
    {
        return y;
    }

    public int xText() 
    {
        return xText;
    }

    public int yText() 
    {
        return yText;
    }

    public String getNom() 
    {
        return nom;
    }

    public void setXText(int xText) 
    {
        this.xText = xText;
    }

    public void setYText(int yText) 
    {
        this.yText = yText;
    }

    public void setNom(String nom) 
    {
        this.nom = nom;
    }

    public void setX(int x) 
    {
        this.x = x;
    }

    public void setY(int y) 
    {
        this.y = y;
    }

    @Override
    public String toString() 
    {
        return "Noeud [nom=" + nom + ", x=" + x + ", y=" + y + "]";
    }



    
}