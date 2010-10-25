package ihm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MenuGerant extends JFrame {
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu premier_menu = new JMenu("Clientèle");
	private JMenu deuxieme_menu = new JMenu("Stock");

	private JMenuItem item1 = new JMenuItem("Gestion Compte Client");
	private JMenuItem item2 = new JMenuItem("Messagerie");
	private JMenuItem item3 = new JMenuItem("Articles");
	private JMenuItem item4 = new JMenuItem("Commandes");
	private JMenuItem item5 = new JMenuItem("Promotions");

	public MenuGerant(){
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		// il faut, pour chaque item, lui implémenter son actionPerformed correspondant
		// à la fenetre qu'il faudra ouvrir 
		
		
		
		item1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre de recherche d'un client
				FenetreRechercheClient recherche = new FenetreRechercheClient(null, "Recherche de clients", true);
				recherche.setVisible(true);
			}
		});
		
		item2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre contenant la boite de reception interne du gérant
				FenetreMessagerie message = new FenetreMessagerie();
				message.setVisible(true);
			}
		});
		
		item3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre avec la dernière version du catalogue client
				FenetreCatalogueGerant fenetreCatalogue = new FenetreCatalogueGerant();
				fenetreCatalogue.setVisible(true);
			}
		});
		
		item4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre contenant le tableau des articles en rupture de stock 
				// et/ou à commander 
				FenetreReapprovisionnement fenetreStock = new FenetreReapprovisionnement();
				fenetreStock.setVisible(true);
			}
		});
		
		item5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				//ouvrir la fenetre permettant accès au compte
				FenetrePromotionsGerant fenetrePromo = new FenetrePromotionsGerant();
				fenetrePromo.setVisible(true);
			}
		});
		
		// Ajout des différents sous-menus à leur menu correspondant comme cela a été défini
		// dans la maquette graphique des interfaces
		this.premier_menu.add(item1);
		this.premier_menu.add(item2);
		this.deuxieme_menu.add(item3);
		this.deuxieme_menu.add(item4);
		this.deuxieme_menu.add(item5);
		
		// Ajout des menus à l'objet barre de menus qui contient tous les menus
		this.menuBar.add(premier_menu);
		this.menuBar.add(deuxieme_menu);
		
		//Ajout de la barre de menus à notre objet MenuGerant
		this.setJMenuBar(menuBar);
		// Enfin on rend visible l'objet
		this.setVisible(true);
		
			
	}
}
