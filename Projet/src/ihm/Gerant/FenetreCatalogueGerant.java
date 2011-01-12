package ihm.Gerant;

import ihm.modeleTableau.ModeleTableauCatalogue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import metier.Article;

/**
 * <b> Cette classe permet au gérant de gérer son catalogue d'article. </b>
 * 
 * 
 * @see FenetreFormulaireArticleGerant
 */
public class FenetreCatalogueGerant extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel image;
	public ModeleTableauCatalogue modTabCatalogue = new ModeleTableauCatalogue();
	public JTable tableau = new JTable();
	public JPanel panneauTableau = new JPanel();

	/**
	 * <b> Création du constructeur de la classe FenetreCatalogueGerant. </b>
	 * <p>
	 * La fenêtre sera créée selon les instructions de la méthode
	 * initComponent().
	 * </p>
	 * 
	 * @param parent
	 *            JFrame utilisé pour créer la fenêtre
	 * @param modal
	 *            Booléen indiquant si la fenêtre doit bloquer ou non les
	 *            interactions avec les autres fenêtres
	 * 
	 */
	public FenetreCatalogueGerant(JFrame parent, boolean modal) {
		super(parent, "Gestion du Catalogue Article", modal);
		this.setSize(500, 700);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}

	/**
	 * <p>
	 * Initialisation des composants de la fenêtre :
	 * <ul>
	 * <li>un JPanel accueillant une image située en haut de la fenêtre.</li>
	 * <li>un JPanel accueillant les boutons qui permettent de gérer le
	 * catalogue d'article (Ajouter, modifier et supprimer un article).</li>
	 * <li>un JPanel accueillant un tableau avec la liste des articles
	 * disponibles.</li>
	 * <li>un JPanel accueillant le bouton de retour à la page précédente.</li>
	 * </ul>
	 * </p>
	 * 
	 * @see FenetreFormulaireArticleGerant
	 * @see ModeleTableauCatalogue
	 * @see ModeleTableauCatalogue#ModeleTableauCatalogue(boolean, boolean)
	 */
	private void initComponent() {

		final JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new GridLayout(2, 1, 5, 5));

		// Récupération et ajout de l'image sur un JPanel //
		// -----------------------------------------------//
		image = new JLabel(new ImageIcon("Ressources/images/catalogue.jpg"));
		final JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.add(image);

		// Définition du tableau qui accueillera l'ensemble des articles disponibles //
		// ------------ après interrogation de la base de données -------------------//
		// --------------------------------------------------------------------------//

		modTabCatalogue = new ModeleTableauCatalogue(false, true);
		tableau = new JTable(modTabCatalogue);

		final JScrollPane tab = new JScrollPane(tableau);
		panneauTableau.add(tab);

		// Création du panneau qui accueillera les boutons du haut permettant //
		// ----------------- la gestion des articles -------------------------//
		// -------------------------------------------------------------------//

		final JPanel panneauBoutonHaut = new JPanel();
		panneauBoutonHaut.setLayout(new GridLayout(1, 4, 5, 5));

		// Définition du bouton permettant d'ajouter un article au catalogue //
		// ------------------------------------------------------------------//

		// Dans ce cas, on construit une nouvelle fenetre
		// FenetreFormulaireArticleGerant

		final JButton boutonAjouter = new JButton("Ajouter");
		boutonAjouter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Ouverture du formulaire d'ajout d'un article dans la base de
				// donnees

				FenetreFormulaireArticleGerant formulaire = new FenetreFormulaireArticleGerant(
						null, "Ajout d'article", true);
				formulaire.setVisible(true);

				dispose();

			}
		});

		// Définition du bouton permettant de supprimer un article du catalogue //
		// ---------------------------------------------------------------------//

		// Nous vérifions d'abord qu'une ligne a bien été selectionnée et nous
		// demandons l'accord du gérant avant de supprimer l'article de la base

		JButton boutonSupprimer = new JButton("Supprimer");
		boutonSupprimer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Récupération du numéro de ligne sélectionnée
				int ligne = tableau.getSelectedRow();

				// Traitement du cas où aucune ligne n'est sélectionnée dans le
				// tableau
				if (ligne == -1) {

					JOptionPane
							.showMessageDialog(
									null,
									"Aucune ligne sélectionnée. Veuillez en sélectionner une",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} else {
					// Récupération de l'identifiant de l'article sélectionné
					// dans le tableau
					String numArticle = tableau.getValueAt(ligne, 0).toString();

					// Affichage d'une fenêtre pour demander la confirmation de
					// la suppression de l'article
					int res = JOptionPane.showConfirmDialog(null,
							"Confirmer_vous la suppression de l'article "
									+ numArticle + " ? ", "Confirmation",
							JOptionPane.YES_NO_OPTION);

					if (res == JOptionPane.OK_OPTION) {
						// Suppression de l'article sélectionné dans le
						// catalogue
						Article.supprimerArticleBDD(numArticle);
						dispose();

						FenetreCatalogueGerant fen = new FenetreCatalogueGerant(
								null, true);
						fen.setVisible(true);

					}
				}

			}
		});

		// Définition du bouton permettant de modifier un article du catalogue //
		// --------------------------------------------------------------------//
		JButton boutonModifier = new JButton("Modifier");
		boutonModifier.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Récupération du numéro de ligne sélectionnée
				int ligne = tableau.getSelectedRow();
				String numArticle = "";

				// Traitement du cas où aucune ligne n'est sélectionnée dans le
				// tableau
				if (ligne == -1) {

					JOptionPane
							.showMessageDialog(
									null,
									"Aucune ligne sélectionnée. Veuillez en sélectionner une",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} else {
					// Récupération de l'identifiant de l'article sélectionné
					// dans le tableau
					numArticle = tableau.getValueAt(ligne, 0).toString();

					// Ouverture du formulaire pour modifier l'article
					// sélectionné
					FenetreFormulaireArticleGerant formulaire = new FenetreFormulaireArticleGerant(
							null, "Modifier l'article " + numArticle, true,
							numArticle);
					formulaire.setVisible(true);

					// Fermeture de la fenêtre après l'ouverture du formulaire
					dispose();

				}

			}

		});

		// Ajout des boutons de gestion des articles sur ce JPanelpanneauBoutonHaut //
		// -------------------------------------------------------------------------//
		panneauBoutonHaut.add(boutonAjouter);
		panneauBoutonHaut.add(boutonModifier);
		panneauBoutonHaut.add(boutonSupprimer);

		// Insertion de l'image et des boutons de gestion sur un même JPanel panneauHaut //
		// -----------------------------------------------------------------------------//
		panneauHaut.add(panIcon);
		panneauHaut.add(panneauBoutonHaut);

		// Définition du panneau qui contiendra le bouton de retour à la page précédente //
		// ------------------------------------------------------------------------------//
		JPanel panneauBouton = new JPanel();
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panneauBouton.add(retourBouton);

		// Ajout de tous les composants au conteneur de la fenêtre //
		// --------------------------------------------------------//
		this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
		this.getContentPane().add(panneauTableau, BorderLayout.CENTER);
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);

		pack();

	}

}
