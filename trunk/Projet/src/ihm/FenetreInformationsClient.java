package ihm;
import ihm.Panneau;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;


public class FenetreInformationsClient extends JFrame {
	
	// Cette fen�tre va permettere de donner un descriptif du magasin aux clients 
	// acc�dant au sous-menu "Informations" dans le menu "Contact"
	
	public FenetreInformationsClient(){
        
        this.setTitle("Qui sommes-nous ?");
        this.setSize(750, 300);
        this.setLocationRelativeTo(null);               
        this.setResizable(true);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        //Instanciation d'un objet JPanel
        Panneau pan = new Panneau();
        //D�finition de sa couleur de fond
        pan.setBackground(Color.white);        
        //On pr�vient notre JFrame que ce sera notre Panneau qui sera son contentPane
        this.setContentPane(pan);
        
        this.setVisible(true);
	}       
   
	
}

