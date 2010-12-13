package ihm.Gerant;


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

import metier.Article;

public class FenetreCommandeReapprovisionnement extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel quantiteLabel;
	private JComboBox quantite;
	public static int quantiteSelectionnee ;
	
	/**
	 * Constructeur de la classe  dans laquelle le g�rant pourra choisir 
	 * la quantit�e qu'il d�sire commander de l'article s�lectionn� dans le tableau des
	 * articles en rupture de stock ou en quantit� insuffisante
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public FenetreCommandeReapprovisionnement(JFrame parent, String title, boolean modal,String idArticle){
		super(parent, title, modal);
		this.setSize(400, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(idArticle);
	}
	
	private void initComponent(String idArticle){
		
		final String identifiantArticle=idArticle;
		
		// D�finition du panneau dans lequel le g�rant s�lectionnera la quantit� d'un article
		JPanel panneauQuantite=new JPanel();
		panneauQuantite.setBackground(Color.white);
		panneauQuantite.setPreferredSize(new Dimension(220, 60));
		panneauQuantite.setBorder(BorderFactory.createTitledBorder("Ajout au panier"));
		quantiteLabel = new JLabel("Quantit� : ");
		
		// Pour g�rer la quantit� selectionn�e, il ne pourra d�passer la quantit� en stock
		quantite=new JComboBox();
		
		for(int j=1;j<501;j++){
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
		// de la commande d'un article en rupture de stock
		JPanel panneauBoutons=new JPanel();
		panneauBoutons.setBackground(Color.white);
		
		JButton boutonValiderSelection= new JButton("Valider Commande Article");
		JButton boutonAnnulerSelection= new JButton("Annuler Commande Article");
		
		//D�finition des actions relatives � chaque bouton
		
		boutonValiderSelection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				Article.modifierStockArticleBDD(identifiantArticle, quantiteSelectionnee);
				setVisible(false); 
			}			
		});
		
		boutonAnnulerSelection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// permet d'annuler la commande en cours et retour vers page principale
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
