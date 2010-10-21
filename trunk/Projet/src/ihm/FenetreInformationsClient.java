package ihm;
import ihm.Panneau;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JDialog;
import javax.swing.JFrame;


public class FenetreInformationsClient extends JFrame {
	
	
	public FenetreInformationsClient(){
        
        this.setTitle("Qui sommes-nous ?");
        this.setSize(750, 300);
        this.setLocationRelativeTo(null);               
        this.setResizable(true);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        //Instanciation d'un objet JPanel
        Panneau pan = new Panneau();
        //Définition de sa couleur de fond
        pan.setBackground(Color.white);        
        //On prévient notre JFrame que ce sera notre Panneau qui sera son contentPane
        this.setContentPane(pan);
        
        this.setVisible(true);
	}       
   
	
}

