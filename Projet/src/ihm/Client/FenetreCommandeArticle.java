package ihm.Client;

import ihm.Accueil.FenetreDialogIdentification;
import ihm.modeleTableau.ModelePanier;
import ihm.modeleTableau.ModeleTableauCatalogue;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import metier.*;
import basededonnees.SGBD;

/**
 * La classe FenetreCommandeArticle affiche la liste des articles et le contenu
 * du panier du client
 * 
 */
public class FenetreCommandeArticle extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel catalogueLabel;
	private JLabel panierLabel;
	public static ArrayList<String[]> panierClient = Commande.preparerPanier();

	/**
	 * Entier static permettant de conserver la derniere ligne modifi�e du
	 * catalogue en cas d'ajout d'article au panier
	 */
	public static int ligneCatalogue;

	/**
	 * <p>
	 * Entier static permettant de :
	 * <ul>
	 * <li>conserver la derni�re ligne modifi�e du panier en cas de retrait
	 * d'article du panier
	 * <li>g�rer le probl�me si un client veut retirer un article de son panier
	 * sans avoir s�lectionn� sa ligne correspondante dans le tableau</li>
	 * </ul>
	 * </p>
	 */
	public static int lignePanier = -1;

	/**
	 * Bool�en static permettant de g�rer le probl�me si un client a retir�
	 * juste avant une certaine quantit� d'un article et qu'il souhaite en
	 * retirer une partie sans r�actualiser le tableau panier
	 */
	public static boolean retraitPanierPossible = false;

	/**
	 * Bool�en static permettant de g�rer le fait que quand un client ouvre
	 * cette fen�tre, il n'a pas encore choisi d'article donc on met ce bool�en
	 * � false et on le passera � true s'il s�lectionne correctement une ligne
	 * du catalogue
	 */
	public static boolean activationLigneCatalogue = false;

	/**
	 * Bool�en static permettant de g�rer le probl�me si g�re un client oublie
	 * de rafra�chir le panier apr�s avoir fait la selection d'un article
	 */
	public static boolean avoirRafraichiApresAjoutPanier = false;

	/**
	 * Bool�en static permettant de savoir si le client va utiliser son bon
	 * d'achat pour cette commande
	 */
	private static boolean utilisationBonReduction = false;

	/**
	 * Entier static permettant de g�rer le montant du bon d'achat que poss�de
	 * un client
	 */
	static int bonAchat = 0;

	/**
	 * D�finition du constructeur de la classe qui va initialiser la fen�tre
	 * selon les instructions de la m�thode
	 * {@link FenetreCommandeArticle#initComponent()}. Cette classe permet
	 * l'affichage simultan� du catalogue et du panier du client.
	 * 
	 * @param parent
	 *            JFrame utilis� pour cr�er la fen�tre
	 * @param modal
	 *            Bool�en indiquant si la fen�tre doit bloquer ou non les
	 *            interactions avec les autres fen�tres
	 */
	public FenetreCommandeArticle(JFrame parent, boolean modal) {
		super(parent, "Catalogue Article", true);
		this.setSize(500, 1000);
		this.setLocation(50, 50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();

	}

	/**
	 * <p>
	 * Initialisation des composants de la fen�tre de commande d'articles :
	 * <ul>
	 * <li>Un JPanel en haut de la fen�tre qui contiendra les intitul�s des
	 * tableaux catalogue/panier ainsi que le JCheckBox pour pouvoir utiliser un
	 * bon d'achat.</li>
	 * <li>un JPanel � gauche qui accueillera le tableau avec le catalogue des
	 * articles en stock.</li>
	 * <li>un JPanel � droite qui accueillera le tableau avec le panier courant
	 * des articles command�s.</li>
	 * <li>un JPanel en bas de la fen�tre qui accueillera les boutons ajout et
	 * retirer un article du panier, rafraichir le panier, valider la commande
	 * et retour au menu utilisateur.</li>
	 * <ul>
	 * </p>
	 */
	private void initComponent() {

		// D�finition du JPanel qui ne contiendra que ----//
		// l'affichage de part et d'autre de la fenetre --//
		// des intitul�s des tableaux catalogue et panier //
		// -----------------------------------------------//
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BorderLayout());

		JPanel panneauCatalogue = new JPanel();
		catalogueLabel = new JLabel("CATALOGUE");
		panneauCatalogue.add(catalogueLabel);
		panneauHaut.add(panneauCatalogue, "West");

		// Affichage d'un JPanel contenant un bouton � cocher uniquement pour //
		// les adh�rents afin de leur demander s'ils veulent utiliser leur ---//
		// ------------------- bon de r�duction ------------------------------//
		//--------------------------------------------------------------------//
		JPanel panneauUtilisationBonsReduction = new JPanel();

		// R�cup�ration des informations concernant la fid�lit� du client //
		// ---------------------------------------------------------------//
		final ArrayList<String> fideliteClient = SGBD
				.recupererInformationFideliteClient(FenetreDialogIdentification.clientUserIdentifiant);

		// Si le client est adh�rent, nous calculons le montant de son bon de
		// r�duction
		if (fideliteClient.get(0).equals("Oui")) {
			int nbPointsCarte = Integer.parseInt(fideliteClient.get(1));
			bonAchat = CarteFidelite.calculerBonsReductions(nbPointsCarte);
		}

		// D�finition du JCheckBox pour leur demander s'ils veulent utiliser
		// leur bon de r�duction
		JCheckBox utiliseBonAchat = new JCheckBox(
				"Cochez si vous voulez utiliser votre bon de r�duction de "
						+ bonAchat + " �");
		utiliseBonAchat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				utilisationBonReduction = ((JCheckBox) e.getSource())
						.isSelected();
			}
		});

		// Si le client est adh�rent, nous lui affichons le bouton � cocher dans
		// sa fen�tre
		if (fideliteClient.get(0).equals("Oui") & bonAchat > 0) {
			panneauUtilisationBonsReduction.add(utiliseBonAchat);
		}

		panneauHaut.add(panneauUtilisationBonsReduction, "Center");

		// D�finition d'un JPanel contenant le texte Panier et qui //
		// sera situ� au dessus du tableau du Panier dans la fen�tre //
		// -----------------------------------------------------------//
		JPanel panneauPanier = new JPanel();
		panierLabel = new JLabel("PANIER");
		panneauPanier.add(panierLabel);

		panneauHaut.add(panneauPanier, "East");

		// Ajout du JPanel au conteneur en haut de la fenetre //
		//----------------------------------------------------//
		this.getContentPane().add(panneauHaut, BorderLayout.NORTH);

		// D�finition du JPanel qui accueillera le catalogue //
		// --------------------------------------------------//
		JPanel panneauTableauCatalogue = new JPanel();
		panneauTableauCatalogue.setLayout(new GridLayout(1, 2, 5, 5));

		final JTable tableau = new JTable(new ModeleTableauCatalogue(false,
				false));
		panneauTableauCatalogue.add(new JScrollPane(tableau), "North");

		this.getContentPane().add(panneauTableauCatalogue, BorderLayout.WEST);

		// D�finition du JPanel qui accueillera les articles du panier //
		// ------------------------------------------------------------//
		final ModelePanier modPan = new ModelePanier(panierClient);
		final JTable panier = new JTable(modPan);
		this.getContentPane().add(new JScrollPane(panier), BorderLayout.EAST);

		// D�finition du JPanel des boutons permettant la confirmation ou //
		// ---------- l'annulation de la commande en cours ---------------//
		// ---------------------------------------------------------------//
		JPanel panneauBouton = new JPanel();

		// D�finition de l'action du bouton Choisir un Article  //
		// qui ouvre une fen�tre de choix de la quantit� que ---//
		// l'on souhaite acheter de l'article ------------------//
		// -----------------------------------------------------//
		JButton commanderArticle = new JButton("Choisir un article");
		commanderArticle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// R�cup�ration du num�ro d'article s�lectionn� � partir de la
				// ligne s�lectionn�e
				ligneCatalogue = tableau.getSelectedRow();
				Object numeroArticle = tableau.getValueAt(ligneCatalogue, 0);
				String numArticle = numeroArticle.toString();

				// R�cup�ration de la position de l'article dans le panier afin
				// de r�cup�rer plus facilement sa quantit� actuellement
				// command�e
				int indiceQuantitePanier = Commande.rechercheArticleDansPanier(
						numArticle, panierClient);
				int quantitePanier = Integer.parseInt(panierClient
						.get(indiceQuantitePanier)[1]);

				// R�cup�ration de la quantit� actuellement en stock
				String quantiteStock = SGBD.selectStringConditionString(
						"ARTICLE", "STOCK", "IDARTICLE", numArticle);

				// En param�tre, nous avons veill� � ce que la quantit� qui sera
				// affich�e dans le JComboBox soit celle en stock moins celle
				//  actuellement command�e pour �viter les erreurs
				FenetreChoixCatalogue fen = new FenetreChoixCatalogue(null,
						"Achat d'article", true, Integer
								.parseInt(quantiteStock) - quantitePanier,
						numArticle);
				fen.setVisible(true);
			}
		});

		/**
		 * <p>
		 * Dans le bouton Valider, nous allons effectuer successivement les
		 * actions suivantes :
		 * <ul>
		 * <li>Cr�ation du panier final qui ne contient que les articles
		 * command�s en quantit� non nulles</li>
		 * <li>Enregistrement de la commande dans la table COMMANDE</li>
		 * <li>Calcul du montant de la facture en tenant compte du fait qu'un
		 * client puisse utiliser ses points de fid�lit� pour les adh�rents</li>
		 * <li>Modification de la base de donn�es en fonction des quantit�s et
		 * articles achet�s</li>
		 * <li>Mise � jour de la carte fid�lit� pour les adh�rents</li>
		 * <li>Affichage de la facture</li>
		 * <li>Vidage du panier</li>
		 * </ul>
		 * </p>
		 */
		JButton boutonValider = new JButton("Valider");

		boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<LigneCommande> listeArticlesPanier = new ArrayList<LigneCommande>();

				if (retraitPanierPossible == true
						&& avoirRafraichiApresAjoutPanier == true) {

					int res = JOptionPane.showConfirmDialog(null,
							"Confirmer votre commande et afficher la facture",
							"Confirmation", JOptionPane.YES_NO_OPTION);
					if (res == JOptionPane.OK_OPTION) {
						// Nous mettons dans la liste des articles que ceux dont
						// la quantit� command�e est sup�rieure � 0
						for (String[] article : panierClient) {
							if (Integer.parseInt(article[1]) > 0) {
								listeArticlesPanier.add(new LigneCommande(
										article[0], Integer
												.parseInt(article[1])));
							}
						}

						if (listeArticlesPanier.size() > 0) {
							// Enregistrement de la commande si la liste des
							// articles command�s n'est pas vide
							java.util.Date date = new java.util.Date();
							@SuppressWarnings("deprecation")
							java.sql.Date dateJour = new java.sql.Date(date
									.getYear(), date.getMonth(), date.getDate());
							Commande nouvelleCommande = new Commande(
									null,
									FenetreDialogIdentification.clientUserIdentifiant,
									listeArticlesPanier, dateJour);

							try {
								// Calcul du montant la commande
								double montantCommande = nouvelleCommande
										.montantTotalArticle(
												listeArticlesPanier,
												FenetreDialogIdentification.clientUserIdentifiant);

								// Traitement du cas o� l'utilisateur a utilis�
								// son bon d'achat
								if (utilisationBonReduction == true) {

									if ((Math.round(montantCommande) - bonAchat) < 0) {
										// Si le montant du bon d'achat est
										// sup�rieur au montant de le commande
										// alors on enregistre un montant de
										// commande nulle dans la base de
										// donn�es
										nouvelleCommande.majMontantCommande(0);
									} else {
										// Si le montant du bon d'achat est
										// inf�rieur au montant de le commande
										// alors on enregistre le montant de la
										// commande auquel on retire le
										// montant du bon d'achat
										nouvelleCommande
												.majMontantCommande((int) Math
														.round(montantCommande)
														- bonAchat);
									}
									int nbPointsAvant = Integer
											.parseInt(fideliteClient.get(1));
									int pointsDepenses = CarteFidelite
											.calculerNombreDePoints(bonAchat);

									// Nous retirons le nombre de points
									// utilis�s sur la carte de fid�lit�
									CarteFidelite
											.modifierBDDcarteFidelite(
													FenetreDialogIdentification.clientUserIdentifiant,
													nbPointsAvant
															- pointsDepenses);
								} else {
									// Si le client n'a pas utilis� de bon
									// d'achat, nous enregistrons le montant
									// calcul� de la commande
									nouvelleCommande
											.majMontantCommande((int) Math
													.round(montantCommande));
								}

								// Mise � jour du nombre de points sur la carte
								// en fonction du montant de la commande
								if (fideliteClient.get(0).equals("Oui")) {

									int nbPointsAvant = Integer
											.parseInt(fideliteClient.get(1));
									int pointsRecoltes = (int) Math
											.round(montantCommande);
									
									// Traitement du cas o� l'utilisateur a utilis�
									// son bon achat. Dans ce cas, nous lui retirons 
									// de sa carte fid�lit� le nombre de points 
									// associ� au bon d'achat utilis�.
									if (utilisationBonReduction == true) {
										int pointsDepenses = CarteFidelite
												.calculerNombreDePoints(bonAchat);

										CarteFidelite
												.modifierBDDcarteFidelite(
														FenetreDialogIdentification.clientUserIdentifiant,
														nbPointsAvant
																- pointsDepenses
																+ pointsRecoltes);
									} else {

										CarteFidelite
												.modifierBDDcarteFidelite(
														FenetreDialogIdentification.clientUserIdentifiant,
														nbPointsAvant
																+ pointsRecoltes);
									}

								}

								// Affichage de la facture
								int pointsDepenses = CarteFidelite
										.calculerNombreDePoints(bonAchat);
								FenetreFactureCommande fenetre = new FenetreFactureCommande(
										null,
										"Facture",
										true,
										FenetreDialogIdentification.clientUserIdentifiant,
										nouvelleCommande, listeArticlesPanier,
										pointsDepenses, utilisationBonReduction);
								fenetre.setVisible(true);

								// Fermeture de la fen�tre du catalogue
								dispose();
								bonAchat = 0;

							} catch (SQLException e1) {
								System.out.println(e1.getMessage());
							}

						} else {

							ImageIcon image = new ImageIcon(
									"Ressources/images/warning.png");
							JOptionPane
									.showMessageDialog(
											null,
											" Aucun article n'a �t� s�lectionn� dans la commande.",
											"Attention",
											JOptionPane.WARNING_MESSAGE, image);
							// Affichage d'un message d'erreur en cas d'essai de
							// validation sans aucun article s�lectionn�
						}

						// Vidage du panier Client
						Commande.viderPanier(panierClient);
						setVisible(false);
					}

				} else {
					ImageIcon image = new ImageIcon("Ressources/images/warning.png");
					JOptionPane
							.showMessageDialog(
									null,
									" Veuillez rafraichir le panier avant de valider votre commande !!!",
									"Attention", JOptionPane.WARNING_MESSAGE,
									image);
					// Affichage d'un message d'erreur en cas de tentative de
					// validation de la commande sans avoir effectu� le
					// rafra�chissement du panier
				}
			}
		});

		// D�finition de l'action du bouton Retour � la page pr�c�dente //
		// --------------------------------------------------------------//
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Le bouton retour permet l'annulation de la commande en cours
				// et le retour au menu utilisateur
				dispose();
				// Vider le panier une fois que le client a appuy� sur le bouton
				Commande.viderPanier(panierClient);
			}
		});

		// D�finition de l'action du bouton Retirer un article du panier qui //
		// ouvre une fen�tre permettant de s�lectionner la quantit� que l'on //
		// souhaite retirer d'un article s�lectionn� dans le tableau Panier //
		// ------------------------------------------------------------------//
		final JButton retirerPanierBouton = new JButton(
				"Retirer un article du panier");

		retirerPanierBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lignePanier = panier.getSelectedRow();

				if (retraitPanierPossible == false) {
					ImageIcon image = new ImageIcon("Ressources/images/warning.png");
					JOptionPane.showMessageDialog(null,
							" Veuillez rafraichir le panier !!!", "Attention",
							JOptionPane.WARNING_MESSAGE, image);
					// Affichage d'un message d'erreur en cas de tentative de
					// retrait d'un article sans avoir rafraichi le panier apr�s
					// avoir d�j� effectu� un retrait d'une certaine quantit� 
					// de ce m�me article 
				}

				if (lignePanier == -1) {
					ImageIcon image = new ImageIcon("Ressources/images/warning.png");
					JOptionPane
							.showMessageDialog(
									null,
									" Veuillez selectionner une ligne article dans le panier",
									"Attention", JOptionPane.WARNING_MESSAGE,
									image);
					// Affichage d'un message d'erreur en cas de tentative de
					// retrait d'article sans avoir s�lectionn� d'article
				}

				if (avoirRafraichiApresAjoutPanier == false) {
					ImageIcon image = new ImageIcon("Ressources/images/warning.png");
					JOptionPane.showMessageDialog(null,
							" Veuillez rafraichir le panier !!!", "Attention",
							JOptionPane.WARNING_MESSAGE, image);
					// Affichage d'un message d'erreur en cas de tentative de
					// retrait d'article du panier sans avoir rafra�chi
					// le panier apr�s avoir effectu� un ajout d'article au
					// panier
				}

				// ligne Panier <> - 1 g�re le pb si un client veut retirer 
				// un article de son panier sans l'avoir s�lectionn� dans le
				// tableau

				// retraitPanierPossible == true g�re le probl�me si un client a
				// retir� juste avant une certaine quantit� d'un article et
				// qu'il souhaite en retirer une partie sans r�actualiser
				// le tableau panier

				// avoirRafraichiApresAjoutPanier==false g�re le probl�me si un
				// client oublie de rafra�chir le panier apr�s avoir fait
				// la s�lection d'un article

				if (lignePanier != -1 && retraitPanierPossible == true
						&& avoirRafraichiApresAjoutPanier == true) {

					// R�cup�ration du num�ro de l'article
					Object numeroArticle = panier.getValueAt(lignePanier, 0);
					String numArticle = numeroArticle.toString();

					String quantitePanier = panier.getValueAt(lignePanier, 1)
							.toString();

					// Ouverture de la fen�tre
					FenetreSuppressionPanier fenetreRetrait = new FenetreSuppressionPanier(
							null, "Retrait d'article du panier", true,
							numArticle, Integer.parseInt(quantitePanier));
					fenetreRetrait.setVisible(true);
				}

			}
		});

		// Tant qu'on a pas r�actualis� le panier initial qui est vide, on ne
		// peut pas utiliser le bouton de retrait d'article
		retirerPanierBouton.setEnabled(false);

		// D�finition de l'action du bouton Rafra�chir le panier //
		// ------------------------------------------------------//
		JButton rafraichirPanierBouton = new JButton("Rafraichir le panier");
		rafraichirPanierBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Le bouton permet de rafraichir le tableau panier
				modPan.actualiserLignes(panierClient);
				modPan.fireTableDataChanged();

				// Condition n�cessaire pour g�rer le fait qu'un client ne
				// puisse s�lectionner
				// aucune ligne dans le catalogue avant d'appuyer sur rafra�chir
				if (activationLigneCatalogue == true) {

					retirerPanierBouton.setEnabled(true);
					retraitPanierPossible = true;
					avoirRafraichiApresAjoutPanier = true;

				} else {

					ImageIcon image = new ImageIcon("Ressources/images/warning.png");
					JOptionPane
							.showMessageDialog(
									null,
									" Veuillez selectionner une ligne article dans le catalogue et appuyez sur le bouton Choisir Article",
									"Attention", JOptionPane.WARNING_MESSAGE,
									image);
				}

			}
		});

		// Ajout des boutons au JPanel des boutons panneauBouton //
		// ------------------------------------------------------//
		panneauBouton.add(commanderArticle);
		panneauBouton.add(boutonValider);
		panneauBouton.add(retourBouton);
		panneauBouton.add(rafraichirPanierBouton);
		panneauBouton.add(retirerPanierBouton);

		// Ajout du JPanel des boutons au conteneur de la fen�tre //
		// -------------------------------------------------------//
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);

		pack();

	}

}
