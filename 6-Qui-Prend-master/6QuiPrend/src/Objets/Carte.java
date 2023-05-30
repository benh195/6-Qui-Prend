package Objets;

public class Carte {
    private int valeur;
    private int teteDeBoeuf;

    /**
     * Constructeur à vide pour les cartes.
     */
    public Carte() {
    }
    
    /**
     * Constructeur de base pour les cartes.
     * @param valeur correpond à la valeur de la carte creer
     */
    public Carte(int valeur) {
        this.valeur = valeur + 1;
        this.teteDeBoeuf = 1;
        if (this.valeur == 55){
            this.teteDeBoeuf = 7;
        }
        else if (this.valeur % 11 == 0){
            this.teteDeBoeuf = 5;
        }
        else if (this.valeur % 10 == 0){
            this.teteDeBoeuf =+ 3;
        }
        else if (this.valeur % 5 == 0){
            this.teteDeBoeuf = 2;
        }
        else{
            this.teteDeBoeuf = 1;
        }
    }

    /**
     * Méthode qui correspond à la règle N°1 du jeu six qui prend. Permet de savoir si la carte que l'on veux poser est superieur a la carte qui la precede dans une des lignes.
     * @param Tligne est le tableau de ligne de l'objet jeu.
     * @return un boolleen qui permet de savoir si la carte est posable dans une des ligne du tableau de lignes.
     */
    public boolean ComparerCarte(Ligne[] Tligne){
        boolean posable = false;
        int i = 0;
        while (!posable && i < Tligne.length){// on parcours toutes les lignes.
            if (this.valeur > Tligne[i].getTligneCarte()[Tligne[i].getTligneCarte().length - 1].getValeur()){
            posable = true;
            } 
            i++;
        }
        return(posable);
    }
    
    /**
     * La méthode PlusPetiteDiff correspond à la règle n°2 des règles du jeu.
     * En revanche cette méthode ne calcule pas une différence mais une somme. Car cette dernière est toujours positif.
     * On constate que la règle n°2 peut être traduit ainsi :
     * On ne peut poser une carte que a la suite d'une carte dont la somme est la plus grande (parmis toutes les cartes en fin de Ligne),
     * mais qui ne dépasse pas le double de la valeur de la carte que l'on souhaite poser.
     * @param Tligne est le tableau de Ligne qui ce trouve en attribut dans la classe Jeu
     * @return ligneppd est le numero (entre 0 et 3) de la ligne ou il faut placer la nouvelle carte.
     */
    public int PlusPetiteDiff(Ligne[] Tligne){
        int ligneppd = 0;
        int somme;
        if ((this.getValeur() + Tligne[0].getTligneCarte()[Tligne[0].getTligneCarte().length - 1].getValeur()) > (2 * this.getValeur())){
            somme = 0;
        }
        else{
            somme = this.getValeur() + Tligne[0].getTligneCarte()[Tligne[0].getTligneCarte().length - 1].getValeur();
        }
        for (int i = 1; i < Tligne.length; i++){
            if ((somme < this.getValeur() + Tligne[i].getTligneCarte()[Tligne[i].getTligneCarte().length - 1].getValeur()) && ((this.getValeur() + Tligne[i].getTligneCarte()[Tligne[i].getTligneCarte().length - 1].getValeur()) < (2 * this.getValeur()))){
                somme = this.getValeur() + Tligne[i].getTligneCarte()[Tligne[i].getTligneCarte().length - 1].getValeur();
                ligneppd = i;
            }
        }
        return(ligneppd);
    }
    
    @Override
    public String toString() {
        return "{[" + getValeur() + "](" + getTeteDeBoeuf() + ")}";
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getTeteDeBoeuf() {
        return teteDeBoeuf;
    }

    public void setTeteDeBoeuf(int teteDeBoeuf) {
        this.teteDeBoeuf = teteDeBoeuf;
    } 
}