package ihm.Client;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FenetreInformationsClient extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel imageFenetre;

	// Cette fenêtre va permettre de donner un descriptif du magasin aux clients 
	// accédant au sous-menu "Informations" dans le menu "Contact"
	
	public FenetreInformationsClient(){
        
        this.setTitle("Qui sommes-nous ?");
        this.setSize(750, 400);
        this.setLocationRelativeTo(null);               
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        
        //Instanciation d'un objet JPanel
        Panneau panneauInformations = new Panneau();
        panneauInformations.setBorder(BorderFactory.createTitledBorder("Mot du Gérant : Alexis Louvel"));
        //Définition de sa couleur de fond
        panneauInformations.setBackground(Color.white);               
        this.getContentPane().add(panneauInformations,"Center");
        
        // Creation du panneau du bas qui accueillera un autre panneau et une image 
        JPanel panneauBas = new JPanel();
        panneauBas.setLayout(new GridLayout(1,2,5,5));
        panneauBas.setBorder(BorderFactory.createTitledBorder("Localisation"));
        
        // Creation du panneau avec l'adresse et le téléphone
        JPanel panneauInformationsSupp = new JPanel();
        JLabel adresse = new JLabel("Adresse : Rue Blaise Pascal - BP 37203 - 35172 BRUZ cedex");
        JLabel telephone = new JLabel("Téléphone : +33 (0)2 99 05 32 32");
        panneauInformationsSupp.add(adresse);
        panneauInformationsSupp.add(telephone);
        
        panneauBas.add(panneauInformationsSupp);
        
        // Creation du panneau qui accueillera l'image
        imageFenetre = new JLabel(new ImageIcon("src/images/carte.jpg"));
		JPanel panImage = new JPanel();
		panImage.setBorder(BorderFactory.createEmptyBorder());
		panImage.add(imageFenetre);
		
		panneauBas.add(panImage);
        
        this.getContentPane().add(panneauBas,"South");
        
        
        this.setVisible(true);
	}       
   
	
}

