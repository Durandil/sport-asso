package ihm;
import ihm.DialogIdentifiant;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import basededonnees.BDDClients;
import basededonnees.SGBD;


public class FenetreDialogIdentification extends JDialog {
	
	DialogIdentifiant ident= new DialogIdentifiant();
	private JLabel identifiantLabel,passwordLabel;
	private JTextField identifiant, password;
	
	
	public FenetreDialogIdentification(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(550, 370);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}
	
	/**
	 * M�thode appel�e pour utiliser la bo�te 
	 * @return Info
	 */
	public DialogIdentifiant showDialogIdent(){
		this.setVisible(true);		
		return this.ident;		
	}
	
	/**
	 * Initialise le contenu de la bo�te
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
				int present = 0;
				ArrayList<String[]> listeMailsMdps = new ArrayList<String[]>();
				listeMailsMdps = BDDClients.afficheSelectMailsMdpsClients();
				DialogIdentifiant login = new DialogIdentifiant(identifiant.getText(),password.getText());
				
				for(int i=0;i<listeMailsMdps.size();i++){
					
					if(identifiant.getText().equals(listeMailsMdps.get(i)[0]))
					{
						present = present + 1;
						if(password.getText().equals(listeMailsMdps.get(i)[1])){
							System.out.println("Identification r�ussie !");
						//Faire appara�tre le Menu utilisateur ici !
						}
						else{
							System.out.println("Mot de passe erron�, veuillez r�essayer.");
						}
					}
					
				}
				
				if(present == 0)
				{
					System.out.println("Ce compte n'existe pas, inscrivez-vous !");
				}
				// v�rifier que le login existe sinon retourner � la page pr�c�dente de choix entre cr�ation et identification
				// lancer la page suivante si succ�s
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
