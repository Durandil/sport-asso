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
 * <b> Cette classe permet la s�lection de la quantit� pour r�approvisionner
 *  un article avec une faible quantit� en stock. <b>
 * <p> Elle appara�t g�n�ralement apr�s avoir s�lectionn� correctement l'article que l'on 
 * souhaite r�approvisionner dans {@link FenetreCommandeReapprovisionnement}.</p>
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
	 * Constructeur de la classe  dans laquelle le g�rant pourra choisir 
	 * la quantit�e qu'il d�sire commander de l'article s�lectionn� dans le tableau des
	 * articles en rupture de stock ou en quantit� insuffisante
	 * 
	 * @param parent
	 *            JFrame utilis� pour cr�er la fen�tre
	 * @param title
	 *            String indiquant le titre de la fen�tre
	 * @param modal
	 *            Bool�en indiquant si la fen�tre doit bloquer ou non les
	 *            interactions avec les autres fen�tres
	 * @param idArticle
	 * 			  Identifiant unique de l'article � r�approvisionner
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
	 * <p> Initialisation des composants de la fen�tre : <ul>
	 * <li> un JPanel contenant un JComboBox dans lequel le g�rant s�lectionnera la quantit� 
	 * � r�approvisionner de l'article.</li>
	 * <li> un JPanel contenant deux boutons : l'un permettant de valider le r�approvisionnement
	 * et l'autre pour l'annuler. </li>
	 * </ul>
	 * </p>
	 * 
	 * @param idArticle
	 * 				Identifiant unique de l'article � r�approvisionner
	 */
	private void initComponent(String idArticle){
		
		final String identifiantArticle=idArticle;
		
		// D�finition du panneau dans lequel le g�rant s�lectionnera la quantit� d'un article //
		//------------------------------------------------------------------------------------//
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
				
				String choix= (String) ((JComboBox) e.getSource()).getSelectedItem();
				quantiteSelectionnee=Integer.parseInt(choix);
			}
		});
		
		panneauQuantite.add(quantiteLabel);
		quantite.setVisible(true);
		panneauQuantite.add(quantite);
		
		// D�finition du panneau dans lequel seront pr�sents les boutons de confirmation //
		// -------ou d'annulation de la commande d'un article en rupture de stock--------//
		JPanel panneauBoutons=new JPanel();
		panneauBoutons.setBackground(Color.white);
		
		// D�finition de l'action relative au bouton valider  qui va effectuer la mise � //
		// ---- jour du stock dans la base de donn�es et revenir au menu pr�c�dent ------//
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
		
		// D�finition de l'action du bouton pour annuler le r�approvisionnement de //
		//---------------l'article pass� en param�tre de la m�thode----------------//
		
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
		
		// Ajout des deux JPanel au conteneur de la fen�tre //
		//--------------------------------------------------//
		this.getContentPane().add(panneauQuantite, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
		
	}
}
