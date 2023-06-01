package CoeurDuJeu;

import java.util.Scanner;

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
        + espace +         "   Pour jouer au jeu tapez #1# dans la console                        "
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