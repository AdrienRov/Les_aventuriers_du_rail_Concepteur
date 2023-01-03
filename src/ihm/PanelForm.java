package src.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableColumn;

import src.Controleur;
import src.metier.Arete;
import src.metier.Noeud;

public class PanelForm extends JPanel implements ActionListener, CellEditorListener
{
    private Controleur ctrl;
    private JButton btnAjouterImage;
    private JButton btnAjouterNoeud ;
    private JButton btnCouleurNoeud;
    private JButton btnParametres;
    private JButton btnSuivant;
    private JButton btnPrecedent;
    private JButton btnAjouterTrajet;
    private JButton btnGenererXml;
    private JTextField  tabTxtNoeud[] = new JTextField[3];
    private JTextField  tabTxtTrajet[] = new JTextField[3];
    private JTable table;
    private Object[][] donneesNoeud     = {{"", "", ""}};
    private String[] entetesNoeud       = {"Nom", "X", "Y"};
    private Object[][] donneesTrajet    = {{"", "", ""}};
    private String[] entetesTrajet      = {"Ville Départ", "Nombre de sections", "Ville Arrivée"};
    private int etat = 0;
    private int verif = 0;
    private boolean etatParam = false;
    private TableColumn tabColNoeud[] = new TableColumn[3];
    private TableColumn tabColTrajet[] = new TableColumn[3];

