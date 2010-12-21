package ihm;
import ihm.Accueil.FenetreCompte;

import ihm.Gerant.FenetreCatalogueGerant;
import ihm.Gerant.FenetrePromotionsGerant;
import ihm.Gerant.FenetreReapprovisionnement;
import ihm.Gerant.FenetreRechercheClient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import exception.Client.ExceptionMailsDifferents;


public class MenuGerant extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Attributs de la classe Menu Gerant qui va accueillir une barre de menu
	 * Les JMenu vont accueillir les JMenuItem qui les sous-menus sur lesquels le gérant pourra cliquer et en 
	 * même temps accéder aux différentes fontionnalités spécifiées dans son cas d'utilisation
	 * 
	 */
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menuClientele = new JMenu("Gestion Clientèle");
	private JMenu menuStock = new JMenu("Gestion des Stocks");
	
	private JMenuItem itemGestionCompte = new JMenuItem("Recherche client");
	private JMenuItem itemMessagerie = new JMenuItem("Messagerie interne ");
	private JMenuItem itemFermer = new JMenuItem("Fermer le compte gérant");
	private JMenuItem itemArticles = new JMenuItem("Gestion des articles");
	private JMenuItem itemCommandes = new JMenuItem("Commandes de réapprovisionnement");
	private JMenuItem itemPromotions = new JMenuItem("Gestion des promotions exceptionnelles");
	
	private JLabel icon;
	
	/**
	 * Constructeur de la classe MenuGérant dans lequel nous spécifions toutes les caractéristiques de
	 * notre menu. Dans un premier temps, on insère une image de fond pour la fenetre. Puis, pour chaque item,
	 * nous allons lui spécifier son action (ie la nouvelle fenêtre à afficher) puis nous allons insérés les items
	 * aux deux menus de la grande barre de menu.
	 * 
	 */
	public MenuGerant(){
		this.setPreferredSize(new Dimension(900,600));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocation(0,0);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		//Insertion de l'image au panneau final
		icon = new JLabel(new ImageIcon("src/images/fenetre.jpg"));
		JPanel panImage = new JPanel();
		panImage.setBackground(Color.white);
		panImage.setLayout(new BorderLayout());
		panImage.add(icon);
		
		this.getContentPane().add(panImage,"West");
		
		// il faut, pour chaque item, lui implémenter son actionPerformed correspondant
		// à la fenetre qu'il faudra ouvrir 
		
		itemGestionCompte.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Afficher la fenetre de recherche d'un client
				FenetreRechercheClient recherche = new FenetreRechercheClient(null, "Recherche de clients", true);
				recherche.setVisible(true);
					
			}
		});
		
		itemMessagerie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Afficher la fenetre contenant la boite de reception interne du gérant
				FenetreMessagerie message = new FenetreMessagerie(true);
				message.setVisible(true);
			}
		});
		
		itemFermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				int res = JOptionPane.showConfirmDialog(null, "Confirmez-vous la fermeture du compte gérant ?","Confirmation",JOptionPane.YES_NO_OPTION);
				if(res==JOptionPane.OK_OPTION){
					dispose();
					try {
						FenetreCompte fen = new FenetreCompte();
						fen.setVisible(true);
					} catch (ExceptionMailsDifferents e1) {
						e1.printStackTrace();
					}
				}
				
			}				
		});
		
		itemArticles.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Afficher la fenetre avec la dernière version du catalogue client
				FenetreCatalogueGerant fenetreCatalogue = new FenetreCatalogueGerant();
				fenetreCatalogue.setVisible(true);
			}
		});
		
		itemCommandes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Afficher la fenetre contenant le tableau des articles en rupture de stock 
				// et/ou à commander 
				FenetreReapprovisionnement fenetreStock = new FenetreReapprovisionnement();
				fenetreStock.setVisible(true);
			}
		});
		
		itemPromotions.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//ouvrir la fenetre permettant accès au compte
				FenetrePromotionsGerant fenetrePromo = new FenetrePromotionsGerant();
				fenetrePromo.setVisible(true);
			}
		});

		
		// Ajout des différents sous-menus à leur menu correspondant comme cela a été défini
		// dans la maquette graphique des interfaces
		this.menuClientele.add(itemGestionCompte);
		this.menuClientele.add(itemMessagerie);
		this.menuClientele.add(itemFermer);
		this.menuStock.add(itemArticles);
		this.menuStock.add(itemCommandes);
		this.menuStock.add(itemPromotions);
		
		// Ajout des menus à l'objet barre de menus qui contient tous les menus
		this.menuBar.add(menuClientele);
		this.menuBar.add(menuStock);
		
		//Ajout de la barre de menus à notre objet MenuGerant
		this.setJMenuBar(menuBar);
		// Enfin on rend visible l'objet
		this.setVisible(true);
		
		pack();
	}
}
