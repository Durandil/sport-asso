package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.Message;


public class FenetreContactVendeur extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Message message = new Message();
	private JTextField sujet;
	private TextArea contenu;
	private JLabel sujetLabel,contenuLabel,introduction,texte;
	
	// Constructeur de la fentre qui sera initialisée avec initComponent()
	
	public FenetreContactVendeur(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(700, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE); 
		this.initComponent();
	}
	
	
	private void initComponent(){
		JPanel texteHautPan= new JPanel();
		texteHautPan.setBorder(BorderFactory.createTitledBorder(""));
		texteHautPan.setBackground(Color.white);
		introduction = new JLabel("Nous contacter");
		// 
		introduction.setSize(12, 1);
		texteHautPan.add(introduction);		
		
		// Création d'un panneau introductif de la fenetre
		JPanel panneauTexte = new JPanel();
		panneauTexte.setBackground(Color.white);
		texte=new JLabel("Si vous avez besoin de renseignements concernant nos produits ou pour toute autre demande");
		texte.setSize(8,1);
		panneauTexte.add(texte);
		
		//Creation du panneau sujetPan qui sert à l'affichage et l'écriture du sujet du message
		JPanel sujetPan=new JPanel();
		sujetPan.setBackground(Color.white);
		sujetPan.setPreferredSize(new Dimension(260,100));
		sujetPan.setBorder(BorderFactory.createTitledBorder("Sujet du message"));
		sujetLabel = new JLabel("Sujet : ");
		sujet = new JTextField();
		sujet.setPreferredSize(new Dimension(150, 25));
		sujetPan.add(sujetLabel);
		sujetPan.add(sujet);
		
		//Creation du panneau contenuPan qui sert à l'affichage et l'écriture du contenu du message
		JPanel contenuPan=new JPanel();
		contenuPan.setBackground(Color.white);
		contenuPan.setPreferredSize(new Dimension(260, 150));
		contenuPan.setBorder(BorderFactory.createTitledBorder("Contenu du message"));
		contenuLabel = new JLabel("Contenu : ");
		contenu = new TextArea();
		contenu.setPreferredSize(new Dimension(150, 100));
		contenuPan.add(contenuLabel);
		contenuPan.add(contenu);
		
		// Création du panneau qui accueillera tous les panneaux contenant les champs du message (sujet,contenu)
		JPanel contentPan=new JPanel();
		contentPan.add(panneauTexte);
		contentPan.add(sujetPan);
		contentPan.add(contenuPan);
		
		// Création du panneau qui va accueillir le bouton d'envoi du message
		JPanel panneauBouton=new JPanel();
		JButton boutonEnvoyer=new JButton("Envoyer");
		
		boutonEnvoyer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				// TODO Améliorer la détermination/numérotation des IDMESSAGE
				// il faudrait ajouter message à la base de données
				java.util.Date date = new java.util.Date();
				
				java.sql.Date dateJour = new java.sql.Date(date.getYear(), date.getMonth(), date.getDate());
				message=new Message(sujet.getText(),contenu.getText(),FenetreDialogIdentification.clientUserIdentifiant,dateJour);
				//message.ajouterBDD();
				// fermeture de la fenetre
				setVisible(false);
			}
		});
		
		// implémenter plus tard le action performed permettant de récupérer le contenu du message 
		// et de le transmettre au gérant
		
		panneauBouton.add(boutonEnvoyer);
		
		this.getContentPane().add(texteHautPan, BorderLayout.NORTH);
		this.getContentPane().add(contentPan, BorderLayout.CENTER);
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);
		
	}
	
}
