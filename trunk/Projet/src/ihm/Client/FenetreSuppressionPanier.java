package ihm.Client;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import basededonnees.SGBD;

import metier.Commande;
import metier.LigneCommande;

/**
 * Cette classe FenetreSuppression s'affiche une fois que le client a choisi un article 
 * dans son panier  et qu'il souhaite en enlever une certaine quantité
 * 
 * @author Utilisateur
 * @see FenetreCommandeArticle
 */
public class FenetreSuppressionPanier extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel quantiteLabel;
	private JComboBox quantite;
	public static int quantiteSelectionnee ;
	
	/**
	 * Constructeur de la classe FenetreSuppression dans laquelle le client pourra choisir 
	 * la quantité qu'il désire enlever de l'article sélectionné dans le tableau du catalogue
	 * 
	 * @param parent
	 * 			
	 * @param title
	 * 			String indiquant le titre de la fenêtre
	 * @param modal
	 * 			Booléen indiquant si la fenêtre doit bloquer ou non les interactions avec les autres
	 * 			fenêtres
	 * @param numeroArticle
	 * 			Identifiant de l'article dont on veut retirer une certaine quantité
	 * @param quantitePanier
	 * 			Entier désignant la quantité actuellement commandée de l'article par le client
	 * 
	 * @see FenetreCommandeArticle
	 * {@link FenetreSuppressionPanier#initComponent(int, String)}
	 * 
	 */
	public FenetreSuppressionPanier(JFrame parent, String title, boolean modal,String numeroArticle,int quantitePanier){
		super(parent, title, modal);
		this.setSize(500, 300);
		this.setLocation(50,50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(quantitePanier,numeroArticle);
	}
	
	/**
	 * <p> Initialisation des composants de la fenêtre :<ul>
	 * <li> le JPanel contenant la description de l'article.</li>
	 * <li> le JPanel contenant le JComboBox permettant de sélectionner la quantité à retirer
	 * du panier sans dépasser la quantité déjà commandée.</li>
	 * <li> le JPanel contenant les boutons de confirmation ou d'annulation des actions 
	 * effectuées sur la fenêtre.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param quantiteEntree
	 * 				Entier désignant la quantité actuellement commandée de l'article par le client
	 * 
	 * @param idArticle
	 * 				Identifiant de l'article dont on veut retirer une certaine quantité
	 */
	private void initComponent(int quantiteEntree, String idArticle){
		final String identifiantArticle = idArticle;
		
		// Définition du panneau dans lequel le client verra la description de l'article
		JPanel panneauDescription = new JPanel();
		panneauDescription.setBackground(Color.white);
		panneauDescription.setLayout(new GridLayout(2,1,0,5));
		panneauDescription.setBorder(BorderFactory.createTitledBorder("Informations techniques"));
		JLabel referenceArticleLabel = new JLabel("Référence : " + idArticle );
		JLabel descriptionArticleLabel = new JLabel("Description : " + SGBD.selectStringConditionString("ARTICLE", "DESCRIPTION", "IDARTICLE", idArticle));
		panneauDescription.add(referenceArticleLabel);
		panneauDescription.add(descriptionArticleLabel);
		
		// Définition du panneau dans lequel le client sélectionnera la quantité d'un article
		JPanel panneauQuantite=new JPanel();
		panneauQuantite.setBackground(Color.white);
		panneauQuantite.setPreferredSize(new Dimension(220, 60));
		panneauQuantite.setBorder(BorderFactory.createTitledBorder("Supprimer du panier"));
		quantiteLabel = new JLabel("Quantité : ");
		
		// Pour gérer la quantité selectionnée, il ne pourra dépasser la quantité 
		// qu'il a déjà commandée de l'article
		quantite=new JComboBox();
		for(int i=1;i<=quantiteEntree;i++){
			quantite.addItem(i+"");
		}
		
		quantite.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String choix= (String) ((JComboBox) e.getSource()).getSelectedItem();
				quantiteSelectionnee=Integer.parseInt(choix);
			}
		});
		
		panneauQuantite.add(quantiteLabel);
		quantite.setVisible(true);
		panneauQuantite.add(quantite);
		
		// Définition du panneau dans lequel seront présents les boutons de confirmation ou d'annulation 
		// du choix d'un article dans le panier
		JPanel panneauBoutons=new JPanel();
		panneauBoutons.setBackground(Color.white);
		
		JButton boutonValiderSuppression= new JButton("Valider Modification Panier");
		JButton boutonAnnulerSuppression= new JButton("Annuler Modification Panier");
		
		//Définition des actions relatives à chaque bouton
		
		boutonValiderSuppression.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// Modificationdans le panier.
				Commande.enleverArticlePanier(identifiantArticle, quantiteSelectionnee+"", FenetreCommandeArticle.panierClient);
				
				dispose();
				// On change la valeur du booleen retraitPanierPossible pour qu'il ne
				// puisse pas faire deux retraits consécutifs d'un même article sans
				// rafraîchir le panier
				FenetreCommandeArticle.retraitPanierPossible=false;
				
			}			
		});
		
		boutonAnnulerSuppression.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// permet d'annuler la sélection en cours et retour vers page principale
				dispose();
			}			
		});
		
		//Ajout des boutons au panneau principal des boutons panneauBoutons
		panneauBoutons.add(boutonValiderSuppression);
		panneauBoutons.add(boutonAnnulerSuppression);
		
		// Ajout des JPanel crées ci-dessus dans le conteneur de la fenêtre
		this.getContentPane().add(panneauDescription, BorderLayout.NORTH);
		this.getContentPane().add(panneauQuantite, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
		
	}
}

