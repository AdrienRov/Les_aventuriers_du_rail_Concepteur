package src.ihm;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import java.awt.FlowLayout;
import java.util.Locale;
import java.awt.Color;
import java.awt.Dimension;
import src.Controleur;
import java.awt.GridLayout;
import java.awt.*;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.*;

public class PanelForm extends JPanel implements ActionListener
{
    private Controleur ctrl;
    private JButton btnAjouterImage;
    private JButton btnAjouterNeud ;
    private JButton btnCouleurNoeud;
    private JButton btnParametres;
    private JButton btnSuivant;
    private JButton btnPrecedent;
    private JPanel  panelMenu;

    public PanelForm(Controleur ctrl)
    {
        this.ctrl = ctrl;
        GridBagConstraints g = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        
        this.btnAjouterImage    = new JButton("Ajouter une image de map");
        this.btnAjouterNeud     = new JButton("Ajouter un neud"         );
        this.btnCouleurNoeud    = new JButton("Couleur des noeuds"      );
        this.btnParametres      = new JButton("Paramètres"              );
        this.btnSuivant         = new JButton("Suivant"                 );
        this.btnPrecedent       = new JButton("Precedent"               );

        JButton[] tabBtn = {  this.btnPrecedent, this.btnAjouterImage, this.btnAjouterNeud, this.btnCouleurNoeud, this.btnParametres, this.btnSuivant};
        
        //Ajout de la couleur sur les boutons
        this.btnSuivant.setBackground   (Color.GREEN  );
        this.btnPrecedent.setBackground (Color.RED);
        //ouvrir l'arborecence de fichier pour choisir une image
        this.btnAjouterImage.addActionListener(this);
        this.btnAjouterNeud.addActionListener(this);


        this.setBackground(new Color(35,31,32));

       

        for(int i = 0; i < tabBtn.length; i++)
        {
            if(i == 0)
            {
                //positionner le bouton vers le bas de la fenêtre (50px)
                tabBtn[i].setPreferredSize    (new Dimension(150, 50));
                g.insets = new Insets(10,10,50,10);
                g.gridx = 0;
                g.gridy = i;
                this.add(tabBtn[i], g);
            }
            if(i == 5)
            {
                tabBtn[i].setPreferredSize    (new Dimension(150, 50));
                g.insets = new Insets (50,0,0,0);
                g.gridx = 0;
                g.gridy = i;
                this.add(tabBtn[i],g);
            }
            if(i != 0 && i != 5)
            {
                System.out.println("i = " + i);
                tabBtn[i].setPreferredSize   (new Dimension(200, 50));
                g.insets = new Insets(10,10,10,10);
                g.gridx = 0;
                g.gridy = i;
                this.add(tabBtn[i],g);
            }
            
        }
        
        
        this.setVisible(true);
    }
    private File getFileDialog()
    {
        JFileChooser fc = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fc.setCurrentDirectory(workingDirectory);
        int returnVal = fc.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            return fc.getSelectedFile();
        }
        else
        {
            return null;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btnAjouterNeud)
        {
            this.ctrl.setActiveNoeud(true);
        }
        if(e.getSource() == this.btnAjouterImage)
        {
            File f = this.getFileDialog();
            if (f != null)
            {
                this.ctrl.afficherCarte(f.getAbsolutePath());
            }
        }
    }
}
