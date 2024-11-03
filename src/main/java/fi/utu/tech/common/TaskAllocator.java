package fi.utu.tech.common;

import java.util.ArrayList;
import java.util.List;

/**
 * You need to modify this file
 */


public class TaskAllocator {

    /**
     * Allocator that creates list of two (2) GradingTask objects with each having half of the given submissions
     * @param submissions The submissions to be allocated
     * @return The two GradingTask objects in a list, each having half of the submissions
     */
    public static List<GradingTask> sloppyAllocator(List<Submission> submissions) {
        // TODO: Tehtävä 4
        List<GradingTask> allocationList = new ArrayList<>(2);
        int size = submissions.size();
        GradingTask gradingTask1 = new GradingTask(submissions.subList(0, (int) Math.floor((float) size /2)));
        GradingTask gradingTask2 = new GradingTask(submissions.subList((int) Math.ceil((float) size/2), size));
        allocationList.add(0, gradingTask1);
        allocationList.add(1, gradingTask2);
        return allocationList;
    }


    
    /**
     * Allocate List of ungraded submissions to tasks
     * @param submissions List of submissions to be graded
     * @param taskCount Amount of tasks to be generated out of the given submissions
     * @return List of GradingTasks allocated with some amount of submissions (depends on the implementation)
     */
    public static List<GradingTask> allocate(List<Submission> submissions, int taskCount) {
        // TODO: Tehtävä 5
        List<GradingTask> allocatedList = new ArrayList<>(taskCount);
        //Create array for allocating submissions into sublists
        List<List<Submission>> submissionSublists = new ArrayList<>(taskCount);
        //Fill submissionSublists array with empty ArrayLists
        for (int i = 0; i < taskCount; i++){
            submissionSublists.add(new ArrayList<>());
        }
        //Allocate items to submissionSublists using round-robin distribution
        for (int i = 0; i<submissions.size(); i++){
            submissionSublists.get(i % taskCount).add(submissions.get(i));
        }
        // Create GradingTask -objects from sublists and add them to the allocatedList -array
        for (List<Submission> sublist: submissionSublists){
            allocatedList.add(new GradingTask(sublist));
        }

        return allocatedList;
    }
}
