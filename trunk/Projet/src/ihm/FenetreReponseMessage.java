package ihm;

import ihm.Accueil.FenetreDialogIdentification;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import exception.ExceptionChampVide;
import exception.ExceptionExcesDeCaracteres;

import metier.Message;

/**
 * Cette classe FenetreReponseMessage fait suite à la fenêtre
 * {@link FenetreLectureMessage} lorsque l'utilisateur appuie sur le bouton
 * Répondre. Cette classe définit les composants permettant l'affichage des
 * champs de saisie du sujet et de saisie du contenu du message. Elle permet à
 * un utilisateur de répondre à un message envoyé par un autre utilisateur à
 * travers la messagerie interne de l'application.
 * 
 * {@link FenetreLectureMessage} {@link FenetreMessagerie}
 * 
 * @author Utilisateur
 * 
 */
public class FenetreReponseMessage extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField sujetMessage;
	private JLabel sujetLabel;
	private JTextArea contenuMessage;
	private JLabel contenuLabel;

	/**
	 * Constructeur de la classe FenetreReponseMessage qui sera initialisée
	 * grâce à la méthode
	 * {@link FenetreReponseMessage#initComponent(boolean, String)}
	 * 
	 * @param reponseDuGerant
	 *            Booléen indiquant quel utilisateur utilise la fenêtre (true)
	 *            pour le gérant et (false) pour le client
	 */
	public FenetreReponseMessage(boolean reponseDuGerant, String sujetDuMessage) {
		super();
		this.setTitle("Répondre au message");
		this.setAlwaysOnTop(true);
		this.setSize(500, 400);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(reponseDuGerant, sujetDuMessage);
	}

	/**
	 * <p>
	 * Initialisation des composants de la fenêtre de reponse :
	 * <ul>
	 * <li>un JPanel contenant le champ de saisie du sujet du message.</li>
	 * <li>un JPanel contenant le champ de saisie du corps du message.</li>
	 * <li>un JPanel contenant les boutons de retour à la boite de réception et
	 * d'envoi du message.</li>
	 * 
	 * @param reponseDuGerant
	 *            Booléen indiquant quel utilisateur utilise la fenêtre (true)
	 *            pour le gérant et (false) pour le client
	 * @param sujetDuMessage
	 *            Sujet du message intial (auquel l'utilisateur va répondre)
	 */
	public void initComponent(boolean reponseDuGerant, String sujetDuMessage) {

		final boolean reponseGerant = reponseDuGerant;
		String gerant = "client";
		if (reponseDuGerant == true) {
			gerant = "gérant";
		}

		// Sujet Message
		JPanel panneauSujet = new JPanel();
		sujetMessage = new JTextField("RE (" + gerant + ") : " + sujetDuMessage);
		sujetMessage.setPreferredSize(new Dimension(200, 25));
		sujetLabel = new JLabel("Objet : ");
		panneauSujet.add(sujetLabel);
		panneauSujet.add(sujetMessage);
		panneauSujet.setBorder(BorderFactory.createEmptyBorder());

		// Contenu Message
		JPanel panneauContenuMessage = new JPanel();
		panneauContenuMessage.setBorder(BorderFactory
				.createLineBorder(Color.darkGray));
		contenuMessage = new JTextArea(8, 30);
		contenuMessage.setBorder(BorderFactory.createLineBorder(Color.black));
		contenuMessage.setEnabled(true);
		contenuMessage.setLineWrap(true);
		contenuMessage.setWrapStyleWord(true);
		contenuLabel = new JLabel("Contenu : ");
		panneauContenuMessage.add(contenuLabel);
		panneauContenuMessage.add(contenuMessage);

		JPanel panneauIdentificationMessage = new JPanel();
		panneauIdentificationMessage.add(panneauSujet, "East");
		panneauIdentificationMessage.setBorder(BorderFactory
				.createLineBorder(Color.GRAY));

		// Définition du panneau dans lequel seront présents les boutons de
		// retour à la page précédente
		// et d'envoi de message
		JPanel panneauBoutonsBas = new JPanel();

		JButton boutonRetourMessagerie = new JButton(
				"Retour à la Boite de Réception");
		JButton boutonEnvoyer = new JButton("Envoyer");

		// Définition des actions relatives à chaque bouton//
		// ------------------------------------------------//

		// Création du bouton permettant le retour à la messagerie //
		// ---------------------------------------------------------//
		boutonRetourMessagerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FenetreMessagerie fenetre = new FenetreMessagerie(reponseGerant);
				fenetre.setVisible(true);
				// permet le retour vers la page contenant la boite de reception
				// du gérant
			}
		});

		// Création du bouton permettant d'envoyer un message après avoir
		// vérifier que les
		// champs remplis respectent les critères pour être inscrits dans la
		// base de données //
		// -----------------------------------------------------------------------------------//
		boutonEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String champ = "";
				try {
					if (contenuMessage.getText().length() == 0) {
						champ = "corps";
						throw new ExceptionChampVide(
								"Le contenu du message est vide !");
					}
					if (sujetMessage.getText().length() == 0) {
						champ = "sujet";
						throw new ExceptionChampVide(
								"Le message n'a pas de sujet !");
					}

					if (contenuMessage.getText().length() > 300) {
						champ = "corps";
						throw new ExceptionExcesDeCaracteres(
								"Le corps du message est trop long !");
					}
					if (sujetMessage.getText().length() > 90) {
						champ = "sujet";
						throw new ExceptionExcesDeCaracteres(
								"Le sujet du message est trop long !");
					} else {
						java.util.Date date = new java.util.Date();

						@SuppressWarnings("deprecation")
						java.sql.Date dateJour = new java.sql.Date(date
								.getYear(), date.getMonth(), date.getDate());
						new Message(sujetMessage.getText(), contenuMessage
								.getText(),
								FenetreLectureMessage.idExpediteurMessage,
								dateJour, false);

						FenetreMessagerie fenetre = new FenetreMessagerie(
								reponseGerant);
						fenetre.setVisible(true);

						// fermeture de la fenetre
						dispose();

					}

				} catch (ExceptionChampVide e2) {
					System.out.println(e2.getMessage());
					JOptionPane.showMessageDialog(null,
							"Votre message n'a pas de " + champ
									+ ",veuillez le préciser.", "Attention",
							JOptionPane.ERROR_MESSAGE);

				} catch (ExceptionExcesDeCaracteres e3) {
					System.out.println(e3.getMessage());
					JOptionPane
							.showMessageDialog(
									null,
									"Le "
											+ champ
											+ " de votre message est trop long,veuillez le raccourcir",
									"Attention", JOptionPane.ERROR_MESSAGE);
				}
				// Création une nouvelle instance de message

				// int verificationChampMessage = Message.verifierChampMessage(
				// contenuMessage.getText(), sujetMessage.getText());
				// System.out.println(verificationChampMessage);
				//
				// switch (verificationChampMessage) {
				// case 0:
				// java.util.Date date = new java.util.Date();
				//
				// @SuppressWarnings("deprecation")
				// java.sql.Date dateJour = new java.sql.Date(date.getYear(),
				// date.getMonth(), date.getDate());
				// new Message(sujetMessage.getText(), contenuMessage
				// .getText(),
				// FenetreDialogIdentification.clientUserIdentifiant,
				// dateJour, true);
				//
				// // fermeture de la fenetre
				// dispose();
				// FenetreMessagerie fenetre = new FenetreMessagerie(
				// reponseGerant);
				// fenetre.setVisible(true);
				//
				// break;
				//
				// case 1:
				// JOptionPane
				// .showMessageDialog(
				// null,
				// "Il y a trop de caractères dans la zone du contenu. Reduisez votre message",
				// "Attention", JOptionPane.ERROR_MESSAGE);
				// break;
				// case 2:
				// JOptionPane
				// .showMessageDialog(
				// null,
				// "Il y a trop de caractères dans le champ du sujet. Reduisez l'intitulé",
				// "Attention", JOptionPane.ERROR_MESSAGE);
				// break;
				// case 3:
				// JOptionPane
				// .showMessageDialog(
				// null,
				// "Votre message contient un caractère interdit : ' ",
				// "Attention", JOptionPane.ERROR_MESSAGE);
				// break;
				// case 4:
				// JOptionPane
				// .showMessageDialog(
				// null,
				// " Le contenu du message est vide, veuillez le remplir",
				// "Attention ", JOptionPane.ERROR_MESSAGE);
				// break;
				// default:
				// break;
				// }
			}
		});

		// Ajout des boutons au panneau principal des boutons panneauBoutons
		panneauBoutonsBas.add(boutonRetourMessagerie);
		panneauBoutonsBas.add(boutonEnvoyer);

		// Ajout des JPanel crées ci-dessus au conteneur de la fenêtre
		this.getContentPane().add(panneauIdentificationMessage,
				BorderLayout.NORTH);
		this.getContentPane().add(panneauContenuMessage, BorderLayout.CENTER);
		this.getContentPane().add(panneauBoutonsBas, BorderLayout.SOUTH);

	}

}
