package metier;

import java.util.ArrayList;
import java.util.HashMap;

import basededonnees.SGBD;

public class Categorie {

	// Constructeur d'une catégorie d'articles
	// Celle-ci se définit par un id, un nom et une quantité limite
	// Lorsque cette quantité limite est atteinte, le gérant est averti d'un besoin de réapprovisionnement
	
	public Categorie(String idCategorie, String nomCategorie, int quantiteLimite) {
		this.idCategorie = idCategorie;
		this.nomCategorie = nomCategorie;
		this.quantiteLimite = quantiteLimite;
		ajouterBDD();
	}
	private void ajouterBDD() {
		// TODO Auto-generated method stub
	
	
		String requete = "INSERT INTO CATEGORIE (IDCATEGORIE, NOMCATEGORIE, QUANTITELIMITE) VALUES ( "
		+ "'"
		+ this.idCategorie
		+ "',"
		+ "'"
		+ this.nomCategorie
		+ "',"
		+ this.quantiteLimite
		+")";

		SGBD.executeUpdate(requete);
	}
	private String idCategorie;
	private String nomCategorie;
	private int quantiteLimite;
	public void setQuantiteLimite(int quantiteLimite) {
		this.quantiteLimite = quantiteLimite;
	}
	public int getQuantiteLimite() {
		return quantiteLimite;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setIdCategorie(String idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getIdCategorie() {
		return idCategorie;
	}
	
}
