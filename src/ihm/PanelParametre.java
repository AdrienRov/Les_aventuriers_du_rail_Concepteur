package src.ihm;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JTextField;


import src.Controleur;



public class PanelParametre extends JPanel {

    private JTextField texte1;
    private JLabel label1;
    private JTextField texte2;
    private JLabel label2;
    private Controleur ctrl;

    public PanelParametre(Controleur ctrl){
        texte1 = new JTextField();
        label1 = new JLabel("Nombre de Joueur");
        texte2 = new JTextField();
        label2 = new JLabel("Nombre de tronçon pour chaque joueur");
        this.ctrl = ctrl;
    }
    
}
