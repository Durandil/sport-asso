package ihm;
import ihm.DialogInfo;
import ihm.DialogMessage;
import ihm.FenetreCommandeArticle;
import ihm.FenetreContactVendeur;
import ihm.FenetreDialogGestionCompteClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;


public class MenuUtilisateur extends JFrame{
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu deuxieme_menu = new JMenu("Mon Compte");
	private JMenu troisieme_menu = new JMenu("Catalogue");
	private JMenu quatrieme_menu = new JMenu("Promotions");
	private JMenu cinquieme_menu = new JMenu("Contact");
		
	
	private JMenuItem item2 = new JMenuItem("Fermer");
	private JMenuItem item3 = new JMenuItem("Mes informations");
	private JMenuItem item4 = new JMenuItem("Mon programme fid�lit�");
	private JMenuItem item5 = new JMenuItem("Nous Contacter");
	private JMenuItem item6 = new JMenuItem("Informations");
	private JMenuItem item7 = new JMenuItem("Articles");
	private JMenuItem item8 = new JMenuItem("Promotions en cours");
	
	// Cette variable static contiendra normalement le client en cours d'utilisation de l'application
	// pour le moment elle sert � faire fonctionner la fen�tre
	public static DialogInfo client = new DialogInfo("Orange SA","rue de la d�fense", "Nice", "13000", "0410322322", "mpanel@orange.fr");
	
	public MenuUtilisateur(){
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
			
		//On initialise nos sous-menus (JMenuItem) avec leurs actions correspondantes
		//--------------------------
			
		item2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}				
		});
		
		item3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				//ouvrir la fenetre permettant acc�s au compte
				FenetreDialogGestionCompteClient fenetreGestionCompte =new FenetreDialogGestionCompteClient(null,"Informations client",true,client);
				DialogInfo gestion= fenetreGestionCompte.showClient();
			}
		});
		
		item4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				//ouvrir la fenetre permettant acc�s au informations sur programme de fidelite
				 FenetreFideliteClient fenetreProgrammeFidelite =new  FenetreFideliteClient(null,"programme fidelit�",true,client);
				DialogInfo gestion= fenetreProgrammeFidelite.showClientFidelite();
			}
		});
		
		item5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				FenetreContactVendeur contactVendeur= new FenetreContactVendeur(null,"Nous Contacter",true);
				DialogMessage mes = contactVendeur.showDialog();
			}
		});
		
		item6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				FenetreInformationsClient contactVendeur= new FenetreInformationsClient();
			}
		});
		
		item7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				FenetreCommandeArticle catalogue = new FenetreCommandeArticle();
				catalogue.setVisible(true);
			}
		});
		
		item8.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				FenetrePromotions promotion = new FenetrePromotions();
				promotion.setVisible(true);
			}
		});
		
		// Ajout des sous-menus � leur menu respectif dans l'ordre descendant que l'on d�sire 
		// � l'affichage 
		this.deuxieme_menu.add(item3);
		this.deuxieme_menu.add(item4);
		this.deuxieme_menu.add(item2);
		
		this.troisieme_menu.add(item7);
		
		this.quatrieme_menu.add(item8);
		
		this.cinquieme_menu.add(item6);
		this.cinquieme_menu.add(item5);
			
	    //L'ordre d'ajout va d�terminer l'ordre d'apparition dans le menu de gauche � droite
	    //Le premier ajout� sera tout � gauche de la barre de menu et inversement pour le dernier
		this.menuBar.add(deuxieme_menu);
		this.menuBar.add(troisieme_menu);
		this.menuBar.add(quatrieme_menu);
		this.menuBar.add(cinquieme_menu);
		//-------------------------
		
		//Ajout de la barre de menu � notre objet MenuUtilisateur
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		}
	
	
}


