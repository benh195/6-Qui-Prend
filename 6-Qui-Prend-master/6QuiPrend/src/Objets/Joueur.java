package Objets;

import CoeurDuJeu.Jeu;
import CoeurDuJeu.Menu;
import Outiles.Utile;
import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
public class Joueur {
    final private String nom;
    private int nbTeteDeBoeuf;
    private Carte Tmain[];
    private Carte carteChoisie;

    /**
     *Constructeur pour les tests unitaires.
     * @param i correspond a gabriel pour le joueur 1 et a Colleen pour le joueur 2 dans les tests unitaires.
     */
    public Joueur(int i){
        if (i == 1){
            this.nom = "Gabriel";
            this.nbTeteDeBoeuf = 100;
            this.Tmain = null;
            this.carteChoisie = null;
        }
        else{
            this.nom = "Colleen";
            this.nbTeteDeBoeuf = 50;
            this.Tmain = null;
            this.carteChoisie = null;
        }
    }
    /**
     * Constructeur de joueur
     * @param jeu est l'objet jeu que l'on appelle ici pour avoir acces au paquet de carte.
     */
    public Joueur(Jeu jeu) {
        System.out.println("Comment vous appellez vous joueur ?");
        Scanner joueur = new Scanner(System.in);
        this.nom = joueur.nextLine();
        this.nbTeteDeBoeuf = 0;
        this.Tmain = new Carte[10];
        if (Menu.getRep() != 3){
            for(int i = 0; i < 10; i++){
            this.Tmain[i] = Utile.RecupererCarte(jeu.getPaquet(),0);
            jeu.setPaquet(Utile.EnleverCarte(jeu.getPaquet(),0));
            }
        }
    }
    
    /**
     * Méthode utilisée dans le cas de la variante 2 pour permmetre aux joueurs de choisir leur cartes.
     * @param jeu est l'objet jeu que l'on appelle ici pour avoir acces au paquet de carte.
     * @param posi correspond a la position dans la main de la carte que l'on ajoute.
     */
    public void CreationMainVar2(Jeu jeu, int posi){
        System.out.println(this.nom + " vous devez choisir une carte parmis celle restante dans le paquet");
        System.out.println("voici le paquet");//on affiche le paquet
        for (int i = 0; i < jeu.getPaquet().length; i++){
                System.out.println(jeu.getPaquet()[i]);
        }
        Scanner joueur = new Scanner(System.in);
        boolean present = false;
        while(!present){//permet de dire si la carte est deja prise ou non et permet de rentrer une autre valeur
            int valchoisie = joueur.nextInt();
            for (int i = 0; i < jeu.getPaquet().length; i++){
                if (jeu.getPaquet()[i].getValeur() == valchoisie){
                        this.Tmain[posi] = Utile.RecupererCarte(jeu.getPaquet(), i);
                        jeu.setPaquet(Utile.EnleverCarte(jeu.getPaquet(), i));
                        present = true;
                }
            }
            if (!present){
                System.out.println("La carte que vous avez choisie a déjà été prise, choisissez en une autre");
            }
        }
    }

    /**
     * Permet de trier dans l'ordre croissant la main d'un joueur.
     */
    public void TrierMain(){//simple méthode de trie appliqué à des variables de type Carte.
        int posMin;
        for (int i = 0; i < this.Tmain.length - 1; i++){
            posMin = i;
            for (int j = i +1; j < this.Tmain.length; j++){
                if (this.Tmain[j].getValeur() < this.Tmain[posMin].getValeur()){
                    posMin = j;
                }
            }
            Carte temp = this.Tmain[posMin];
            this.Tmain[posMin] = this.Tmain[i];
            this.Tmain[i] = temp;
        }
        
    }
    
    /**
     * Permet au joueur de choisir la carte qu'il veut joueur et la stocke dans l'atribut carteChoisie.
     */
    public void ChoisirCarte(){
        Carte carteChoisie = new Carte();
        System.out.println("Et voici vos cartes " + this.nom + ": Choisisez celle que vous voulez poser (entrez la valeur de celle-ci).");
        for (int i = 0; i <this.Tmain.length; i++){
            System.out.println(this.Tmain[i]);
        }
        Scanner joueur = new Scanner(System.in);
        boolean present = false;
        while(!present){//permet d'éviter une erreur si l'on ne tape pas la bonne valeur
            int valchoisie = joueur.nextInt();
            for (int i = 0; i <this.Tmain.length; i++){
                if (this.Tmain[i].getValeur() == valchoisie){
                    carteChoisie = Utile.RecupererCarte(this.Tmain, i);
                    this.Tmain = Utile.EnleverCarte(this.Tmain, i);
                    present = true;
                }
            }
            if (!present){
                System.out.println("La carte que vous avez choisie ne fait pas partie de votre main, choisissez en une autre");
            }
        }
        setCarteChoisie(carteChoisie);
    }
    
    /**
     * Permet au joueur de poser la carte qu'il a choisi avec ChoisirCarte().
     * @param tabLigne correspond au tableau de ligne de l'objet jeu.
     * @param numLigne correspond au numero de la ligne qu'il faut modifier.
     * @return le nouveau tableau de ligne avec la ligne modifiée.
     */
    public Ligne[] PoserCarte(Ligne[] tabLigne, int numLigne){
        Ligne[] newTligne = new Ligne[tabLigne.length];
        for (int i = 0; i < tabLigne.length; i++){//passe par chaque ligne du tableau.
            if (i == numLigne){//si on est sur la ligne a modifier, on a deux cas : soit on pose la carte facilement car il y a la place, soit on il n'y a pas la place donc on recupere la ligne et on pose la carte en suite.
                if ((tabLigne[i].getNbCarte() + 1) <= tabLigne[i].getLongueurMax() && this.carteChoisie.getValeur() > tabLigne[i].getTligneCarte()[tabLigne[i].getTligneCarte().length-1].getValeur()){
                    newTligne[i] = new Ligne(i, tabLigne[i].getNbCarte()+1,tabLigne[i].getTligneCarte(),this.carteChoisie);
                }
                else{
                    this.nbTeteDeBoeuf = this.nbTeteDeBoeuf + Utile.RecupererTeteDeBoeuf(tabLigne[i]);
                    newTligne[i] = new Ligne(i, this.carteChoisie);
                }
            }
            else{//si on est sur une ligne a ne pas modifier.
                newTligne[i] = new Ligne();
                newTligne[i] = tabLigne[i];
            }
        }
        return(newTligne);
    }
    
    /**
     * permet au joueur de choisir une ligne dans le cas ou il doit choisir une ligne a recuoerer parcequ'il ne pouvait pas poser sa carte.
     * @return le numero de la ligne choisie
     */
    public int ChoisirLigne(){
        Scanner joueur = new Scanner(System.in);
        int numLigne = joueur.nextInt();
        numLigne = numLigne - 1; 
        return(numLigne);
    }

    @Override
    public String toString() {
        return (nom);
    }

    public int getNbTeteDeBoeuf() {
        return nbTeteDeBoeuf;
    }

    public void setNbTeteDeBoeuf(int nbTeteDeBoeuf) {
        this.nbTeteDeBoeuf = nbTeteDeBoeuf;
    }

    public Carte[] getTmain() {
        return Tmain;
    }

    public void setTmain(Carte[] Tmain) {
        this.Tmain = Tmain;
    }

    public Carte getCarteChoisie() {
        return carteChoisie;
    }

    public void setCarteChoisie(Carte carteChoisie) {
        this.carteChoisie = carteChoisie;
    }

    public String getNom() {
        return nom;
    }
}
