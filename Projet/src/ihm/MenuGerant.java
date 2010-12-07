package ihm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import basededonnees.SGBD;


public class MenuGerant extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menuClientele = new JMenu("Clientèle");
	private JMenu menuStock = new JMenu("Stock");

	private JMenuItem itemGestionCompte = new JMenuItem("Gestion Compte Client");
	private JMenuItem itemMessagerie = new JMenuItem("Messagerie");
	private JMenuItem itemFermer = new JMenuItem("Fermer");
	private JMenuItem itemArticles = new JMenuItem("Articles");
	private JMenuItem itemCommandes = new JMenuItem("Commandes");
	private JMenuItem itemPromotions = new JMenuItem("Promotions");

	private JLabel icon;

	public MenuGerant(){
		this.setPreferredSize(new Dimension(900,600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(0,0);
		
		this.setLayout(new BorderLayout());
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
				ArrayList<String> liste = new ArrayList<String>();
				
				
				
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
				System.exit(0);
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
