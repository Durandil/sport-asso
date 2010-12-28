package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import metier.Message;

/**
 * Cette classe FenetreLectureMessage permet à tout utilisateur de l'application ayant un 
 * message dans sa boite de réception interne de pouvoir lire le message
 * Elle est construite à partir de {@link FenetreLectureMessage#FenetreLectureMessage(JFrame, String, boolean, String, String, String, String, String, boolean)}
 * 
 * @author Utilisateur
 * 
 *
 */
public class FenetreLectureMessage extends JDialog{

	private static final long serialVersionUID = 1L;
	private JTextField expediteurMessage,dateMessage,sujetMessage;
	private JTextArea contenuMessage;
	private JLabel expediteurLabel,dateLabel,sujetLabel,contenuLabel;
	public static String idExpediteurMessage ="";
	
	/**
	 * Constructeur de la fenêtre de lecture de message qui prend en paramètre tous les 
	 * attributs d'un message. Cette fenêtre est construite à partir de {@link FenetreLectureMessage#initComponent(String, String, String, String, String, boolean)}
	 * 
	 * @param parent
	 *            JFrame utilisé pour créer la fenêtre
	 * @param title
	 *            String indiquant le titre de la fenêtre
	 * @param modal
	 *            Booléen indiquant si la fenêtre doit bloquer ou non les
	 *            interactions avec les autres fenêtres
	 * @param exp
	 * 			  Identifiant unique de l'expéditeur
	 * @param sujetM
	 * 			  Sujet du message selectionné pour être lu
	 * @param contenuM
	 * 			  Contenu du message selectionné pour être lu
	 * @param dateM
	 * 			  Date d'envoi du message selectionné pour être lu
	 * @param ident
	 * 			  Identifiant unique du message
	 * @param lectureMessageGerant
	 * 			  Booléen indiquant si le message lu est une réponse du gérant 
	 * 			  (true) pour les clients et (false) pour le gérant
	 */
	public FenetreLectureMessage(JFrame parent, String title, boolean modal ,
			String exp, String sujetM,String contenuM,String dateM,
			String ident, boolean lectureMessageGerant ){
		
		super(parent, title, modal);
		this.setSize(900, 300);
		this.setLocation(50,50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(exp,sujetM,contenuM,dateM,ident,lectureMessageGerant);
	}
	
	
	/**
	 * <p>Initialisation des composants contenus dans la fenêtre: <ul>
	 * <li> un JPanel en haut de la fenêtre accueillant la date d'envoi, l'expéditeur 
	 * et le sujet du message. </li>
	 * <li> un JPanel au centre de la fenêtre accueillant le contenu du message.</li>
	 * <li> un JPanel en bas de fenêtre contenant trois boutons : retour à la page précédente,
	 * répondre au message ou supprimer.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param expediteur
	 * 			  Identifiant unique de l'expéditeur 
	 * @param sujet
	 * 			  Sujet du message selectionné pour être lu
	 * @param contenu
	 * 			  Contenu du message selectionné pour être lu
	 * @param date
	 * 			  Date d'envoi du message selectionné pour être lu
	 * @param identifiant
	 * 			  Identifiant unique du message
	 * @param reponseDuGerant
	 * 			  Booléen indiquant si le message lu est une réponse du gérant 
	 * 			  (true) pour les clients et (false) pour le gérant
	 */
	private void initComponent(String expediteur,String sujet,String contenu,
			String date,String identifiant,boolean reponseDuGerant){
		
		final String numMessage=identifiant;
		final boolean reponseGerant = reponseDuGerant;
		idExpediteurMessage = expediteur;
		
		// L'expéditeur
		JPanel panneauExpediteur= new JPanel();
		expediteurMessage=new JTextField(expediteur);
		expediteurMessage.setEnabled(false); // pas modifiable
		expediteurMessage.setPreferredSize(new Dimension(130, 25));
		expediteurLabel=new JLabel("Expéditeur : ");
		panneauExpediteur.add(expediteurLabel);
		panneauExpediteur.add(expediteurMessage);
		panneauExpediteur.setBorder(BorderFactory.createEmptyBorder());
		
		// Date Reception Message
		JPanel panneauDate= new JPanel();
		dateMessage=new JTextField(date);
		dateMessage.setEnabled(false);
		dateMessage.setPreferredSize(new Dimension(90, 25));
		dateLabel=new JLabel("Date : ");
		panneauDate.add(dateLabel);
		panneauDate.add(dateMessage);
		panneauDate.setBorder(BorderFactory.createEmptyBorder());
		
		// Sujet Message
		JPanel panneauSujet= new JPanel();
		sujetMessage=new JTextField(sujet);
		sujetMessage.setEnabled(false);
		sujetMessage.setPreferredSize(new Dimension(200, 25));
		sujetLabel=new JLabel("Objet : ");
		panneauSujet.add(sujetLabel);
		panneauSujet.add(sujetMessage);
		panneauSujet.setBorder(BorderFactory.createEmptyBorder());
		
		// Contenu Message
		JPanel panneauContenuMessage= new JPanel();
		panneauContenuMessage.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		contenuMessage=new JTextArea(8,30);
		contenuMessage.setLineWrap(true);
		contenuMessage.setWrapStyleWord(true);
		contenuMessage.setText(contenu);
		contenuMessage.setEnabled(false);
		contenuLabel=new JLabel("Contenu : ");
		panneauContenuMessage.add(contenuLabel);
		panneauContenuMessage.add(contenuMessage);
		
		JPanel panneauIdentificationMessage = new JPanel();
		panneauIdentificationMessage.setLayout(new BorderLayout());
		panneauIdentificationMessage.add(panneauExpediteur,"West");
		panneauIdentificationMessage.add(panneauDate,"Center");
		panneauIdentificationMessage.add(panneauSujet,"East");
		panneauIdentificationMessage.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		// Définition du panneau dans lequel seront présents les boutons de retour à la page précédente 
		// et de suppression du message
		JPanel panneauBoutonsBas=new JPanel();
		
		JButton boutonRetourMessagerie= new JButton("Retour à la Boite de Réception");
		JButton boutonSupprimerMessage= new JButton("Supprimer Message");
		JButton boutonRepondre = new JButton("Répondre");
		
		//Définition des actions relatives à chaque bouton
		
		boutonRetourMessagerie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// permet le retour vers la page contenant la boite de reception du gérant
				FenetreMessagerie messagerie = new FenetreMessagerie(reponseGerant);
				messagerie.setVisible(true);
				
				dispose();
			}			
		});
		
		// Définition de l'action du bouton supprimer
		boutonSupprimerMessage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// Suppression du message de la base de données
				Message.supprimerBDD(numMessage);
				
				// Fermeture de la fenêtre et ouverture d'une nouvelle 
				// fenêtre boite de réception
				FenetreMessagerie messagerie = new FenetreMessagerie(reponseGerant);
				messagerie.setVisible(true);
				
				dispose();				
			}			
		});
		
		// Définition de l'action du bouton répondre qui ferme  la //
		// page en cours et ouvre une fenêtre de réponse au message//
		//---------------------------------------------------------//
		boutonRepondre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Génération d'une page de réponse à un email
				dispose();
				FenetreReponseMessage fenReponse = new FenetreReponseMessage(reponseGerant,sujetMessage.getText());
				fenReponse.setVisible(true);
			}			
		});
		
		//Ajout des boutons au JPanel principal des boutons : panneauBoutons
		panneauBoutonsBas.add(boutonRetourMessagerie);
		panneauBoutonsBas.add(boutonSupprimerMessage);
		panneauBoutonsBas.add(boutonRepondre);
		
		// Ajout des 3 principaux JPanel au conteneur de la fenêtre
		this.getContentPane().add(panneauIdentificationMessage, BorderLayout.NORTH);
		this.getContentPane().add(panneauContenuMessage, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutonsBas, BorderLayout.SOUTH);
		
	}
}
