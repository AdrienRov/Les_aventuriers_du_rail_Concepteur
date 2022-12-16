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
import java.util.ArrayList;
import javax.swing.JLabel;

public class PanelParamSuivant extends JPanel implements ActionListener {
    
    private Controleur ctrl;

    ArrayList<JLabel> listeLabel; 
    ArrayList<JButton> listeBtn;
    ArrayList<JPanel> listePanel;

    private JButton btnPrecedent;
    private JButton btnCarteFace;
    private JButton btnCarteDos;
    private JPanel panelSud;
    private JPanel panelCentre;
    private JFileChooser fc = new JFileChooser();

    public PanelParamSuivant(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(35,31,32));

        this.listeBtn = new ArrayList<JButton>();
        this.listeLabel = new ArrayList<JLabel>();
        this.listePanel = new ArrayList<JPanel>();

        for(int i=0; i<=8; i++)
        {
            this.listeLabel.add(new JLabel());
        }

        this.listeLabel.get(0).setText("<html>Choisissez une image de pour les wagons rouge</html>");
        this.listeLabel.get(1).setText("<html>Choisissez une image de pour les wagons bleu</html>");
        this.listeLabel.get(2).setText("<html>Choisissez une image de pour les wagons vert</html>");
        this.listeLabel.get(3).setText("<html>Choisissez une image de pour les wagons jaune</html>");
        this.listeLabel.get(4).setText("<html>Choisissez une image de pour les wagons noir</html>");
        this.listeLabel.get(5).setText("<html>Choisissez une image de pour les wagons violet</html>");
        this.listeLabel.get(6).setText("<html>Choisissez une image de pour les wagons marron</html>");
        this.listeLabel.get(7).setText("<html>Choisissez une image de pour les wagons blanc</html>");

        for(int i=0; i<=8; i++)
        {
            this.listeBtn.add(new JButton());
        }

        for(int i=0; i<=4; i++)
        {
            this.listePanel.add(new JPanel(new GridLayout(1, 4,0,0)));
        }

        for(int i=0; i<2; i++)
        {
            listePanel.get(0).add(listeLabel.get(i));
            listePanel.get(0).add(listeBtn.get(i));
        }

        for(int i=2; i<4; i++)
        {
            listePanel.get(1).add(listeLabel.get(i));
            listePanel.get(1).add(listeBtn.get(i));
        }
        for(int i=4; i<6; i++)
        {
            listePanel.get(2).add(listeLabel.get(i));
            listePanel.get(2).add(listeBtn.get(i));
        }
        for(int i=6; i<8; i++)
        {
            listePanel.get(3).add(listeLabel.get(i));
            listePanel.get(3).add(listeBtn.get(i));
        }

        
        this.btnPrecedent = new JButton("Précédent");
        this.btnCarteFace = new JButton();
        this.btnCarteDos = new JButton();

        this.panelSud = new JPanel();
        this.panelCentre = new JPanel();
        this.panelCentre.setLayout(new GridLayout(4, 1,0,0));
        this.panelSud.add(this.btnPrecedent);

        for (JPanel panel : listePanel) 
        {
            this.panelCentre.add(panel);
        }

        this.btnCarteDos.addActionListener(this);
        this.btnCarteFace.addActionListener(this);

        this.add(panelSud, BorderLayout.SOUTH);
        this.add(panelCentre, BorderLayout.CENTER);

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
        // TODO Auto-generated method stub

        for(int i=0; i<8; i++)
        {
            if(e.getSource() == this.listeBtn.get(i)) 
            {
                this.fc.setDialogType(JFileChooser.OPEN_DIALOG);
                int valeurFC = this.fc.showDialog(this, "Ouvrir une image de fond");

                if (valeurFC == JFileChooser.APPROVE_OPTION) 
                {

                    File fichier = fc.getSelectedFile();
                    String nomFichier = fichier.getName();
                    String extension = nomFichier.substring(nomFichier.lastIndexOf("."));

                    if (extension.equals(".jpeg") ||  extension.equals(".png") ||  extension.equals(".jpg")) 
                    {
                        Image image = new ImageIcon(fichier.getPath()).getImage();
                        this.listeBtn.get(i).setIcon(new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        
                    }
                }
            }
        }
        
    }
}
