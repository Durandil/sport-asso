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

public class FenetreReponseMessage extends JFrame {

	private JTextField sujetMessage;
	private JLabel sujetLabel;
	private JTextArea contenuMessage;
	private JLabel contenuLabel;

	public FenetreReponseMessage(boolean ReponseDuGerant) {
		super();
		this.setTitle("Répondre au message");
		this.setAlwaysOnTop(true);	
		this.setSize(500, 400);
		this.setLocation(50,50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(ReponseDuGerant);
	}
	
	public void initComponent(boolean reponseDuGerant){
		
		final boolean reponseGerant = reponseDuGerant;
		String gerant = "du client";
		if(reponseDuGerant==true){
			gerant="du gérant";
		}
		
		// Sujet Message
		JPanel panneauSujet= new JPanel();
		sujetMessage=new JTextField("RE : "+gerant);
		sujetMessage.setPreferredSize(new Dimension(200, 25));
		sujetLabel=new JLabel("Objet : ");
		panneauSujet.add(sujetLabel);
		panneauSujet.add(sujetMessage);
		panneauSujet.setBorder(BorderFactory.createEmptyBorder());
		
		// Contenu Message
		JPanel panneauContenuMessage= new JPanel();
		panneauContenuMessage.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		contenuMessage = new JTextArea(8,30);
		contenuMessage.setBorder(BorderFactory.createLineBorder(Color.black));
		contenuMessage.setEnabled(true);
		contenuMessage.setLineWrap(true);
		contenuMessage.setWrapStyleWord(true);
		contenuLabel=new JLabel("Contenu : ");
		panneauContenuMessage.add(contenuLabel);
		panneauContenuMessage.add(contenuMessage);
		
		JPanel panneauIdentificationMessage = new JPanel();
		panneauIdentificationMessage.add(panneauSujet,"East");
		panneauIdentificationMessage.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		// Définition du panneau dans lequel seront présents les boutons de retour à la page précédente 
		// et d'envoi de message
		JPanel panneauBoutonsBas=new JPanel();
		
		JButton boutonRetourMessagerie= new JButton("Retour à la Boite de Réception");
		JButton boutonEnvoyer = new JButton("Envoyer");
		
		//Définition des actions relatives à chaque bouton
		
		boutonRetourMessagerie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				// permet le retour vers la page contenant la boite de reception du gérant
			}			
		});
		
		boutonEnvoyer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO créer une nouvelle instance de message
				java.util.Date date = new java.util.Date();
				
				@SuppressWarnings("deprecation")
				java.sql.Date dateJour = new java.sql.Date(date.getYear(), date.getMonth(), date.getDate());
				Message message=new Message(sujetMessage.getText(),contenuMessage.getText(),FenetreLectureMessage.idExpediteurMessage,dateJour,!reponseGerant);
				setVisible(false);
			}			
		});
		
		//Ajout des boutons au panneau principal des boutons panneauBoutons
		panneauBoutonsBas.add(boutonRetourMessagerie);
		panneauBoutonsBas.add(boutonEnvoyer);
		
		this.getContentPane().add(panneauIdentificationMessage, BorderLayout.NORTH);
		this.getContentPane().add(panneauContenuMessage, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutonsBas, BorderLayout.SOUTH);
		
	}
	
	
}
