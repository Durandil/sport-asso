package ihm;
import ihm.DialogIdentifiant;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import basededonnees.SGBD;


public class FenetreDialogIdentification extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//DialogIdentifiant ident= new DialogIdentifiant();
	private JLabel identifiantLabel,passwordLabel;
	private JTextField identifiant, motDePasse;
	private JPasswordField password ;
	private JOptionPane erreurMotPasse, erreurCompte, identificationReussie;
	public static String clientUserIdentifiant=""; // permet d'avoir l'identifiant de l'utilisateur 
													 // dans toute l'application
	
	
	public FenetreDialogIdentification(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(550, 370);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
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
		password = new JPasswordField();
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
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				int present = 0;
				ArrayList<String[]> listeMailsMdps = new ArrayList<String[]>();
				listeMailsMdps = SGBD.selectDeuxChampsString("CLIENT", "IDCLIENT", "MOTDEPASSE");
				DialogIdentifiant login = new DialogIdentifiant(identifiant.getText(),password.getText());
				
				for(int i=0;i<listeMailsMdps.size();i++){
					
					if(identifiant.getText().equals(listeMailsMdps.get(i)[0]))
					{
						present = present + 1;
						if(password.getText().equals(listeMailsMdps.get(i)[1])){					
							identificationReussie = new JOptionPane();
							ImageIcon imageInformation = new ImageIcon("src/images/information.jpg");
							identificationReussie.showMessageDialog(null, "Identification réussie !", "Information", JOptionPane.INFORMATION_MESSAGE, imageInformation);
							clientUserIdentifiant=identifiant.getText();
						//Faire apparaître le Menu utilisateur ici !
						
						MenuUtilisateur men = new MenuUtilisateur();
						}
						else{
							erreurMotPasse = new JOptionPane();
							ImageIcon image = new ImageIcon("src/images/warning.png");
							erreurMotPasse.showMessageDialog(null, "Mot de passe erroné, veuillez réessayer.", "Attention", JOptionPane.WARNING_MESSAGE, image);
							//affichage d'un message d'erreur en cas de mot de passe erroné
							}
					}
					
				}
				
				if(present == 0)
				{	
					
					// essai d'affichage d'un message d'erreur en cas de probleme sur le compte
					erreurCompte = new JOptionPane();
					ImageIcon image = new ImageIcon("src/images/warning.png");
					erreurCompte.showMessageDialog(null, "Ce compte n'existe pas, inscrivez-vous !", "Attention", JOptionPane.WARNING_MESSAGE, image);
				}
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
