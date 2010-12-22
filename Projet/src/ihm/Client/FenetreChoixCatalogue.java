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
 * La classe FenetreChoixCatalogue permet de cr�er une fen�tre dans laquelle le client pourra choisir 
 * la quantit� qu'il d�sire ajouter de l'article s�lectionn� dans le tableau du catalogue tout
 * en voyant une description de l'article selectionn�.
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
	 * la quantit� qu'il d�sire ajouter de l'article s�lectionn� dans le tableau du catalogue
	 * 
	 * @param parent
	 *            JFrame utilis� pour cr�er la fen�tre	
	 * @param title
	 * 			String indiquant le titre de la fen�tre
	 * @param modal
	 * 			Bool�en indiquant si la fen�tre doit bloquer ou non les interactions avec les autres
	 * 			fen�tres
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
	 * <p> Initialisation des composants de la fen�tre :<ul>
	 * <li> le JPanel contenant la description de l'article.</li>
	 * <li> le JPanel contenant le JComboBox permettant de s�lectionner la quantit� � ajouter
	 * au panier sans d�passer la quantit� en stock de l'article.</li>
	 * <li> le JPanel contenant les boutons de confirmation ou d'annulation des actions 
	 * effectu�es sur la fen�tre.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param quantiteStock
	 * 				Entier d�signant la quantit� actuellement en stock de l'article
	 * 
	 * @param idArticle
	 * 				Identifiant de l'article dont on veut ajouter une certaine quantit� au panier
	 */
	private void initComponent(int quantiteStock, String idArticle){
		final String  numArticle=idArticle;
		
		// D�finition du panneau dans lequel le client verra la description de l'article
		JPanel panneauDescription = new JPanel();
		panneauDescription.setBackground(Color.white);
		panneauDescription.setLayout(new GridLayout(2,1,0,5));
		panneauDescription.setBorder(BorderFactory.createTitledBorder("Informations techniques"));
		JLabel referenceArticleLabel = new JLabel("R�f�rence : " + idArticle );
		JLabel descriptionArticleLabel = new JLabel("Description : " + SGBD.selectStringConditionString("ARTICLE", "DESCRIPTION", "IDARTICLE", idArticle));
		panneauDescription.add(referenceArticleLabel);
		panneauDescription.add(descriptionArticleLabel);
		
		// D�finition du panneau dans lequel le client s�lectionnera la quantit� d'un article
		JPanel panneauQuantite=new JPanel();
		panneauQuantite.setBackground(Color.white);
		panneauQuantite.setPreferredSize(new Dimension(220, 60));
		panneauQuantite.setBorder(BorderFactory.createTitledBorder("Ajout au panier"));
		quantiteLabel = new JLabel("Quantit� : ");
		
		//D�fintion d'un JComboBox dans lequel le client choisira la quantit� qu'il veut ajoute ou retirer
		// de son panier en fonction du bouton dans lequel il aura cliqu� dans FenetreCommandeArticle
		quantite=new JComboBox();
		
		// Pour g�rer la quantit� selectionn�e, il ne pourra d�passer la quantit� en stock
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
		
		// D�finition du panneau dans lequel seront pr�sents les boutons de confirmation ou d'annulation 
		// du choix d'un article dans le panier
		JPanel panneauBoutons=new JPanel();
		panneauBoutons.setBackground(Color.white);
		
		JButton boutonValiderSelection= new JButton("Valider S�lection Article");
		JButton boutonAnnulerSelection= new JButton("Annuler S�lection Article");
		
		//D�finition des actions relatives � chaque bouton
		
		boutonValiderSelection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				// Ajout de la quantit� selectionn�e au panier du client
				Commande.ajouterArticlePanier(numArticle, quantiteSelectionnee, FenetreCommandeArticle.panierClient);
				
				// On change la valeur du bool�en pour indiquer le client a bien 
				// s�lectionn� une ligne dans le catalogue
				FenetreCommandeArticle.activationLigneCatalogue=true;
				
				// On change la valeur du booleen avoirRafraichiApresAjoutPanier pour qu'il ne
				// puisse pas faire deux ajouts cons�cutifs d'un m�me article sans
				// rafra�chir le panier
				FenetreCommandeArticle.avoirRafraichiApresAjoutPanier=false;
			}			
		});
		
		boutonAnnulerSelection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// permet d'annuler la s�lection en cours et retour vers page principale
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
