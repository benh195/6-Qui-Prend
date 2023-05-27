package Outiles;

import Objets.Carte;
import Objets.Joueur;
import Objets.Ligne;
import CoeurDuJeu.Jeu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gabriel
 */
public class UtileTest {
    
    public UtileTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    private Carte[] TestPaquet(){ //paquet de test avec toute les cartes.
        Carte[] testPaquet = new Carte[104];
        for(int i = 0; i < testPaquet.length; i++){
            testPaquet[i] = new Carte(i);
        }
        return (testPaquet);
    }
    
    /**
     * Test of MelangeTableau method, of class Utile.
     */
    @Test
    public void testMelangeTableau() {
        System.out.println("MelangeTableau");
        Carte[] tab = TestPaquet();//le paquet est créer et toutes les cartes sont rangées par ordre croissant.
        tab = Utile.MelangeTableau(tab);//on melange alors le paquet
        int compt = 0;
        for(int i = 0; i < tab.length-1; i++){
            if(tab[i].getValeur() > tab[i+1].getValeur()){//si on trouve une valeur supérieure à celle de la carte qui suit, on ajoute 1 à la variable compt
                compt++;
            }
        }
        assertTrue(compt > 0.4*tab.length);
    }

    /**
     * Test of RecupererCarte method, of class Utile.
     */
    @Test
    public void testRecupererCarte() {
        System.out.println("RecupererCarte");
        Carte[] tab = new Carte[2];
        tab[0] = new Carte(0);
        tab[1] = new Carte(1);
        int posi = 0;
        Carte expResult = new Carte(0);
        Carte result = Utile.RecupererCarte(tab, posi);
        assertTrue(result.getValeur() == expResult.getValeur());
    }

    /**
     * Test of EnleverCarte method, of class Utile.
     */
    @Test
    public void testEnleverCarte() {
        System.out.println("EnleverCarte");
        Carte[] tab = new Carte[2];
        Carte c1 = new Carte(0);
        Carte c2 = new Carte(1);
        int posi = 0;
        Carte[] expResult = new Carte[1];
        expResult[0] = new Carte(1);
        Carte[] result = Utile.EnleverCarte(tab, posi);
        assertTrue(result.length == expResult.length);
    }

    /**
     * Test of RecupererTeteDeBoeuf method, of class Utile.
     */
    @Test
    public void testRecupererTeteDeBoeuf() {
        System.out.println("RecupererTeteDeBoeuf");
        Jeu jeu = new Jeu();
        Carte[] tab = TestPaquet();//le paquet est créer et toutes les cartes sont rangées par ordre croissant.
        Ligne l = new Ligne(1, new Carte(54));//ligne avec une seule carte la 55 qui compte 7 tetes de boeuf.
        int expResult = 7;
        int result = Utile.RecupererTeteDeBoeuf(l);
        assertEquals(expResult, result);
    }

    /**
     * Test of JoueurGagnant method, of class Utile.
     */
    @Test
    public void testJoueurGagnant() {
        System.out.println("JoueurGagnant");
        Joueur[] Tjoueur = new Joueur[2];
        Joueur j1 = new Joueur(1);
        Joueur j2 = new Joueur(2);
        Tjoueur[0] = j1;
        Tjoueur[1] = j2;
        String expResult = "Colleen";
        Joueur result = Utile.JoueurGagnant(Tjoueur);
        assertTrue(expResult.equals(result.getNom()));
    }
}