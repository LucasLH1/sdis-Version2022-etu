package siolacol;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class PeriodeTest {

    private Pompier unPompierDispo1;
    private Pompier unPompierDispo2;

    private Pompier unPompierAuTravail1;
    private Pompier unPompierAuTravail2;

    private Periode unePeriode;

    @BeforeEach
    void setUp() {
        unPompierDispo1 = new Pompier("Legall", "Yasmina", "3");
        unPompierDispo2 = new Pompier("Martin", "Alain", "17");
        unPompierAuTravail1 = new Pompier("Dubois", "Yves", "9");
        unPompierAuTravail2 = new Pompier("Dupond", "Carole", "4");

        unePeriode = new Periode(new GregorianCalendar(2022, 10, 04), 1);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Missioner un pompier disponible")
    void missionnerUnPompierDispo() {

        unePeriode.setStatutPompier("d", unPompierDispo1);

        unePeriode.missionner(unPompierDispo1);
        assertEquals("m", unPompierDispo1.getStatut(unePeriode));
    }

    @Test
    @DisplayName("Missioner un pompier au travail")
    void missionnerUnPompierAuTravail() {

        unePeriode.setStatutPompier("t", unPompierAuTravail1);

        unePeriode.missionner(unPompierAuTravail1);
        assertEquals("m", unPompierAuTravail1.getStatut(unePeriode));
    }

    @Test
    @DisplayName("Selection d'une equipe complete de pompiers disponibles")
    void selectEquipeDispo() {

        unePeriode.setStatutPompier("d", unPompierDispo1);
        unePeriode.setStatutPompier("d", unPompierDispo2);
        unePeriode.setStatutPompier("t", unPompierAuTravail1);
        unePeriode.setStatutPompier("t", unPompierAuTravail2);

        ArrayList<Pompier> equipe = unePeriode.selectEquipe(2);

        assertEquals(2, equipe.size());
        assertTrue(equipe.contains(unPompierDispo1));
        assertTrue(equipe.contains(unPompierDispo2));
        assertFalse(equipe.contains(unPompierAuTravail1));
        assertFalse(equipe.contains(unPompierAuTravail2));
    }


    @Test
    @DisplayName("Selection d'une equipe complete de pompiers disponibles et au travail")
    void selectEquipeDispoEtAuTravail() {

        unePeriode.setStatutPompier("d", unPompierDispo1);
        unePeriode.setStatutPompier("d", unPompierDispo2);
        unePeriode.setStatutPompier("t", unPompierAuTravail1);
        unePeriode.setStatutPompier("t", unPompierAuTravail2);

        ArrayList<Pompier> equipe = unePeriode.selectEquipe(3);

        assertEquals(3, equipe.size());
        assertTrue(equipe.contains(unPompierDispo1));
        assertTrue(equipe.contains(unPompierDispo2));
        assertTrue(equipe.contains(unPompierAuTravail1));
        assertFalse(equipe.contains(unPompierAuTravail2));
    }

    @Test
    @DisplayName("Selection d'une equipe complete avec tous les pompiers disponibles et au travail")
    void selectEquipeTousDispoEtAuTravail() {

        unePeriode.setStatutPompier("d", unPompierDispo1);
        unePeriode.setStatutPompier("d", unPompierDispo2);
        unePeriode.setStatutPompier("t", unPompierAuTravail1);
        unePeriode.setStatutPompier("t", unPompierAuTravail2);

        ArrayList<Pompier> equipe = unePeriode.selectEquipe(4);

        assertEquals(4, equipe.size());
        assertTrue(equipe.contains(unPompierDispo1));
        assertTrue(equipe.contains(unPompierDispo2));
        assertTrue(equipe.contains(unPompierAuTravail1));
        assertTrue(equipe.contains(unPompierAuTravail2));
    }


    @Test
    @DisplayName("Missioner un pompier dispo bis")
    void missionnerUnPompierDispoBis() {

        unePeriode.setStatutPompier("d", unPompierDispo1);

        int nbPompiersEnMission = unePeriode.getEnMission().size();

        unePeriode.missionner(unPompierDispo1);
        unePeriode.missionner(unPompierDispo1);

        assertEquals("m", unPompierDispo1.getStatut(unePeriode));
        assertEquals(nbPompiersEnMission+1, unePeriode.getEnMission().size());
    }

    @Test
    @DisplayName("Selection d'une equipe incomplete de pompiers disponibles")
    void selectEquipeDispoIncomplete() {

        unePeriode.setStatutPompier("d", unPompierDispo1);
        unePeriode.setStatutPompier("d", unPompierDispo2);

        ArrayList<Pompier> equipe = unePeriode.selectEquipe(3);

        assertTrue(equipe.contains(unPompierDispo1));
        assertTrue(equipe.contains(unPompierDispo2));
        assertEquals(2, equipe.size());
    }

    @Test
    @DisplayName("Selection d'une equipe incomplete de pompiers disponibles et au travail")
    void selectEquipeDispoEtAuTravailIncomplete() {
        unePeriode.setStatutPompier("d", unPompierDispo1);
        unePeriode.setStatutPompier("d", unPompierDispo2);
        unePeriode.setStatutPompier("t", unPompierAuTravail1);
        unePeriode.setStatutPompier("t", unPompierAuTravail2);

        ArrayList<Pompier> equipe = unePeriode.selectEquipe(50);

        assertEquals(4, equipe.size());
        assertTrue(equipe.contains(unPompierDispo1));
        assertTrue(equipe.contains(unPompierDispo2));
        assertTrue(equipe.contains(unPompierAuTravail1));
        assertTrue(equipe.contains(unPompierAuTravail2));
    }

    @Test
    @DisplayName("Selection d'une equipe complete de pompiers au travail")
    void selectEquipeAuTravail() {

        unePeriode.setStatutPompier("t", unPompierAuTravail1);
        unePeriode.setStatutPompier("t", unPompierAuTravail2);

        ArrayList<Pompier> equipe = unePeriode.selectEquipe(2);

        assertEquals(2, equipe.size());
        assertFalse(equipe.contains(unPompierDispo1));
        assertFalse(equipe.contains(unPompierDispo2));
        assertTrue(equipe.contains(unPompierAuTravail1));
        assertTrue(equipe.contains(unPompierAuTravail2));
    }

    @Test
    @DisplayName("Selection d'une equipe sans pompier disponible")
    void selectEquipeIncompleteAuTravail() {

        ArrayList<Pompier> equipe = unePeriode.selectEquipe(20);

        assertEquals(0, equipe.size());
    }

    @Test
    @DisplayName("Selection de plusieurs equipes avec les memes pompiers disponibles et au travail")
    void selectPlusieursEquipesTousDispoEtAuTravail() {

        unePeriode.setStatutPompier("d", unPompierDispo1);
        unePeriode.setStatutPompier("d", unPompierDispo2);
        unePeriode.setStatutPompier("t", unPompierAuTravail1);
        unePeriode.setStatutPompier("t", unPompierAuTravail2);

        ArrayList<Pompier> equipe1 = unePeriode.selectEquipe(4);
        ArrayList<Pompier> equipe2 = unePeriode.selectEquipe(4);


        assertEquals(4, equipe1.size());
        assertTrue(equipe1.contains(unPompierDispo1));
        assertTrue(equipe1.contains(unPompierDispo2));
        assertTrue(equipe1.contains(unPompierAuTravail1));
        assertTrue(equipe1.contains(unPompierAuTravail2));

        assertEquals(4, equipe2.size());
        assertTrue(equipe2.contains(unPompierDispo1));
        assertTrue(equipe2.contains(unPompierDispo2));
        assertTrue(equipe2.contains(unPompierAuTravail1));
        assertTrue(equipe2.contains(unPompierAuTravail2));
    }

}