package fi.utu.tech.assignment6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Using GradingTask class modified in assignment 1
import fi.utu.tech.common.GradingTask;
// Allocation methods should be implemented in TaskAllocator class
import fi.utu.tech.common.TaskAllocator;

import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;

public class App6 {
    public static void main(String[] args) {
        // Generate list of ungraded "submissions" for testing
        List<Submission> ungradedSubmissions = SubmissionGenerator.generateSubmissions(21, 200, Strategy.UNFAIR);

        // Copy the solution from the previous exercise here as a starting point.
        // You may use the line of code above to generate submissions for testing.
        // "unfair" strategy makes one of the generated submissions a lot more "challencing" 
        // (ie. it takes longer to grade).
        // This will better demonstrate the pros of dynamic work allocation.
    }
}
