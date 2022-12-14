package src.ihm;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JTextField;


import src.Controleur;



public class PanelParametre extends JPanel 
{

    private JTextField texte1;
    private JLabel labelTitle;
    private JLabel label1;
    private JTextField texte2;
    private JLabel label2;
    private Controleur ctrl;

    public PanelParametre(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setBackground(new Color(35,31,32));
        this.labelTitle = new JLabel("Parametre");
        this.labelTitle.setForeground(Color.WHITE);
        this.labelTitle.setFont(new Font("Arial", Font.BOLD, 30));
        texte1 = new JTextField();
        label1 = new JLabel("Nombre de Joueur");
        texte2 = new JTextField();
        label2 = new JLabel("Nombre de tronçon pour chaque joueur");
        
        this.add(this.labelTitle);
    }
    
}
