package metier;

import java.util.ArrayList;
import java.util.Scanner;

import basededonnees.SGBD;

public class Gerant extends Utilisateur {
	
	private String prenom;
	private String nom;


	public Gerant(String nom, String prenom, String mail, String adresse,
			String idVille, String telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.idVille = idVille;
		this.adresse = adresse;
		this.telephone = telephone;
		this.motDePasse = genererMdp();
		System.out.println("Votre mot de passe est : " + this.motDePasse);
	}
	

}
