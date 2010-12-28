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
 * Cette classe FenetreLectureMessage permet � tout utilisateur de l'application ayant un 
 * message dans sa boite de r�ception interne de pouvoir lire le message
 * Elle est construite � partir de {@link FenetreLectureMessage#FenetreLectureMessage(JFrame, String, boolean, String, String, String, String, String, boolean)}
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
	 * Constructeur de la fen�tre de lecture de message qui prend en param�tre tous les 
	 * attributs d'un message. Cette fen�tre est construite � partir de {@link FenetreLectureMessage#initComponent(String, String, String, String, String, boolean)}
	 * 
	 * @param parent
	 *            JFrame utilis� pour cr�er la fen�tre
	 * @param title
	 *            String indiquant le titre de la fen�tre
	 * @param modal
	 *            Bool�en indiquant si la fen�tre doit bloquer ou non les
	 *            interactions avec les autres fen�tres
	 * @param exp
	 * 			  Identifiant unique de l'exp�diteur
	 * @param sujetM
	 * 			  Sujet du message selectionn� pour �tre lu
	 * @param contenuM
	 * 			  Contenu du message selectionn� pour �tre lu
	 * @param dateM
	 * 			  Date d'envoi du message selectionn� pour �tre lu
	 * @param ident
	 * 			  Identifiant unique du message
	 * @param lectureMessageGerant
	 * 			  Bool�en indiquant si le message lu est une r�ponse du g�rant 
	 * 			  (true) pour les clients et (false) pour le g�rant
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
	 * <p>Initialisation des composants contenus dans la fen�tre: <ul>
	 * <li> un JPanel en haut de la fen�tre accueillant la date d'envoi, l'exp�diteur 
	 * et le sujet du message. </li>
	 * <li> un JPanel au centre de la fen�tre accueillant le contenu du message.</li>
	 * <li> un JPanel en bas de fen�tre contenant trois boutons : retour � la page pr�c�dente,
	 * r�pondre au message ou supprimer.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param expediteur
	 * 			  Identifiant unique de l'exp�diteur 
	 * @param sujet
	 * 			  Sujet du message selectionn� pour �tre lu
	 * @param contenu
	 * 			  Contenu du message selectionn� pour �tre lu
	 * @param date
	 * 			  Date d'envoi du message selectionn� pour �tre lu
	 * @param identifiant
	 * 			  Identifiant unique du message
	 * @param reponseDuGerant
	 * 			  Bool�en indiquant si le message lu est une r�ponse du g�rant 
	 * 			  (true) pour les clients et (false) pour le g�rant
	 */
	private void initComponent(String expediteur,String sujet,String contenu,
			String date,String identifiant,boolean reponseDuGerant){
		
		final String numMessage=identifiant;
		final boolean reponseGerant = reponseDuGerant;
		idExpediteurMessage = expediteur;
		
		// L'exp�diteur
		JPanel panneauExpediteur= new JPanel();
		expediteurMessage=new JTextField(expediteur);
		expediteurMessage.setEnabled(false); // pas modifiable
		expediteurMessage.setPreferredSize(new Dimension(130, 25));
		expediteurLabel=new JLabel("Exp�diteur : ");
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
		
		// D�finition du panneau dans lequel seront pr�sents les boutons de retour � la page pr�c�dente 
		// et de suppression du message
		JPanel panneauBoutonsBas=new JPanel();
		
		JButton boutonRetourMessagerie= new JButton("Retour � la Boite de R�ception");
		JButton boutonSupprimerMessage= new JButton("Supprimer Message");
		JButton boutonRepondre = new JButton("R�pondre");
		
		//D�finition des actions relatives � chaque bouton
		
		boutonRetourMessagerie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// permet le retour vers la page contenant la boite de reception du g�rant
				FenetreMessagerie messagerie = new FenetreMessagerie(reponseGerant);
				messagerie.setVisible(true);
				
				dispose();
			}			
		});
		
		// D�finition de l'action du bouton supprimer
		boutonSupprimerMessage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// Suppression du message de la base de donn�es
				Message.supprimerBDD(numMessage);
				
				// Fermeture de la fen�tre et ouverture d'une nouvelle 
				// fen�tre boite de r�ception
				FenetreMessagerie messagerie = new FenetreMessagerie(reponseGerant);
				messagerie.setVisible(true);
				
				dispose();				
			}			
		});
		
		// D�finition de l'action du bouton r�pondre qui ferme  la //
		// page en cours et ouvre une fen�tre de r�ponse au message//
		//---------------------------------------------------------//
		boutonRepondre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//G�n�ration d'une page de r�ponse � un email
				dispose();
				FenetreReponseMessage fenReponse = new FenetreReponseMessage(reponseGerant,sujetMessage.getText());
				fenReponse.setVisible(true);
			}			
		});
		
		//Ajout des boutons au JPanel principal des boutons : panneauBoutons
		panneauBoutonsBas.add(boutonRetourMessagerie);
		panneauBoutonsBas.add(boutonSupprimerMessage);
		panneauBoutonsBas.add(boutonRepondre);
		
		// Ajout des 3 principaux JPanel au conteneur de la fen�tre
		this.getContentPane().add(panneauIdentificationMessage, BorderLayout.NORTH);
		this.getContentPane().add(panneauContenuMessage, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutonsBas, BorderLayout.SOUTH);
		
	}
}
