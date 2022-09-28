package siolacol;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class PompierTest {

    private Pompier unPompierDispo;
    private Pompier unPompierAuTravail;
    private Periode unePeriode;


    @BeforeEach
    void setUp() {
        unPompierDispo = new Pompier("Legall", "Yasmina", "3");
        unPompierAuTravail = new Pompier("Dubois", "Yves", "9");

        unePeriode = new Periode(new GregorianCalendar(2022, 10, 04), 1);

        unePeriode.setStatutPompier("d", unPompierDispo);
        unePeriode.setStatutPompier("t", unPompierAuTravail);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Getter du numéro de bip")
    void getNumeroBip() {
        assertEquals("3", unPompierDispo.getNumeroBip(), "Le getter de numéro de bip ne fonctionne pas");
        assertEquals("9", unPompierAuTravail.getNumeroBip(), "Le getter de numéro de bip ne fonctionne pas");

    }

    @Test
    @DisplayName("Getter du statut disponible")
    void getStatutDispo() {
        assertEquals("d", unPompierDispo.getStatut(unePeriode), "Problème avec le getter pour statut dispo");
    }

    @Test
    @DisplayName("Getter du statut au travail")
    void getStatutAuTravail() {
        assertEquals("t", unPompierAuTravail.getStatut(unePeriode), "Problème avec le getter pour statut au travail");
    }

    @Test
    @DisplayName("Missioner un pompier dispo")
    void missionnerDispo() {
        unPompierDispo.missionner(unePeriode);
        assertEquals("m", unPompierDispo.getStatut(unePeriode));
    }

    @Test
    @DisplayName("Missioner un pompier au travail")
    void missionnerAuTravail() {
        unPompierAuTravail.missionner(unePeriode);
        assertEquals("m", unPompierAuTravail.getStatut(unePeriode));
    }

}