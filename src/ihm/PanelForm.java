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

    public PanelForm(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new GridBagLayout());
        this.btnAjouterImage    = new JButton("Ajouter une image de map");
        this.btnAjouterNeud     = new JButton("Ajouter un neud"         );
        this.btnCouleurNoeud    = new JButton("Couleur des noeuds"      );
        this.btnParametres      = new JButton("Paramètres"              );
        this.btnSuivant         = new JButton("Suivant"                 );
        this.btnPrecedent       = new JButton("Precedent"               );
        //positionner le bouton vers le bas de la fenêtre (50px) 
        this.btnAjouterImage.setPreferredSize   (new Dimension(200, 50));
        this.btnAjouterNeud.setPreferredSize    (new Dimension(200, 50));
        this.btnCouleurNoeud.setPreferredSize   (new Dimension(200, 50));
        this.btnParametres.setPreferredSize     (new Dimension(200, 50));
        this.btnSuivant.setPreferredSize        (new Dimension(150, 30));
        this.btnPrecedent.setPreferredSize      (new Dimension(150, 30));
        //Ajout de la couleur sur les boutons
        this.btnSuivant.setBackground   (Color.RED  );
        this.btnPrecedent.setBackground (Color.GREEN);
        //ouvrir l'arborecence de fichier pour choisir une image
        this.btnAjouterImage.addActionListener(e -> {
            File f = this.getFileDialog();
            if (f != null)
            {
                System.out.println(f.getAbsolutePath());
                this.ctrl.afficherCarte(f.getAbsolutePath());
            }
        });
        this.setBackground(new Color(35,31,32));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10,10,50,10);
        g.gridx = 0;
        g.gridy = 0;
        this.add(this.btnPrecedent, g);
        g.insets = new Insets(10,10,10,10);
        g.gridx = 0;
        g.gridy = 1;
        this.add(this.btnAjouterImage,g);
        g.insets = new Insets(10,10,10,10);
        g.gridx = 0;
        g.gridy = 2;
        this.add(this.btnAjouterNeud,g);
        g.insets = new Insets(10,10,10,10);
        g.gridx = 0;
        g.gridy = 3;
        this.add(this.btnCouleurNoeud,g);
        g.insets = new Insets(10,10,10,10);
        g.gridx = 0;
        g.gridy = 4;
        this.add(this.btnParametres,g);
        g.insets = new Insets (50,0,0,0);
        g.gridx = 0;
        g.gridy = 5;
        this.add(this.btnSuivant,g);
        this.btnAjouterNeud.addActionListener(this);
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
            this.ctrl.setActiveNoeud();
        }
            
    }
}
