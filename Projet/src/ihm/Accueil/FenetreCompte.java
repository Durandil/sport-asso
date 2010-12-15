package ihm.Accueil;
 

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import exception.ExceptionCaractereInterdit;
import exception.ExceptionCodePostalDifferentDeCinqChiffres;
import exception.ExceptionCodePostalIncorrect;
import exception.ExceptionExcesDeCaracteres;
import exception.ExceptionMailDejaExistant;
import exception.ExceptionMailSansArobase;
import exception.ExceptionMailsDifferents;
import exception.ExceptionNumeroDeTelephoneDifferentDeDixChiffres;
import exception.ExceptionNumeroDeTelephoneIncorrect;

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
	public FenetreCompte()throws ExceptionMailsDifferents{

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
		
		String heures=heure.getHours()+"";
		if(heure.getHours()<10){
			heures = "0"+heures;
		}
		
		String minutes=heure.getMinutes()+"";
		if(heure.getMinutes()<10){
			minutes = "0"+minutes;
		}
		
		heureLabel=new JLabel("Heure : " + heures +"h"+ minutes);
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
			public void actionPerformed(ActionEvent e) {
				//dispose();
				FenetreDialogCreationCompte compte = null;
				try {
					compte = new FenetreDialogCreationCompte(null, "Cr�ation Compte Client", true);
					compte.setVisible(true);
					dispose();
				} catch (ExceptionMailSansArobase e1) {
					
					e1.printStackTrace();
				} catch (ExceptionMailsDifferents e2) {

					e2.printStackTrace();
				} catch (ExceptionMailDejaExistant e3) {
					
					e3.printStackTrace();
				} catch (ExceptionCaractereInterdit e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				} catch (ExceptionExcesDeCaracteres e5) {
					// TODO Auto-generated catch block
					e5.printStackTrace();
				} catch (ExceptionCodePostalDifferentDeCinqChiffres e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				} catch (ExceptionNumeroDeTelephoneDifferentDeDixChiffres e7) {
					// TODO Auto-generated catch block
					e7.printStackTrace();
				} catch (ExceptionCodePostalIncorrect e8) {
					// TODO Auto-generated catch block
					e8.printStackTrace();
				} catch (ExceptionNumeroDeTelephoneIncorrect e9) {
					// TODO Auto-generated catch block
					e9.printStackTrace();
				}
				
			}	
		});
		
		boutonIdentificationClient.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// On affiche le contenu de la fen�tre d'identifiaction du client
				//dispose();
				FenetreDialogIdentification identificationClient = new FenetreDialogIdentification(null, "Identification client", true);
				identificationClient.setVisible(true);
				dispose();
			}	
		});	
		
		boutonIdentificationGerant.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// on affiche le contenu de la fen�tre d'identification du g�rant avec 
				// la m�me fen�tre que le g�rant car on consid�re qu'un g�rant est une extension
				// d'un utilisateur
				//dispose();
				FenetreDialogIdentification identificationGerant = new FenetreDialogIdentification(null,"Identification g�rant",true);
				identificationGerant.setVisible(true);
				dispose();
			}
		});
		
		boutonDeconnexion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// on ferme l'application
				int res = JOptionPane.showConfirmDialog(null, "Confirmez-vous la fermeture du logiciel ?","Confirmation",JOptionPane.YES_NO_OPTION);
				if(res==JOptionPane.OK_OPTION){
					System.exit(0);
				}
			}	
		});	
		
		this.setVisible(true);
			
	}

}
