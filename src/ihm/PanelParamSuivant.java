package src.ihm;

import src.Controleur;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.JLabel;

public class PanelParamSuivant extends JPanel implements ActionListener {

    private Controleur ctrl;

    JLabel[]  listeLabel;
    JButton[] listeBtn;
    JPanel[]  listePanel;
    File[]    listeFichier;

    private JButton      btnPrecedent;
    private JButton      btnSauvegarder;
    private JPanel       panelSud;
    private JPanel       panelCentre;
    private JLabel       labelTitre;
    private JFileChooser fc = new JFileChooser();

    public PanelParamSuivant(Controleur ctrl) {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(35, 31, 32));

        this.listeBtn     = new JButton[10];
        this.listeLabel   = new JLabel[10];
        this.listePanel   = new JPanel[5];
        this.listeFichier = new File[10];

        for (int i = 0; i < listeLabel.length; i++) {
            this.listeLabel[i] = new JLabel();
        }

        this.listeLabel[0] = new JLabel("<html>Choisissez une image pour les wagons rouge</html>");
        this.listeLabel[1] = new JLabel("<html>Choisissez une image pour les wagons bleu</html>");
        this.listeLabel[2] = new JLabel("<html>Choisissez une image pour les wagons vert</html>");
        this.listeLabel[3] = new JLabel("<html>Choisissez une image pour les wagons jaune</html>");
        this.listeLabel[4] = new JLabel("<html>Choisissez une image pour les wagons noir</html>");
        this.listeLabel[5] = new JLabel("<html>Choisissez une image pour les wagons violet</html>");
        this.listeLabel[6] = new JLabel("<html>Choisissez une image pour les wagons marron</html>");
        this.listeLabel[7] = new JLabel("<html>Choisissez une image pour les wagons blanc</html>");
        this.listeLabel[8] = new JLabel("<html>Choisissez une image pour la locomotive       </html>");
        this.listeLabel[9] = new JLabel("<html>Choisissez une image pour le verso des cartes objectifs </html>");

        for (JLabel label : listeLabel) {
            label.setForeground(Color.WHITE);
            label.setHorizontalAlignment(JLabel.CENTER);
        }

        labelTitre = new JLabel("Paramètres");
        labelTitre.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitre.setForeground(Color.WHITE);
        labelTitre.setHorizontalAlignment(JLabel.CENTER);
        this.add(labelTitre, BorderLayout.NORTH);

        for (int i = 0; i < listeBtn.length; i++) {

            this.listeBtn[i] = new JButton();
        }

        for (int i = 0; i < listePanel.length; i++) {
            this.listePanel[i] = new JPanel(new GridLayout(1, 4, 0, 0));
            this.listePanel[i].setBackground(new Color(35, 31, 32));
        }

        for (int i = 0; i < 2; i++) {
            listePanel[0].add(listeLabel[i]);
            listePanel[0].add(listeBtn[i]);
        }

        for (int i = 2; i < 4; i++) {
            listePanel[1].add(listeLabel[i]);
            listePanel[1].add(listeBtn[i]);
        }
        for (int i = 4; i < 6; i++) {
            listePanel[2].add(listeLabel[i]);
            listePanel[2].add(listeBtn[i]);
        }
        for (int i = 6; i < 8; i++) 
        {
            listePanel[3].add(listeLabel[i]);
            listePanel[3].add(listeBtn[i]);
        }
        for (int i = 8; i < 10; i++) 
        {
            listePanel[4].add(listeLabel[i]);
            listePanel[4].add(listeBtn[i]);
        }

        this.btnPrecedent   = new JButton("Précédent");
        this.btnPrecedent.addActionListener(this);

        this.btnSauvegarder = new JButton("Sauvegarder");
        this.btnSauvegarder.addActionListener(this);

        this.panelSud       = new JPanel();
        this.panelSud.setBackground(new Color(35, 31, 32));
        this.panelCentre    = new JPanel();
        this.panelCentre.setLayout(new GridLayout(7, 1, 0, 0));
        this.panelCentre.setBackground(new Color(35, 31, 32));
        this.panelSud.add(this.btnPrecedent);
        this.panelSud.add(this.btnSauvegarder);

        for (JPanel panel : listePanel) 
        {
            this.panelCentre.add(panel);
        }

        for (JButton bouton : listeBtn) 
        {
            bouton.addActionListener(this);
        }
        this.add(panelSud, BorderLayout.SOUTH);
        this.add(panelCentre, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        for (int i = 0; i < listeBtn.length; i++) 
        {
            if (e.getSource() == this.listeBtn[i]) 
            {
                this.fc.setDialogType(JFileChooser.OPEN_DIALOG);
                int valeurFC = this.fc.showDialog(this, "Ouvrir une image de fond");

                if (valeurFC == JFileChooser.APPROVE_OPTION) 
                {

                    File fichier = fc.getSelectedFile();
                    String nomFichier = fichier.getName();
                    String extension = nomFichier.substring(nomFichier.lastIndexOf("."));

                    if (extension.equals(".jpeg") || extension.equals(".png") || extension.equals(".jpg")) 
                    {
                        Image image = new ImageIcon(fichier.getPath()).getImage();
                        this.listeBtn[i].setIcon(new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        // définir la taille de l'image dans le bouton
                        this.listeBtn[i].setBorderPainted(false);
                        this.listeBtn[i].setContentAreaFilled(false);
                        this.listeBtn[i].setFocusPainted(false);
                        this.listeBtn[i].setOpaque(false);
                        this.listeBtn[i].setBorder(null);
                        this.listeFichier[i] = fichier;
                    }
                }
            }
        }

        if (e.getSource() == this.btnPrecedent) 
        {
            this.ctrl.getParametre(true);
        }

        if (e.getSource() == this.btnSauvegarder) 
        {

            // récupere le chemin des images
            for (int i = 0; i < listeBtn.length; i++) 
            {
                if (this.listeBtn[i].getIcon() != null && this.listeBtn[i].getIcon() instanceof ImageIcon) 
                {
                    this.ctrl.setCheminImage(i, this.ctrl.fileToString(this.listeFichier[i]));

                }
            }

            this.ctrl.getParametre(false);


        }

    }
}