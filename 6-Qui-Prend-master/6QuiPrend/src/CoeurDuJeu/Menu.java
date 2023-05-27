package CoeurDuJeu;

import java.util.Scanner;

/**
 * @author Gabriel
 */
public class Menu {
    static private int nbJoueur;
    static private int rep;
    
    /**
     * Demande à l'utlisateur le nombre de joueurs dans la partie.
     */
    private static void DemanderNbJoueur(){
        do{
        System.out.println("Combien y a t-il de joueur ?");
        Scanner menu = new Scanner(System.in);
        nbJoueur = menu.nextInt();
        if(nbJoueur < 2 && nbJoueur > 10){
            System.out.println("Nombre de joueur incorrecte, recommencer");
        }
        }while(nbJoueur < 2 && nbJoueur > 10);
    }
    
    /**
     *Affichage du menu avec le choix du mode de jeu.
     */
    public static void Menu(){
        String espace = System.getProperty("line.separator");//ligne de code trouvé sur Internet, on attribut à la variable string espace la propriété de sauter une ligne quand on l'affiche
        System.out.println("                    BIENVENUE DANS 6 QUI PREND !!!                    "//affichage du menu de jeu
        + espace +         "                                                                      "
        + espace +         "<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>"
        + espace +         "                                                                      "
        + espace +         "   Plusieurs mode de jeu sont disponible sans compter l'extention !   "//choix du mode
        + espace +         "                                                                      "
        + espace +         "   Pour jouer au mode classique tapez #1#                             "
        + espace +         "   Pour jouer à la variante 1 du mode classique tapez #2#             "
        + espace +         "   Pour jouer à la variante 2 du mode classique tapez #3#             "
        + espace +         "   Pour jouer à l'extention tapez #4#                                 "
        + espace +         "   Pour jouer à la variante 1 de l'extention tapez #5#                "
        + espace +         "   Pour jouer à la variante 2 de l'extention tapez #6#                "
        + espace +         "                                                                      "
        + espace +         "<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>"
        + espace +         "                                                                      ");
        Scanner menu = new Scanner(System.in);
        rep = menu.nextInt();
        DemanderNbJoueur();
    }

    public static int getNbJoueur() {
        return nbJoueur;
    }

    public static int getRep() {
        return rep;
    }   
}