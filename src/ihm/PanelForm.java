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
    private JButton btnAjouterNoeud ;
    private JButton btnCouleurNoeud;
    private JButton btnParametres;
    private JButton btnSuivant;
    private JButton btnPrecedent;
    private JPanel  panelMenu;
    private JButton btnAjouterTrajet;
    private int etat = 0;
    private boolean etatParam = false;

    public PanelForm(Controleur ctrl)
    {
        this.ctrl = ctrl;
        GridBagConstraints g = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        
        this.btnAjouterImage    = new JButton("Ajouter une image de map");
        this.btnAjouterNoeud    = new JButton("Ajouter un noeud"         );
        this.btnCouleurNoeud    = new JButton("Couleur des noeuds"      );
        this.btnParametres      = new JButton("Paramètres"              );
        this.btnSuivant         = new JButton("Suivant"                 );
        this.btnPrecedent       = new JButton("Precedent"               );
        this.btnAjouterTrajet   = new JButton("Ajouter un trajet"       );
        
        //Ajout de la couleur sur les boutons
        this.btnSuivant.setBackground   (Color.GREEN  );
        this.btnPrecedent.setBackground (Color.RED);
        //ouvrir l'arborecence de fichier pour choisir une image
        this.btnAjouterImage.addActionListener(this);
        this.btnAjouterNoeud.addActionListener(this);
        this.btnAjouterTrajet.addActionListener(this);
        this.btnSuivant.addActionListener(this);
        this.btnPrecedent.addActionListener(this);
        this.btnParametres.addActionListener(this);

        this.setBackground(new Color(35,31,32));

        this.initPanel(this.etat);
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

    public void initPanel(int numPanel)
    {
        if(numPanel >= 3)
        {
            this.etat = 2;
        }
        if(numPanel <= 0)
        {
            this.etat = 0;
        }
        numPanel = this.etat;
        System.out.println("numPanel = " + numPanel);
        this.removeAll();
        JButton[] tabBtn = {  this.btnPrecedent, this.btnAjouterImage, this.btnAjouterNoeud, this.btnCouleurNoeud, this.btnParametres, this.btnSuivant, this.btnAjouterTrajet};
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10,10,10,10);
                
        for(JButton btn : tabBtn)
        {
            btn.setPreferredSize    (new Dimension(150, 50));
        }
        g.gridx = 0;
        g.gridy = 0;
        this.add(tabBtn[0], g);

        if(numPanel == 0)
        {
            g.gridy = g.gridy + 1;
            this.add(tabBtn[1], g);
            g.gridy = g.gridy + 1;
            this.add(tabBtn[3], g);
            g.gridy = g.gridy + 1;
            this.add(tabBtn[4], g);
        }
        if(numPanel == 1)
        {
            g.gridy = g.gridy + 5;
            this.add(tabBtn[2], g);
            
        }
        if(numPanel == 2)
        {
            g.gridy = g.gridy + 1;
            this.add(tabBtn[6], g);
        }
        g.gridy = g.gridy + 1;
        this.add(tabBtn[5], g);

        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnAjouterNoeud)
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
        if(e.getSource() == this.btnAjouterTrajet)
        {
            System.out.println("Ajouter un trajet");
            this.ctrl.setActiveTrajet(true);
        }
        if(e.getSource() == this.btnSuivant)
        {
            this.initPanel(++this.etat);
            this.ctrl.refreshFrame();
        }
        if(e.getSource() == this.btnPrecedent)
        {
            this.initPanel(--this.etat);
            this.ctrl.refreshFrame();
        }

        if(e.getSource() == this.btnParametres)
        {
            this.etatParam = !this.etatParam;
            this.ctrl.getParametre(this.etatParam);
            System.out.println("Parametres"+ this.etatParam);
        }
    }
}
