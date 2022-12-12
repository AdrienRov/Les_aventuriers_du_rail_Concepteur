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

import java.awt.FlowLayout;
import java.util.Locale;
import java.awt.Color;
import java.awt.Dimension;
import src.Controleur;
import java.awt.GridLayout;
import java.io.File;
import java.awt.BorderLayout;

public class PanelForm extends JPanel
{
    private Controleur ctrl;
    private JButton btnAjouterImage;

    public PanelForm(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.btnAjouterImage = new JButton("Ajouter une image de map");
        this.btnAjouterImage.setLocation(0, 50);
        //positionner le bouton vers le bas de la fenêtre (50px) 
        this.btnAjouterImage.setPreferredSize(new Dimension(200, 50));
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
        this.add(this.btnAjouterImage);
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
}
