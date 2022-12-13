package src.metier;

public class Arete
{
    private int n1y ;
    private int n1x ;
    private int n2y ;
    private int n2x ;
 
    private Noeud noeudDepart;
    private Noeud noeudarrive;

    private int nbVoiture;
    
    Arete( Noeud noeudDepart, Noeud noeudarrive, int nbVoiture)
    {
        this.noeudDepart = noeudDepart;
        this.noeudarrive = noeudarrive;
        
        this.nbVoiture = nbVoiture;
        this.n1x = noeudarrive.x();
        this.n1y = noeudarrive.y();
        this.n2x = noeudDepart.x();
        this.n2y = noeudDepart.y();

    }
   
}

