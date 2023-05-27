package CoeurDuJeu;

import Objets.Carte;
import Objets.Joueur;
import Objets.Ligne;
import Outiles.Utile;

/**
 *
 * @author Gabriel
 */
public class Jeu {
    private Carte[] paquet;
    private Carte[] copiePaquet;
    private Joueur[] Tjoueur;
    private Ligne[] Tligne;
    private int nbTeteMax;

    /**
     *Constructeur d'un jeu.
     */
    public Jeu() {
        this.paquet = CreerPaquet();//creation du paquet.
        this.copiePaquet = this.paquet;//on copie le paquet pour pouvoir le reutiliser sans avoir a le recreer a chaque nouvelle manche.
        this.Tjoueur = CreerJoueur();//creation des joueurs.
        this.Tligne = CreerLigne();//creation des lignes.
        this.nbTeteMax = 0;
    }

    /**
     *methode qui permet de creerun paquet de carte pour le jeu six qui prend.
     * @return le paquet tout juste creer.
     */
    private Carte[] CreerPaquet(){
        int rep = Menu.getRep();
        if (rep == 2){//création d'un paquet pour la variante 1 du mode classique
            paquet = new Carte[(Menu.getNbJoueur()*10)+4];
            for (int i = 0; i < (Menu.getNbJoueur()*10)+4; i++){
                paquet[i] = new Carte(i);
            }
            paquet = Utile.MelangeTableau(paquet);
        }
        else{//création d'un paquet pour le mode classique
            paquet = new Carte[104];
            for (int i = 0; i < 104; i++){
                paquet[i] = new Carte(i);
            }
            if (rep != 3){//le paquet de 104 cartes tout juste créer n'est pas mélanger dans la variante 2 du mode classique pour ainsi faciliter le choix de chaque joueur
            paquet = Utile.MelangeTableau(paquet);
            }
        }
        return (paquet);
    }

    /**
     * methode qui permet de creer les joueur de six qui prend et les range dans un tableau.
     * @return un tableau de joueurs fraichement créés.
     */
    private Joueur[] CreerJoueur(){
        Tjoueur = new Joueur[Menu.getNbJoueur()];
        for (int i = 0; i < Menu.getNbJoueur(); i++){
                Tjoueur[i] = new Joueur(this);
        }
        if (Menu.getRep() == 3){//boucle utilisée pour la création des main des joueurs par choix d'une carte à la fois
            int j = 0;
            while(j < 10){
                for (int i = 0; i < Menu.getNbJoueur(); i++){
                    Tjoueur[i].CreationMainVar2(this,j);
                }
                j++;
            }
            paquet = Utile.MelangeTableau(paquet);//on mélange ce qu'il reste du paquet pour que les lignes soit choisies aléatoirement
        }
        for (int i = 0; i < Menu.getNbJoueur(); i++){
            Tjoueur[i].TrierMain();
        }
        return(Tjoueur);
    }

    /**
     * methode qui creer les ligns du jeu.
     * @return un tableau de lignes.
     */
    private Ligne[] CreerLigne(){
        Tligne = new Ligne[4];
        for (int i = 0; i < 4; i++){
            Tligne[i] = new Ligne(this, i);
        }
        return(Tligne);
    }

    /**
     * Permet de trier les joueur en fonction de la carte choisie.
     * Ainsi il suffi de parcourir Tjoueur pour faire jouer les joueurs dans le bonne ordre.
     * @param Tjoueur est le tableau de joueur.
     * @return le tableau de joueur trier.
     */
    private Joueur[] TrierTjoueur(Joueur[] Tjoueur){
        int posMin;
        for(int i = 0; i < Tjoueur.length - 1; i++){
            posMin = i;
            for(int j = i + 1; j < Tjoueur.length; j++){
                if (Tjoueur[j].getCarteChoisie().getValeur() < Tjoueur[posMin].getCarteChoisie().getValeur()){
                    posMin = j;
                }
            }
            Joueur temp = Tjoueur[posMin];
            Tjoueur[posMin] = Tjoueur[i];
            Tjoueur[i] = temp;
        }
        return(Tjoueur);
    }
    
