package metier;

import java.util.ArrayList;
import java.util.HashMap;

import basededonnees.SGBD;

public class Categorie {

	// Constructeur d'une catégorie d'articles
	// Celle-ci se définit par un id, un nom et une quantité limite
	// Lorsque cette quantité limite est atteinte, le gérant est averti d'un besoin de réapprovisionnement
	
	private String idCategorie;
	private String nomCategorie;
	private int quantiteLimite;
	
}
