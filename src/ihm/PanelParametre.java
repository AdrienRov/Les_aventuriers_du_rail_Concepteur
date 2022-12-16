package src.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;

import src.Controleur;



public class PanelParametre extends JPanel implements ActionListener
{

    private ArrayList<JTextField> listeTexte;
    private ArrayList<JLabel> listeLabel;
    private ArrayList<JPanel> listePanel;
    
    
    private JButton btnSauvegarder;
    private JButton btnSuivant;
    private Controleur ctrl;
    private JPanel panelCentre;
    private JPanel panelSud;
    private JLabel labelTitre;


    public PanelParametre(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(35,31,32));

        this.listeTexte = new ArrayList<JTextField>();
        this.listeLabel = new ArrayList<JLabel>();
        this.listePanel = new ArrayList<JPanel>();
        
        for(int i=0; i<11; i++)
        {
            this.listeTexte.add(new JTextField());
            this.listeLabel.add(new JLabel());
        }

        for (JTextField texte : listeTexte) {
            texte.setPreferredSize(new Dimension(100, 30));
            texte.setFont(new Font("Arial", Font.PLAIN, 20));
        }

        listeLabel.get(0).setText("<html>Nombre de Joueur minimum</html>");
        listeLabel.get(1).setText("À maximum");
        listeLabel.get(2).setText("<html>Nombre de wagon que détient un joueur déclanchant la fin de partie</html>");
        listeLabel.get(3).setText("<html>Nombre de points pour 1 wagon</html>");
        listeLabel.get(4).setText("<html>Nombre de points pour 2 wagons</html>");
        listeLabel.get(5).setText("<html>Nombre de points pour 3 wagons</html>");
        listeLabel.get(6).setText("<html>Nombre de points pour 4 wagons</html>");
        listeLabel.get(7).setText("<html>Nombre de points pour 5 wagons</html>");
        listeLabel.get(8).setText("<html>Nombre de points pour 6 wagons</html>");
        listeLabel.get(9).setText("<html>Nombre de wagon pour chaque joueur</html>");
        listeLabel.get(10).setText("<html>À partir de combien de joueur voulez vous ajouter les doubles voies</html>");

        labelTitre = new JLabel("Paramètres");
        btnSauvegarder = new JButton("<html>Sauvegarder</html>");
        for (JLabel label : listeLabel) 
        {
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 10));
        }

        for(int i=0; i<6; i++)
        {
            listePanel.add(new JPanel());
        }

        for(int i=0; i<listePanel.size(); i++)
        {
            listePanel.get(i).setLayout(new GridLayout(1, 4,0,0));
            listePanel.get(i).setBackground(new Color(35,31,32));
        }

        for(int i=0; i<2; i++)
        {
            listePanel.get(1).add(listeLabel.get(i));
            listePanel.get(1).add(listeTexte.get(i));
        }

        for(int i=2; i<4; i++)
        {
            listePanel.get(2).add(listeLabel.get(i));
            listePanel.get(2).add(listeTexte.get(i));
        }
        for(int i=4; i<6; i++)
        {
            listePanel.get(3).add(listeLabel.get(i));
            listePanel.get(3).add(listeTexte.get(i));
        }
        for(int i=6; i<8; i++)
        {
            listePanel.get(4).add(listeLabel.get(i));
            listePanel.get(4).add(listeTexte.get(i));
        }
        for(int i=8; i<10; i++)
        {
            listePanel.get(5).add(listeLabel.get(i));
            listePanel.get(5).add(listeTexte.get(i));
        }

        //faire un panel au centre pour mettre les labels
        this.panelCentre = new JPanel();
        this.panelCentre.setLayout(new GridLayout(6, 1,0,0));
        this.panelCentre.setBackground(new Color(35,31,32));
    
        for (JPanel panel : listePanel) 
        {
            panelCentre.add(panel);
        }
        //centrer le titre
        this.labelTitre.setHorizontalAlignment(JLabel.CENTER);

        this.labelTitre.setForeground(Color.WHITE);
        this.labelTitre.setFont(new Font("Arial", Font.BOLD, 20));
        this.btnSauvegarder.setPreferredSize(new Dimension(100, 20));
        this.btnSauvegarder.setFont(new Font("Arial", Font.BOLD, 10));
        this.btnSuivant = new JButton("Suivant");
        this.btnSuivant.setPreferredSize(new Dimension(100, 20));
        this.btnSuivant.setFont(new Font("Arial", Font.BOLD, 10));

        this.btnSauvegarder.addActionListener(this);
        this.btnSuivant.addActionListener(this);

        this.panelSud = new JPanel();
        this.panelSud.setBackground(new Color(35,31,32));
        this.panelSud.add(this.btnSauvegarder);
        this.panelSud.add(this.btnSuivant);
        this.add(this.labelTitre, BorderLayout.NORTH);
        this.add(this.panelSud, BorderLayout.SOUTH);
        this.add(this.panelCentre, BorderLayout.CENTER);
        

    }
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == this.btnSauvegarder) 
        {
            //vérifier que toutes les valeurs sont rentrées
            if(this.listeTexte.get(0).getText().equals("") || this.listeTexte.get(1).getText().equals("") || 
            this.listeTexte.get(2).getText().equals("") || this.listeTexte.get(3).getText().equals("") || 
            this.listeTexte.get(4).getText().equals("") || this.listeTexte.get(5).getText().equals("") || 
            this.listeTexte.get(6).getText().equals("") || this.listeTexte.get(7).getText().equals("") || 
            this.listeTexte.get(8).getText().equals("") || this.listeTexte.get(9).getText().equals("") || 
            this.listeTexte.get(10).getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
            System.out.println("Sauvegarder");
            //récupérer les valeurs des textes et les envoyer au controleur
            this.ctrl.setNbJoueur(Integer.parseInt(this.listeTexte.get(0).getText()));
            this.ctrl.setNbWagonJoueur(Integer.parseInt(this.listeTexte.get(1).getText()));
            this.ctrl.setNbWagonFinPartie(Integer.parseInt(this.listeTexte.get(2).getText()));
            this.ctrl.setNbPoint1Wagon(Integer.parseInt(this.listeTexte.get(3).getText()));
            this.ctrl.setNbPoint2Wagon(Integer.parseInt(this.listeTexte.get(4).getText()));
            this.ctrl.setNbPoint3Wagon(Integer.parseInt(this.listeTexte.get(5).getText()));
            this.ctrl.setNbPoint4Wagon(Integer.parseInt(this.listeTexte.get(6).getText()));
            this.ctrl.setNbPoint5Wagon(Integer.parseInt(this.listeTexte.get(7).getText()));
            this.ctrl.setNbPoint6Wagon(Integer.parseInt(this.listeTexte.get(8).getText()));
            this.ctrl.setNbJoueurDoublesVoies(Integer.parseInt(this.listeTexte.get(9).getText()));
            }
            
        }

        if(e.getSource() == this.btnSuivant)
        {
            this.ctrl.getParametreSuivant();
        }
    }
}