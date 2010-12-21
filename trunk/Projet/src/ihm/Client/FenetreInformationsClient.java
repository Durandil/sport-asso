package ihm.Client;

import java.awt.*;
import javax.swing.*;

/**
 * La classe FenetreInformationsClient permet d'afficher la fenêtre regroupant le descriptif du
 * magasin aux clients accédant au sous-menu "Informations" dans le menu "Contact". 
 * Nous retrouvons le message de présentation de l'entreprise, un panneau avec l'adresse et le numéro
 * de téléphone ainsi qu'une carte pour localiser le magasin
 * 
 * @author Utilisateur
 * @see ihm.MenuUtilisateur
 * @see Panneau
 */
public class FenetreInformationsClient extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel imageFenetre;

	/**
	 * Constructeur de la classe qui initialise la fenêtre et ses composants
	 */
	public FenetreInformationsClient(){
        
        this.setTitle("Qui sommes-nous ?");
        this.setSize(750, 400);
        this.setLocation(50,50);               
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        
        //Instanciation d'un objet JPanel pour mettre le panneau contenant le message de présentation
        // du magasin
        Panneau panneauInformations = new Panneau();
        panneauInformations.setBorder(BorderFactory.createTitledBorder("Mot du Gérant : M. Poirier"));
        //Définition de sa couleur de fond
        panneauInformations.setBackground(Color.white);               
        this.getContentPane().add(panneauInformations,"Center");
        
        // Creation du panneau du bas qui accueillera un autre panneau et une image 
        JPanel panneauBas = new JPanel();
        panneauBas.setLayout(new GridLayout(1,2,5,5));
        panneauBas.setBorder(BorderFactory.createTitledBorder("Localisation"));
        
        // Creation du panneau avec l'adresse et le téléphone du magasin
        JPanel panneauInformationsSupp = new JPanel();
        JLabel adresse = new JLabel("Adresse : Rue Blaise Pascal - BP 37203 - 35172 BRUZ cedex");
        JLabel telephone = new JLabel("Téléphone : +33 (0)2 99 05 32 32");
        panneauInformationsSupp.add(adresse);
        panneauInformationsSupp.add(telephone);
        
        panneauBas.add(panneauInformationsSupp);
        
        // Creation du panneau qui accueillera l'image (la carte de france)
        imageFenetre = new JLabel(new ImageIcon("src/images/carte.jpg"));
		JPanel panImage = new JPanel();
		panImage.setBorder(BorderFactory.createEmptyBorder());
		panImage.add(imageFenetre);
		
		panneauBas.add(panImage);
        
        this.getContentPane().add(panneauBas,"South");
        
        this.setVisible(true);
	}       
   
	
}

