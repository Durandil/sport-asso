package ihm;
import ihm.Panneau;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FenetreInformationsClient extends JFrame {
	
	// Cette fenêtre va permettre de donner un descriptif du magasin aux clients 
	// accédant au sous-menu "Informations" dans le menu "Contact"
	
	public FenetreInformationsClient(){
        
        this.setTitle("Qui sommes-nous ?");
        this.setSize(750, 300);
        this.setLocationRelativeTo(null);               
        this.setResizable(true);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setLayout(new GridLayout(1,2,5,5));
        
        //Instanciation d'un objet JPanel
        Panneau panneauInformations = new Panneau();
        panneauInformations.setBorder(BorderFactory.createTitledBorder("Mot du Gérant : Alexis Louvel"));
        //Définition de sa couleur de fond
        panneauInformations.setBackground(Color.white);               
        this.getContentPane().add(panneauInformations);
        
        // Creation du panneau avec l'adresse et le téléphone
        JPanel panneauInformationsSupp = new JPanel();
        panneauInformationsSupp.setBorder(BorderFactory.createEmptyBorder());
        panneauInformationsSupp.setBackground(Color.white);
        JLabel adresse = new JLabel("Adresse : Rue Blaise Pascal - BP 37203 - 35172 BRUZ cedex");
        JLabel telephone = new JLabel("Téléphone : +33 (0)2 99 05 32 32");
        panneauInformationsSupp.add(adresse);
        panneauInformationsSupp.add(telephone);
        
        this.getContentPane().add(panneauInformationsSupp);
        
        
        this.setVisible(true);
	}       
   
	
}

