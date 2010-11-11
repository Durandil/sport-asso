package ihm;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

import basededonnees.SGBD;

import metier.Association;
import metier.Particulier;


public class FenetreDialogCreationCompte extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private DialogInfo zInfo = new DialogInfo();
	private JLabel typeCompteLabel,denominationLabel, icon, nomLabel, prenomLabel, adresseLabel,villeLabel,cpLabel,telLabel,fideliteLabel, identifiantLabel;
	private JTextField nom, prenom,adresse,ville,codePostal,telephone,identifiant,denomination;
	private JComboBox compte, fidelite;
	// Par défaut, le client désire une carte de fiélité, le booléen associé vaut ainsi "vrai" à l'origine
	private boolean estFidele = true;
	private JOptionPane creationCorrecte, affichageMotDePasse, mailDejaUtilise ;
	public static String itemSelectionne ;
	public static String itemFidelite ;
	
	
	/**
	 * Constructeur
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public FenetreDialogCreationCompte(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(400, 750);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}
	
	
	/**
	 * Initialise le contenu de la boîte
	 */
	private void initComponent(){
		//Icone
		icon = new JLabel(new ImageIcon("src/images/logos.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);
		
		// Le type de compte
		JPanel panTypeCompte=new JPanel();
		panTypeCompte.setBackground(Color.white);
		panTypeCompte.setPreferredSize(new Dimension(220, 60));
		panTypeCompte.setBorder(BorderFactory.createTitledBorder("Type de compte"));
		typeCompteLabel = new JLabel("Statut Compte : ");
		
		compte=new JComboBox();
		compte.addItem("Compte Particulier");
		compte.addItem("Compte Collectivites");
		compte.add(typeCompteLabel);
		compte.setVisible(true);
		compte.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				itemSelectionne = (String) ((JComboBox) e.getSource()).getSelectedItem();
				//int indiceComboBox=((JComboBox) e.getSource()).getSelectedIndex();
				
				if(itemSelectionne == "Compte Particulier"){
					nom.setEnabled(true);
					prenom.setEnabled(true);
					denomination.setEnabled(false);
				}
				else{
					nom.setEnabled(false);
					prenom.setEnabled(false);
					denomination.setEnabled(true);
				}
			}
		});
		
		panTypeCompte.add(compte);

		
		// La denomination
		JPanel panDenomination= new JPanel();
		panDenomination.setBackground(Color.white);
		panDenomination.setPreferredSize(new Dimension(220, 60));
		denomination=new JTextField();
		denomination.setPreferredSize(new Dimension(90, 25));
		panDenomination.setBorder(BorderFactory.createTitledBorder("Denomination"));
		denominationLabel=new JLabel("Denomination");
		panDenomination.add(denominationLabel);
		panDenomination.add(denomination);
		
		//Le nom
		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(220, 60));
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(90, 25));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom"));
		nomLabel = new JLabel("Nom :");
		panNom.add(nomLabel);
		panNom.add(nom);
		
		
		//Le prenom
		JPanel panPrenom = new JPanel();
		panPrenom.setBackground(Color.white);
		panPrenom.setPreferredSize(new Dimension(220, 60));
		panPrenom.setBorder(BorderFactory.createTitledBorder("Prenom"));
		prenomLabel = new JLabel("Prenom : ");
		prenom = new JTextField();
		prenom.setPreferredSize(new Dimension(90, 25));
		panPrenom.add(prenomLabel);
		panPrenom.add(prenom);

		//L'adresse
		JPanel panAdresse = new JPanel();
		panAdresse.setBackground(Color.white);
		panAdresse.setPreferredSize(new Dimension(220, 60));
		panAdresse.setBorder(BorderFactory.createTitledBorder("Adresse"));
		adresseLabel = new JLabel("Adresse : ");
		adresse = new JTextField();
		adresse.setPreferredSize(new Dimension(100, 25));
		panAdresse.add(adresseLabel);
		panAdresse.add(adresse);

		//ville
		JPanel panVille = new JPanel();
		panVille.setBackground(Color.white);
		panVille.setPreferredSize(new Dimension(220, 60));
		panVille.setBorder(BorderFactory.createTitledBorder("Ville"));
		villeLabel = new JLabel("Ville: ");
		ville = new JTextField();
		ville.setPreferredSize(new Dimension(90, 25));
		panVille.add(villeLabel);
		panVille.add(ville);
	
		//Code Postal
		JPanel panCP = new JPanel();
		panCP.setBackground(Color.white);
		panCP.setPreferredSize(new Dimension(220, 60));
		panCP.setBorder(BorderFactory.createTitledBorder("Code Postal"));
		cpLabel = new JLabel("Code Postal : ");
		codePostal = new JTextField();
		codePostal.setPreferredSize(new Dimension(100,25));
		panCP.add(cpLabel);
		panCP.add(codePostal);
		
		// Telephone
		JPanel panTelephone = new JPanel();
		panTelephone.setBackground(Color.white);
		panTelephone.setPreferredSize(new Dimension(220, 60));
		panTelephone.setBorder(BorderFactory.createTitledBorder("Telephone"));
		telLabel = new JLabel("Telephone : ");
		telephone = new JTextField();
		telephone.setPreferredSize(new Dimension(90, 25));
		panTelephone.add(telLabel);
		panTelephone.add(telephone);
		
		//Identifiant
		JPanel panIdentifiant = new JPanel();
		panIdentifiant.setBackground(Color.white);
		panIdentifiant.setPreferredSize(new Dimension(220, 60));
		panIdentifiant.setBorder(BorderFactory.createTitledBorder("Identifiant"));
		identifiantLabel = new JLabel("Email : ");
		identifiant = new JTextField();
		identifiant.setPreferredSize(new Dimension(90, 25));
		panIdentifiant.add(identifiantLabel);
		panIdentifiant.add(identifiant);
		
		// La demande de carte de fidelité
		JPanel panFidelite = new JPanel();
		panFidelite.setBackground(Color.white);
		panFidelite.setPreferredSize(new Dimension(220, 80));
		fideliteLabel = new JLabel("Desirez-vous une carte de fidelité ? : ");
		panFidelite.add(fideliteLabel);
		
		
		fidelite=new JComboBox();
		fidelite.addItem("Oui");
		fidelite.addItem("Non");
		fidelite.setVisible(true);
		fidelite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				// Le booléen estFidele passe à faux si l'utilisateur répond "Non" à la question
				// Il reste à vrai s'il répond "Oui"
				itemFidelite = (String) ((JComboBox) e.getSource()).getSelectedItem();
				if(itemFidelite == "Non"){
					estFidele = false;
				}
				else{
					estFidele = true;
				}
			}	
		});
		
		panFidelite.add(fidelite);
		
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panTypeCompte);
		content.add(panIdentifiant);
		content.add(panDenomination);
		content.add(panNom);
		content.add(panPrenom);
		content.add(panAdresse);
		content.add(panVille);
		content.add(panCP);
		content.add(panTelephone);
		content.add(panFidelite);
		
		
		JPanel control = new JPanel();
		JButton validationBouton = new JButton("Valider");
		
		validationBouton.addActionListener(new ActionListener(){
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				/** ATTENTION ! Au début, le type de compte est sur "Compte Particulier" par défaut 
				 * Mais le champ Dénomination n'est pas grisé il faut resélectionner Compte Particulier
				 * pour que Dénomination se grise.
				 */
				
				//listeMails recense l'ensemble des adresses mails présentes dans la base
				//L'entier test passe à 1 si l'adresse renseignée existe déjà, auquel cas on avertit l'utilisateur
				//Si test reste à 0, on ajoute le client dans la BDD
				ArrayList<String> listeMails = new ArrayList<String>();
				listeMails = SGBD.selectListeString("CLIENT", "IDCLIENT");
				int test = 0;
				
				for (int i = 0; i < listeMails.size(); i++) {
					if (identifiant.getText().equals(listeMails.get(i))) {
						test = 1;
					}
				}

				if (test == 1) {
					mailDejaUtilise = new JOptionPane();
					ImageIcon image = new ImageIcon("src/images/warning.png");
					mailDejaUtilise.showMessageDialog(null, "Cette adresse mail est déjà utilisée par un autre utilisateur !", "Attention", JOptionPane.WARNING_MESSAGE, image);
				}
				// on pourra enregistrer dans base de données la nouvelle
				// création de compte
				/** TODO : Gestion de l'id ville...**/
				else {
					String s = new String();
					s= SGBD.selectStringConditionString("VILLE", "CODECOMMUNE", "NOMVILLE", ville.getText());
					System.out.println(s);
					if (denomination.getText().isEmpty())
						
					{
						
						Particulier p = new Particulier(nom.getText(), prenom
								.getText(), identifiant.getText(), adresse
								.getText(), s , telephone.getText(), estFidele);
					} else {
						Association a = new Association(denomination.getText(),
								identifiant.getText(), adresse.getText(), s,
								telephone.getText(),estFidele);
					}
					
					//System.out.println("Un nouveau compte a été crée, votre identifiant est : " + identifiant.getText());
					creationCorrecte = new JOptionPane();
					ImageIcon imageInformation = new ImageIcon("src/images/information.jpg");
					creationCorrecte.showMessageDialog(null, "Un nouveau compte a été crée, votre identifiant est : " + identifiant.getText(), "Information", JOptionPane.INFORMATION_MESSAGE, imageInformation);
					
					FenetreDialogIdentification.clientUserIdentifiant=identifiant.getText();
					
					//On recherche le mot de passe dans la base avant de l'afficher
					String motDePasse = SGBD.selectStringConditionString("CLIENT", "MOTDEPASSE", "IDCLIENT", identifiant.getText());
					affichageMotDePasse = new JOptionPane();
					affichageMotDePasse.showMessageDialog(null, "Retenez votre mot de passe : " + motDePasse, "Information", JOptionPane.INFORMATION_MESSAGE, imageInformation);
					
					setVisible(false);
					// Essai d'ouverture du menu Utilisateur après une création de compte correcte
					//MenuUtilisateur men = new MenuUtilisateur();
				}
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
