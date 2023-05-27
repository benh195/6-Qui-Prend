package SixQuiPrend;

import CoeurDuJeu.Jeu;
import CoeurDuJeu.Menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Menu.Menu(); //affichage du menu
        Jeu jeu = new Jeu();//instantiation de l'objet jeu 
        jeu.Jouer();//joue une partie en fonction du mode choisi dans le menu 
        
        /*  ligne de code utilisées pour afficher le paquet, les joueurs et les lignes. Plus utilisé mais toujours là au cas où.
            for (int i = 0; i < jeu.getPaquet().length; i++){
                System.out.println(jeu.getPaquet()[i]);
            }
        for (int i = 0; i < jeu.getTjoueur().length; i++){
            System.out.println(jeu.getTjoueur()[i]);
            for (int j = 0; j < jeu.getTjoueur()[i].getTmain().length ; j++){
                System.out.println(jeu.getTjoueur()[i].getTmain()[j]);
                
            }
            System.out.println("nombre de tete de boeuf" + jeu.getTjoueur()[i].getNbTeteDeBoeuf());
        }
        for (int i = 0; i < 4; i++){
            System.out.println(jeu.getTligne()[i]);
            for (int j = 0; j < jeu.getTligne()[i].getTligneCarte().length; j++){
                System.out.println(jeu.getTligne()[i].getTligneCarte()[j]);
            }
        }*/
    }
}
