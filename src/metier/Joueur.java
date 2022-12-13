package src.metier;

import java.util.ArrayList;

public class Joueur {
    private ArrayList<CartesVoitures> carteVoiture;
    private ArrayList<CarteObjectif> carteObjectif;

    private int scoreMinumum;

    public Joueur(ArrayList<CartesVoitures> carteVoiture, ArrayList<CarteObjectif> carteObjectif, int score) {
        this.carteVoiture = carteVoiture;
        this.carteObjectif = carteObjectif;

    }

    public ArrayList<CartesVoitures> getCarteVoiture() {
        return carteVoiture;
    }

    public ArrayList<CarteObjectif> getCarteObjectif() {
        return carteObjectif;
    }





    
}

