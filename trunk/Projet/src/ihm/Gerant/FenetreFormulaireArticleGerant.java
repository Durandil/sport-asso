package ihm.Gerant;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exception.ExceptionChampVide;
import exception.ExceptionExcesDeCaracteres;
import exception.Article.ExceptionPoidsAberrant;
import exception.Article.ExceptionPrixAberrant;
import exception.Article.ExceptionStockAberrant;

import basededonnees.SGBD;

import metier.Article;

/**
 * Cette classe devra permettre d'ouvrir un formulaire d'ajout ou de modification 
 * d'un article dans le catalogue pour le gérant.
 * 
 * @author Utilisateur
 *	
 * {@link FenetreCatalogueGerant}
 */
public class FenetreFormulaireArticleGerant extends JDialog{


	private static final long serialVersionUID = 1L;
	JLabel numArticleLabel, descriptionLabel, poidsLabel, catPrixLabel,
			catSportLabel, prixLabel, stockLabel;
	JTextField numArticle, description, poids, prix, stock;
	JComboBox catPrixBox, catSportBox;
	String itemSportSelectionne = "";
	String itemPrixSelectionne = "";
	Dimension dimensionStandard = new Dimension(220, 60);
	JPanel panDescription = new JPanel();
	JPanel panPoids = new JPanel();
	JPanel panCategoriePrix = new JPanel();
	JPanel panCategorieSport = new JPanel();
	JPanel panStock = new JPanel();
	JPanel panPrixInitial = new JPanel();

	/**
	 * Constructeur de la fenêtre du formulaire d'ajout d'un article dans la
	 * base de données Il est construit à partir de
	 * {@link FenetreFormulaireArticleGerant#initComponent()}.
	 * 
	 * @param parent
	 *            JFrame utilisé pour créer la fenêtre
	 * @param title
	 *            String indiquant le titre de la fenêtre
	 * @param modal
	 *            Booléen indiquant si la fenêtre doit bloquer ou non les
	 *            interactions avec les autres fenêtres
	 * 
	 */
	public FenetreFormulaireArticleGerant(JFrame parent, String title,
			boolean modal) {
		super(parent, title, modal);
		this.setSize(450, 600);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}

