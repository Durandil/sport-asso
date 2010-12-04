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
import metier.LigneCommande;


public class FenetreSuppressionPanier extends JDialog {
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
	public FenetreSuppressionPanier(JFrame parent, String title, boolean modal,String numeroArticle,int quantitePanier){
		super(parent, title, modal);
		this.setSize(500, 300);
		this.setLocation(50,50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(quantitePanier,numeroArticle);
	}
	
	private void initComponent(int quantiteEntree, String idArticle){
		final String identifiantArticle = idArticle;
		
		// D�finition du panneau dans lequel le client s�lectionnera la quantit� d'un article
		JPanel panneauQuantite=new JPanel();
		panneauQuantite.setBackground(Color.white);
		panneauQuantite.setPreferredSize(new Dimension(220, 60));
		panneauQuantite.setBorder(BorderFactory.createTitledBorder("Supprimer du panier"));
		quantiteLabel = new JLabel("Quantit� : ");
		
		// Pour g�rer la quantit� selectionn�e, il ne pourra d�passer la quantit� qu'il a d�j� prise
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
		
		// D�finition du panneau dans lequel seront pr�sents les boutons de confirmation ou d'annulation 
		// du choix d'un article dans le panier
		JPanel panneauBoutons=new JPanel();
		panneauBoutons.setBackground(Color.white);
		
		JButton boutonValiderSuppression= new JButton("Valider Modification Panier");
		JButton boutonAnnulerSuppression= new JButton("Annuler Modification Panier");
		
		//D�finition des actions relatives � chaque bouton
		
		boutonValiderSuppression.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO il faudra faire les modifications �ventuelles dans le panier.
				Commande.enleverArticlePanier(identifiantArticle, quantiteSelectionnee+"", FenetreCommandeArticle.panierClient);
				
				for (int i = 0; i < FenetreCommandeArticle.panierClient.size(); i++) {
					System.out.println("ARTICLE : "+FenetreCommandeArticle.panierClient.get(i)[0]+", quantit� dans panier :"+FenetreCommandeArticle.panierClient.get(i)[1]);
				}
				setVisible(false);
				
			}			
		});
		
		boutonAnnulerSuppression.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// permet d'annuler la s�lection en cours et retour vers page principale
				setVisible(false);
			}			
		});
		
		//Ajout des boutons au panneau principal des boutons panneauBoutons
		panneauBoutons.add(boutonValiderSuppression);
		panneauBoutons.add(boutonAnnulerSuppression);
		
		this.getContentPane().add(panneauQuantite, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
		
	}
}