    /**
     * Methode qui organise le deroulement d'un tour.
     * Chaque joueur choisie une carte à poser, on trie les joueurs puis on leur fait poser dans l'ordre leur carte sur les lignes.
     */
    private void Tour(){
        //demande à tous les joueurs de choisir une carte
        for (int i = 0; i < this.Tjoueur.length; i++){
            System.out.println("Voici les lignes");
            for (int j = 0; j < 4; j++){
            System.out.print(this.Tligne[j] + " ");
                for (int k = 0; k < this.Tligne[j].getTligneCarte().length; k++){
                    System.out.print(" " + this.Tligne[j].getTligneCarte()[k] + " ");
                }
                System.out.println();
            }
            this.Tjoueur[i].ChoisirCarte();
        }
        //trie les joueurs en fonction de leur carte choisie
        this.Tjoueur = TrierTjoueur(Tjoueur);
        //pose les cartes dans le bonne ordre
        for (int i = 0; i < this.Tjoueur.length; i++){
            if (Tjoueur[i].getCarteChoisie().ComparerCarte(this.Tligne)){
                this.Tligne = Tjoueur[i].PoserCarte(this.Tligne,Tjoueur[i].getCarteChoisie().PlusPetiteDiff(this.Tligne));
            }
            else{
                System.out.println("La carte que tu as choisi " + Tjoueur[i] + " ne peut pas être posée.");
                System.out.println("Tu dois choisir une ligne dont tu récupèreras toutes les têtes de boeuf");
                System.out.println("Voici les lignes");
                for (int j = 0; j < 4; j++){
                    System.out.print(this.Tligne[j] + " ");
                    for (int k = 0; k < this.Tligne[j].getTligneCarte().length; k++){
                        System.out.print(" " + this.Tligne[j].getTligneCarte()[k] + " ");
                    }
                    System.out.println();
                }
                this.Tligne = Tjoueur[i].PoserCarte(this.Tligne,Tjoueur[i].ChoisirLigne());
            }
        }   
    }

    /**
     * Methode qui organise le deroulement d'une manche.
     * On repete 10 fois un tour pour que toutes les cartes de joueurs soit posées. On prepere alors la manche qui suit et on change le seuil nbTeteMax.
     */
    public void Manche(){
        do {
            System.out.println("Nouvelle manche");
            for (int i = 0; i < 10; i++){
                Tour();
            }
            PreparerNewManche();
            ChangerNbTeteMax();
            for (int i = 0; i < this.getTjoueur().length; i++){
            System.out.println(this.getTjoueur()[i] + " a " + this.getTjoueur()[i].getNbTeteDeBoeuf() + " têtes de boeuf");
            }
        }while (this.nbTeteMax <= 66);
    }

    /**
     * Methode qui permet de preparer une nouvelle manche.
     * Recupere le paquet redistribut des cartes aux joueurs et recreer des lignes. 
     */
    private void PreparerNewManche(){
        if (Menu.getRep() != 3){//on recupere le paquet pour generer une nouvelle manche
            this.paquet = Utile.MelangeTableau(this.copiePaquet);
            for (int i = 0; i < this.Tjoueur.length; i++){//on distribut a nouveau 10 cartes aux joueurs
                Carte[] newTcarte = new Carte[10];
                for(int j = 0; j < 10; j++){
                    newTcarte[j] = Utile.RecupererCarte(this.paquet,0);
                    this.paquet = Utile.EnleverCarte(this.paquet,0);
                    this.Tjoueur[i].setTmain(newTcarte);   
                }
                this.Tjoueur[i].TrierMain();
            }
        }
        else{//cas de la variante 2 du mode classique
            this.paquet = this.copiePaquet;
            for (int i = 0; i < this.Tjoueur.length; i++){
                this.Tjoueur[i].setTmain(new Carte[10]);
            }
            int j = 0;
            while(j < 10){
                for (int i = 0; i < this.Tjoueur.length; i++){
                    Tjoueur[i].CreationMainVar2(this,j);
                }
                j++;
            }
            for (int i = 0; i < Menu.getNbJoueur(); i++){
            Tjoueur[i].TrierMain();
            }
            paquet = Utile.MelangeTableau(paquet);//on mélange ce qu'il reste du paquet pour que les lignes soit choisies aléatoirement
        }
        for (int i = 0; i < this.Tligne.length; i++){//creation des nouvelles lignes
            this.Tligne[i] = new Ligne(this, i);
        }
    }

    /**
     * Methode qui change le nbTeteMax.
     * Met a jour la variable nbTeteMax qui permet de savoir quand une parti doit s'arreter.
     */
    private void ChangerNbTeteMax(){
        int teteMax = Tjoueur[0].getNbTeteDeBoeuf();
        for (int i = 1; i < this.Tjoueur.length; i++){
            if (teteMax < Tjoueur[i].getNbTeteDeBoeuf()){
                teteMax = Tjoueur[i].getNbTeteDeBoeuf();
            }
        }
        this.nbTeteMax = teteMax;
    }

    /**
     *Methode qui permet de commencer une partie.
     */
    public void Jouer(){
            Manche();
            System.out.println("Le joueur gagnant est " + Utile.JoueurGagnant(this.Tjoueur));
    }

    public Carte[] getPaquet() {
        return paquet;
    }
    
    public void setPaquet(Carte[] paquet) {
        this.paquet = paquet;
    }

    public Carte[] getCopiePaquet() {
        return copiePaquet;
    }

    public void setCopiePaquet(Carte[] copiePaquet) {
        this.copiePaquet = copiePaquet;
    }
    
    public Joueur[] getTjoueur() {
        return Tjoueur;
    }

    public void setTjoueur(Joueur[] Tjoueur) {
        this.Tjoueur = Tjoueur;
    }

    public Ligne[] getTligne() {
        return Tligne;
    }

    public void setTligne(Ligne[] Tligne) {
        this.Tligne = Tligne;
    }
}
