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
        List<GradingTask> gradingTask = TaskAllocator.allocate(ungradedSubmissions, ungradedSubmissions.size());
        ExecutorService executorService = Executors.newFixedThreadPool(gradingTask.size());

            for(var gs : gradingTask){
                executorService.execute(gs);
            }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("Grading did not finish in time.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Annetaan palautukset gradeAll-metodille ja saadaan arvioidut palautukset takaisin
        //List<Submission> gradedSubmissions =  gradingTask.gradeAll(ungradedSubmissions);
        /*
         * TODO: Muokkaa common-pakkauksen GradingTask-luokkaa siten,
         * että alla oleva run()-metodi (ilman argumentteja!) tarkistaa palautukset (ungradedSubmissions).
         * Yllä olevaa gt.gradeAll()-metodia ei tule enää käyttää suoraan
         * tästä main-metodista. Tarkemmat ohjeet tehtävänannossa.
         * Joudut keksimään, miten GradingTaskille voi antaa tehtävät ja miten ne siltä saa noukittua
         */


        // Tulostetaan arvioidut palautukset
        System.out.println("------------ CUT HERE ------------");
        for (GradingTask task : gradingTask) {
            System.out.println(task.getGradedSubmissions());
        }


        // Lasketaan funktion suoritusaika
        System.out.printf("Total time for grading: %d ms%n", System.currentTimeMillis()-startTime);
    }
}
