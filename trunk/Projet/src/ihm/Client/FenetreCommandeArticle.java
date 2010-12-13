package ihm.Client;
import ihm.FenetreChoixCatalogue;
import ihm.Accueil.FenetreDialogIdentification;
import ihm.modeleTableau.ModelePanier;
import ihm.modeleTableau.ModeleTableauCatalogue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import metier.CarteFidelite;
import metier.Commande;
import metier.LigneCommande;

import basededonnees.SGBD;


public class FenetreCommandeArticle extends JFrame{
	// Creer la base de données correspondante aux articles 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel catalogueLabel;
	private JLabel panierLabel;
	public static ArrayList<String[]> panierClient = Commande.preparerPanier();
	// pour conserver la derniere ligne modifiée du catalogue en cas ajout article panier
	public static int ligneCatalogue;
	// pour conserver la dernière ligne modifiée du panier en cas de retrait article panier
	public static int lignePanier = -1 ;
	public static boolean retraitPanierPossible = false;
	public static boolean activationLigneCatalogue=false;	
	// quand un client ouvre cette fenetre, il n'a pas encore choisi d'article 
	// donc on  met ce booleen à false et on changera la valeur s'il le fait correctement
	public static boolean avoirRafraichiApresAjoutPanier=false;
	
	/*
	 * Définition du constructeur de la classe qui va initialiser la fenetre selon les instructions de la méthode
	 * initComponent(). Cette classe permet l'affichage simultané du catalogue et du panier du client.
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

        
    private void initComponent(){
    	
    	// Définition du panneau qui ne contiendra que l'affichage de part et d'autre de la fenetre
    	// des intitulés des tableaux catalogie e panier
    	JPanel panneauHaut= new JPanel();
    	panneauHaut.setLayout(new BorderLayout());
    	
    	JPanel panneauCatalogue=new JPanel();
    	catalogueLabel= new JLabel("CATALOGUE");
    	panneauCatalogue.add(catalogueLabel);
    	panneauHaut.add(panneauCatalogue,"West");
    	
    	
    	JPanel panneauPanier=new JPanel();
    	panierLabel= new JLabel("PANIER");
    	panneauPanier.add(panierLabel);
    	panneauHaut.add(panneauPanier,"East");
    	
    	
    	// Ajout du panneau au panneau "principal" en haut de la fenetre
    	this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
    	
    	// Définition du panneau qui accueillera le catalogue et un JComboBox de tri du tableau
    	// selon divers critères
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
	    
	    
	    JButton commanderArticle = new JButton("Choisir un article");
	    commanderArticle.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ligneCatalogue = tableau.getSelectedRow();
				Object numeroArticle = tableau.getValueAt(ligneCatalogue, 0);
				String numArticle = numeroArticle.toString();
				
				int indiceQuantitePanier= Commande.rechercheArticleDansPanier(numArticle, panierClient);
				int quantitePanier= Integer.parseInt(panierClient.get(indiceQuantitePanier)[1]);
				
				String quantiteStock=SGBD.selectStringConditionString("ARTICLE", "STOCK", "IDARTICLE",numArticle);
				FenetreChoixCatalogue fen = new FenetreChoixCatalogue(null,"Achat d'article",true,Integer.parseInt(quantiteStock)-quantitePanier,numArticle);
				fen.setVisible(true);
			}
		});
	    
		JButton boutonValider=new JButton("Valider");
			
		boutonValider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// TODO il faudra modifier la base de données en fonction des quantités et articles achetés
				// et enregistrer la commande dans la table COMMANDE
				// puis vider le panier
				ArrayList<LigneCommande> listeArticlesPanier= new ArrayList<LigneCommande>();
				
				if(retraitPanierPossible==true && avoirRafraichiApresAjoutPanier == true){
					
					// on ne met dans liste des articles que ceux dont la quantité commandée 
					// est supérieure à 0
					for(String[] article : panierClient){
						if(Integer.parseInt(article[1])>0){
							listeArticlesPanier.add(new LigneCommande(article[0],Integer.parseInt(article[1])));
						}
					}
					
					if(listeArticlesPanier.size()>0){
						java.util.Date date = new java.util.Date();
					
						@SuppressWarnings("deprecation")
						java.sql.Date dateJour = new java.sql.Date(date.getYear(), date.getMonth(), date.getDate());
					
						
						Commande nouvelleCommande = new Commande(null, FenetreDialogIdentification.clientUserIdentifiant, listeArticlesPanier, dateJour);
						try {
							// calcul de la commande
							double montantCommande = nouvelleCommande.montantTotalArticle(listeArticlesPanier, FenetreDialogIdentification.clientUserIdentifiant);
<<<<<<< .mine

=======
							for(LigneCommande ligne : listeArticlesPanier){
								System.out.println(ligne.getIdArticle()+ " qté : " + ligne.getQuantite());
							}
>>>>>>> .r179
							System.out.println("Montant commande : " + montantCommande);
							
							// mise à jour du nombre de points sur la carte
							ArrayList<String> fideliteClient= SGBD.recupererInformationFideliteClient(FenetreDialogIdentification.clientUserIdentifiant);
							if(fideliteClient.get(0).equals("Oui")){
								int nbPointsAvant = Integer.parseInt(fideliteClient.get(1));
								int pointsRecoltes = (int) Math.round(montantCommande);
								
								CarteFidelite.modifierBDDcarteFidelite(FenetreDialogIdentification.clientUserIdentifiant, nbPointsAvant+pointsRecoltes);
								
							}

						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					else{
						ImageIcon image = new ImageIcon("src/images/warning.png");
						JOptionPane.showMessageDialog(null, " Aucun article n'a été sélectionné dans la commande.", "Attention", JOptionPane.WARNING_MESSAGE, image);
						//affichage d'un message d'erreur en cas d'essai de validation sans aucun article selectionné
					}
					
					Commande.viderPanier(panierClient); // vidage panier Client
					setVisible(false);
					
				}
				else{
					ImageIcon image = new ImageIcon("src/images/warning.png");
					JOptionPane.showMessageDialog(null, " Veuillez rafraichir le panier avant de valider votre commande !!!", "Attention", JOptionPane.WARNING_MESSAGE, image);
					//affichage d'un message d'erreur en cas de tentative de validation de la commande sans avoir effectué le rafraichissement du panier
				}
			}
		});
			
		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// le bouton retour permet l'annulation de la commande en cours et le retour au menu utilisateur
				setVisible(false);
				// vider le panier une fois que le client a appuyé sur le bouton
				Commande.viderPanier(panierClient);
			}			
		});
		
		final JButton retirerPanierBouton = new JButton("Retirer un article du panier");
		retirerPanierBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				lignePanier = panier.getSelectedRow();
				
				if(retraitPanierPossible == false){
					ImageIcon image = new ImageIcon("src/images/warning.png");
					JOptionPane.showMessageDialog(null, " Veuillez rafraichir le panier !!!", "Attention", JOptionPane.WARNING_MESSAGE, image);
					//affichage d'un message d'erreur en cas de tentative de retrait d'article sans avoir rafraichi le panier
				}
				
				if(lignePanier == -1){
					ImageIcon image = new ImageIcon("src/images/warning.png");
					JOptionPane.showMessageDialog(null, " Veuillez selectionner une ligne article dans le panier", "Attention", JOptionPane.WARNING_MESSAGE, image);
					//affichage d'un message d'erreur en cas de tentative de retrait d'article sans avoir selectionné d'article
				}
				
				if(avoirRafraichiApresAjoutPanier==false){
					ImageIcon image = new ImageIcon("src/images/warning.png");
					JOptionPane.showMessageDialog(null, " Veuillez rafraichir le panier !!!", "Attention", JOptionPane.WARNING_MESSAGE, image);
				}
				
				// ligne Panier <> - 1 gère le pb si un client veut retirer un article de son panier
				// sans l'avoir selectionné dans le tableau
				
				// retraitPanierPossible == true gère le pb si un client a retiré juste avant une certaine
				// quantité d'un article et qu'il souhaite en retirer une partie sans reactualiser le tableau panier
				
				// avoirRafraichiApresAjoutPanier==false gère le pb si un client oublie de refraichir 
				// le panier après avoir fait la selection d'un article
				
				if(lignePanier != - 1 && retraitPanierPossible == true && avoirRafraichiApresAjoutPanier==true){
					Object numeroArticle = panier.getValueAt(lignePanier, 0);
					String numArticle = numeroArticle.toString();
					
					String quantitePanier = panier.getValueAt(lignePanier, 1).toString();
					FenetreSuppressionPanier fenetreRetrait = new FenetreSuppressionPanier(null, "Retrait d'article du panier", true,numArticle,Integer.parseInt(quantitePanier) );
					fenetreRetrait.setVisible(true);
				}

			}			
		});
		//tant qu'on a pas reactualisé le panier initial qui est vide, on ne peut pas utiliser 
		//le bouton de retrait d'article
		retirerPanierBouton.setEnabled(false);
		
		
		JButton rafraichirPanierBouton = new JButton("Rafraichir le panier");
		rafraichirPanierBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// le bouton permet de rafraichir le tableau panier
				modPan.actualiserLigne(ligneCatalogue, panierClient);
				modPan.fireTableDataChanged();
				System.out.println("Dernière Ligne modifiée : "+ligneCatalogue);
				
				// condition nécessaire pour gerer le fait qu'un client ne selectionne
				// aucune ligne dans le catalogue avant appuyer sur rafraichir
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
