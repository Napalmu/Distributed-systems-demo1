package fi.utu.tech.assignment6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Käytetään tehtässä 1 muokattua GradingTask-oliota
import fi.utu.tech.common.GradingTask;
// Allokointifunktiot implementoidaan TaskAllocator-luokassa
import fi.utu.tech.common.TaskAllocator;

import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;

public class App6 {
    public static void main(String[] args) throws InterruptedException {
        // Generoidaan kasa esimerkkitehtäväpalautuksia
        List<Submission> ungradedSubmissions = SubmissionGenerator.generateSubmissions(21, 200, Strategy.UNFAIR);

        // Kopioi edellisen tehtävän ratkaisu tähän lähtökohdaksi
        // Voit käyttää yllä olevaa riviä palautusten generointiin. Se ei vaikuta ratkaisuun, mutta
        // "epäreilu" strategia tekee yhdestä palautuksesta paljon muita haastavamman tarkistettavan,
        // demonstroiden dynaamisen työnjaon etuja paremmin.
        // Otetaan funktion aloitusaika talteen suoritusajan laskemista varten
        long startTime = System.currentTimeMillis();
        // Tulostetaan tiedot esimerkkipalautuksista ennen arviointia
        for (var ug : ungradedSubmissions) {
            System.out.println(ug);
        }

        // Luodaan uusi arviointitehtävä
        List<GradingTask> gradingTask = TaskAllocator.allocate(ungradedSubmissions, 9);
        // Annetaan palautukset gradeAll-metodille ja saadaan arvioidut palautukset takaisin
        //List<Submission> gradedSubmissions =  gradingTask.gradeAll(ungradedSubmissions);
        /*
         * TODO: Muokkaa common-pakkauksen GradingTask-luokkaa siten,
         * että alla oleva run()-metodi (ilman argumentteja!) tarkistaa palautukset (ungradedSubmissions).
         * Yllä olevaa gt.gradeAll()-metodia ei tule enää käyttää suoraan
         * tästä main-metodista. Tarkemmat ohjeet tehtävänannossa.
         * Joudut keksimään, miten GradingTaskille voi antaa tehtävät ja miten ne siltä saa noukittua
         */
        //Luodaan säikeet


        // Tulostetaan arvioidut palautukset
        System.out.println("------------ CUT HERE ------------");
        for (var gs : gradingTask.get(0).getGradedSubmissions()) {
            System.out.println(gs);
        }


        // Lasketaan funktion suoritusaika
        System.out.printf("Total time for grading: %d ms%n", System.currentTimeMillis()-startTime);
    }
}
