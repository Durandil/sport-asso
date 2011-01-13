package ihm.Client;

import ihm.modeleTableau.ModeleTableauCommande;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import metier.Commande;
import metier.LigneCommande;

import basededonnees.SGBD;

/**
 * Cette classe FenetreFactureCommande permet d'afficher une facture
 * correspondant � la commande effectu�e juste avant dans
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
	 * Constructeur de la classe {@link FenetreFactureCommande} gr�ce aux
	 * instructions de la m�thode
	 * {@link FenetreFactureCommande#initComponent(String, Commande, ArrayList, int, boolean)}
	 * 
	 * @param parent
	 *            JFrame utilis� pour cr�er la fen�tre
	 * @param title
	 *            String indiquant le titre de la fen�tre
	 * @param modal
	 *            Bool�en indiquant si la fen�tre doit bloquer ou non les
	 *            interactions avec les autres fen�tres
	 * @param identifiantClient
	 *            Identifiant unique du client
	 * @param commandeP
	 *            Instance de la classe Commande contenant les informations de
	 *            la commande
	 * @param panierClient
	 *            ArrayList<LigneCommande> r�pertoriant la liste des articles
	 *            command�s en quantit� non nulles
	 * @param bonsAchatUtilises
	 *            Entier indiquant le montant du bon d'achat du client
	 * @param utilisationBonsAchat
	 *            Bool�en indiquant si le client a utilis� son bon d'achat
	 * @throws SQLException
	 */
	public FenetreFactureCommande(JFrame parent, String title, boolean modal,
			String identifiantClient, Commande commandeP,
			ArrayList<LigneCommande> panierClient, int bonsAchatUtilises,
			boolean utilisationBonsAchat) throws SQLException {

		super(parent, title, modal);
		this.setSize(600, 800);
		this.setLocation(50, 50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.initComponent(identifiantClient, commandeP, panierClient,
				bonsAchatUtilises, utilisationBonsAchat);
	}

	/**
	 * <p>
	 * Initialisation de la fen�tre d'affichage de la facture avec les
	 * composants :
	 * <ul>
	 * <li>Un JPanel comportant les informations relatives au client</li>
	 * <li>Un JPanel comportant les informations relatives � la commande</li>
	 * <li>Un tableau comportant la liste des articles command�s
	 * <li>Un JPanel comportant le montant total de la commande apr�s d�duction
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
	 *            ArrayList<LigneCommande> r�pertoriant la liste des articles
	 *            command�s en quantit� non nulles
	 * @param bonsAchat
	 *            Entier indiquant le montant du bon d'achat du client
	 * @param utilisationBonsAchat
	 *            Bool�en indiquant si le client a utilis� son bon d'achat
	 * @throws SQLException
	 */
	private void initComponent(String idClient, Commande commande,
			ArrayList<LigneCommande> panier, int bonsAchat,
			boolean utilisationBonsAchat) throws SQLException {

		// R�cup�ration des diff�rentes informations du client //
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

		// Cr�ation du panneau JPanel du haut qui contiendra les //
		// ---------- caract�ristiques du client ----------------//
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

		// Cr�ation du JPanel qui contiendra les informations relatives � la //
		// ------ commande : le num�ro et la date de la commande ------------//
		// -------------------------------------------------------------------//
		JPanel panneauDateCommande = new JPanel();
		dateCommandeLabel = new JLabel("Date commande : " + commande.getDate());
		numCommandeLabel = new JLabel("Num�ro de commande : "
				+ commande.getIdCommande());
		panneauDateCommande.add(dateCommandeLabel);
		panneauDateCommande.add(numCommandeLabel);

		panneauHaut.add(panneauCaracteristiquesClient);
		panneauHaut.add(panneauDateCommande);

		// Cr�ation du tableau avec le listing de tous //
		// les articles achet�s dans la commande ------//
		// --------------------------------------------//
		ModeleTableauCommande modele = new ModeleTableauCommande(panier,
				commande, idClient);
		JTable tableau = new JTable(modele);
		JScrollPane tab = new JScrollPane(tableau);
		tableau.setEnabled(false);

		// Cr�ation d'un JPanel qui contiendra   //
		// le montant total de la commande apr�s //
		// ----- utilisation du bon d'achat -----//
		// --------------------------------------//
		
		JPanel panneauBas = new JPanel();

		panneauBas.setLayout(new GridLayout(3, 1, 2, 0));
		panneauBas.setBorder(BorderFactory.createLineBorder(Color.gray));
		bonsAchatLabel = new JLabel(
				"Nombre de points de r�duction utilis�s  sur votre carte de fid�lit�  :  "
						+ bonsAchat);

		// Si le client a utilis� son bon achat, nous affichons
		// le montant du bon de r�duction
		if (utilisationBonsAchat == true) {
			panneauBas.add(bonsAchatLabel);
		}
		totalLabel = new JLabel("Total commande :  "
				+ SGBD.selectStringConditionString("COMMANDE",
						"MONTANTCOMMANDE", "IDCOMMANDE",
						commande.getIdCommande()) + " �");
		panneauBas.add(totalLabel);

		// Cr�ation d'un bouton pour enregistrer la facture
		JButton boutonEnregistrer = new JButton("Enregistrer la facture "
				+ commande.getIdCommande());

		// R�cup�ration des dimensions principales de la fen�tre
		// pour la capture d'�cran
		final int x = this.getLocation().x;
		final int y = this.getLocation().y;
		final int wi = this.getWidth();
		final int hei = this.getHeight();

		boutonEnregistrer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {

					Robot robot = new Robot();
					// Capture de l'�cran d�fini dans le rectangle respectant
					// les dimensions
					// de la fen�tre
					BufferedImage bi = robot.createScreenCapture(new Rectangle(
							x, y, wi, hei));

					// D�finition de l'objet JFileChooser qui permettra
					// d'enregistrer
					// la facture
					FileSystemView vueSysteme = FileSystemView
							.getFileSystemView();
					JFileChooser filechooser = new JFileChooser(vueSysteme
							.getDefaultDirectory());

					filechooser.setDialogTitle("Enregistrer la facture");
					filechooser.setSelectedFile(new File(filechooser
							.getCurrentDirectory(), "Facture.jpg"));

					// Impl�mentation du filtre pour choisir le format du
					// fichier de la facture
					filechooser.setFileFilter(new FileFilter() {

						public boolean accept(File f) {
							// V�rification de l'extension du fichier f
							String extension = f
									.getName()
									.substring(f.getName().lastIndexOf('.') + 1)
									.toLowerCase();

							if (f.isDirectory()) {
								return true;
							}
							if (extension.equals("jpg")) {
								return true;
							}
							return false;
						}

						public String getDescription() {
							return "Fichier accept� : jpg";
						}
					});

					// Ouverture d'une bo�te de dialogue pour enregistrer la
					// facture
					int sauvegardeFacture = filechooser.showSaveDialog(null);

					if (sauvegardeFacture == JFileChooser.APPROVE_OPTION) {

						// R�cup�ration du fichier s�lectionn�
						File file = filechooser.getSelectedFile();

						try {
							// R�cup�ration de l'extension du fichier
							// s�lectionn�
							String extension = file.getName().substring(
									file.getName().lastIndexOf(".") + 1);

							// si l'extension n'est pas un jpg, alors on affiche
							// un message d'erreur
							// sinon on enregistre la facture
							if (extension.equals("jpg")) {
								javax.imageio.ImageIO
										.write(bi, extension, file);
							} else {
								JOptionPane
										.showMessageDialog(null,
												"Vous n'avez pas s�lectionn� un bon format pour votre facture");
							}

						} catch (IOException err) {
							JOptionPane.showMessageDialog(
									null,
									"Echec de la sauvegarde: "
											+ err.getMessage());
						}
					}

				} catch (AWTException e2) {
					System.out.println(e2.getMessage());
				}

			}
		});

		panneauBas.add(boutonEnregistrer);
		
		// Ajout des composants JPanel cr�es ci-dessus 
		// dans le conteneur de la fen�tre 
		this.getContentPane().add(panneauHaut, "North");
		this.getContentPane().add(tab, "Center");
		this.getContentPane().add(panneauBas, "South");

	}


}
