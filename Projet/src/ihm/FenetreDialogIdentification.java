package ihm;
import ihm.DialogIdentifiant;

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
import javax.swing.JTextField;


public class FenetreDialogIdentification extends JDialog {
	
	DialogIdentifiant ident= new DialogIdentifiant();
	private JLabel identifiantLabel,passwordLabel;
	private JTextField identifiant, password;
	private boolean sendData;
	
	
	public FenetreDialogIdentification(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(550, 370);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}
	
	/**
	 * Méthode appelée pour utiliser la boîte 
	 * @return Info
	 */
	public DialogIdentifiant showDialogIdent(){
		this.sendData = false;
		this.setVisible(true);		
		return this.ident;		
	}
	
	/**
	 * Initialise le contenu de la boîte
	 */
	private void initComponent(){
		//Le nom
		JPanel panIdentifiant = new JPanel();
		panIdentifiant.setBackground(Color.white);
		panIdentifiant.setPreferredSize(new Dimension(220, 60));
		identifiant = new JTextField();
		identifiant.setPreferredSize(new Dimension(100, 25));
		panIdentifiant.setBorder(BorderFactory.createTitledBorder("Identifiant"));
		identifiantLabel = new JLabel("Email :");
		panIdentifiant.add(identifiantLabel);
		panIdentifiant.add(identifiant);
		
		
		//Le prenom
		JPanel panPassword = new JPanel();
		panPassword.setBackground(Color.white);
		panPassword.setPreferredSize(new Dimension(220, 60));
		panPassword.setBorder(BorderFactory.createTitledBorder("Mot de passe :"));
		passwordLabel = new JLabel("Mot de passe : ");
		password = new JTextField();
		password.setPreferredSize(new Dimension(90, 25));
		panPassword.add(passwordLabel);
		panPassword.add(password);
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panIdentifiant);
		content.add(panPassword);
		
		JPanel panneauBoutons = new JPanel();
		
		JButton validationBouton = new JButton("Valider");
		
		validationBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {				
				DialogIdentifiant login = new DialogIdentifiant(identifiant.getText(),password.getText());
				// vérifier que le login existe sinon retourner à la page précédente de choix entre création et identification
				// lancer la page suivante si succès
				setVisible(false);
			}
						
		});
		
		JButton annulationBouton = new JButton("Annuler");
		annulationBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}			
		});
		
		panneauBoutons.add(validationBouton);
		panneauBoutons.add(annulationBouton);
		
		
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutons, BorderLayout.SOUTH);
		
	}
}