    public PanelForm(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout(new GridBagLayout());
        
        this.btnAjouterImage    = new JButton("Ajouter une image de map");
        this.btnAjouterNoeud    = new JButton("Ajouter"        );
        this.btnCouleurNoeud    = new JButton("Couleur des noeuds"      );
        this.btnParametres      = new JButton("Paramètres"              );
        this.btnSuivant         = new JButton("Suivant"                 );
        this.btnPrecedent       = new JButton("Precedent"               );
        this.btnAjouterTrajet   = new JButton("Ajouter un trajet"       );
        this.btnGenererXml      = new JButton("Générer le fichier XML"  );
        for(int i = 0; i < 3; i++)
        {
            this.tabTxtNoeud[i] = new JTextField();
            this.tabTxtTrajet[i] = new JTextField();
        }
        this.table = new JTable(this.donneesNoeud, this.entetesNoeud);
        
        
        
        //Ajout de la couleur sur les boutons
        this.btnSuivant.setBackground   (Color.GREEN  );
        this.btnPrecedent.setBackground (Color.RED);
        this.btnGenererXml.setBackground(Color.GREEN);
        //ouvrir l'arborecence de fichier pour choisir une image
        this.btnAjouterImage.addActionListener(this);
        this.btnAjouterNoeud.addActionListener(this);
        this.btnCouleurNoeud.addActionListener(this);
        this.btnAjouterTrajet.addActionListener(this);
        this.btnSuivant.addActionListener(this);
        this.btnPrecedent.addActionListener(this);
        this.btnParametres.addActionListener(this);
        this.btnGenererXml.addActionListener(this);

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
        if(numPanel >= 5)
        {
            this.etat = 4;
            this.etat = 4;
        }
        if(numPanel <= 0)
        {
            this.etat = 0;
        }
        
        numPanel = this.etat;
        System.out.println("numPanel = " + numPanel);
        this.removeAll();
        JButton[] tabBtn = {  this.btnPrecedent, this.btnAjouterImage, this.btnAjouterNoeud, this.btnCouleurNoeud, this.btnParametres, this.btnSuivant, this.btnAjouterTrajet, this.btnGenererXml};
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
            this.refreshTabNoeud();
            //supprimer le listener sur les cellules
            for(int i = 0; i < tabColNoeud.length; i++)
            {
                if(tabColNoeud[i] != null)
                {
                    tabColNoeud[i].getCellEditor().removeCellEditorListener(this);
                }
            }
            
            for(int i = 0; i < tabColNoeud.length; i++)
            {
                tabColNoeud[i] = this.table.getColumnModel().getColumn(i);
                tabColNoeud[i].setCellEditor(new DefaultCellEditor(tabTxtNoeud[i]));
                tabColNoeud[i].getCellEditor().addCellEditorListener(this);
            }
            g.gridy = g.gridy + 5;
            JLabel label = new JLabel("<html><center>Cliquer sur <br>la mappe pour <br>placer un nœud</center></html>");
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            this.add(label, g);  
            g.gridy = g.gridy + 1;
            JScrollPane scrollPane = new JScrollPane(this.table);
            scrollPane.setPreferredSize(new Dimension(200, 100));
            if(this.ctrl.getAllNoeuds().isEmpty())
            {
                this.remove(scrollPane);
            }
            else
            {
                this.add(scrollPane, g);
            }
            
            g.gridy = g.gridy + 1;
            tabBtn[2].setPreferredSize    (new Dimension(100, 25));
            this.add(tabBtn[2],g);
        }
        if(numPanel == 2)
        {
            this.refreshTabTrajet();
            // TableColumn tabColTrajet[] = new TableColumn[3];
            for(int i = 0; i < tabColTrajet.length; i++)
            {
                if(tabColTrajet[i] != null)
                {
                    tabColTrajet[i].getCellEditor().removeCellEditorListener(this);
                }
            }
            for(int i = 0; i < tabColTrajet.length; i++)
            {
                tabColTrajet[i] = this.table.getColumnModel().getColumn(i);
                tabColTrajet[i].setCellEditor(new DefaultCellEditor(tabTxtTrajet[i]));
                tabColTrajet[i].getCellEditor().addCellEditorListener(this);
            }
            g.gridy = g.gridy + 1;
            JLabel label = new JLabel("<html><center>Cliquer sur <br> deux nœuds pour <br> créer une arête</center></html>");
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            this.add(label, g);
            g.gridy = g.gridy + 1;
            JScrollPane scrollPane = new JScrollPane(this.table);
            scrollPane.setPreferredSize(new Dimension(200, 100));
            if(this.ctrl.getAllTrajets().isEmpty())
            {
                this.remove(scrollPane);
            }
            else
            {
                this.add(scrollPane, g);
            }
        }
        if(numPanel == 3)
        {
            g.gridy = g.gridy + 1;
            this.add(tabBtn[4], g);
        }
        if(numPanel == 4)
        {
            g.gridy = g.gridy + 50;
            this.add(tabBtn[7], g);
        }
        else
        {
            g.gridy = g.gridy + 1;
            this.add(tabBtn[5], g);
        }
        this.ctrl.setEtatPanel(numPanel);
        this.repaint();
        this.revalidate();
    }

    public void refreshTabNoeud()
    {
        System.out.println("refreshTabNoeud");
        if(this.ctrl.getAllNoeuds().isEmpty())
        {
            Object[][] donneesNoeud = {{"", "", ""}};
            this.table = new JTable(donneesNoeud, entetesNoeud);
            return;
        }
        Object[][] donneesNoeud = new Object[this.ctrl.getAllNoeuds().size()][3];
        String[] entetesNoeud = {"Nom", "X", "Y"};
        for(int i = 0; i < this.ctrl.getAllNoeuds().size(); i++)
        {
            Noeud n = this.ctrl.getAllNoeuds().get(i);
            System.out.println("VALLLLLLL = "+n.getNom());
            donneesNoeud[i][0] = n.getNom();
            donneesNoeud[i][1] = n.x();
            donneesNoeud[i][2] = n.y();
        }
        this.table = new JTable(donneesNoeud, entetesNoeud);
    }
    
    public void refreshTabTrajet()
    {
        System.out.println("refreshTabTrajet");
        if(this.ctrl.getAllTrajets().isEmpty())
        {
            Object[][] donneesTrajet = {{"", "", ""}};
            this.table = new JTable(donneesTrajet, entetesTrajet);
            return;
        }
        Object[][] donneesTrajet = new Object[this.ctrl.getAllTrajets().size()][3];
        String[] entetesTrajet = {"Départ", "Voitures", "Arrivée"};
        for(int i = 0; i < this.ctrl.getAllTrajets().size(); i++)
        {
            Arete a = this.ctrl.getAllTrajets().get(i);
            System.out.println("VALLLLLLL = "+a.getNoeudDepart().getNom() + " " + a.getNbVoiture() + " " + a.getNoeudarrive().getNom());
            donneesTrajet[i][0] = a.getNoeudDepart().getNom();
            donneesTrajet[i][1] = ""+a.getNbVoiture();
            donneesTrajet[i][2] = a.getNoeudarrive().getNom();
        }
        this.table = new JTable(donneesTrajet, entetesTrajet);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnAjouterNoeud)
        {
            this.ctrl.notification("Cliquer sur la map pour ajouter un noeud");
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
            this.ctrl.notification("Sélectionner un noeud de départ");
            this.ctrl.setActiveTrajet(true);
        }
        if(e.getSource() == this.btnSuivant)
        {
            this.initPanel(++this.etat);
            this.ctrl.refreshFrame();
            if(this.etat == 1 && this.etatParam == true)
            {
                this.etatParam = false;
                this.ctrl.getParametre(this.etatParam);
            }
        }
        if(e.getSource() == this.btnPrecedent)
        {
            this.initPanel(--this.etat);
            this.ctrl.refreshFrame();
            if(this.etat == 0 && this.etatParam == true)
            {
                this.etatParam = false;
                this.ctrl.getParametre(this.etatParam);
            }
        }

        if(e.getSource() == this.btnParametres)
        {
            this.etatParam = !this.etatParam;
            this.ctrl.getParametre(this.etatParam);
            System.out.println("Parametres"+ this.etatParam);
        }
        if(e.getSource() == this.btnGenererXml)
        {
            this.ctrl.genererXml();
            System.out.println("Générer XML");
        }
    }

    public void editingStopped(ChangeEvent e) {
        // Mettre a jour les valeurs
        // Phase de vérification des entrées dans le panel

        String nom = "";
        this.verif++;
        System.out.println("verif = "+this.verif+"---------------------------------" + e.getSource());
        
        if(etat == 1)
        {
            int x = this.ctrl.getAllNoeuds().get(this.table.getSelectedRow()).x();
            int y = this.ctrl.getAllNoeuds().get(this.table.getSelectedRow()).y();
            
            if(this.table.getSelectedColumn() == 0)
            {
                nom = this.table.getValueAt(this.table.getSelectedRow(), this.table.getSelectedColumn()).toString();
                System.out.println("colonnes = "+this.table.getSelectedColumn());
                this.ctrl.getAllNoeuds().get(this.table.getSelectedRow()).setNom(nom);            
            }
            if(this.table.getSelectedColumn() == 1)
            {
                x = Integer.parseInt(this.table.getValueAt(this.table.getSelectedRow(), this.table.getSelectedColumn()).toString());
                System.out.println("colonnes = "+this.table.getSelectedColumn());
                this.ctrl.getAllNoeuds().get(this.table.getSelectedRow()).setX(x);
                
            }
            if(this.table.getSelectedColumn() == 2)
            {
                y = Integer.parseInt(this.table.getValueAt(this.table.getSelectedRow(), this.table.getSelectedColumn()).toString());
                System.out.println("colonnes = "+this.table.getSelectedColumn());
                this.ctrl.getAllNoeuds().get(this.table.getSelectedRow()).setY(y);
            }
            JButton btn = new JButton("");
            btn.setLocation(x, y);
            this.ctrl.getAllNoeuds().get(this.table.getSelectedRow()).setButton(btn);
        }
        
        this.ctrl.refreshFrame();
    }
    @Override
    public void editingCanceled(ChangeEvent e) {
        // TODO Auto-generated method stub
        
    }
}