package ihm;
import javax.swing.JDialog;

// Classe provisoire permettant la gestion provisoire des messages envoyés par les clients
// dans la fenetre FenetreContactVendeur

public class DialogMessage extends JDialog {
	
	private String sujet,contenu;
	
	public DialogMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DialogMessage(String sujet, String contenu) {
		super();
		this.sujet = sujet;
		this.contenu = contenu;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	
	
}