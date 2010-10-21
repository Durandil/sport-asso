package ihm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MenuGerant extends JFrame {
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu premier_menu = new JMenu("Client�le");
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
		
		// il faut, pour chaque item, lui impl�menter son actionPerformed correspondant
		// � la fenetre qu'il faudra ouvrir 
		
		item5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				//ouvrir la fenetre permettant acc�s au compte
				FenetrePromotionsGerant fenetrePromo = new FenetrePromotionsGerant();
				fenetrePromo.setVisible(true);
			}
		});
		
		item3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre avec la derni�re version du catalogue client
				FenetreCatalogueGerant fenetreCatalogue = new FenetreCatalogueGerant();
				fenetreCatalogue.setVisible(true);
			}
		});
		
		item1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre de recherche d'un client
				FenetreRechercheClient recherche = new FenetreRechercheClient(null, "Recherche de clients", true);
				recherche.setVisible(true);
			}
		});
		
		item2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre contenant la boite de reception interne du g�rant
				FenetreMessagerie message = new FenetreMessagerie();
				message.setVisible(true);
			}
		});
		
		item4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre contenant le tableau des articles en rupture de stock 
				// et/ou � commander 
				FenetreReapprovisionnement fenetreStock = new FenetreReapprovisionnement();
				fenetreStock.setVisible(true);
			}
		});
		
		this.premier_menu.add(item1);
		this.premier_menu.add(item2);
		this.deuxieme_menu.add(item3);
		this.deuxieme_menu.add(item4);
		this.deuxieme_menu.add(item5);
		
		this.menuBar.add(premier_menu);
		this.menuBar.add(deuxieme_menu);
		
		this.setJMenuBar(menuBar);
		this.setVisible(true);
			
	}
}
