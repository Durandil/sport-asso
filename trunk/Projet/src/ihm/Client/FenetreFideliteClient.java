package ihm.Client;

import ihm.Accueil.FenetreDialogCreationCompte;
import ihm.Accueil.FenetreDialogIdentification;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.CarteFidelite;

import basededonnees.SGBD;

/**
 * <p>
 * Cette classe FenetreFideliteClient permet d'afficher pour tous les clients
 * les détails de leur programme fidélité avec le magasin sport'asso :
 * <ul>
 * <li>leur adhésion ou non au programme fidélité</li>
 * <li>leur nombre de points pour les adhérents</li>
 * <li>le montant de leur bon de réduction dans le magasin</li>
 * </ul>
 * </p>
 * 
 * @author Utilisateur
 * 
 */
public class FenetreFideliteClient extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel fideliteLabel;
	private JComboBox fidelite;
	private JLabel pointsFideliteLabel;
	private JTextField pointsFidelite;

	/**
	 * Constructeur de la classe FenetreFideliteClient qui permet de créer une
	 * fenetre avec des composants permettant de connaître les différentes
	 * informations concernant leur adhésion au magasin grâce à un JComboBox, un
	 * JTextField pour afficher les points et un bouton permettant d'afficher le
	 * montant du bon d'achat du client
	 * 
	 * @param parent
	 *            Appel éventuel à un JFrame pour construire la fenêtre
	 * 
	 * @param title
	 *            Titre de la fenetre
	 * 
	 * @param modal
	 *            Booléen indiquant si la fenêtre doit bloquer ou non les
	 *            interactions avec les autres fenêtres
	 * 
	 * 
	 *            {@link ihm.MenuUtilisateur}
	 * 
	 */
	public FenetreFideliteClient(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setSize(400, 500);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}

	/**
	 * <p>
	 * Initialisation des composants de la fenêtre :
	 * <ul>
	 * <li>le JPanel contenant le JComboBox sur l'état du compte du client qui
	 * peut être modifié si le client ne possède pas encore de compte adhérent.</li>
	 * <li>le JPanel contenant le JTextField affichant le nombre de points sur
	 * le compte fidélité.</li>
	 * <li>le JPanel contenant les boutons de confirmation ou d'annulation de
	 * création d'une carte de fidélité pour le client.</li>
	 * </ul>
	 * </p>
	 */
	private void initComponent() {

		// Création du panneau de gestion de la demande d'une carte de fidelité
		JPanel panFidelite = new JPanel();
		panFidelite.setBackground(Color.white);
		panFidelite.setPreferredSize(new Dimension(220, 80));
		fideliteLabel = new JLabel("Desirez-vous une carte de fidelité ? : ");
		panFidelite.add(fideliteLabel);

		// Récupération dans base de donnees des informations sur la fidélité
		ArrayList<String> fideliteClient = new ArrayList<String>();
		fideliteClient = SGBD
				.recupererInformationFideliteClient(FenetreDialogIdentification.clientUserIdentifiant);
		final String estFidele = fideliteClient.get(0);
		int nbPoints;

		if (estFidele.equals("Non")) {
			nbPoints = 0;
			FenetreDialogCreationCompte.itemFidelite = "Non";
		} else {
			FenetreDialogCreationCompte.itemFidelite = "Oui";
			nbPoints = Integer.parseInt(SGBD.selectStringConditionString(
					"CARTE_FIDELITE", "NBPOINTS", "IDCLIENT",
					FenetreDialogIdentification.clientUserIdentifiant)); // récupération
																			// du
																			// nombre
																			// de
																			// points
																			// du
																			// client


		}

		// Création du menu déroulant sur la demande de la carte de fidélité
		fidelite = new JComboBox();
		fidelite.addItem("Oui");
		fidelite.addItem("Non");
		fidelite.setSelectedItem(FenetreDialogCreationCompte.itemFidelite);

		if (FenetreDialogCreationCompte.itemFidelite == "Oui") {
			// si la personne a répondu oui lors de la création de compte, on ne
			// lui permet pas de modifier
			// le contenu du menu déroulant
			fidelite.setEnabled(false);
		} else {
			// dans le cas contraire, il pourra le modifier
			fidelite.setEnabled(true);
		}

		fidelite.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// si la valeur du menu déroulant est modifiée, on change pour
				// l'individu la valeur de l'attribut du compte fidélité
				FenetreDialogCreationCompte.itemFidelite = (String) ((JComboBox) e
						.getSource()).getSelectedItem();

				if (FenetreDialogCreationCompte.itemFidelite == "Oui") {
					// si la personne a répondu oui lors de la création de
					// compte, on ne lui permet pas de modifier
					// le contenu du menu déroulant
					fidelite.setEnabled(false);
				} else {
					// dans le cas contraire, il pourra le modifier
					fidelite.setEnabled(true);
				}
			}
		});

		fidelite.setVisible(true);
		panFidelite.add(fidelite);

		// Création d'un panneau permettant l'affichage du nombre de points
		JPanel panPointsFidelite = new JPanel();
		panPointsFidelite.setBackground(Color.white);
		panPointsFidelite.setPreferredSize(new Dimension(220, 80));
		pointsFideliteLabel = new JLabel("Nombre de points fidelité :");
		final int points = nbPoints;
		pointsFidelite = new JTextField("" + points + "");
		pointsFidelite.setPreferredSize(new Dimension(90, 25));
		pointsFidelite.setVisible(true);
		pointsFidelite.setEnabled(false);

		panPointsFidelite.add(pointsFideliteLabel);
		panPointsFidelite.add(pointsFidelite);

		// Bouton affichage des bons d'achat disponible
		JButton boutonAffichageBons = new JButton(
				"Afficher les bons d'achat disponibles");
		boutonAffichageBons.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Gestion des bons achat du client selon le nombre de points
				// présents
				// sur sa carte de fidelite

				int bonAchat = CarteFidelite.calculerBonsReductions(points);

				ImageIcon imageInformation = new ImageIcon(
						"src/images/information.jpg");
				JOptionPane.showMessageDialog(null,
						"Vous disposez d'un bon d'achat de " + bonAchat + " €",
						"Information sur les bons d'achat",
						JOptionPane.INFORMATION_MESSAGE, imageInformation);

			}
		});

		// Création du panneau central qui accueillera les panneaux crées
		// ci-dessus et qui seront affichés au centre
		// de la fenêtre
		JPanel panneauCentral = new JPanel();
		panneauCentral.add(panFidelite);
		panneauCentral.add(panPointsFidelite);
		panneauCentral.add(boutonAffichageBons);

		// Définition du panneau panneauBoutons qui va accueillir les boutons de
		// confirmation et d'annulation
		JPanel panneauBoutons = new JPanel();

		// Définition du bouton Confirmer qui permet de confirmer la création
		// d'une nouvelle carte de fidélité
		JButton validationBouton = new JButton("Confirmer");
		validationBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Modification du programme fidelité
				// (par ex : passage de non à oui,on ne peut pas passer de oui à
				// non a priori)
				// et retour au menu utilisateur

				if (estFidele.equals("Non")
						& FenetreDialogCreationCompte.itemFidelite
								.equals("Oui")) {
					new CarteFidelite(
							FenetreDialogIdentification.clientUserIdentifiant,
							0);
				}

				dispose();
			}
		});
		
		String nomBouton = "Annuler";
		if(estFidele.equals("Oui")){
			nomBouton = "Retour";
		}
		
		JButton annulationBouton = new JButton(nomBouton);
		annulationBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// Ajout des boutons au panneau panneauBoutons
		panneauBoutons.add(validationBouton);
		panneauBoutons.add(annulationBouton);
		
		// Si le client est adhérent, on ne lui affiche que le bouton retour
		// car il ne modifie aucun paramètre sur son compte donc le bouton
		// confirmer n'a plus aucun intérêt
		if(estFidele.equals("Oui")){
			validationBouton.setVisible(false);
		}
		
		this.getContentPane().add(panneauCentral, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
	}
}
