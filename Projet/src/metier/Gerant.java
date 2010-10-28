package metier;

import java.util.ArrayList;
import java.util.Scanner;

import basededonnees.BDDClients;
import basededonnees.SGBD;

public class Gerant extends Utilisateur {
	
	private String prenom;
	private String nom;
	private String motDePasse;

	public Gerant(String nom, String prenom, String mail, String adresse,
			String ville, String codePostal, String telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.adresse = adresse;
		this.ville = ville;
		this.codePostal = codePostal;
		this.telephone = telephone;
	
		this.motDePasse = genererMdp();
		System.out.println("Votre mot de passe est : " + this.motDePasse);
	}
	

	// M�thode permettant d'activer ou de d�sactiver un compte
	public static void activDesactivCompte() {
		System.out.println("Veuillez entrer l'id du compte que vous souhaiter activer/d�sactiver");

		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();

		ArrayList<String[]> listeMailsEtats = new ArrayList<String[]>();

		listeMailsEtats = BDDClients.afficheSelectMailsEtatsClients();

		for (int i = 0; i < listeMailsEtats.size(); i++) {
			
			
			if (listeMailsEtats.get(i)[0].equals(str)) {
				
				if (listeMailsEtats.get(i)[1].equals("Activ�")) {
					String str2 = null;
					do {
						System.out
								.println("Ce compte est activ�, voulez-vous le d�sactiver (O/N)?");
						Scanner sc2 = new Scanner(System.in);
						str2 = sc2.nextLine();
					} while (!str2.equals("O") && !str2.equals("N"));
					
					if(str2.equals("O")){
						
						SGBD.executeUpdate("UPDATE CLIENTS SET ETATCOMPTE = 'D�sactiv�' " +
											"WHERE MAIL ='" + str+"'");
						System.out.println("Le compte " + str + " a �t� d�sactiv�");
					}
					
					else if (str2.equals("N")){
						System.out.println("Compte laiss� activ�.");
					}
				}
				
				else if (listeMailsEtats.get(i)[1].equals("D�sactiv�")){
					String str3 = null;
					do {
					System.out.println("Ce compte est d�sactiv�, voulez-vous le r�activer (O/N)?");
					Scanner sc3 = new Scanner(System.in);
					str3 = sc3.nextLine();
					} while (!str3.equals("O") && !str3.equals("N"));
					
					if(str3.equals("O")){
						SGBD.executeUpdate("UPDATE CLIENTS SET ETATCOMPTE = 'Activ�' " +
								"WHERE MAIL ='" + str+"'");
						System.out.println("Le compte " + str + " a �t� r�activ�");
					}
					
					else if (str3.equals("N")){
						System.out.println("Compte laiss� d�sactiv�.");
					}
				}
			}
		}	

	}
}