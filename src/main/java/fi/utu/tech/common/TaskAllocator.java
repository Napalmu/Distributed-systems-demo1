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
        // Retruns null for now to suppress warnings
        List<GradingTask> list = new ArrayList<>(2);
        int size = submissions.size();
        GradingTask gradingTask1 = new GradingTask(submissions.subList(0, (int) Math.floor((float) size /2)));
        GradingTask gradingTask2 = new GradingTask(submissions.subList((int) Math.ceil((float) size/2), size));
        list.add(0, gradingTask1);
        list.add(1, gradingTask2);
        return list;
    }


    
    /**
     * Allocate List of ungraded submissions to tasks
     * @param submissions List of submissions to be graded
     * @param taskCount Amount of tasks to be generated out of the given submissions
     * @return List of GradingTasks allocated with some amount of submissions (depends on the implementation)
     */
    public static List<GradingTask> allocate(List<Submission> submissions, int taskCount) {
        // TODO: Tehtävä 5
        // Retruns null for now to suppress warnings
        return null;
    }
}
