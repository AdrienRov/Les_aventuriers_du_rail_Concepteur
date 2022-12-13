package src.metier;

public class Arete
{
    private int n1y ;
    private int n1x ;
    private int n2y ;
    private int n2x ;
 
    private Noeud noeudDepart;
    private Noeud neudarrive;

    private int nbVoiture;
    
    Arete( Noeud noeudDepart, Noeud noeudarrive, int nbVoiture)
    {
        this.noeudDepart = noeudDepart;
        this.neudarrive = noeudarrive;
        
        this.nbVoiture = nbVoiture;
        this.n1x = neudarrive.x();
        this.n1y = neudarrive.y();
        this.n2x = noeudDepart.x();
        this.n2y = noeudDepart.y();

    }
   
}

