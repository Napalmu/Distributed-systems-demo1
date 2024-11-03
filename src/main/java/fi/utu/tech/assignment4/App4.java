package fi.utu.tech.assignment4;

import java.util.ArrayList;
import java.util.List;

// Käytetään tehtässä 1 muokattua GradingTask-oliota
import fi.utu.tech.common.GradingTask;
// Allokointifunktiot implementoidaan TaskAllocator-luokassa
import fi.utu.tech.common.TaskAllocator;

import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;


public class App4 {
    public static void main( String[] args ) throws InterruptedException {
        // Otetaan funktion aloitusaika talteen suoritusajan laskemista varten
        long startTime = System.currentTimeMillis();

        // Generoidaan kasa esimerkkitehtäväpalautuksia
        List<Submission> ungradedSubmissions = SubmissionGenerator.generateSubmissions(21, 200, Strategy.STATIC);

        // Tulostetaan tiedot esimerkkipalautuksista ennen arviointia
        for (var ug : ungradedSubmissions) {
            System.out.println(ug);
        }

        // Luodaan uusi arviointitehtävä
        List<GradingTask> gradingTask = TaskAllocator.sloppyAllocator(ungradedSubmissions);
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
        Thread gradingThread1 = new Thread(gradingTask.get(0));
        Thread gradingThread2 = new Thread(gradingTask.get(1));
        //Käynnistetään säikeet
        gradingThread1.start();
        gradingThread2.start();
        //Laitetaan pääsäie odottamaan, että gradingThread1 ja 2 saavat tehtävät suoritettua
        gradingThread1.join();
        gradingThread2.join();


        // Tulostetaan arvioidut palautukset
        System.out.println("------------ CUT HERE ------------");
        for (var gs : gradingTask.get(0).getGradedSubmissions()) {
            System.out.println(gs);
        }
        for (var gs : gradingTask.get(1).getGradedSubmissions()) {
            System.out.println(gs);
        }

        // Lasketaan funktion suoritusaika
        System.out.printf("Total time for grading: %d ms%n", System.currentTimeMillis()-startTime);
    }
}
