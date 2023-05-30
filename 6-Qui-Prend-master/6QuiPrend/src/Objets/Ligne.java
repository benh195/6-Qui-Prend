package Objets;

import CoeurDuJeu.Jeu;
import Outiles.Utile;

public class Ligne {
    private int numLigne;
    private int nbCarte;
    private int longueurMax;
    private Carte TligneCarte[];

    /**
     * Constructeur de ligne dans le cas ou la ligne n'a pas besoin de modification.
     */
    public Ligne() {
    }
    
    /**
     * Constructeur de ligne dans le cas ou la ligne est pleine.
     * @param num numero de la ligne.
     * @param newCarte est la nouvelle carte que l'on ajoute a la ligne.
     */
    public Ligne(int num, Carte newCarte) {
        this.numLigne = num + 1;
        this.nbCarte = 1;
        this.TligneCarte = new Carte[this.nbCarte];
        this.TligneCarte[0] = newCarte;
        this.longueurMax = 5;
    }
    
    /**
     * Constructeur de ligne dans le cas ou il faut poser une nouvelle carte dans une ligne et que les deux premières règles du jeu sont respectées.
     * @param num de la ligne.
     * @param nbCarte nombre de cartes presente dans la ligne.
     * @param tab tableau de cartes qui corespond au cartes presentent dans la ligne.
     * @param newCarte est la nouvelle carte que l'on ajoute a la ligne.
     */
    public Ligne(int num, int nbCarte, Carte[] tab, Carte newCarte) {
        this.numLigne = num + 1;
        this.nbCarte = nbCarte;
        this.TligneCarte = new Carte[this.nbCarte];
        for (int i = 0; i < nbCarte-1; i++){
            this.TligneCarte[i] = tab[i];
        }
        this.TligneCarte[nbCarte-1] = newCarte;
        this.longueurMax = 5;
    }

    /**
     * Ce constructeur est le premier à être utilisé pour la création des ligne au moment de l'instantiation de l'objet jeu.
     * @param jeu est l'objet jeu que l'on appelle ici pour avoir acces au paquet de carte.
     * @param num est un entier entre 0 et 3 qui correspond au numero de la ligne.
     */
    public Ligne(Jeu jeu, int num) {
        this.numLigne = num + 1;
        this.nbCarte = 1;
        this.TligneCarte = new Carte[this.nbCarte];
        this.TligneCarte[0] = Utile.RecupererCarte(jeu.getPaquet(),0);
        jeu.setPaquet(Utile.EnleverCarte(jeu.getPaquet(),0));
        this.longueurMax = 5;
    }

    /*méthode creer dans les premieres versions du programme mais qui n'est plus utile dans cette version.
    public Carte[] AjouterCarte(Carte newCarte, Carte[] tab){
        Carte [] newTab = new Carte[tab.length+1];
        for (int i = 0; i < tab.length; i++){
            newTab[i] = new Carte();
            newTab[i] = tab[i];
        }
        newTab[tab.length] = new Carte();
        newTab[tab.length] = newCarte;
        return(newTab);
    }*/

    @Override
    public String toString() {
        return "Ligne " + numLigne;
    }

    public int getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(int numLigne) {
        this.numLigne = numLigne;
    }

    public int getNbCarte() {
        return nbCarte;
    }

    public void setNbCarte(int nbCarte) {
        this.nbCarte = nbCarte;
    }

    public Carte[] getTligneCarte() {
        return TligneCarte;
    }

    public void setTligneCarte(Carte[] TligneCarte) {
        this.TligneCarte = TligneCarte;
    }

    public int getLongueurMax() {
        return longueurMax;
    }

    public void setLongueurMax(int longueurMax) {
        this.longueurMax = longueurMax;
    }
}