package fr.umontpellier.iut.commandes.exceptions;

/**
 * Cette class est utilisé lorsque l'utilisateur qui à envoyer la commande à mal rédigé la commande
 */
public class UtilisateurAFaitMauvaiseCommandeException extends Exception {

    public UtilisateurAFaitMauvaiseCommandeException(String message) {
        super(message);
	}

	/**
     *
     */
    private static final long serialVersionUID = 601882022735885224L;

}
