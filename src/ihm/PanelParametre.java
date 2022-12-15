package src.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;

import src.Controleur;



public class PanelParametre extends JPanel 
{

    private JTextField texte1;
    private JLabel label1;
    private JTextField texte2;
    private JLabel label2;
    private JTextField texte3;
    private JLabel label3;
    private JTextField texte4;
    private JLabel label4;
    private JTextField texte5;
    private JLabel label5;
    private JTextField texte6;
    private JLabel label6;
    private JTextField texte7;
    private JLabel label7;
    private JTextField texte8;
    private JLabel label8;
    private JTextField texte9;
    private JLabel label9;
    private JTextField texte10;
    private JLabel label10;

    private JButton btnSauvegarder;
    private Controleur ctrl;
    private JPanel panelCentre;

    public PanelParametre(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(35,31,32));
        
        texte1 = new JTextField();
        label1 = new JLabel("Nombre de Joueur");
        texte2 = new JTextField();
        label2 = new JLabel("Nombre de wagon pour chaque joueur");
        texte3 = new JTextField();
        label3 = new JLabel("<html>Nombre de wagon que détient un joueur </br>déclanchant la fin de partie</html>");
        texte4 = new JTextField();
        label4 = new JLabel("Nombre de points pour 1 wagon");
        texte5 = new JTextField();
        label5 = new JLabel("Nombre de points pour 2 wagons");
        texte6 = new JTextField();
        label6 = new JLabel("Nombre de points pour 3 wagons");
        texte7 = new JTextField();
        label7 = new JLabel("Nombre de points pour 4 wagons");
        texte8 = new JTextField();
        label8 = new JLabel("Nombre de points pour 5 wagons");
        texte9 = new JTextField();
        label9 = new JLabel("Nombre de points pour 6 wagons");
        texte10 = new JTextField();
        label10 = new JLabel("<html>À partir de combien de joueur voulez </br>vous ajouter les doubles voies</html>");
        btnSauvegarder = new JButton("Sauvegarder");

        JTextField[] tabJTextFields = {  this.texte1, this.texte2, this.texte3, this.texte4, this.texte5, this.texte6, this.texte7,this.texte8,this.texte9,this.texte10};
        JLabel[] tLabels = {  this.label1, this.label2, this.label3, this.label4, this.label5, this.label6, this.label7,this.label8,this.label9,this.label10};

        for (JTextField texteField : tabJTextFields) 
        {
            texteField.setPreferredSize    (new Dimension(50, 20));
            //changer la couleur du texte

        }
        for (JLabel label : tLabels) 
        {
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 10));
        }
        //faire un panel au centre pour mettre les labels
        this.panelCentre = new JPanel();
        this.panelCentre.setLayout(new GridLayout(10, 4,0,20));
        this.panelCentre.setBackground(new Color(35,31,32));
    

        for (int i = 0; i < tabJTextFields.length; i++) 
        {
    
            this.panelCentre.add(tLabels[i]);
            this.panelCentre.add(tabJTextFields[i]);
        }

        this.add(this.panelCentre, BorderLayout.CENTER);

 

        

        //récupérer les valurs des textes et les envoyer au controleur
        this.texte1.addActionListener(e -> this.ctrl.setNombreJoueur(Integer.parseInt(this.texte1.getText())));
        this.texte2.addActionListener(e -> this.ctrl.setNombreWagon(Integer.parseInt(this.texte2.getText())));
        this.texte3.addActionListener(e -> this.ctrl.setNombreWagonFin(Integer.parseInt(this.texte3.getText())));
        this.texte4.addActionListener(e -> this.ctrl.setNombrePoint1(Integer.parseInt(this.texte4.getText())));
    }
    
}
