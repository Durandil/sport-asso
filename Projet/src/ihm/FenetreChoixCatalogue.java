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
	 * la quantit�e qu'il d�sire de l'article s�lectionn� dans le tableau du catalogue
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
		// D�finition du panneau dans lequel le client s�lectionnera la quantit� d'un article
		JPanel panneauQuantite=new JPanel();
		panneauQuantite.setBackground(Color.white);
		panneauQuantite.setPreferredSize(new Dimension(220, 60));
		panneauQuantite.setBorder(BorderFactory.createTitledBorder("Ajout au panier"));
		quantiteLabel = new JLabel("Quantit� : ");
		
		// Pour g�rer la quantit� selectionn�e, il ne pourra d�passer la quantit� en stock
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
		
		// D�finition du panneau dans lequel seront pr�sents les boutons de confirmation ou d'annulation 
		// du choix d'un article dans le panier
		JPanel panneauBoutons=new JPanel();
		panneauBoutons.setBackground(Color.white);
		
		JButton boutonValiderSelection= new JButton("Valider S�lection Article");
		JButton boutonAnnulerSelection= new JButton("Annuler S�lection Article");
		
		//D�finition des actions relatives � chaque bouton
		
		boutonValiderSelection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				// TODO il faudra ajouter l'article concern� dans le panier avec la quantit� correspondante
				// et faire les modifications �ventuelles dans la base de donn�es.
				// LigneCommande panierEnCours = new LigneCommande(article,quantite);
				Commande.ajouterArticlePanier(numArticle, quantiteSelectionnee+"", FenetreCommandeArticle.panierClient);
				
				for (int i = 0; i < FenetreCommandeArticle.panierClient.size(); i++) {
					System.out.println("ARTICLE : "+FenetreCommandeArticle.panierClient.get(i)[0]+", quantit� dans panier :"+FenetreCommandeArticle.panierClient.get(i)[1]);
				}
				
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
		
		this.getContentPane().add(panneauQuantite, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
		
	}
}
