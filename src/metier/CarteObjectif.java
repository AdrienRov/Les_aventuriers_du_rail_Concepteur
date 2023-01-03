package src.metier;
public class CarteObjectif
{
    private Noeud noeud1;
    private Noeud noeud2;
    private int score;

    public CarteObjectif(Noeud noeud1, Noeud noeud2, int score)
    {
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
        this.score = score;
    }  

    public Noeud getNoeud1()
    {
        return noeud1;
    }

    public Noeud getNoeud2()
    {
        return noeud2;
    }

    public int getScore()
    {
        return score;
    }

    public void setNoeud1(Noeud noeud1)
    {
        this.noeud1 = noeud1;
    }

    public void setNoeud2(Noeud noeud2)
    {
        this.noeud2 = noeud2;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    @Override
    public String toString()
    {
        return "CarteObjectif [noeud1=" + noeud1 + ", noeud2=" + noeud2 + ", score=" + score + "]";
    }
    

}

