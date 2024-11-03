package fi.utu.tech.assignment5;

import java.util.ArrayList;
import java.util.List;

// Käytetään tehtässä 1 muokattua GradingTask-oliota
import fi.utu.tech.common.GradingTask;
// Allokointifunktiot implementoidaan TaskAllocator-luokassa
import fi.utu.tech.common.TaskAllocator;

import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;


public class App5 {
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
        Thread gradingThread1 = new Thread(gradingTask.get(0));
        Thread gradingThread2 = new Thread(gradingTask.get(1));
        Thread gradingThread3 = new Thread(gradingTask.get(2));
        Thread gradingThread4 = new Thread(gradingTask.get(3));
        Thread gradingThread5 = new Thread(gradingTask.get(4));
        Thread gradingThread6 = new Thread(gradingTask.get(5));
        Thread gradingThread7 = new Thread(gradingTask.get(6));
        Thread gradingThread8 = new Thread(gradingTask.get(7));
        Thread gradingThread9 = new Thread(gradingTask.get(8));

        //Käynnistetään säikeet
        gradingThread1.start();
        gradingThread2.start();
        gradingThread3.start();
        gradingThread4.start();
        gradingThread5.start();
        gradingThread6.start();
        gradingThread7.start();
        gradingThread8.start();
        gradingThread9.start();
        //Laitetaan pääsäie odottamaan, että gradingThreadit saavat tehtävät suoritettua
        gradingThread1.join();
        gradingThread2.join();
        gradingThread3.join();
        gradingThread4.join();
        gradingThread5.join();
        gradingThread6.join();
        gradingThread7.join();
        gradingThread8.join();
        gradingThread9.join();



        // Tulostetaan arvioidut palautukset
        System.out.println("------------ CUT HERE ------------");
        for (var gs : gradingTask.get(0).getGradedSubmissions()) {
            System.out.println(gs);
        }
        for (var gs : gradingTask.get(1).getGradedSubmissions()) {
            System.out.println(gs);
        }
        for (var gs : gradingTask.get(2).getGradedSubmissions()) {
            System.out.println(gs);
        }
        for (var gs : gradingTask.get(3).getGradedSubmissions()) {
            System.out.println(gs);
        }
        for (var gs : gradingTask.get(4).getGradedSubmissions()) {
            System.out.println(gs);
        }
        for (var gs : gradingTask.get(5).getGradedSubmissions()) {
            System.out.println(gs);
        }
        for (var gs : gradingTask.get(6).getGradedSubmissions()) {
            System.out.println(gs);
        }
        for (var gs : gradingTask.get(7).getGradedSubmissions()) {
            System.out.println(gs);
        }
        for (var gs : gradingTask.get(8).getGradedSubmissions()) {
            System.out.println(gs);
        }

        // Lasketaan funktion suoritusaika
        System.out.printf("Total time for grading: %d ms%n", System.currentTimeMillis()-startTime);
    }
}
