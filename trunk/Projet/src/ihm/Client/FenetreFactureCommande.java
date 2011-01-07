package ihm.Client;

import ihm.modeleTableau.ModeleTableauCommande;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import metier.Commande;
import metier.LigneCommande;

import basededonnees.SGBD;

/**
 * Cette classe FenetreFactureCommande permet d'afficher une facture
 * correspondant à la commande effectuée juste avant dans
 * {@link FenetreCommandeArticle}.
 * 
 * 
 * {@link FenetreFactureCommande#FenetreFactureCommande(JFrame, String, boolean, String, Commande, ArrayList, int, boolean)}
 */
public class FenetreFactureCommande extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel nomPrenomLabel, adresseLabel, cpVilleLabel;
	private JLabel dateCommandeLabel;
	private JLabel numCommandeLabel;
	private JLabel totalLabel;
	private JLabel bonsAchatLabel;

	/**
	 * Constructeur de la classe {@link FenetreFactureCommande} grâce aux
	 * instructions de la méthode
	 * {@link FenetreFactureCommande#initComponent(String, Commande, ArrayList, int, boolean)}
	 * 
	 * @param parent
	 *            JFrame utilisé pour créer la fenêtre
	 * @param title
	 *            String indiquant le titre de la fenêtre
	 * @param modal
	 *            Booléen indiquant si la fenêtre doit bloquer ou non les
	 *            interactions avec les autres fenêtres
	 * @param identifiantClient
	 *            Identifiant unique du client
	 * @param commandeP
	 *            Instance de la classe Commande contenant les informations de
	 *            la commande
	 * @param panierClient
	 *            ArrayList<LigneCommande> répertoriant la liste des articles
	 *            commandés en quantité non nulles
	 * @param bonsAchatUtilises
	 *            Entier indiquant le montant du bon d'achat du client
	 * @param utilisationBonsAchat
	 *            Booléen indiquant si le client a utilisé son bon d'achat
	 * @throws SQLException
	 */
	public FenetreFactureCommande(JFrame parent, String title, boolean modal,
			String identifiantClient, Commande commandeP,
			ArrayList<LigneCommande> panierClient, int bonsAchatUtilises,
			boolean utilisationBonsAchat) throws SQLException {

		super(parent, title, modal);
		this.setSize(600, 650);
		this.setLocation(50, 50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.initComponent(identifiantClient, commandeP, panierClient,
				bonsAchatUtilises, utilisationBonsAchat);
	}

	/**
	 * <p>
	 * Initialisation de la fenêtre d'affichage de la facture avec les
	 * composants :
	 * <ul>
	 * <li>Un JPanel comportant les informations relatives au client</li>
	 * <li>Un JPanel comportant les informations relatives à la commande</li>
	 * <li>Un tableau comportant la liste des articles commandés
	 * <li>Un JPanel comportant le montant total de la commande après déduction
	 * du bon d'achat</li>
	 * </ul>
	 * </p>
	 * 
	 * @param idClient
	 *            Identifiant unique du client
	 * @param commande
	 *            Instance de la classe Commande contenant les informations de
	 *            la commande
	 * @param panier
	 *            ArrayList<LigneCommande> répertoriant la liste des articles
	 *            commandés en quantité non nulles
	 * @param bonsAchat
	 *            Entier indiquant le montant du bon d'achat du client
	 * @param utilisationBonsAchat
	 *            Booléen indiquant si le client a utilisé son bon d'achat
	 * @throws SQLException
	 */
	private void initComponent(String idClient, Commande commande,
			ArrayList<LigneCommande> panier, int bonsAchat,
			boolean utilisationBonsAchat) throws SQLException {

		// Récupération des différentes informations du client //
		// -----------------------------------------------------//
		String nom = SGBD.selectStringConditionString("CLIENT", "NOMCLIENT",
				"IDCLIENT", idClient);
		String prenom = SGBD.selectStringConditionString("CLIENT",
				"PRENOMCLIENT", "IDCLIENT", idClient);
		String denomination = SGBD.selectStringConditionString("CLIENT",
				"DENOMINATIONCLIENT", "IDCLIENT", idClient);
		String adresse = SGBD.selectStringConditionString("CLIENT",
				"NOMCLIENT", "ADRESSECLIENT", idClient);
		String idVille = SGBD.selectStringConditionString("CLIENT", "IDVILLE",
				"IDCLIENT", idClient);
		String codePostal = SGBD.selectStringConditionString("VILLE",
				"CODEPOSTAL", "IDVILLE", idVille);
		String ville = SGBD.selectStringConditionString("VILLE", "NOMVILLE",
				"IDVILLE", idVille);

		// Création du panneau JPanel du haut qui contiendra les //
		// ---------- caractéristiques du client ----------------//
		// -------------------------------------------------------//
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new GridLayout(3, 1, 5, 5));

		JPanel panneauCaracteristiquesClient = new JPanel();
		nomPrenomLabel = new JLabel(nom.toUpperCase() + " " + prenom
				+ denomination.toUpperCase());
		adresseLabel = new JLabel(adresse);
		cpVilleLabel = new JLabel(codePostal + " " + ville);

		panneauCaracteristiquesClient.add(nomPrenomLabel);
		panneauCaracteristiquesClient.add(adresseLabel);
		panneauCaracteristiquesClient.add(cpVilleLabel);

		// Création du JPanel qui contiendra les informations relatives à la //
		// ------ commande : le numéro et la date de la commande ------------//
		// -------------------------------------------------------------------//
		JPanel panneauDateCommande = new JPanel();
		dateCommandeLabel = new JLabel("Date commande : " + commande.getDate());
		numCommandeLabel = new JLabel("Numéro de commande : "
				+ commande.getIdCommande());
		panneauDateCommande.add(dateCommandeLabel);
		panneauDateCommande.add(numCommandeLabel);

		panneauHaut.add(panneauCaracteristiquesClient);
		panneauHaut.add(panneauDateCommande);

		// Création du tableau avec le listing de tous //
		// les articles achetés dans la commande ------//
		// --------------------------------------------//
		ModeleTableauCommande modele = new ModeleTableauCommande(panier,
				commande, idClient);
		JTable tableau = new JTable(modele);
		JScrollPane tab = new JScrollPane(tableau);
		tableau.setEnabled(false);

		// Création d'un JPanel qui contiendra   //
		// le montant total de la commande après //
		// ----- utilisation du bon d'achat -----//
		// --------------------------------------//
		JPanel panneauBas = new JPanel();
		panneauBas.setLayout(new GridLayout(2, 1, 2, 0));
		panneauBas.setBorder(BorderFactory.createLineBorder(Color.gray));
		bonsAchatLabel = new JLabel(
				"Nombre de points de réduction utilisés  sur votre carte de fidélité  :  "
						+ bonsAchat);
		
		// Si le client a utilisé son bon achat, nous affichons 
		// le montant du bon de réduction
		if (utilisationBonsAchat == true) {
			panneauBas.add(bonsAchatLabel);
		}
		totalLabel = new JLabel("Total commande :  "
				+ SGBD.selectStringConditionString("COMMANDE",
						"MONTANTCOMMANDE", "IDCOMMANDE",
						commande.getIdCommande()) + " €");
		panneauBas.add(totalLabel);

		// Ajout des composants JPanel crées ci-dessus 
		// dans le conteneur de la fenêtre 
		this.getContentPane().add(panneauHaut, "North");
		this.getContentPane().add(tab, "Center");
		this.getContentPane().add(panneauBas, "South");

	}

}
