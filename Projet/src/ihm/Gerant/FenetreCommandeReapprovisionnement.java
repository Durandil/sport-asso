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
/**
 * <b> Cette classe permet la sélection de la quantité pour réapprovisionner
 *  un article avec une faible quantité en stock. <b>
 * <p> Elle apparaît généralement après avoir sélectionné correctement l'article que l'on 
 * souhaite réapprovisionner dans {@link FenetreCommandeReapprovisionnement}.</p>
 * 
 * @author Utilisateur
 * 
 * {@link FenetreReapprovisionnement}
 *
 */
public class FenetreCommandeReapprovisionnement extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel quantiteLabel;
	private JComboBox quantite;
	public static int quantiteSelectionnee ;
	
	/**
	 * Constructeur de la classe  dans laquelle le gérant pourra choisir 
	 * la quantitée qu'il désire commander de l'article sélectionné dans le tableau des
	 * articles en rupture de stock ou en quantité insuffisante
	 * 
	 * @param parent
	 *            JFrame utilisé pour créer la fenêtre
	 * @param title
	 *            String indiquant le titre de la fenêtre
	 * @param modal
	 *            Booléen indiquant si la fenêtre doit bloquer ou non les
	 *            interactions avec les autres fenêtres
	 * @param idArticle
	 * 			  Identifiant unique de l'article à réapprovisionner
	 */
	public FenetreCommandeReapprovisionnement(JFrame parent, String title, boolean modal,String idArticle){
		super(parent, title, modal);
		this.setSize(400, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(idArticle);
	}
	
	/**
	 * <p> Initialisation des composants de la fenêtre : <ul>
	 * <li> un JPanel contenant un JComboBox dans lequel le gérant sélectionnera la quantité 
	 * à réapprovisionner de l'article.</li>
	 * <li> un JPanel contenant deux boutons : l'un permettant de valider le réapprovisionnement
	 * et l'autre pour l'annuler. </li>
	 * </ul>
	 * </p>
	 * 
	 * @param idArticle
	 * 				Identifiant unique de l'article à réapprovisionner
	 */
	private void initComponent(String idArticle){
		
		final String identifiantArticle=idArticle;
		
		// Définition du panneau dans lequel le gérant sélectionnera la quantité d'un article //
		//------------------------------------------------------------------------------------//
		JPanel panneauQuantite=new JPanel();
		panneauQuantite.setBackground(Color.white);
		panneauQuantite.setPreferredSize(new Dimension(220, 60));
		panneauQuantite.setBorder(BorderFactory.createTitledBorder("Ajout au panier"));
		quantiteLabel = new JLabel("Quantité : ");
		
		// Pour gérer la quantité selectionnée, il ne pourra dépasser la quantité en stock
		quantite=new JComboBox();
		
		for(int j=1;j<501;j++){
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
		
		// Définition du panneau dans lequel seront présents les boutons de confirmation //
		// -------ou d'annulation de la commande d'un article en rupture de stock--------//
		JPanel panneauBoutons=new JPanel();
		panneauBoutons.setBackground(Color.white);
		
		// Définition de l'action relative au bouton valider  qui va effectuer la mise à //
		// ---- jour du stock dans la base de données et revenir au menu précédent ------//
		//-------------------------------------------------------------------------------//
		
		JButton boutonValiderSelection= new JButton("Valider Commande Article");
		
		boutonValiderSelection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				Article.modifierStockArticleBDD(identifiantArticle, quantiteSelectionnee);
				dispose();
				FenetreReapprovisionnement fen = new FenetreReapprovisionnement(null, true);
				fen.setVisible(true);
			}			
		});
		
		// Définition de l'action du bouton pour annuler le réapprovisionnement de //
		//---------------l'article passé en paramètre de la méthode----------------//
		
		JButton boutonAnnulerSelection= new JButton("Annuler Commande Article");
		
		boutonAnnulerSelection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// permet d'annuler la commande en cours et retour vers page principale
				dispose();
				FenetreReapprovisionnement fen = new FenetreReapprovisionnement(null,true);
				fen.setVisible(true);
			}			
		});
		
		//Ajout des boutons au JPanel principal des boutons panneauBoutons //
		//-----------------------------------------------------------------//
		panneauBoutons.add(boutonValiderSelection);
		panneauBoutons.add(boutonAnnulerSelection);
		
		// Ajout des deux JPanel au conteneur de la fenêtre //
		//--------------------------------------------------//
		this.getContentPane().add(panneauQuantite, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
		
	}
}
