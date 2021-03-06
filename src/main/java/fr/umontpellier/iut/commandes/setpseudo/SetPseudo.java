package fr.umontpellier.iut.commandes.setpseudo;

import fr.umontpellier.iut.GestionnairePseudonyme;
import fr.umontpellier.iut.bd.BaseDonnee;
import fr.umontpellier.iut.commandes.Commande;
import fr.umontpellier.iut.commandes.exceptions.UtilisateurAFaitUneMauvaiseCommandeException;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

interface SetPseudo extends Commande {

    @Override
    default void execute(Message messageRecue) {
        String feedBack;
        String[] messageSplit = messageRecue.getContentRaw().split(" ", 2);
        if (messageSplit.length != 2)
            feedBack = getMessageAide();
        else
            feedBack = executeSetPseudo(messageSplit[1], messageRecue.getAuthor());
        messageRecue.getChannel().sendMessage(feedBack).queue();
    }

    default String executeSetPseudo(String designationGuilde, User user) {
        try {
            long idGuild = getIdGuild(designationGuilde, user);
            int indexPseudo = BaseDonnee.attribuerNouveauPseudo(user.getIdLong(), idGuild);
            return GestionnairePseudonyme.getMessageNominationParLaCape(indexPseudo);
        } catch (UtilisateurAFaitUneMauvaiseCommandeException e) {
            return e.getMessage();
        }
    }

    long getIdGuild(String designationGuild, User user) throws UtilisateurAFaitUneMauvaiseCommandeException;

    @Override
    String getMessageAide();
}
