package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import metier.Commande;




public class FenetreChoixCatalogue extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel quantiteLabel;
	private JComboBox quantite;
	public static int quantiteSelectionnee ;
	
	/**
	 * Constructeur de la classe FenetreChoixCatalogue dans laquelle le client pourra choisir 
	 * la quantitée qu'il désire de l'article sélectionné dans le tableau du catalogue
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public FenetreChoixCatalogue(JFrame parent, String title, boolean modal,int stock,String idArticle){
		super(parent, title, modal);
		this.setSize(400, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(stock,idArticle);
	}
	
	private void initComponent(int quantiteStock, String idArticle){
		final String  numArticle=idArticle;
		// Définition du panneau dans lequel le client sélectionnera la quantité d'un article
		JPanel panneauQuantite=new JPanel();
		panneauQuantite.setBackground(Color.white);
		panneauQuantite.setPreferredSize(new Dimension(220, 60));
		panneauQuantite.setBorder(BorderFactory.createTitledBorder("Ajout au panier"));
		quantiteLabel = new JLabel("Quantité : ");
		
		// Pour gérer la quantité selectionnée, il ne pourra dépasser la quantité en stock
		quantite=new JComboBox();
		
		for(int j=1;j<=quantiteStock;j++){
			quantite.addItem(j+"");
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
		
		JButton boutonValiderSelection= new JButton("Valider Sélection Article");
		JButton boutonAnnulerSelection= new JButton("Annuler Sélection Article");
		
		//Définition des actions relatives à chaque bouton
		
		boutonValiderSelection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				// TODO il faudra ajouter l'article concerné dans le panier avec la quantité correspondante
				// et faire les modifications éventuelles dans la base de données.
				// LigneCommande panierEnCours = new LigneCommande(article,quantite);
				Commande.ajouterArticlePanier(numArticle, quantiteSelectionnee+"", FenetreCommandeArticle.panierClient);
				
				for (int i = 0; i < FenetreCommandeArticle.panierClient.size(); i++) {
					System.out.println("ARTICLE : "+FenetreCommandeArticle.panierClient.get(i)[0]+", quantité dans panier :"+FenetreCommandeArticle.panierClient.get(i)[1]);
				}
				
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
		
		this.getContentPane().add(panneauQuantite, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
		
	}
}
