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
 * <b> Cette classe permet au g�rant de g�rer son catalogue d'article. </b>
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
	 * <b> Cr�ation du constructeur de la classe FenetreCatalogueGerant. </b>
	 * <p>
	 * La fen�tre sera cr��e selon les instructions de la m�thode
	 * initComponent().
	 * </p>
	 * 
	 * @param parent
	 *            JFrame utilis� pour cr�er la fen�tre
	 * @param modal
	 *            Bool�en indiquant si la fen�tre doit bloquer ou non les
	 *            interactions avec les autres fen�tres
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
	 * Initialisation des composants de la fen�tre :
	 * <ul>
	 * <li>un JPanel accueillant une image situ�e en haut de la fen�tre.</li>
	 * <li>un JPanel accueillant les boutons qui permettent de g�rer le
	 * catalogue d'article (Ajouter, modifier et supprimer un article).</li>
	 * <li>un JPanel accueillant un tableau avec la liste des articles
	 * disponibles.</li>
	 * <li>un JPanel accueillant le bouton de retour � la page pr�c�dente.</li>
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

		// R�cup�ration et ajout de l'image sur un JPanel //
		// -----------------------------------------------//
		image = new JLabel(new ImageIcon("Ressources/images/catalogue.jpg"));
		final JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.add(image);

		// D�finition du tableau qui accueillera l'ensemble des articles disponibles //
		// ------------ apr�s interrogation de la base de donn�es -------------------//
		// --------------------------------------------------------------------------//

		modTabCatalogue = new ModeleTableauCatalogue(false, true);
		tableau = new JTable(modTabCatalogue);

		final JScrollPane tab = new JScrollPane(tableau);
		panneauTableau.add(tab);

		// Cr�ation du panneau qui accueillera les boutons du haut permettant //
		// ----------------- la gestion des articles -------------------------//
		// -------------------------------------------------------------------//

		final JPanel panneauBoutonHaut = new JPanel();
		panneauBoutonHaut.setLayout(new GridLayout(1, 4, 5, 5));

		// D�finition du bouton permettant d'ajouter un article au catalogue //
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

		// D�finition du bouton permettant de supprimer un article du catalogue //
		// ---------------------------------------------------------------------//

		// Nous v�rifions d'abord qu'une ligne a bien �t� selectionn�e et nous
		// demandons l'accord du g�rant avant de supprimer l'article de la base

		JButton boutonSupprimer = new JButton("Supprimer");
		boutonSupprimer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// R�cup�ration du num�ro de ligne s�lectionn�e
				int ligne = tableau.getSelectedRow();

				// Traitement du cas o� aucune ligne n'est s�lectionn�e dans le
				// tableau
				if (ligne == -1) {

					JOptionPane
							.showMessageDialog(
									null,
									"Aucune ligne s�lectionn�e. Veuillez en s�lectionner une",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} else {
					// R�cup�ration de l'identifiant de l'article s�lectionn�
					// dans le tableau
					String numArticle = tableau.getValueAt(ligne, 0).toString();

					// Affichage d'une fen�tre pour demander la confirmation de
					// la suppression de l'article
					int res = JOptionPane.showConfirmDialog(null,
							"Confirmer_vous la suppression de l'article "
									+ numArticle + " ? ", "Confirmation",
							JOptionPane.YES_NO_OPTION);

					if (res == JOptionPane.OK_OPTION) {
						// Suppression de l'article s�lectionn� dans le
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

		// D�finition du bouton permettant de modifier un article du catalogue //
		// --------------------------------------------------------------------//
		JButton boutonModifier = new JButton("Modifier");
		boutonModifier.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// R�cup�ration du num�ro de ligne s�lectionn�e
				int ligne = tableau.getSelectedRow();
				String numArticle = "";

				// Traitement du cas o� aucune ligne n'est s�lectionn�e dans le
				// tableau
				if (ligne == -1) {

					JOptionPane
							.showMessageDialog(
									null,
									"Aucune ligne s�lectionn�e. Veuillez en s�lectionner une",
									"Attention", JOptionPane.ERROR_MESSAGE);
				} else {
					// R�cup�ration de l'identifiant de l'article s�lectionn�
					// dans le tableau
					numArticle = tableau.getValueAt(ligne, 0).toString();

					// Ouverture du formulaire pour modifier l'article
					// s�lectionn�
					FenetreFormulaireArticleGerant formulaire = new FenetreFormulaireArticleGerant(
							null, "Modifier l'article " + numArticle, true,
							numArticle);
					formulaire.setVisible(true);

					// Fermeture de la fen�tre apr�s l'ouverture du formulaire
					dispose();

				}

			}

		});

		// Ajout des boutons de gestion des articles sur ce JPanelpanneauBoutonHaut //
		// -------------------------------------------------------------------------//
		panneauBoutonHaut.add(boutonAjouter);
		panneauBoutonHaut.add(boutonModifier);
		panneauBoutonHaut.add(boutonSupprimer);

		// Insertion de l'image et des boutons de gestion sur un m�me JPanel panneauHaut //
		// -----------------------------------------------------------------------------//
		panneauHaut.add(panIcon);
		panneauHaut.add(panneauBoutonHaut);

		// D�finition du panneau qui contiendra le bouton de retour � la page pr�c�dente //
		// ------------------------------------------------------------------------------//
		JPanel panneauBouton = new JPanel();
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panneauBouton.add(retourBouton);

		// Ajout de tous les composants au conteneur de la fen�tre //
		// --------------------------------------------------------//
		this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
		this.getContentPane().add(panneauTableau, BorderLayout.CENTER);
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);

		pack();

	}

}