	//
	/**
	 * Constructeur de la fenêtre du formulaire de modification des champs d'un
	 * article. Il est construit à partir de
	 * {@link FenetreFormulaireArticleGerant#initComponent(String)}.
	 * 
	 * @param parent
	 *            JFrame utilisé pour créer la fenêtre
	 * @param title
	 *            String indiquant le titre de la fenêtre
	 * @param modal
	 *            Booléen indiquant si la fenêtre doit bloquer ou non les
	 *            interactions avec les autres fenêtres
	 * @param idArticle
	 *            Identifiant unique de l'article que l'on souhaite modifier
	 */
	public FenetreFormulaireArticleGerant(JFrame parent, String title,
			boolean modal, String idArticle) {
		super(parent, title, modal);
		this.setSize(450, 600);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(idArticle);
	}
	/**
	 * <p> Initialisation des composants dans le formulaire d'ajout d'un article :<ul>
	 * <li> un JPanel qui accueillir les sous-JPanels contenant les JTextField et
	 * JComboBox vides permettant l'ajout d'un nouvel article. </li>
	 * <li> un JPanel qui va accueillir les boutons permettant de valider les champs saisis
	 * et d'annuler les saisies des champs(fermeture de la fenêtre). </li>
	 * </ul>
	 * </p>
	 */
	private void initComponent() {
		JPanel panneauCentral = new JPanel();

		// Remplissage des JPanel qui vont accueillir les JTextField de saisie des   //
		// champs et les JComboBox afin que le gérant puisse choisir la catégorie et //
		// ------------- le type de sport auquel appartient l'article ---------------//
		panDescription.setPreferredSize(dimensionStandard);
		panPoids.setPreferredSize(dimensionStandard);
		panCategoriePrix.setPreferredSize(dimensionStandard);
		panCategorieSport.setPreferredSize(dimensionStandard);
		panStock.setPreferredSize(dimensionStandard);
		panPrixInitial.setPreferredSize(dimensionStandard);

		panDescription.setBorder(BorderFactory.createEmptyBorder());
		panPoids.setBorder(BorderFactory.createEmptyBorder());
		panCategoriePrix.setBorder(BorderFactory.createEmptyBorder());
		panCategorieSport.setBorder(BorderFactory.createEmptyBorder());
		panStock.setBorder(BorderFactory.createEmptyBorder());
		panPrixInitial.setBorder(BorderFactory.createEmptyBorder());

		descriptionLabel = new JLabel("Description : ");
		poidsLabel = new JLabel("Poids : ");
		catPrixLabel = new JLabel("Catégorie de Prix : ");
		catSportLabel = new JLabel("Catégorie de sport : ");
		prixLabel = new JLabel("Prix Initial : ");
		stockLabel = new JLabel("Stock : ");

		description = new JTextField();
		poids = new JFormattedTextField();
		prix = new JFormattedTextField();
		stock = new JFormattedTextField();

		description.setPreferredSize(new Dimension(90, 25));
		poids.setPreferredSize(new Dimension(90, 25));
		prix.setPreferredSize(new Dimension(90, 25));
		stock.setPreferredSize(new Dimension(90, 25));

		// Définition des deux JComboBox pour le type de sport et la catégorie de prix //
		//-----------------------------------------------------------------------------// 
		catPrixBox = new JComboBox();
		catSportBox = new JComboBox();

		// Récupération de la liste de tous les types de sport existants
		ArrayList<String> listeTypeSport = new ArrayList<String>();
		listeTypeSport = SGBD.selectListeString("TYPE_SPORT", "NOMTYPE");
		if (listeTypeSport.size() > 0) {
			for (int i = 0; i < listeTypeSport.size(); i++) {
				catSportBox.addItem(listeTypeSport.get(i));
			}
			catSportBox.setSelectedIndex(0);
		}

		catSportBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				itemSportSelectionne = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
			}
		});

		
		ArrayList<String> listeCategoriePrix = new ArrayList<String>();
		// Récupération de la liste de toutes les catégories de prix existantes
		listeCategoriePrix = SGBD
				.selectListeString("CATEGORIE", "NOMCATEGORIE");
		if (listeCategoriePrix.size() > 0) {
			for (int i = 0; i < listeCategoriePrix.size(); i++) {
				catPrixBox.addItem(listeCategoriePrix.get(i));
			}
			catPrixBox.setSelectedIndex(0);
		}

		catPrixBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				itemPrixSelectionne = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
			}
		});
		
		
		// Ajout des titres correspondants à chacun des champs à compléter //
		//-----------------------------------------------------------------//
		panDescription.add(descriptionLabel);
		panPoids.add(poidsLabel);
		panCategoriePrix.add(catPrixLabel);
		panCategorieSport.add(catSportLabel);
		panStock.add(stockLabel);
		panPrixInitial.add(prixLabel);
		
		// Ajout des JTextField et JComboBox correspondants à chacun des champs à compléter //
		//----------------------------------------------------------------------------------//
		panDescription.add(description);
		panPoids.add(poids);
		panStock.add(stock);
		panPrixInitial.add(prix);
		panCategoriePrix.add(catPrixBox);
		panCategorieSport.add(catSportBox);
		
		// Ajout des sous JPanel au "grand" JPanel qui regroupe tous les champs à compléter //
		//----------------------------------------------------------------------------------//
		panneauCentral.add(panDescription);
		panneauCentral.add(panPoids);
		panneauCentral.add(panCategoriePrix);
		panneauCentral.add(panCategorieSport);
		panneauCentral.add(panStock);
		panneauCentral.add(panPrixInitial);

		
		// Définition du JPanel qui va accueillir les boutons de confirmation et d'annulation //
		//------------------------------------------------------------------------------------//
		JPanel panneauBoutonsBas = new JPanel();
		
		// Définition de l'action du bouton confirmer qui va vérifier que les champs saisis //
		// --------------------et selectionnés soient corrects -----------------------------//
		JButton boutonConfirmation = new JButton("Confirmer");

		boutonConfirmation.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					String p = poids.getText();
					String st = stock.getText();
					String prx = prix.getText();
					// On supprime l'espace créé par l'existence des milliers (ex : "15 000") 
					p = p.replaceAll("[\\s\u00a0]+", "");
					st = st.replaceAll("[\\s\u00a0]+", "");
					prx = prx.replaceAll("[\\s\u00a0]+", "");

					if (description.getText().length() == 0
							| prix.getText().length() == 0
							| poids.getText().length() == 0
							| stock.getText().length() == 0) {
						throw new ExceptionChampVide(
								"Au moins l'un des champs est vide");
					}

					if (description.getText().length() > 40) {
						throw new ExceptionExcesDeCaracteres(
								"La description de l'article est trop longue");
					}

					long stockArticle = Long.parseLong(stock.getText());
					long poidsArticle = Long.parseLong(poids.getText());
					double prixInitial = Double.parseDouble(prix.getText());

					if (stockArticle < 0 | stockArticle >= 1000000) {
						throw new ExceptionStockAberrant(
								"Le stock saisi est aberrant.");
					}

					if (poidsArticle < 0 | poidsArticle >= 1000000) {
						throw new ExceptionPoidsAberrant(
								"Le poids saisi est aberrant.");
					}

					if (prixInitial < 0 | prixInitial >= 1000000) {
						throw new ExceptionPrixAberrant(
								"Le prix saisi est aberrant.");
					}
					//Si aucune exception n'est levée, l'article est ajouté à la base de données
					else {

						String typ = SGBD.selectStringConditionString(
								"TYPE_SPORT", "IDTYPE", "NOMTYPE",
								itemSportSelectionne);
						String cat = SGBD.selectStringConditionString(
								"CATEGORIE", "IDCATEGORIE", "NOMCATEGORIE",
								itemPrixSelectionne);

						System.out.println(typ + " " + cat);
						Article art = new Article(description.getText(), Double
								.parseDouble(prx), Integer.parseInt(st), Float
								.parseFloat(p), typ, cat, "En stock");

						// Fermeture de la page
						dispose();
						FenetreCatalogueGerant fen = new FenetreCatalogueGerant();
						fen.setVisible(true);

					}

				} catch (ExceptionChampVide e1) {
					System.out.println(e1.getMessage());
					JOptionPane
							.showMessageDialog(
									null,
									"Au moins l'un des champs est vide, remplissez ce(s) champ(s)",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionExcesDeCaracteres e2) {
					System.out.println(e2.getMessage());
					JOptionPane
							.showMessageDialog(
									null,
									"La description que vous avez saisie est trop longue, veuillez la raccourcir",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionStockAberrant e3) {
					System.out.println(e3.getMessage());
					JOptionPane
							.showMessageDialog(
									null,
									"Le stock saisi doit être compris entre 0 et 999 999 !",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionPoidsAberrant e4) {
					System.out.println(e4.getMessage());
					JOptionPane
							.showMessageDialog(
									null,
									"Le poids saisi doit être compris entre 0 et 999 999 grammes ! (moins d'une tonne)",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionPrixAberrant e5) {
					System.out.println(e5.getMessage());
					JOptionPane
							.showMessageDialog(
									null,
									"Le prix saisi doit être compris entre 0 et 999 999€ ! ",
									"Attention", JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		
		// Définition de l'action du bouton retour à la page précédente qui annule //
		// --------- tout ce qui a pu être fait sur la page et la ferme -----------//
		JButton boutonRetour = new JButton("Retour à la page précédente");
		boutonRetour.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Permet le retour à la page precedente
				dispose();
				FenetreCatalogueGerant fen = new FenetreCatalogueGerant();
				fen.setVisible(true);
			}
		});
		
		// Ajout des boutons au JPanel panneauBasFenetre destiné à accueillir les boutons //
		//--------------------------------------------------------------------------------//
		panneauBoutonsBas.add(boutonConfirmation);
		panneauBoutonsBas.add(boutonRetour);
		
		// Ajout des 2 JPanels principaux au conteneur de la fenêtre //
		//-----------------------------------------------------------//
		this.getContentPane().add(panneauCentral, "Center");
		this.getContentPane().add(panneauBoutonsBas, "South");
	}

	/**
	 * Méthode initComponent qui remplit les composants JTextField et les
	 * JComboBox à partir des attribus de l'article entrés en paramètre afin de
	 * pouvoir éventuellement le modifier
	 * 
	 * @param idArticle
	 *            Identifiant unique de l'article
	 */
	private void initComponent(String idArticle) {
		
		// Récupération dans la base de données des attributs de l'article afin de      //
		// pré-remplir les JTextField avec la valeur enregistrée dans la base de données//
		// et de compléter la valeur par défaut des JComboBox                           //
		//------------------------------------------------------------------------------//
		final String numArticle = idArticle;
		String descriptionA = SGBD.selectStringConditionString("ARTICLE",
				"DESCRIPTION", "IDARTICLE", idArticle);
		String poidsA = SGBD.selectStringConditionString("ARTICLE", "POIDS",
				"IDARTICLE", idArticle);
		String prixA = SGBD.selectStringConditionString("ARTICLE",
				"PRIXINITIAL", "IDARTICLE", idArticle);
		String stockA = SGBD.selectStringConditionString("ARTICLE", "STOCK",
				"IDARTICLE", idArticle);
		itemPrixSelectionne = SGBD.selectStringConditionString("ARTICLE",
				"IDCATEGORIE", "IDARTICLE", idArticle);
		itemSportSelectionne = SGBD.selectStringConditionString("ARTICLE",
				"IDTYPE", "IDARTICLE", idArticle);

		// Récupération du nom de la catégorie de prix et d'article pour le mettre //
		// ---------------- comme Item par défaut des JComboBox -------------------//
		String nomCategorieA = SGBD.selectStringConditionString("CATEGORIE",
				"NOMCATEGORIE", "IDCATEGORIE", itemPrixSelectionne);
		String nomtypeSportA = SGBD.selectStringConditionString("TYPE_SPORT",
				"NOMTYPE", "IDTYPE", itemSportSelectionne);

		JPanel panneauCentral = new JPanel();

		// Remplissage des JPanel qui vont accueillir les JTextField de modification des //
		// champs et les JComboBox afin que le gérant puisse changer la catégorie et     //
		//----------- le type de sport auquel appartient l'article ----------------------//
		//-------------------------------------------------------------------------------//
		panDescription.setPreferredSize(dimensionStandard);
		panPoids.setPreferredSize(dimensionStandard);
		panStock.setPreferredSize(dimensionStandard);
		panPrixInitial.setPreferredSize(dimensionStandard);
		panCategoriePrix.setPreferredSize(dimensionStandard);
		panCategorieSport.setPreferredSize(dimensionStandard);

		panDescription.setBorder(BorderFactory.createEmptyBorder());
		panPoids.setBorder(BorderFactory.createEmptyBorder());
		panStock.setBorder(BorderFactory.createEmptyBorder());
		panPrixInitial.setBorder(BorderFactory.createEmptyBorder());
		panCategoriePrix.setBorder(BorderFactory.createEmptyBorder());
		panCategorieSport.setBorder(BorderFactory.createEmptyBorder());

		descriptionLabel = new JLabel("Description : ");
		poidsLabel = new JLabel("Poids : ");
		prixLabel = new JLabel("Prix Initial : ");
		stockLabel = new JLabel("Stock : ");
		catPrixLabel = new JLabel("Catégorie de Prix : ");
		catSportLabel = new JLabel("Catégorie de sport : ");
		
		// Remplissage des JTexField avec les valeurs initiales de l'article passé en //
		// ------------------------en paramètre --------------------------------------//
		description = new JTextField(descriptionA);
		poids = new JTextField(poidsA);
		prix = new JTextField(prixA);
		stock = new JTextField(stockA);
		stock.setEnabled(false);

		description.setPreferredSize(new Dimension(100, 20));
		poids.setPreferredSize(new Dimension(100, 20));
		prix.setPreferredSize(new Dimension(100, 20));
		stock.setPreferredSize(new Dimension(100, 20));

		// Définition des deux JComboBox pour le type de sport et la catégorie de prix //
		//-----------------------------------------------------------------------------//
		catPrixBox = new JComboBox();
		catSportBox = new JComboBox();

		// Récupération de la liste de tous les types de sport existants
		ArrayList<String> listeTypeSport = new ArrayList<String>();
		listeTypeSport = SGBD.selectListeString("TYPE_SPORT", "NOMTYPE");

		if (listeTypeSport.size() > 0) {
			// Ajout de tous les types de sport au JComboBox
			for (int i = 0; i < listeTypeSport.size(); i++) {
				catSportBox.addItem(listeTypeSport.get(i));
			}
			// On met par défaut la catégorie de sport de l'article lorsqu'il a
			// été enregistré
			// la dernière fois dans la base de données
			catSportBox.setSelectedItem(nomtypeSportA);
		}

		catSportBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String choix = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
				itemSportSelectionne = SGBD.selectStringConditionString(
						"TYPE_SPORT", "IDTYPE", "NOMTYPE", choix);
			}
		});

		// Récupération de la liste de toutes les catégories de prix existantes
		ArrayList<String> listeCategoriePrix = new ArrayList<String>();
		listeCategoriePrix = SGBD
				.selectListeString("CATEGORIE", "NOMCATEGORIE");

		if (listeCategoriePrix.size() > 0) {
			// Ajout de toutes les catégories de prix au JComboBox
			for (int i = 0; i < listeCategoriePrix.size(); i++) {
				catPrixBox.addItem(listeCategoriePrix.get(i));
			}
			// On met par défaut la catégorie de prix de l'article lorsqu'il a
			// été enregistré
			// la dernière fois dans la base de données
			catPrixBox.setSelectedItem(nomCategorieA);

		}

		catPrixBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String choix = (String) ((JComboBox) e.getSource())
						.getSelectedItem();
				itemPrixSelectionne = SGBD.selectStringConditionString(
						"CATEGORIE", "IDCATEGORIE", "NOMCATEGORIE", choix);
			}
		});

		// Ajout des titres JLabel de chaque champ à leur JPanel correspondant //
		//---------------------------------------------------------------------//
		panDescription.add(descriptionLabel);
		panPoids.add(poidsLabel);
		panStock.add(stockLabel);
		panPrixInitial.add(prixLabel);
		panCategoriePrix.add(catPrixLabel);
		panCategorieSport.add(catSportLabel);

		// Ajout des JTextField et des JComboBox à leur JPanel correspondant //
		//-------------------------------------------------------------------//
		panDescription.add(description);
		panPoids.add(poids);
		panStock.add(stock);
		panPrixInitial.add(prix);
		panCategoriePrix.add(catPrixBox);
		panCategorieSport.add(catSportBox);

		// Ajout des JPanel de chaque champ au grand JPanel //
		//--------------------------------------------------//
		panneauCentral.add(panDescription);
		panneauCentral.add(panPoids);
		panneauCentral.add(panStock);
		panneauCentral.add(panPrixInitial);
		panneauCentral.add(panCategoriePrix);
		panneauCentral.add(panCategorieSport);



		// Définition du JPanel qui va accueillir les 2 boutons de la fenetre :  //
		// ---- Confirmer les modifications et retour à la page précédente ------//
		// ----------------------------------------------------------------------//

		JPanel panneauBoutonsBas = new JPanel();

		// Définition de l'action du bouton de confirmation qui enregistre les      //
		// modifications sur l'article dans la base de données après avoir vérifié  //
		// ----------- que les champs remplis soient corrects ----------------------//
		//--------------------------------------------------------------------------// 
		JButton boutonConfirmation = new JButton("Confirmer modification");

		boutonConfirmation.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {

					if (description.getText().length() == 0
							| prix.getText().length() == 0
							| poids.getText().length() == 0
							| stock.getText().length() == 0) {
						throw new ExceptionChampVide(
								"Au moins l'un des champs est vide");
					}

					if (description.getText().length() > 40) {
						throw new ExceptionExcesDeCaracteres(
								"La description de l'article est trop longue");
					}

					long stockArticle = Long.parseLong(stock.getText());
					long poidsArticle = Long.parseLong(poids.getText());
					double prixInitial = Double.parseDouble(prix.getText());

					if (stockArticle < 0 | stockArticle >= 1000000) {
						throw new ExceptionStockAberrant(
								"Le stock saisi est aberrant.");
					}

					if (poidsArticle < 0 | poidsArticle >= 1000000) {
						throw new ExceptionPoidsAberrant(
								"Le poids saisi est aberrant.");
					}

					if (prixInitial < 0 | prixInitial >= 1000000) {
						throw new ExceptionPrixAberrant(
								"Le prix saisi est aberrant.");
					}

					else {
						// On enregistre la modification de l'article
						Article.modifierArticleBDD(numArticle,
								description.getText(), prix.getText(),
								poids.getText(), stock.getText(),
								itemSportSelectionne, itemPrixSelectionne);

						// Puis la page se ferme
						dispose();
						FenetreCatalogueGerant fen = new FenetreCatalogueGerant();
						fen.setVisible(true);
					}

				} catch (ExceptionChampVide e1) {
					System.out.println(e1.getMessage());
					JOptionPane
							.showMessageDialog(
									null,
									"Au moins l'un des champs est vide, remplissez ce(s) champ(s)",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionExcesDeCaracteres e2) {
					System.out.println(e2.getMessage());
					JOptionPane
							.showMessageDialog(
									null,
									"La description que vous avez saisie est trop longue (40 caractères maximum), veuillez la raccourcir",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionStockAberrant e3) {
					System.out.println(e3.getMessage());
					JOptionPane
							.showMessageDialog(
									null,
									"Le stock saisi doit être compris entre 0 et 999 999 !",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionPoidsAberrant e4) {
					System.out.println(e4.getMessage());
					JOptionPane
							.showMessageDialog(
									null,
									"Le poids saisi doit être compris entre 0 et 999 999 grammes ! (moins d'une tonne)",
									"Attention", JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionPrixAberrant e5) {
					System.out.println(e5.getMessage());
					JOptionPane
							.showMessageDialog(
									null,
									"Le prix saisi doit être compris entre 0 et 999 999€ ! ",
									"Attention", JOptionPane.ERROR_MESSAGE);

				}

				// int verificationChamp = Article.verifierChampsArticles(
				// description.getText(), prix.getText(), poids.getText(),
				// stock.getText());
				//
				// switch (verificationChamp) {
				// case 0:
				// // enregistrer la modification d'article
				// Article.modifierArticleBDD(numArticle,
				// description.getText(), prix.getText(),
				// poids.getText(), stock.getText(),
				// itemSportSelectionne, itemPrixSelectionne);
				//
				// // puis fermer la page
				// dispose();
				// FenetreCatalogueGerant fen = new FenetreCatalogueGerant();
				// fen.setVisible(true);
				//
				// break;
				// case 1:
				// JOptionPane
				// .showMessageDialog(
				// null,
				// "Le champ description est trop long, modifiez ce champ",
				// "Attention", JOptionPane.ERROR_MESSAGE);
				// break;
				// case 2:
				// JOptionPane
				// .showMessageDialog(
				// null,
				// "Un des champs que vous avez rempli est vide, remplissez ce champ",
				// "Attention", JOptionPane.ERROR_MESSAGE);
				// break;
				// case 3:
				// JOptionPane
				// .showMessageDialog(
				// null,
				// "Le stock indiqué n'est pas valide, modifiez ce champ",
				// "Attention", JOptionPane.ERROR_MESSAGE);
				// break;
				// case 4:
				// JOptionPane
				// .showMessageDialog(
				// null,
				// "Le poids indiqué n'est pas valide, modifiez ce champ",
				// "Attention", JOptionPane.ERROR_MESSAGE);
				// break;
				// case 5:
				// JOptionPane
				// .showMessageDialog(
				// null,
				// "Le prix indiqué n'est pas valide, modifiez ce champ",
				// "Attention", JOptionPane.ERROR_MESSAGE);
				//
				// break;
				// default:
				// break;
				// }

			}
		});

		// Définition de l'action du bouton retour qui annule les modifications et //
		// -------- permet le retour vers la page de gestion du catalogue ---------//
		//-------------------------------------------------------------------------//
		JButton boutonRetour = new JButton("Retour à la page précédente");
		boutonRetour.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Permet le retour à la page precedente
				dispose();
				FenetreCatalogueGerant fen = new FenetreCatalogueGerant();
				fen.setVisible(true);

			}
		});

		// Ajout des boutons au JPanel panneauBoutonsBas //
		//-----------------------------------------------//
		panneauBoutonsBas.add(boutonConfirmation);
		panneauBoutonsBas.add(boutonRetour);

		// Ajout des 2 principaux JPanel au conteneur de la fenêtre //
		//----------------------------------------------------------//
		this.getContentPane().add(panneauCentral, "Center");
		this.getContentPane().add(panneauBoutonsBas, "South");
		
	}

}
