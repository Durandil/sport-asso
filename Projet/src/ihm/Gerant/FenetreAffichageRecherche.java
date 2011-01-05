package ihm.Gerant;

import ihm.modeleTableau.ModeleTableauClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * <b> FenetreAffichageRecherche affiche les r�sultats d'une recherche de
 * clients par le g�rant </b>
 * <p>
 * Les clients affich�s correspondent � la recherche d'un g�rant selon plusieurs
 * crit�res :
 * <ul>
 * <li>l'identifiant du client</li>
 * <li>le nom pour les particuliers ou la d�nomination pour les collectivit�s</li>
 * <li>la ville</li>
 * </ul>
 * </p>
 * 
 * 
 * @see FenetreAffichageRecherche#FenetreAffichageRecherche(JFrame, String,
 *      boolean, String, String, String, String)
 * @see ModeleTableauClient
 */
public class FenetreAffichageRecherche extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * <b> Constructeur de la fen�tre d'affichage des r�sultats d'une recherche
	 * de clients par le g�rant. </b>
	 * 
	 * <p>
	 * Elle sera construite avec la m�thode initComponent.
	 * </p>
	 * 
	 * @param parent
	 *            JFrame utilis� pour cr�er la fen�tre
	 * @param title
	 *            String indiquant le titre de la fen�tre
	 * @param modal
	 *            Bool�en indiquant si la fen�tre doit bloquer ou non les
	 *            interactions avec les autres fen�tres
	 * @param idClient
	 *            String saisi dans le champ de recherche identifiant client
	 * @param nom
	 *            String saisi dans le champ de recherche nom client
	 * @param denom
	 *            String saisi dans le champ de recherche d�nomination client
	 * @param ville
	 *            String saisi dans le champ de recherche ville client
	 */
	public FenetreAffichageRecherche(JFrame parent, String title,
			boolean modal, String idClient, String nom, String denom,
			String ville) {
		super(parent, title, modal);
		this.setSize(700, 600);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.initComponent(idClient, nom, denom, ville);
	}

	/**
	 * <p>
	 * Initialisation des composants de la fen�tre avec :
	 * <ul>
	 * <li>un JPanel contenant un tableau avec la liste des clients
	 * correspondants � la recherche.</li>
	 * <li>un JPanel contenant deux boutons : l'un permettant d'acc�fer � la
	 * fiche d'un client apr�s l'avoir s�lectionn� et l'autre permettant de
	 * revenir � la page pr�c�dente.</li>
	 * 
	 * @param idClient
	 *            String saisi dans le champ de recherche identifiant client
	 * @param nom
	 *            String saisi dans le champ de recherche nom client
	 * @param denom
	 *            String saisi dans le champ de recherche d�nomination client
	 * @param ville
	 *            String saisi dans le champ de recherche ville client
	 * 
	 *            {@link FicheClient}
	 * 
	 *            {@link ModeleTableauClient }
	 */
	private void initComponent(String idClient, String nom, String denom,
			String ville) {

		// D�finition d'un panneau JPanel qui va accueillir le tableau des
		// r�sultats
		// de la recherche au moyen du constructeur de la classe
		// ModeleTableauClient

		JPanel panneauCentral = new JPanel();
		final ModeleTableauClient modele = new ModeleTableauClient(idClient,
				nom, denom, ville);
		final JTable tableauRechercheClient = new JTable(modele);
		tableauRechercheClient.setVisible(true);

		// Dans le cas o� le tableau ne contient aucune ligne,c'est � dire
		// qu'aucun client
		// ne correspond � la recherche effectu�e, on lui affiche un message
		// pour le lui
		// signifier
		if (modele.getRowCount() == 0) {
			dispose();
			JOptionPane
					.showMessageDialog(
							null,
							"Aucun client ne correspond aux crit�res de votre recherche !",
							"Attention", JOptionPane.ERROR_MESSAGE);
		}

		panneauCentral.add(new JScrollPane(tableauRechercheClient));

		JPanel panneauBouton = new JPanel();

		// D�finition du premier bouton de la fen�tre qui permet l'acc�s � une
		// fiche client
		//
		JButton validationRecherche = new JButton("Acc�s client");
		validationRecherche.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// nous recup�rons le num�ro de la ligne s�lectionn�e par le
				// g�rant dans
				// le tableau
				int ligne = tableauRechercheClient.getSelectedRow();

				// si aucune ligne est s�lectionn�e, alors nous affichons un
				// message pour lui
				// signifier l'erreur
				if (ligne == -1) {

					JOptionPane
							.showMessageDialog(
									null,
									"Aucune ligne s�lectionn�e. Veuillez en s�lectionner une",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} else {
					// R�cup�ration de l'identifiant du client dans la premi�re
					// colonne du tableau
					String identifiant = tableauRechercheClient.getValueAt(
							ligne, 0).toString();

					// Affichage d'une boite de dialogue pour confirmer le
					// client selectionn�
					int res = JOptionPane
							.showConfirmDialog(null,
									"confirmez la s�lection client de : "
											+ identifiant);

					if (res == JOptionPane.OK_OPTION) {
						dispose();
						// on affiche la fiche client correspondante �
						// l'identifiant saisi
						FicheClient ficheDuClient = new FicheClient(null,
								"Fiche du client : " + identifiant, true,
								identifiant);
						ficheDuClient.setVisible(true);

					}

				}
			}

		});

		// D�finition du bouton de retour et de l'action sp�cifique de celui-ci
		// //
		// ----------------------------------------------------------------------//
		JButton retourRecherche = new JButton("Retour");
		retourRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// Ajout des 2 boutons au JPanel destin�s � les accueillir //
		// ---------------------------------------------------------//
		panneauBouton.add(validationRecherche);
		panneauBouton.add(retourRecherche);

		// Ajout des 2 principaux JPanel au conteneur de la fen�tre
		this.getContentPane().add(panneauCentral, "Center");
		this.getContentPane().add(panneauBouton, "South");

	}

}
