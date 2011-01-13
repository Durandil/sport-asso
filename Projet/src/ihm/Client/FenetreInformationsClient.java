package ihm.Client;

import java.awt.*;
import javax.swing.*;

/**
 * La classe FenetreInformationsClient permet d'afficher la fen�tre regroupant
 * le descriptif du magasin aux clients acc�dant au sous-menu "Informations"
 * dans le menu "Contact". Nous retrouvons le message de pr�sentation de
 * l'entreprise, un JPanel avec l'adresse et le num�ro de t�l�phone ainsi qu'une
 * carte pour localiser le magasin.
 * 
 * @see ihm.MenuUtilisateur
 * @see Panneau
 */
public class FenetreInformationsClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel imageFenetre;

	/**
	 * Constructeur de la classe qui initialise la fen�tre et ses composants
	 */
	public FenetreInformationsClient() {

		this.setTitle("Qui sommes-nous ?");
		this.setSize(750, 400);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setVisible(true);

		// Instanciation du JPanel pour mettre le panneau contenant le //
		// ---------- message de pr�sentation du magasin --------------//
		// -------------------------------------------------------------//

		Panneau panneauInformations = new Panneau();
		panneauInformations.setBorder(BorderFactory
				.createTitledBorder("Mot du G�rant : M. Poirier"));

		// D�finition de la couleur de fond
		panneauInformations.setBackground(Color.white);

		// Cr�ation du JPanel panneauBas qui accueillera //
		// ------- un autre JPanel et une image ---------//
		// ----------------------------------------------//
		JPanel panneauBas = new JPanel();
		panneauBas.setLayout(new GridLayout(1, 2, 5, 5));
		panneauBas.setBorder(BorderFactory.createTitledBorder("Localisation"));

		// Cr�ation du JPanel avec l'adresse et le t�l�phone du magasin //
		// --------------------------------------------------------------//
		JPanel panneauInformationsSupp = new JPanel();
		JLabel adresse = new JLabel(
				"Adresse : Rue Blaise Pascal - BP 37203 - 35172 BRUZ cedex");
		JLabel telephone = new JLabel("T�l�phone : +33 (0)2 99 05 32 32");
		panneauInformationsSupp.add(adresse);
		panneauInformationsSupp.add(telephone);

		panneauBas.add(panneauInformationsSupp);

		// Cr�ation du JPanel qui accueillera l'image (la carte de france) //
		// -----------------------------------------------------------------//
		imageFenetre = new JLabel(new ImageIcon("Ressources/images/carte.jpg"));
		JPanel panImage = new JPanel();
		panImage.setBorder(BorderFactory.createEmptyBorder());
		panImage.add(imageFenetre);

		panneauBas.add(panImage);

		// Ajout des deux composants au conteneur de la fen�tre //
		// ------------------------------------------------------//
		this.getContentPane().add(panneauInformations, "Center");
		this.getContentPane().add(panneauBas, "South");
		
		this.setVisible(true);
	}

}
