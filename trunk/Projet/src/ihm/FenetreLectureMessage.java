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

import metier.Article;
import metier.Message;

public class FenetreLectureMessage extends JDialog{

	private JTextField expediteur,date,sujet;
	private JTextArea contenu;
	private JLabel expediteurLabel,dateLabel,sujetLabel,contenuLabel;

	public FenetreLectureMessage(JFrame parent, String title, boolean modal ,Message messageClient ){
		super(parent, title, modal);
		this.setSize(200, 350);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(messageClient);
	}
	
	private void initComponent(Message emailRecu){
		
		// L'expéditeur
		JPanel panneauExpediteur= new JPanel();
		panneauExpediteur.setBackground(Color.white);
		panneauExpediteur.setPreferredSize(new Dimension(220, 60));
		expediteur=new JTextField(emailRecu.getExpediteur());
		expediteur.setEnabled(false); // pas modifiable
		expediteur.setPreferredSize(new Dimension(90, 25));
		expediteurLabel=new JLabel("Expéditeur : ");
		panneauExpediteur.add(expediteurLabel);
		panneauExpediteur.add(expediteur);
		
		// Date Reception Message
		JPanel panneauDate= new JPanel();
		panneauDate.setBackground(Color.white);
		panneauDate.setPreferredSize(new Dimension(220, 60));
		date=new JTextField(emailRecu.getDateEnvoi().toString());
		date.setEnabled(false);
		date.setPreferredSize(new Dimension(90, 25));
		dateLabel=new JLabel("Date : ");
		panneauDate.add(dateLabel);
		panneauDate.add(date);
		
		// Sujet Message
		JPanel panneauSujet= new JPanel();
		panneauSujet.setBackground(Color.white);
		panneauSujet.setPreferredSize(new Dimension(220, 60));
		sujet=new JTextField(emailRecu.getSujet());
		sujet.setEnabled(false);
		sujet.setPreferredSize(new Dimension(90, 25));
		sujetLabel=new JLabel("Objet : ");
		panneauExpediteur.add(sujetLabel);
		panneauExpediteur.add(sujet);
		
		// Contenu Message
		JPanel panneauContenuMessage= new JPanel();
		panneauContenuMessage.setBackground(Color.white);
		panneauContenuMessage.setPreferredSize(new Dimension(220, 60));
		contenu=new JTextArea(emailRecu.getContenu());
		contenu.setEnabled(false);
		contenu.setPreferredSize(new Dimension(90, 25));
		contenuLabel=new JLabel("Denomination");
		panneauContenuMessage.add(contenuLabel);
		panneauContenuMessage.add(contenu);
		
		JPanel panneauIdentificationMessage = new JPanel();
		panneauIdentificationMessage.add(panneauExpediteur);
		panneauIdentificationMessage.add(panneauDate);
		panneauIdentificationMessage.add(panneauSujet);
		
		// Définition du panneau dans lequel seront présents les boutons de retour à la page précédente 
		// et de suppression du message
		JPanel panneauBoutonsBas=new JPanel();
		panneauBoutonsBas.setBackground(Color.white);
		
		JButton boutonRetourMessagerie= new JButton("Retour à la Boite de Réception");
		JButton boutonSupprimerMessage= new JButton("Supprimer Message");
		
		//Définition des actions relatives à chaque bouton
		
		boutonRetourMessagerie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				// permet le retour vers la page contenant la boite de reception du gérant
			}			
		});
		
		boutonSupprimerMessage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO permet de supprimer le message sélectionné dans la base de donnée
				setVisible(false);
			}			
		});
		
		//Ajout des boutons au panneau principal des boutons panneauBoutons
		panneauBoutonsBas.add(boutonRetourMessagerie);
		panneauBoutonsBas.add(boutonSupprimerMessage);
		
		this.getContentPane().add(panneauIdentificationMessage, BorderLayout.NORTH);
		this.getContentPane().add(panneauContenuMessage, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutonsBas, BorderLayout.SOUTH);
		
	}
}
