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



/**
 * La classe FenetreChoixCatalogue permet de créer une fenêtre dans laquelle le client pourra choisir 
 * la quantité qu'il désire ajouter de l'article sélectionné dans le tableau du catalogue tout
 * en voyant une description de l'article selectionné.
 * 
 * @author Utilisateur
 *
 */
public class FenetreChoixCatalogue extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel quantiteLabel;
	private JComboBox quantite;
	public static int quantiteSelectionnee ;
	
	/**
	 * Constructeur de la classe FenetreChoixCatalogue dans laquelle le client pourra choisir 
	 * la quantité qu'il désire ajouter de l'article sélectionné dans le tableau du catalogue
	 * 
	 * @param parent
	 *            JFrame utilisé pour créer la fenêtre	
	 * @param title
	 * 			String indiquant le titre de la fenêtre
	 * @param modal
	 * 			Booléen indiquant si la fenêtre doit bloquer ou non les interactions avec les autres
	 * 			fenêtres
	 * 
	 * @see FenetreCommandeArticle
	 * 
	 */
	public FenetreChoixCatalogue(JFrame parent, String title, boolean modal,int stock,String idArticle){
		super(parent, title, modal);
		this.setSize(400, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(stock,idArticle);
	}
	
	/**
	 * <p> Initialisation des composants de la fenêtre :<ul>
	 * <li> le JPanel contenant la description de l'article.</li>
	 * <li> le JPanel contenant le JComboBox permettant de sélectionner la quantité à ajouter
	 * au panier sans dépasser la quantité en stock de l'article.</li>
	 * <li> le JPanel contenant les boutons de confirmation ou d'annulation des actions 
	 * effectuées sur la fenêtre.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param quantiteStock
	 * 				Entier désignant la quantité actuellement en stock de l'article
	 * 
	 * @param idArticle
	 * 				Identifiant de l'article dont on veut ajouter une certaine quantité au panier
	 */
	private void initComponent(int quantiteStock, String idArticle){
		final String  numArticle=idArticle;
		
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
		panneauQuantite.setBorder(BorderFactory.createTitledBorder("Ajout au panier"));
		quantiteLabel = new JLabel("Quantité : ");
		
		//Défintion d'un JComboBox dans lequel le client choisira la quantité qu'il veut ajoute ou retirer
		// de son panier en fonction du bouton dans lequel il aura cliqué dans FenetreCommandeArticle
		quantite=new JComboBox();
		
		// Pour gérer la quantité selectionnée, il ne pourra dépasser la quantité en stock
		for(int j=1;j<=quantiteStock;j++){
			quantite.addItem(j+"");
		}
		
		quantite.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

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
		
		JButton boutonValiderSelection= new JButton("Valider Sélection Article");
		JButton boutonAnnulerSelection= new JButton("Annuler Sélection Article");
		
		//Définition des actions relatives à chaque bouton
		
		boutonValiderSelection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				// Ajout de la quantité selectionnée au panier du client
				Commande.ajouterArticlePanier(numArticle, quantiteSelectionnee, FenetreCommandeArticle.panierClient);
				
				// On change la valeur du booléen pour indiquer le client a bien 
				// sélectionné une ligne dans le catalogue
				FenetreCommandeArticle.activationLigneCatalogue=true;
				
				// On change la valeur du booleen avoirRafraichiApresAjoutPanier pour qu'il ne
				// puisse pas faire deux ajouts consécutifs d'un même article sans
				// rafraîchir le panier
				FenetreCommandeArticle.avoirRafraichiApresAjoutPanier=false;
			}			
		});
		
		boutonAnnulerSelection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// permet d'annuler la sélection en cours et retour vers page principale
				setVisible(false);
			}			
		});
		
		//Ajout des boutons au panneau principal des boutons panneauBoutons
		panneauBoutons.add(boutonValiderSelection);
		panneauBoutons.add(boutonAnnulerSelection);
		
		this.getContentPane().add(panneauDescription, BorderLayout.NORTH);
		this.getContentPane().add(panneauQuantite, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
		
	}
}
