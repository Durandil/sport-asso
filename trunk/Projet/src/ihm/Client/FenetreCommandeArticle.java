package ihm.Client;
import ihm.Accueil.FenetreDialogIdentification;
import ihm.modeleTableau.ModelePanier;
import ihm.modeleTableau.ModeleTableauCatalogue;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import metier.*;
import basededonnees.SGBD;

public class FenetreCommandeArticle extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel catalogueLabel;
	private JLabel panierLabel;
	public static ArrayList<String[]> panierClient = Commande.preparerPanier();

	/**
	 * Entier static permettant de conserver la derniere ligne modifiée
	 * du catalogue en cas d'ajout d'article au panier
	 */
	public static int ligneCatalogue;
	
	/**
	 * <p>Entier static permettant de : <ul> 
	 * <li> conserver la dernière ligne modifiée du panier en cas de 
	 * retrait d'article du panier
	 * <li> gérer le problème si un client veut retirer un article de son panier
	 * sans l'avoir selectionné sa ligne correspondante dans le tableau</li>
	 * </ul>
	 * </p>
	 */
	public static int lignePanier = -1 ;
	
	/**
	 * Booléen static permettant de gérer le problème si un client a retiré juste 
	 * avant une certaine quantité d'un article et qu'il souhaite en retirer une partie
	 * sans reactualiser le tableau panier
	 */
	public static boolean retraitPanierPossible = false;
	
	/**
	 * Booléen static permettant de gérer le fait que quand un client ouvre
	 * cette fenetre, il n'a pas encore choisi d'article donc on  met ce booleen à false
	 * et on le passera à true s'il sélectionne correctement une ligne du catalogue
	 */
	public static boolean activationLigneCatalogue=false;	
	
	/**
	 * Booléen static permettant de gérer le problème si gère un client oublie de rafraîchir 
	 * le panier après avoir fait la selection d'un article 
	 */
	public static boolean avoirRafraichiApresAjoutPanier=false;
	
	/**
	 * Booléen static permettant de savoir si le client va utiliser ces bons d'achat pour
	 * cette commande
	 */
	private static boolean utilisationBonReduction=false;
	
	/**
	 * Entier static permettant de gérer le montant du bon d'achat que possède un client
	 */
	static int bonAchat = 0 ;
	
	/**
	 * Définition du constructeur de la classe qui va initialiser la fenetre selon les instructions de la méthode
	 * {@link FenetreCommandeArticle#initComponent()}. Cette classe permet l'affichage simultané du catalogue et du panier du client.
	 */
	public FenetreCommandeArticle(){
		super();
		this.setTitle("Catalogue Article");
		this.setSize(500, 1000);
		this.setLocation(50,50);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
		
	}         

    /**
     * Initialisation     
     */
    private void initComponent(){
    	
    	// Définition du panneau qui ne contiendra que l'affichage de part et d'autre de la fenetre
    	// des intitulés des tableaux catalogie et panier
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new BorderLayout());
    	
    	JPanel panneauCatalogue=new JPanel();
    	catalogueLabel= new JLabel("CATALOGUE");
    	panneauCatalogue.add(catalogueLabel);
    	panneauHaut.add(panneauCatalogue,"West");
    	
    	// Affichage d'un JPanel contenant un bouton à cocher uniquement pour les adhérents
    	// afin de leur demander s'ils veulent utiliser leur bon de réduction
    	JPanel panneauUtilisationBonsReduction = new JPanel();
		
    	//Récupération des informations concernant la fidélité du client
    	ArrayList<String> fideliteClient= SGBD.recupererInformationFideliteClient(FenetreDialogIdentification.clientUserIdentifiant);
		
    	// Si le client est adhérent, on calcule le montant de leur bon de réduction
    	if(fideliteClient.get(0).equals("Oui")){
    		int nbPointsCarte = Integer.parseInt(fideliteClient.get(1));
    		bonAchat = CarteFidelite.calculerBonsReductions(nbPointsCarte);  
		}
    	
    	// Définition du JCheckBox pour leur demander s'ils veulent utiliser leur bon de réduction
    	JCheckBox utiliseBonAchat = new JCheckBox("Cochez si vous voulez utiliser votre bon de réduction de "+ bonAchat + " €");
    	utiliseBonAchat.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				utilisationBonReduction = ((JCheckBox)e.getSource()).isSelected();
			}
		});
    	
    	// Si le client est adhérent, on lui affiche le bouton à cocher dans sa fenêtre
    	if(fideliteClient.get(0).equals("Oui")){
    		panneauUtilisationBonsReduction.add(utiliseBonAchat);
		}
    	
    	panneauHaut.add(panneauUtilisationBonsReduction,"Center");
    	
    	// Définition d'un JPanel contenant le texte Panier et qui sera situé au dessus
    	// du tableau du Panier dans la fenêtre
    	JPanel panneauPanier=new JPanel();
    	panierLabel= new JLabel("PANIER");
    	panneauPanier.add(panierLabel);
    	
    	panneauHaut.add(panneauPanier,"East");
    	
    	
    	// Ajout du panneau au panneau "principal" en haut de la fenetre
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
    	
    	
    	
    	
    	// Définition du panneau qui accueillera le catalogue 
    	JPanel panneauTableauCatalogue = new JPanel();
    	panneauTableauCatalogue.setLayout(new GridLayout(1,2,5,5));
    	
	    final JTable tableau = new JTable(new ModeleTableauCatalogue(false,false));
	    panneauTableauCatalogue.add(new JScrollPane(tableau),"North");
	    
	    this.getContentPane().add(panneauTableauCatalogue, BorderLayout.WEST);
	    
	    // Définition du panneau qui accueillera les aricles du panier
	    final ModelePanier modPan = new ModelePanier(panierClient);
	    final JTable panier = new JTable(modPan);     
	    this.getContentPane().add(new JScrollPane(panier), BorderLayout.EAST);
	     
	    
	    // Définition du panneau des boutons permettant la confirmation ou l'annulation de la commande en cours    
	    JPanel panneauBouton=new JPanel();
	    
	    // Définition de l'action du bouton Choisir un Article qui ouvre une fenêtre
	    // de choix de la quantité que l'on souhaite acheter de l'article
	    JButton commanderArticle = new JButton("Choisir un article");
	    commanderArticle.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// Récupération du numéro d'article sélectionné à partir de la ligne sélectionnée
				ligneCatalogue = tableau.getSelectedRow();
				Object numeroArticle = tableau.getValueAt(ligneCatalogue, 0);
				String numArticle = numeroArticle.toString();
				
				// Récupération de la position de l'article dans le panier afin de récupérer
				// plus facilement sa quantité actuellement commandée
				int indiceQuantitePanier= Commande.rechercheArticleDansPanier(numArticle, panierClient);
				int quantitePanier= Integer.parseInt(panierClient.get(indiceQuantitePanier)[1]);
				
				//Récupération de la quantité actuellement en stock 
				String quantiteStock=SGBD.selectStringConditionString("ARTICLE", "STOCK", "IDARTICLE",numArticle);
				
				// En paramètre, nous avons veillé à ce quantité qui sera affiché dans le JComboBox soit 
				// celle en stock moins celle actuellement commandée pour éviter les erreurs
				FenetreChoixCatalogue fen = new FenetreChoixCatalogue(null,"Achat d'article",true,Integer.parseInt(quantiteStock)-quantitePanier,numArticle);
				fen.setVisible(true);
			}
		});
	    
		JButton boutonValider=new JButton("Valider");
			
		boutonValider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Creation du panier final qui ne contient que les articles commandés en quantité non nulles
				// Enregistrement de  la commande dans la table COMMANDE
				// Calcul du montant de la facture en tenant compte du fait qu'un client 
				// puisse utiliser ses points de fidélité pour les adhérents
				// Modification de la base de données en fonction des quantités et articles achetés
				// Mise à jour de la carte fidélité pour les adhérents
				// Affichage de la facture
				// Vidage du panier
				ArrayList<LigneCommande> listeArticlesPanier= new ArrayList<LigneCommande>();
				
				if(retraitPanierPossible==true && avoirRafraichiApresAjoutPanier == true){
					
					int res = JOptionPane.showConfirmDialog(null, "Confirmer votre commande et afficher la facture","Confirmation",JOptionPane.YES_NO_OPTION);
					if(res == JOptionPane.OK_OPTION){
						// On ne met dans liste des articles que ceux dont la quantité commandée 
						// est supérieure à 0
						for(String[] article : panierClient){
							if(Integer.parseInt(article[1])>0){
								listeArticlesPanier.add(new LigneCommande(article[0],Integer.parseInt(article[1])));
							}
						}
						
						if(listeArticlesPanier.size()>0){
							// Enregistrement de la commande si la liste des articles commandé n'est pas vide
							java.util.Date date = new java.util.Date();
							@SuppressWarnings("deprecation")
							java.sql.Date dateJour = new java.sql.Date(date.getYear(), date.getMonth(), date.getDate());
							Commande nouvelleCommande = new Commande(null, FenetreDialogIdentification.clientUserIdentifiant, listeArticlesPanier, dateJour);
							
							try {
								// Calcul du montant la commande
								double montantCommande = nouvelleCommande.montantTotalArticle(listeArticlesPanier, FenetreDialogIdentification.clientUserIdentifiant);
								
								
								ArrayList<String> fideliteClient= SGBD.recupererInformationFideliteClient(FenetreDialogIdentification.clientUserIdentifiant);
								
								if(utilisationBonReduction==true){
									
									if((Math.round(montantCommande)-bonAchat)<0){
										// Si le montant du bon d'achat est supérieur au montant de le commande
										// alors on enregistre un montant de commande nulle dans la base de données
										nouvelleCommande.majMontantCommande(0);
									}
									else{
										// si le montant du bon d'achat est inférieur au montant de le commande
										// alors on enregistre le montant de la commande auquel on retire le
										// montant du bon d'achat
										nouvelleCommande.majMontantCommande((int)Math.round(montantCommande)-bonAchat);
									}
									
								}
								else{
									// Si le client n'a pas utilisé de bon d'achat, on enregistre le montant 
									// calculé de la commande
									nouvelleCommande.majMontantCommande((int)Math.round(montantCommande));
								}
								
								// Mise à jour du nombre de points sur la carte
								if(fideliteClient.get(0).equals("Oui")){
									int nbPointsAvant = Integer.parseInt(fideliteClient.get(1));
									int pointsRecoltes = (int) Math.round(montantCommande);
									
									CarteFidelite.modifierBDDcarteFidelite(FenetreDialogIdentification.clientUserIdentifiant, nbPointsAvant+pointsRecoltes);
									
								}
									
								// Affichage de la facture
								FenetreFactureCommande fenetre = new FenetreFactureCommande(null, "Facture", true, FenetreDialogIdentification.clientUserIdentifiant,nouvelleCommande,listeArticlesPanier,bonAchat,utilisationBonReduction );
								fenetre.setVisible(true);
								dispose();
								bonAchat=0;
								
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							
						}
						else{
							
							ImageIcon image = new ImageIcon("src/images/warning.png");
							JOptionPane.showMessageDialog(null, " Aucun article n'a été sélectionné dans la commande.", "Attention", JOptionPane.WARNING_MESSAGE, image);
							//affichage d'un message d'erreur en cas d'essai de validation sans aucun article selectionné
						}
						
						// Vidage panier Client
						Commande.viderPanier(panierClient); 
						setVisible(false);
					}
					
					
				}
				else{
					ImageIcon image = new ImageIcon("src/images/warning.png");
					JOptionPane.showMessageDialog(null, " Veuillez rafraichir le panier avant de valider votre commande !!!", "Attention", JOptionPane.WARNING_MESSAGE, image);
					//affichage d'un message d'erreur en cas de tentative de validation de la commande sans avoir effectué le rafraichissement du panier
				}
			}
		});
		
		// Définition de l'action du bouton Retour à la page précédente
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// le bouton retour permet l'annulation de la commande en cours et le retour au menu utilisateur
				dispose();
				// vider le panier une fois que le client a appuyé sur le bouton
				Commande.viderPanier(panierClient);
			}			
		});
		
		// Définition de l'action du bouton Retirer un article du panier qui ouvre une fenetre
		// permettant de sélectionner la quantité que l'on souhaite retirer d'un article
		// sélectionné dans le tableau Panier
		final JButton retirerPanierBouton = new JButton("Retirer un article du panier");
		retirerPanierBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				lignePanier = panier.getSelectedRow();
				
				if(retraitPanierPossible == false){
					ImageIcon image = new ImageIcon("src/images/warning.png");
					JOptionPane.showMessageDialog(null, " Veuillez rafraichir le panier !!!", "Attention", JOptionPane.WARNING_MESSAGE, image);
					// Affichage d'un message d'erreur en cas de tentative de 
					//retrait d'un article sans avoir rafraichi le panier après avoir déjà
					// effectué un retrait d'une certaine quantité de ce même article
				}
				
				if(lignePanier == -1){
					ImageIcon image = new ImageIcon("src/images/warning.png");
					JOptionPane.showMessageDialog(null, " Veuillez selectionner une ligne article dans le panier", "Attention", JOptionPane.WARNING_MESSAGE, image);
					// Affichage d'un message d'erreur en cas de tentative de retrait d'article sans avoir selectionné d'article
				}
				
				if(avoirRafraichiApresAjoutPanier==false){
					ImageIcon image = new ImageIcon("src/images/warning.png");
					JOptionPane.showMessageDialog(null, " Veuillez rafraichir le panier !!!", "Attention", JOptionPane.WARNING_MESSAGE, image);
					// Affichage d'un message d'erreur en cas de tentative de retrait d'article
					// du panier sans avoir rafraîchi le panier après avoir effectué un ajout
					// d'article au panier
				}
				
				// ligne Panier <> - 1 gère le pb si un client veut retirer un article de son panier
				// sans l'avoir selectionné dans le tableau
				
				// retraitPanierPossible == true gère le pb si un client a retiré juste avant une certaine
				// quantité d'un article et qu'il souhaite en retirer une partie sans reactualiser le tableau panier
				
				// avoirRafraichiApresAjoutPanier==false gère le pb si un client oublie de refraichir 
				// le panier après avoir fait la selection d'un article
				
				if(lignePanier != - 1 && retraitPanierPossible == true && avoirRafraichiApresAjoutPanier==true){
					// Récupération du numéro de l'article 
					Object numeroArticle = panier.getValueAt(lignePanier, 0);
					String numArticle = numeroArticle.toString();
					
					String quantitePanier = panier.getValueAt(lignePanier, 1).toString();
					// Ouverture de la fenêtre 
					FenetreSuppressionPanier fenetreRetrait = new FenetreSuppressionPanier(null, "Retrait d'article du panier", true,numArticle,Integer.parseInt(quantitePanier) );
					fenetreRetrait.setVisible(true);
				}

			}			
		});
		
		//tant qu'on a pas reactualisé le panier initial qui est vide, on ne peut pas utiliser 
		//le bouton de retrait d'article
		retirerPanierBouton.setEnabled(false);
		
		// Définition de l'action du bouton Rafraîchir le panier
		JButton rafraichirPanierBouton = new JButton("Rafraichir le panier");
		rafraichirPanierBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// Le bouton permet de rafraichir le tableau panier
				modPan.actualiserLignes(panierClient);
				modPan.fireTableDataChanged();
			
				// Condition nécessaire pour gérer le fait qu'un client ne selectionne
				// aucune ligne dans le catalogue avant d'appuyer sur rafraîchir
				if(activationLigneCatalogue==true){
					retirerPanierBouton.setEnabled(true);
					retraitPanierPossible=true;
					avoirRafraichiApresAjoutPanier=true;
				}
				else{
					ImageIcon image = new ImageIcon("src/images/warning.png");
					JOptionPane.showMessageDialog(null, " Veuillez selectionner une ligne article dans le catalogue et appuyez sur le bouton Choisir Article", "Attention", JOptionPane.WARNING_MESSAGE, image);
				}
				
			}			
		});
		
		panneauBouton.add(commanderArticle);
		panneauBouton.add(boutonValider);
		panneauBouton.add(retourBouton);
		panneauBouton.add(rafraichirPanierBouton);
		panneauBouton.add(retirerPanierBouton);
			
	
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
  
	    pack();
	    
	   
        }
	
	
}
