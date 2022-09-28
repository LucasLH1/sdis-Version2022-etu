package siolacol;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class CaserneTest {

    private Pompier unPompierDispo1;
    private Pompier unPompierDispo2;

    private Pompier unPompierAuTravail1;
    private Pompier unPompierAuTravail2;

    private Periode unePeriode;

    private ArrayList<Pompier> lesPompiers;

    private Caserne uneCaserne;

    @BeforeEach
    void setUp() {

        unPompierDispo1 = new Pompier("Legall", "Yasmina", "3");
        unPompierDispo2 = new Pompier("Martin", "Alain", "17");
        unPompierAuTravail1 = new Pompier("Dubois", "Yves", "9");
        unPompierAuTravail2 = new Pompier("Dupond", "Carole", "4");

        unePeriode = new Periode(new GregorianCalendar(2022, 10, 04), 1);

        uneCaserne = new Caserne(null);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Appel equipe complète")
    void appelEquipeComplete() {

        unePeriode.setStatutPompier("d", unPompierDispo1);
        unePeriode.setStatutPompier("d", unPompierDispo2);
        unePeriode.setStatutPompier("t", unPompierAuTravail1);
        unePeriode.setStatutPompier("t", unPompierAuTravail2);

        int nbPompiersAppeles = uneCaserne.appelEquipe(unePeriode,3);

        assertEquals(3, nbPompiersAppeles);
    }

    @Test
    @DisplayName("Appel equipe incomplète")
    void appelEquipeIncomplete() {

        unePeriode.setStatutPompier("d", unPompierDispo1);
        unePeriode.setStatutPompier("d", unPompierDispo2);
        unePeriode.setStatutPompier("t", unPompierAuTravail1);
        unePeriode.setStatutPompier("t", unPompierAuTravail2);

        int nbPompiersAppeles = uneCaserne.appelEquipe(unePeriode,5);

        assertEquals(4, nbPompiersAppeles);
    }

    @Test
    @DisplayName("Appel equipe vide")
    void appelEquipeVide() {

        int nbPompiersAppeles = uneCaserne.appelEquipe(unePeriode,3);

        assertEquals(0, nbPompiersAppeles);
    }

    @Test
    @DisplayName("Appel deux equipes")
    void appelDeuxEquipes() {

        unePeriode.setStatutPompier("d", unPompierDispo1);
        unePeriode.setStatutPompier("d", unPompierDispo2);
        unePeriode.setStatutPompier("t", unPompierAuTravail1);
        unePeriode.setStatutPompier("t", unPompierAuTravail2);

        int nbPompiersAppeles1 = uneCaserne.appelEquipe(unePeriode,3);
        int nbPompiersAppeles2 = uneCaserne.appelEquipe(unePeriode,5);

        assertEquals(3, nbPompiersAppeles1);
        assertEquals(1, nbPompiersAppeles2);

    }

    @Test
    @DisplayName("Appel deux equipes dont une vide")
    void appelDeuxEquipesDontUneVide() {

        unePeriode.setStatutPompier("d", unPompierDispo1);
        unePeriode.setStatutPompier("d", unPompierDispo2);
        unePeriode.setStatutPompier("t", unPompierAuTravail1);
        unePeriode.setStatutPompier("t", unPompierAuTravail2);

        int nbPompiersAppeles1 = uneCaserne.appelEquipe(unePeriode,5);
        int nbPompiersAppeles2 = uneCaserne.appelEquipe(unePeriode,5);

        assertEquals(4, nbPompiersAppeles1);
        assertEquals(0, nbPompiersAppeles2);

    }

}