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
import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.*;


public class PanelParametre extends JPanel {

    private Controleur ctrl;

    public PanelParametre(Controleur ctrl){
        this.ctrl = ctrl;

    }
    
}
