package Outiles;


import Objets.Carte;
import Objets.Joueur;
import Objets.Ligne;

/**
 * @author Gabriel
 */
public class Utile {
    
    /**
     * Pour un tableau donné renvoie le tableau melangé.
     * @param tab est un tableau de Carte, cette méthode n'est utilisée que pour mélanger le paquet de cartes donc le tableau est forcement le paquet et pas un autre tableau.
     * @return le tableau pris en paramètre bien mélangé.
     */
    public static Carte[] MelangeTableau(Carte[] tab){
        for (int k = 0; k < 555; k++){//correspond au nombre d'échange effectué dans le tableau.
            int i = 0 + (int)(Math.random() * (((tab.length-1) - 0) + 1));//choisi la position d'un premier element du tableau aleatoiorement.
            int j = 0 + (int)(Math.random() * (((tab.length-1) - 0) + 1));//choisi la position d'un deuxieme element du tableau aleatoiorement.
            Carte temp;//creation d'un variable tempon pour stocker une carte, puis on effectue l'échange. 
            temp = tab[i];
            tab[i] = tab[j];
            tab[j] = temp;
        }
        return (tab);
    }
    
    /**
     * Pour un tableau donné cherche la carte dont la position corespond à celle donnée en parametre.
     * @param tab est un tableau de cartes, soit le paquet soit la main d'un joueur.
     * @param posi est la position ou se trouve la carte à recuperer dans le tableau.
     * @return la carte qu'il faut recuperer.
     */
    public static Carte RecupererCarte(Carte[] tab, int posi){
        Carte enleveCarte = new Carte();
        for (int i = 0; i < tab.length; i++){
            if (i == posi){
                enleveCarte = tab[i];//recupere la carte.
            }
        }
        return(enleveCarte);
    }
    
    /**
     * Pour un tableau donné enleve la carte de la position donnée en recopiant tous les element sauf un dans un autre tableau dont la taille est inferieur de 1'element
     * @param tab est un tableau de cartes, soit le paquet soit la main d'un joueur.
     * @param posi est la position ou se trouve la carte à enlever dans le tableau.
     * @return le nouveau tableau avec une carte en moins.
     */
    public static Carte[] EnleverCarte(Carte[] tab, int posi){
        Carte[] newTab = new Carte[tab.length-1];//reconstruit le tableau avec un element en moins.
        for (int i = 0; i < tab.length-1; i++){
            if (i < posi){
                newTab[i] = new Carte();
                newTab[i] = tab[i];
                }
            else{
                newTab[i] = new Carte();
                newTab[i] = tab[i+1];
            }
        }
        return(newTab);
    }
    
    /**
     * Calcule la somme des tetes de boeuf de toute les cartes d'une ligne donnée.
     * @param l est la ligne dans la quelle on recupère toutes les tetes de boeuf.
     * @return la somme de toute les tetes de boeuf de la ligne choisie.
     */
    public static int RecupererTeteDeBoeuf(Ligne l){
        int sommeTete = 0;
        for (int i = 0; i < l.getTligneCarte().length; i++){
            sommeTete = sommeTete + l.getTligneCarte()[i].getTeteDeBoeuf();
        }
        return (sommeTete);
    }
    
    /**
     * On parcours chaque joueur pour trouver le joueur gagnant, celui qui a le moins de tete de boeuf.
     * @param Tjoueur est le tableau de joueur
     * @return le joueur avec le moins de tetes de boeuf, celui qui a donc gagné la partie.
     */
    public static Joueur JoueurGagnant(Joueur[] Tjoueur){
        Joueur joueurGagnant = Tjoueur[0];
        int teteMin = Tjoueur[0].getNbTeteDeBoeuf();
        for (int i = 1; i < Tjoueur.length; i++){
            if (teteMin > Tjoueur[i].getNbTeteDeBoeuf()){
                joueurGagnant = Tjoueur[i];
                teteMin = Tjoueur[i].getNbTeteDeBoeuf();
            }
        }
        return(joueurGagnant);
    }
    
    
    
    
    
}
