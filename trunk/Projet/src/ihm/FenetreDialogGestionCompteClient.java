package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class FenetreDialogGestionCompteClient extends JDialog {
	private DialogInfo zInfo = new DialogInfo();

	private JLabel denominationLabel, icon, nomLabel, prenomLabel, adresseLabel,villeLabel,cpLabel,telLabel,fideliteLabel, identifiantLabel;
	private JTextField nom, prenom,ville,codePostal,telephone,identifiant,denomination;
	private TextArea adresse;

	
	/**
	 * Constructeur
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public FenetreDialogGestionCompteClient(JFrame parent, String title, boolean modal,DialogInfo client){
		super(parent, title, modal);
		this.setSize(550, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(client);
	}
	
	/**
	 * Méthode appelée pour utiliser la boîte 
	 * @return zInfo
	 */
	public DialogInfo showClient(){
		this.setVisible(true);		
		return this.zInfo;		
	}
	
	/**
	 * Initialise le contenu de la boîte
	 */
	private void initComponent(DialogInfo client){
		//Icone
		icon = new JLabel(new ImageIcon("src/images/logos.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);
		
		//Identifiant
		JPanel panIdentifiant = new JPanel();
		panIdentifiant.setBackground(Color.white);
		panIdentifiant.setPreferredSize(new Dimension(260, 60));
		panIdentifiant.setBorder(BorderFactory.createTitledBorder("Identifiant"));
		identifiantLabel = new JLabel("Email : ");
		identifiant = new JTextField(client.getIdentifiant());
		identifiant.setEnabled(false);
		identifiant.setPreferredSize(new Dimension(115, 25));
		panIdentifiant.add(identifiantLabel);
		panIdentifiant.add(identifiant);
		
		// La denomination
		// if typeCompte=collectivités association
		JPanel panDenomination= new JPanel();
		panDenomination.setBackground(Color.white);
		panDenomination.setPreferredSize(new Dimension(260, 60));
		denomination=new JTextField();
		denomination.setPreferredSize(new Dimension(90, 25));
		panDenomination.setBorder(BorderFactory.createTitledBorder("Denomination"));
		denominationLabel=new JLabel("Denomination");
		panDenomination.add(denominationLabel);
		panDenomination.add(denomination);
		
		// else
		//Le nom
		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(260, 60));
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(90, 25));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom"));
		nomLabel = new JLabel("Nom :");
		panNom.add(nomLabel);
		panNom.add(nom);
		
		
		//Le prenom
		JPanel panPrenom = new JPanel();
		panPrenom.setBackground(Color.white);
		panPrenom.setPreferredSize(new Dimension(260, 60));
		panPrenom.setBorder(BorderFactory.createTitledBorder("Prenom"));
		prenomLabel = new JLabel("Prenom : ");
		prenom = new JTextField();
		prenom.setPreferredSize(new Dimension(90, 25));
		panPrenom.add(prenomLabel);
		panPrenom.add(prenom);

		//L'adresse
		JPanel panAdresse = new JPanel();
		panAdresse.setBackground(Color.white);
		panAdresse.setPreferredSize(new Dimension(260, 90));
		panAdresse.setBorder(BorderFactory.createTitledBorder("Adresse"));
		adresseLabel = new JLabel("Adresse : ");
		adresse = new TextArea(client.getAdresse());
		adresse.setBackground(Color.LIGHT_GRAY);
		adresse.setPreferredSize(new Dimension(140, 50));
		panAdresse.add(adresseLabel);
		panAdresse.add(adresse);

		//ville
		JPanel panVille = new JPanel();
		panVille.setBackground(Color.white);
		panVille.setPreferredSize(new Dimension(260, 60));
		panVille.setBorder(BorderFactory.createTitledBorder("Ville"));
		villeLabel = new JLabel("Ville: ");
		ville = new JTextField(client.getVille());
		ville.setPreferredSize(new Dimension(90, 25));
		panVille.add(villeLabel);
		panVille.add(ville);
		
		//Code Postal
		JPanel panCP = new JPanel();
		panCP.setBackground(Color.white);
		panCP.setPreferredSize(new Dimension(260, 60));
		panCP.setBorder(BorderFactory.createTitledBorder("Code Postal"));
		cpLabel = new JLabel("Code Postal : ");
		codePostal = new JTextField(client.getCodePostal());
		codePostal.setPreferredSize(new Dimension(100,25));
		panCP.add(cpLabel);
		panCP.add(codePostal);
		
		// Telephone
		JPanel panTelephone = new JPanel();
		panTelephone.setBackground(Color.white);
		panTelephone.setPreferredSize(new Dimension(260, 60));
		panTelephone.setBorder(BorderFactory.createTitledBorder("Telephone"));
		telLabel = new JLabel("Telephone : ");
		telephone = new JTextField(client.getTelephone());
		telephone.setPreferredSize(new Dimension(90, 25));
		panTelephone.add(telLabel);
		panTelephone.add(telephone);
		
				
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panIdentifiant);
		content.add(panDenomination);
		content.add(panNom);
		content.add(panPrenom);
		content.add(panAdresse);
		content.add(panVille);
		content.add(panCP);
		content.add(panTelephone);
		
		
		JPanel control = new JPanel();
		JButton validationBouton = new JButton("Valider");
		
		validationBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				
				// on pourra enregistrer dans base de données la modification
				zInfo = new DialogInfo(nom.getText(),adresse.getText(),prenom.getText(),ville.getText(),(codePostal.getText()),telephone.getText(),identifiant.getText());
				setVisible(false);
			}
						
		});
		
		JButton annulationBouton = new JButton("Annuler");
		annulationBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}			
		});
		
		control.add(validationBouton);
		control.add(annulationBouton);
		
		this.getContentPane().add(panIcon, BorderLayout.WEST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}

}

