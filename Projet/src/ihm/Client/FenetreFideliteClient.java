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
 * les d�tails de leur programme fid�lit� avec le magasin sport'asso :
 * <ul>
 * <li>leur adh�sion ou non au programme fid�lit�</li>
 * <li>leur nombre de points pour les adh�rents</li>
 * <li>le montant de leur bon de r�duction dans le magasin</li>
 * </ul>
 * </p>
 * 
 * 
 */
public class FenetreFideliteClient extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel fideliteLabel;
	private JComboBox fidelite;
	private JLabel pointsFideliteLabel;
	private JTextField pointsFidelite;

	/**
	 * Constructeur de la classe FenetreFideliteClient qui permet de cr�er une
	 * fen�tre avec des composants permettant de conna�tre les diff�rentes
	 * informations concernant leur adh�sion au magasin gr�ce � un JComboBox, un
	 * JTextField pour afficher les points et un bouton permettant d'afficher le
	 * montant du bon d'achat du client
	 * 
	 * @param parent
	 *            Appel �ventuel � un JFrame pour construire la fen�tre
	 * 
	 * @param title
	 *            Titre de la fenetre
	 * 
	 * @param modal
	 *            Bool�en indiquant si la fen�tre doit bloquer ou non les
	 *            interactions avec les autres fen�tres
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
	 * Initialisation des composants de la fen�tre :
	 * <ul>
	 * <li>le JPanel contenant le JComboBox sur l'�tat du compte fid�lit� du
	 * client qui peut �tre modifi� si le client ne poss�de pas encore de compte
	 * adh�rent.</li>
	 * <li>le JPanel contenant le JTextField affichant le nombre de points sur
	 * le compte fid�lit�.</li>
	 * <li>le JPanel contenant les boutons de confirmation ou d'annulation de
	 * cr�ation d'une carte de fid�lit� pour le client.</li>
	 * </ul>
	 * </p>
	 */
	private void initComponent() {

		// Cr�ation du JPanel de gestion de la //
		// demande d'une carte de fidelit� ----//
		// ------------------------------------//
		JPanel panFidelite = new JPanel();
		panFidelite.setBackground(Color.white);
		panFidelite.setPreferredSize(new Dimension(220, 80));
		fideliteLabel = new JLabel("Desirez-vous une carte de fidelit� ? : ");
		panFidelite.add(fideliteLabel);

		// R�cup�ration dans la base de donn�es // 
		// des informations sur la fid�lit� ----//
		// -------------------------------------//
		ArrayList<String> fideliteClient = new ArrayList<String>();
		fideliteClient = SGBD
				.recupererInformationFideliteClient(FenetreDialogIdentification.clientUserIdentifiant);
		final String estFidele = fideliteClient.get(0);
		int nbPoints;

		if (estFidele.equals("Non")) {
			// Si le client n'est pas adh�rent
			nbPoints = 0;
			FenetreDialogCreationCompte.itemFidelite = "Non";
		} else {
			// Si le client est adh�rent, r�cup�ration du nombre de points du
			// client
			FenetreDialogCreationCompte.itemFidelite = "Oui";

			nbPoints = Integer.parseInt(SGBD.selectStringConditionString(
					"CARTE_FIDELITE", "NBPOINTS", "IDCLIENT",
					FenetreDialogIdentification.clientUserIdentifiant));
		}

		// Cr�ation du menu d�roulant sur la demande de la carte de fid�lit� //
		// -------------------------------------------------------------------//
		fidelite = new JComboBox();
		fidelite.addItem("Oui");
		fidelite.addItem("Non");
		fidelite.setSelectedItem(FenetreDialogCreationCompte.itemFidelite);

		if (FenetreDialogCreationCompte.itemFidelite == "Oui") {
			// Si la personne a r�pondu oui lors de la cr�ation de compte,
			// nous ne lui permettons pas de modifier le contenu du menu
			// d�roulant
			fidelite.setEnabled(false);

		} else {

			// Dans le cas contraire, il pourra le modifier
			fidelite.setEnabled(true);

		}

		fidelite.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Si la valeur du menu d�roulant est modifi�e, nous changeons
				// pour l'individu la valeur de l'attribut du compte fid�lit�
				FenetreDialogCreationCompte.itemFidelite = (String) ((JComboBox) e
						.getSource()).getSelectedItem();

				if (FenetreDialogCreationCompte.itemFidelite == "Oui") {
					// Si la personne a r�pondu oui lors de la cr�ation de
					// compte, nous ne lui permettons pas de modifier
					// le contenu du menu d�roulant
					fidelite.setEnabled(false);
				} else {
					// Dans le cas contraire, il pourra le modifier
					fidelite.setEnabled(true);
				}
			}
		});

		fidelite.setVisible(true);
		panFidelite.add(fidelite);

		// Cr�ation d'un JPanel permettant l'affichage du nombre de points //
		// -----------------------------------------------------------------//
		JPanel panPointsFidelite = new JPanel();
		panPointsFidelite.setBackground(Color.white);
		panPointsFidelite.setPreferredSize(new Dimension(220, 80));
		pointsFideliteLabel = new JLabel("Nombre de points fidelit� :");
		final int points = nbPoints;
		pointsFidelite = new JTextField("" + points + "");
		pointsFidelite.setPreferredSize(new Dimension(90, 25));
		pointsFidelite.setVisible(true);
		pointsFidelite.setEnabled(false);

		panPointsFidelite.add(pointsFideliteLabel);
		panPointsFidelite.add(pointsFidelite);

		// D�finition du bouton d'affichage du bon d'achat disponible //
		// -----------------------------------------------------------//
		JButton boutonAffichageBons = new JButton(
				"Afficher les bons d'achat disponibles");
		boutonAffichageBons.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Gestion des bons achat du client selon le nombre de points
				// pr�sents sur sa carte de fidelite

				int bonAchat = CarteFidelite.calculerBonsReductions(points);

				ImageIcon imageInformation = new ImageIcon(
						"Ressources/images/information.jpg");
				JOptionPane.showMessageDialog(null,
						"Vous disposez d'un bon d'achat de " + bonAchat + " �",
						"Information sur les bons d'achat",
						JOptionPane.INFORMATION_MESSAGE, imageInformation);

			}
		});

		// Cr�ation du JPanel central qui accueillera les JPanel cr�es //
		// ci-dessus et qui seront affich�s au centre de la fen�tre ---//
		// ------------------------------------------------------------//
		JPanel panneauCentral = new JPanel();
		panneauCentral.add(panFidelite);
		panneauCentral.add(panPointsFidelite);
		panneauCentral.add(boutonAffichageBons);

		// D�finition du JPanel panneauBoutons qui va //
		// accueillir les boutons de confirmation et -//
		// ------------- d'annulation ----------------//
		// -------------------------------------------//
		JPanel panneauBoutons = new JPanel();

		// D�finition du bouton Confirmer qui permet de confirmer la cr�ation //
		// --------------- d'une nouvelle carte de fid�lit� ------------------//
		// --------------------------------------------------------------------//
		JButton validationBouton = new JButton("Confirmer");
		validationBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Modification du programme fidelit�
				// (par ex : passage de non � oui, on ne peut 
				// pas passer de oui � non a priori) 

				if (estFidele.equals("Non")
						& FenetreDialogCreationCompte.itemFidelite
								.equals("Oui")) {

					new CarteFidelite(
							FenetreDialogIdentification.clientUserIdentifiant,
							0);

				}

				// Et retour au menu utilisateur
				dispose();
			}
		});

		// D�finition de l'action du bouton annulation qui permet de fermer //
		// ----------------------- la fen�tre en cours ---------------------//
		// ------------------------------------------------------------------//

		String nomBouton = "Annuler";
		if (estFidele.equals("Oui")) {
			nomBouton = "Retour";
		}

		JButton annulationBouton = new JButton(nomBouton);
		annulationBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// Ajout des boutons au JPanel panneauBoutons //
		// -------------------------------------------//
		panneauBoutons.add(validationBouton);
		panneauBoutons.add(annulationBouton);

		// Si le client est adh�rent, nous ne lui affichons que le bouton retour
		// car il ne modifie aucun param�tre sur son compte donc le bouton
		// confirmer n'a plus aucun int�r�t
		if (estFidele.equals("Oui")) {
			validationBouton.setVisible(false);
		}

		// Ajout des principaux composants JPanel au conteneur de la fen�tre //
		// -------------------------------------------------------------------//
		this.getContentPane().add(panneauCentral, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
	}
}
