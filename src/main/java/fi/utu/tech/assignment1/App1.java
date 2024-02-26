package fi.utu.tech.assignment1;

import java.util.List;

import fi.utu.tech.common.GradingTask;
import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;

public class App1 {
    public static void main( String[] args )
    {
        // Save the starting time of the function to calculate the execution time
        long startTime = System.currentTimeMillis();

        // Generate list of ungraded "submissions" for testing
        List<Submission> ungradedSubmissions = SubmissionGenerator.generateSubmissions(21, 200, Strategy.STATIC);

        // Print list of submissions *before* grading
        for (var ug : ungradedSubmissions) {
            System.out.println(ug);
        }

        // Create a new grading task
        GradingTask gradingTask = new GradingTask();

        // All submissions are given to gradeAll method, which will return the submissions as graded
        List<Submission> gradedSubmissions =  gradingTask.gradeAll(ungradedSubmissions);
        /*
         * TODO: Modify the GradingTask class of the package named "common"
         * so that the run() method below checks ungradedSubmissions
         * (without being given any arguments!). The gt.gradeAll()
         * method above should no longer be used directly from this main method.
         * For more detailed instructions, see the assignment.
         * You will have to figure out how to give Submission objects to GradingTasks
         * and how to get the graded submissions back after being graded.
         */
        // gradingTask.run();
        
        // Print the graded submissions
        System.out.println("------------ CUT HERE ------------");
        for (var gs : gradedSubmissions) {
            System.out.println(gs);
        }

        // The total time of execution
        System.out.printf("Total time for grading: %d ms%n", System.currentTimeMillis()-startTime);
    }
}
