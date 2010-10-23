package metier;

import java.util.ArrayList;
import java.util.Scanner;

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
	
	/** TODO : À finir ! **/
	// Méthode permettant d'activer ou de désactiver un compte
	public static void activDesactivCompte() {
		System.out
				.println("Veuillez entrer l'id du compte que vous souhaiter activer/désactiver");

		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();

		ArrayList<String[]> listeMails = new ArrayList<String[]>();

		listeMails = SGBD.afficheSelectClients();

		for (int i = 0; i < listeMails.size(); i++) {
			System.out.println(listeMails.get(i)[0]);
			System.out.println(listeMails.get(i)[1]);
			
			if (listeMails.get(i)[0].equals(str)) {
				
				if (listeMails.get(i)[1].equals("Activé")) {
					
					System.out.println("Ce compte est activé, voulez-vous le désactiver (O/N)?");
					Scanner sc2 = new Scanner(System.in);
					String str2 = sc2.nextLine();
					
					if(str2.equals("O")){
						
					}
					
					else if (str2.equals("N")){
						System.out.println("Compte laissé activé.");
					}
				}
				
				else if (listeMails.get(i)[1].equals("Désactivé")){
					
					System.out.println("Ce compte est désactivé, voulez-vous le réactiver (O/N)?");
					Scanner sc3 = new Scanner(System.in);
					String str3 = sc3.nextLine();
					
					if(str3.equals("O")){
						
					}
					
					else if (str3.equals("N")){
						System.out.println("Compte laissé désactivé.");
					}
				}
			}
		}	

	}
}
