package fi.utu.tech.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * You need to modify this file
 */

public class GradingTask implements Runnable{
    private final List<Submission> ungradedSubmissions;
    private List<Submission> gradedSubmissions;

    public void run(){
        //System.out.println("Grading started in: " + Thread.currentThread().getName());
        this.gradeAll(ungradedSubmissions);
        //System.out.println("Grading completed in: " + Thread.currentThread().getName());
    }


    public GradingTask(List<Submission> ungradedSubmissions)
    {

        this.ungradedSubmissions = ungradedSubmissions;
    }
    private Random rnd = new Random();

    /**
     * Grades all given submissions. Does not mutate the given objects
     * @param submissions List of submissions to be graded
     * @return List of graded submissions (new objects)
     */
    public List<Submission> gradeAll(List<Submission> submissions) {
        List<Submission> graded = new ArrayList<>();
        for (var s : submissions) {
            graded.add(grade(s));
        }
        return this.gradedSubmissions = graded;
    }
    public List<Submission> getGradedSubmissions(){
        return this.gradedSubmissions;
    }
    /**
     * Grades the given submission
     * @param s Ungraded submission to be graded
     * @return New submission object with a given grade
     */
    public Submission grade(Submission s) {
        try {
            Thread.sleep(s.getDifficulty());
        } catch (InterruptedException e) {
            System.err.println("Who dared to interrupt my sleep?!");
        }
        return s.grade(rnd.nextInt(6));
    }


}
