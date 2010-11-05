package ihm;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class FenetreCompte extends JFrame {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton boutonCreation = new JButton(" Cr�er un compte");
	private JButton boutonIdentificationClient = new JButton(" Identification Client");
	private JButton boutonIdentificationGerant = new JButton("Identification Gerant");
	private JButton boutonDeconnexion = new JButton("D�connexion");
	private JPanel pan = new JPanel();
	private JLabel icon,iconEast,iconWest, accueilLabel,heureLabel,dateLabel; 
	private Date heure ;
	
	@SuppressWarnings("deprecation")
	public FenetreCompte(){

		this.setTitle("Informations client");
		this.setSize(900, 550);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
        //D�finition de la couleur de fond
        pan.setBackground(Color.white);        
        //On pr�vient notre JFrame que ce sera notre JPanel qui sera son contentPane
        this.setContentPane(pan);
        
        // je vais scinder mon �cran en plusierus parties pour pouvoir ins�rer des images, des boutons 
        // aux endroits que je d�sire
        this.setLayout(new BorderLayout());
        
        // D�finition du panneau qui se trouvera en haut de la fen�tre
        JPanel panneauHaut= new JPanel();
        panneauHaut.setLayout(new BorderLayout());
        
        // Cr�ation d'un sous panneau accueillant le logo du magasin
        icon = new JLabel(new ImageIcon("src/images/logo.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.add(icon);	
		
		//Cr�ation du panneau avec l'affichage du texte "Bienvenue"
		JPanel panAccueil=new JPanel();
		panAccueil.setBackground(Color.white);
		accueilLabel=new JLabel("Bienvenue");
		accueilLabel.setFont(new Font("Times",Font.BOLD, 36));
		panAccueil.add(accueilLabel);	
		
		// Cr�ation du panneau contenant l'heure et la date
		JPanel panHeure=new JPanel();
		panHeure.setBackground(Color.white);
		panHeure.setLayout(new GridLayout(2,1,5,5));
		
		//Pour r�cup�rer la date et l'heure, nous avons besoin d'un objet Date 
		// o� l'on recupera ses informations gr�ce aux m�thodes de cette classe
		heure=new Date();
		
		JPanel panneauDate = new JPanel();
		panneauDate.setBackground(Color.WHITE);
		dateLabel=new JLabel("Jour : " + heure.toGMTString().substring(0, 11));
		dateLabel.setFont(new Font("Times",Font.BOLD, 20));
		panneauDate.add(dateLabel);
		
		JPanel panneauHeure = new JPanel();
		panneauHeure.setBackground(Color.white);
		heureLabel=new JLabel("Heure : " + heure.getHours()+"h"+ heure.getMinutes());
		heureLabel.setFont(new Font("Times",Font.BOLD, 20));
		panneauHeure.add(heureLabel);
		
		panHeure.add(panneauDate);
		panHeure.add(panneauHeure);
		
		panneauHaut.add("West",panIcon);
		panneauHaut.add("Center",panAccueil);
		panneauHaut.add("East",panHeure);
		
		
		// D�finition du panneau qui se trouvera au centre avec les boutons
		JPanel panBoutons= new JPanel();
		panBoutons.setLayout(new GridLayout(4,1,10,10));
		panBoutons.add(boutonCreation);
		panBoutons.add(boutonIdentificationClient);
		panBoutons.add(boutonIdentificationGerant);
		panBoutons.add(boutonDeconnexion);
		
		// D�finition du panneau qui se trouvera � droite
		iconEast=new JLabel(new ImageIcon("src/images/trophee.jpg"));
		JPanel panIconEast = new JPanel();
		panIconEast.setBackground(Color.white);
		panIconEast.add(iconEast);
		
		// D�finition du panneau qui se trouvera � gauche
		iconWest=new JLabel(new ImageIcon("src/images/france.jpg"));
		JPanel panIconWest = new JPanel();
		panIconWest.setBackground(Color.white);
		panIconWest.add(iconWest);
		
		// Ajout des diff�rents panneaux au panneau principal
        this.getContentPane().add("North",panneauHaut);
        this.getContentPane().add("Center",panBoutons);
        this.getContentPane().add("West",panIconWest);
        this.getContentPane().add("East",panIconEast);
		
        // D�finition des actions de chacun des boutons pr�sents sur la fen�tre
		boutonCreation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				FenetreDialogCreationCompte compte = new FenetreDialogCreationCompte(null, "Cr�ation Compte Client", true);
				compte.setVisible(true); 
			}	
		});
		
		boutonIdentificationClient.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// On affiche le contenu de la fen�tre d'identifiaction du client
				FenetreDialogIdentification identificationClient = new FenetreDialogIdentification(null, "Identification client", true);
				identificationClient.setVisible(true); 
			}	
		});	
		
		boutonIdentificationGerant.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// on affiche le contenu de la fen�tre d'identification du g�rant avec 
				// la m�me fen�tre que le g�rant car on consid�re qu'un g�rant est une extension
				// d'un utilisateur
				FenetreDialogIdentification identificationGerant = new FenetreDialogIdentification(null,"Identification g�rant",true);
				identificationGerant.setVisible(true);
				
			}
		});
		
		boutonDeconnexion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// on ferme l'application
				System.exit(0);
			}	
		});	
		
		this.setVisible(true);
			
	}

}
