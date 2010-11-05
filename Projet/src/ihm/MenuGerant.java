package ihm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MenuGerant extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menuClientele = new JMenu("Client�le");
	private JMenu menuStock = new JMenu("Stock");

	private JMenuItem itemGestionCompte = new JMenuItem("Gestion Compte Client");
	private JMenuItem itemMessagerie = new JMenuItem("Messagerie");
	private JMenuItem itemArticles = new JMenuItem("Articles");
	private JMenuItem itemCommandes = new JMenuItem("Commandes");
	private JMenuItem itemPromotions = new JMenuItem("Promotions");

	public MenuGerant(){
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		// il faut, pour chaque item, lui impl�menter son actionPerformed correspondant
		// � la fenetre qu'il faudra ouvrir 
		
		itemGestionCompte.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre de recherche d'un client
				FenetreRechercheClient recherche = new FenetreRechercheClient(null, "Recherche de clients", true);
				recherche.setVisible(true);
			}
		});
		
		itemMessagerie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre contenant la boite de reception interne du g�rant
				FenetreMessagerie message = new FenetreMessagerie();
				message.setVisible(true);
			}
		});
		
		itemArticles.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre avec la derni�re version du catalogue client
				FenetreCatalogueGerant fenetreCatalogue = new FenetreCatalogueGerant();
				fenetreCatalogue.setVisible(true);
			}
		});
		
		itemCommandes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// Afficher la fenetre contenant le tableau des articles en rupture de stock 
				// et/ou � commander 
				FenetreReapprovisionnement fenetreStock = new FenetreReapprovisionnement();
				fenetreStock.setVisible(true);
			}
		});
		
		itemPromotions.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				//ouvrir la fenetre permettant acc�s au compte
				FenetrePromotionsGerant fenetrePromo = new FenetrePromotionsGerant();
				fenetrePromo.setVisible(true);
			}
		});
		
		// Ajout des diff�rents sous-menus � leur menu correspondant comme cela a �t� d�fini
		// dans la maquette graphique des interfaces
		this.menuClientele.add(itemGestionCompte);
		this.menuClientele.add(itemMessagerie);
		this.menuStock.add(itemArticles);
		this.menuStock.add(itemCommandes);
		this.menuStock.add(itemPromotions);
		
		// Ajout des menus � l'objet barre de menus qui contient tous les menus
		this.menuBar.add(menuClientele);
		this.menuBar.add(menuStock);
		
		//Ajout de la barre de menus � notre objet MenuGerant
		this.setJMenuBar(menuBar);
		// Enfin on rend visible l'objet
		this.setVisible(true);
		
			
	}
}
