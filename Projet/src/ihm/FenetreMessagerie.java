package ihm;

import ihm.modeleTableau.ModeleMessagerie;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import metier.Message;

/**
 * La classe FenetreMessagerie permet aux différents utilisateurs d'avoir accès
 * à une boite de réception pour pallier au fait que JavaMail ne fonctionne pas
 * à l'Ensai. De cette boite de réception, l'utilisateur peut accéder à d'autres
 * fenêtres permettant de lire ses messages, les supprimer ou d'y répondre.
 * 
 * @see FenetreLectureMessage
 * @see FenetreReponseMessage
 * 
 * @author Utilisateur
 * 
 */
public class FenetreMessagerie extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de la classe {@link FenetreMessagerie} initialisée grâce à
	 * la méthode
	 * 
	 * @link {@link FenetreMessagerie#initComponent(boolean)}
	 * 
	 * @param boiteMessagerieGerant
	 *            Booléen indiquant quel utilisateur utilise la fenêtre (true)
	 *            pour le gérant et (false) pour les clients
	 *
	 * @param parent
	 *            JFrame utilisé pour créer la fenêtre

	 * @param modal
	 *            Booléen indiquant si la fenêtre doit bloquer ou non les
	 *            interactions avec les autres fenêtres
	 */
	public FenetreMessagerie(boolean boiteMessagerieGerant, JFrame parent, boolean modal) {
		super(parent,"Boite de Réception ",modal);
		this.setTitle("Boite de Réception ");
		this.setSize(500, 900);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent(boiteMessagerieGerant);
	}

	private void initComponent(boolean messagerieGerant) {
		final boolean messagerieDuGerant = messagerieGerant;

		// Création d'une table contenant tous les messages envoyés par les //
		// clients au gérant après interrogation de la base de données //
		// -------------------------------------------------------------------//
		final ModeleMessagerie modele = new ModeleMessagerie(messagerieGerant);
		final JTable tableauMessage = new JTable(modele);
		final JScrollPane tab = new JScrollPane(tableauMessage);

		// Création du panneau qui se situera en haut de la fenêtre créée //
		// ----------------------------------------------------------------//
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BorderLayout());

		/**
		 * Création d'un "sous-panneau" accueillant tous les boutons relatifs
		 * aux actions que le gérant peut faire sur les messages de ses clients,
		 * qui sera inséré dans panneauHaut
		 */
		JPanel panneauTitle = new JPanel();
		JButton boutonLire = new JButton("Lire");
		JButton boutonSupprimerTout = new JButton("Supprimer tout");

		// Si le tableau est vide, nous désactivons          //
		// les boutons afin d'éviter aux utilisateurs        //
		// de vouloir faire des actions dans un tableau vide //
		// --------------------------------------------------//
		if (modele.getRowCount() == 0) {
			boutonLire.setEnabled(false);
			boutonSupprimerTout.setEnabled(false);
		}

		// Définition de l'action du bouton permettant de lire un message //
		// ----------------------------------------------------------------//
		boutonLire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération de la ligne selctionnée et des attributs du
				// message
				int ligne = tableauMessage.getSelectedRow();

				if (ligne != -1) {
					String identifiantMail = tableauMessage
							.getValueAt(ligne, 0).toString();
					String expediteur = tableauMessage.getValueAt(ligne, 1)
							.toString();
					String sujet = tableauMessage.getValueAt(ligne, 2)
							.toString();
					String contenu = tableauMessage.getValueAt(ligne, 3)
							.toString();
					String date = tableauMessage.getValueAt(ligne, 4)
							.toString();
					
					// Fermeture de la fenêtre
					dispose();
					
					// Ouverture de la fenetre de lecture de message en passant
					// en
					// paramètre
					// du constructeur les attributs du message selectionné
					FenetreLectureMessage fenMessage = new FenetreLectureMessage(
							null, "Message : " + sujet, true, expediteur,
							sujet, contenu, date, identifiantMail,
							messagerieDuGerant);
					fenMessage.setVisible(true);

					
				} else {
					JOptionPane.showMessageDialog(null,
							"Vous n'avez sélectionné aucun message",
							"Attention", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		// Définition de l'action du bouton permettant //
		// ------ de supprimer tous les messages ------//
		// --------------------------------------------//
		boutonSupprimerTout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Suppression de tous les messages de la Base de Données
				// correspondants à l'utilisateur
				// courant de l'application
				Message.supprimerAllBDD();

				FenetreMessagerie message = new FenetreMessagerie(true,null,true);
				message.setVisible(true);
				
				dispose();
			}
		});

		// Ajout de ses boutons au sous JPanel << panneauTitle>> //
		// -------------------------------------------------------//
		panneauTitle.add(boutonLire);
		panneauTitle.add(boutonSupprimerTout);
		panneauHaut.add(panneauTitle, "Center");

		// Définition du panneau panneauBouton qui accueillera le bouton //
		// ---- permettant de retourner à la page précédente ------------//
		// --------------------------------------------------------------//
		JPanel panneauBouton = new JPanel();

		JButton retourBouton = new JButton("Retour");
		retourBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// l'instruction permet de fermer la fenetre en cours
				dispose();
			}
		});

		panneauBouton.add(retourBouton);

		// Ajout des principaux composants JPanel au conteneur de la fenêtre //
		// -------------------------------------------------------------------//
		this.getContentPane().add(tab, BorderLayout.CENTER);
		this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
		this.getContentPane().add(panneauBouton, BorderLayout.SOUTH);

		pack();

	}

}
