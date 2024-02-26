# Assignment DEMO1

## Learning objectives
The learning objectives for this week's demo tasks are recap of interface classes, thread creation, startup and waiting (joining), task allocation, the concept of parallelism and concurrency, and separating the tasks from task executors (task objects vs thread objects).

## General instructions
Assignments for should be completed in the folders allocated to them (folders under src/main in the directory tree): i.e. the first exercise of this week should be made in the folder "assignment1", the second exercise in the folder "assignment2", etc. This week's assignments build on each other, so the App class for the next assignment should be based on the solution of the previous assignment an so on. In other words, always copy the `main` method of the App class of the previous assignment to the `main` method of the next assignment to continue working.

## Folder structure

The "story" of the assignment is a kind of simulation of students submitting their exercises. The grading process usually works in such a way that students submit a pile of submissions that need to be graded on a scale of 0-5. Assignments are checked by n number of graders, to whom the work must be *allocated* in some way. In other words, which grader checks which assignment. The exercise revolves around the concepts of *submissions*, *grading tasks* and *graders*.

In the `fi.tech.utu.common` package, there are classes represent these concepts. They are common for all assignments. Some of the classes in the `common` package should not be changed (they are clearly marked), but some should. Changes made to `common` package classes never overwrite changes made in a previous task, but merely add functionality, which is why they do not need to be copied into task-specific folders.

### Submission
The class corresponding a submission. In this simple simulation, the objects do not actually contain any "real submission data", but the instead have a few attributes that are relevant to the job:

- `String submitter` contains the name of the student who submitted the submission
- The `int grade` contains the grade for submission (by default, before grading it is 0).
- `int difficulty` reflects how *difficult* the student's solution is to grade. In other words, how long it actually takes to grade the task

In addition to the obvious "getter" and "setter" methods, the Submission class has a method `grade(int newGrade)` that allows you to set a grade for the submission. It is noteworthy that **Submission class objects are immutable**, i.e. when assigning a grade, the original `Submission` class object is not mutated, but a new `Submission` class object is created which is identical to the original except for the grade. This new instance is returned to the caller.

This class should not be modified.

### SubmissionGenerator
Since we do not have any real data set of student submissions, we will create test data for this purpose using the `SubmissionGenerator` class. The `SubmissionGenerator` class effectively has a single public static method that is used to generate a list of test submissions (a list of `Submission` objects). The test submissions contain a randomly generated name for the submitter.

The `public static List<Submission> generateSubmissions(int amount, int difficulty, Strategy strategy)` generates submissions based on the arguments given to it. The arguments are shown below

- `int amount`: How many test submissions to generate
- `int difficulty`: How challenging submissions will be generated (i.e. how long it will take to check the submissions). The final difficulty of a single submission is affected by both the `difficulty` value and the `strategy` enum.
- `enum Strategy strategy`: The strategy used to generate submissions. Will affect the final "difficulty" value of submissions.
	- `STATIC`: All submissions generated will have the same difficulty value specified by the `difficulty` argument
	- `LINEAR`: The difficulty of the submissions to be generated increases linearly from zero to the value specified by the `difficulty` argument.
	- `UNFAIR`: One of the submissions is 10 times more difficult than others, the others are static according to the difficulty value
	- `RANDOM`: The difficulty of the generated submissions is random between [0, `difficulty`).

This class should not be modified.

### GradingTask
The instances of the `GradingTask` class are meant to represent the task of grading. They contain methods that do the actual grading:

- `public Submission grade(Submission s)`: Grades the submission given to the method. It is well known that the time taken for the grading consists of procrastination which depends on the difficulty of the submission and after this, the grade will be randomly chosen. This same "algorithm" is also used in our simulation. More seriously, `Thread.sleep()` is a so-called simulated load that tries to represent the time taken for grading. In real problems, the execution time of the algorithm would be affected by both the CPU and I/O usage of the algorithm. Guessing a random number just by itself is not a particularly heavy operation for a computer, which is why `Thread.sleep` is used here.

This class needs to be modified for some assignments

### TaskAllocator
This class needs to be modified for some tasks. More detailed instructions on this later on.


## Assignments

### Starting point and goal
The starting point of the assignment is one where the given `main` method (in the `App1` class) simply creates a bunch of task submissions according to the given parameters and grades the tasks with the `gradeAll` method of the `GradingTask` class, and finally prints out information about the graded submissions. In addition, the `main` method measures the time spent on the grading and prints this at the end of the program execution. All grading is being done in a single thread, grading one submissions after the other. The goal of these assignments is to transform, step by step, this single-threaded application into a multi-threaded program capable of grading submissions simultaneously and in parallel. Note that this step-by-step approach means that in many situations the assignments depend on the previous ones and the assignments should therefore be done in correct order unless otherwise stated.

### Assignment 1 - Defining the tasks
As you will hopefully have learned from the course material, the thread abstraction in Java is provided by the `Thread` class. In other words, using the methods provided by the `Thread` class, it is possible to create a new thread in which to run Java code. The code to be executed in a separate thread depends on the *task* assigned to the thread object. This task can be defined in two ways: either by *extending* the `Thread` class and overriding its `run` method, or by defining a custom task class that *implements* the `Runnable` interface. In general, the use of `Runnable` interface is recommended, since this does not bind the job to a `Thread` class.

Since our goal is to add threading to the grading of submissions, the first step is to modify the `GradingTask` class of the **common** package to *implement* the `Runnable` interface (without worrying about threading just yet). Modify the `GradingTask` class so that calling the `run` method required by the `Runnable` interface from the `main` method of the `App1` class will grade the list of submissions. Note that the `run` method required by the `Runnable` interface has the signature `public void run()`, which means that the `run` method takes **no parameters** and **returns nothing**. The submissions to be graded, must therefore be passed to/from `GradingTask` in some other way.

Assignment 1 is probably solved correctly when the `main` method does not directly reference the `gradeAll` or `grade` methods, but it is still possible to print the graded list of submissions from the `main` method. **Do not** print the list of graded submissions directly from the `GradingTask` class. You should do the printing in the `main` method.

### Assignment 2 - Outsourced evaluation
Implementing the `Runnable` interface and calling the `run` method alone is not enough to execute the code in a separate thread -- after all, the `Runnable` interface only specifies that the class must have a `public void run()` method, and calling that method is no different from calling any other method. In other words, no unconventional black magic has happened yet.

To run the method defined in the `run` method in a separate thread, you must take advantage of the functionality provided by the `Thread` class. Find out how the `Thread` class can execute `Runnable` type objects in a separate thread and take the necessary steps to get the `GradingTask` to do the grading in a separate thread.

Note! If done correctly, the program may throw exceptions (such as NullPointerException) or grading task object may return incomplete submission lists. This is just a taste of the problems caused by the so-called shared state, which will be discussed in more detail in the next demos. However, this particular problem will be addressed in the next assignment. You may want to try to work out the reason for the errors by yourself before moving on to the next assignment.

### Assignment 3 - Wait a sec
As mentioned in the previous assignment, the threaded version of the program may throw exceptions or may otherwise not work correctly. The exception is caused by the fact that when the grading task is started, the main method continues to execute concurrently (or in parallel). In other words, the main method gives a list of ungraded submissions to the grader thread, starts up the thread and then immediately calls the method to get graded submissions. As there are no graded submissions (yet) or the entire list of graded submissions is missing, the program will crash with an exception.

Modify the main method so that the main thread does not attempt to touch the graded submissions until the grader thread has completed the grading. In other words, make the main thread waits for the grader thread to complete. Note! Avoid the so-called *busy waiting* approach.

Once you have completed the assignment, compare the execution time with the original single-threaded implementation - the times should be pretty much in the same order of magnitude. Why is this? One of the main purposes of parallel threading should be to improve execution times, right? Spoilers again at the beginning of the next assignment.

### Assignment 4 - With a friend
The complexity of the program has increased: we are using threads and fixed a single race condition already, but the execution times have not improved. If you look at the life cycle of the process, you will see that the main thread effectively outsources the grading to a separate (single) thread, but during the grading, the main thread is essentially just waiting. In other words, only one thread is still working at a time, and the grading hasn't gotten any faster. If the grading speed is to be increased, the number of grading threads must be increased and the grading tasks must be distributed among them in some way.

In this assignment, a simple task allocation algorithm should be implemented. The `common` package contains a class called `TaskAllocator`, which contains the body of the method `public static List<GradingTask> sloppyAllocator(List<Submission> submissions)`. Implement this method so that, given a list of submissions (List<Submission> submissions). The method should return two `GradingTask` objects in a list, each with containing approximately half of the total submissions to be graded.

In the `main` method itself, update the method so that **all** two grading tasks received from the allocator are given to separate threads, **all** threads are started, and **all** threads are waited to complete. After all the grading threads are completed, collect **all** graded submissions from **all** grading tasks. Although so far the allocation method produces only two `GradingTask` objects (and thus only two threads are needed), implement the the above main method in such a way that you do not rely at any point on there being two tasks or threads, but instead use the size of the `List<GradingTask>` you get from the allocation method, for example.

### Assignment 5 - Mass force
The previous assignment's allocation algorithm was hard-coded to always split the submission list into just two parts. Implement the method `public static List<GradingTask> allocate(List<Submission> submissions, int taskCount)` in the `TaskAllocator` class, so that it can divide the given submissions into the number of parts specified by the `taskCount` variable. So, for example, if there are 20 submissions in the `submissions` list and `taskCount` is 4, the method should return 4 `GradingTask` objects in a list, each task having 5 submissions to grade. Change the `main` method to use the new allocation algorithm and examine how the task count affects the execution time. You may notice that the execution time decreases quite a bit, even if more threads are used than your machine has CPU cores. Consider what the reasons for this might be.

Hint: If you can't figure out how to implement the allocation method, search for information using a keyword like [round-robin item allocation](https://en.wikipedia.org/wiki/Round-robin_item_allocation). Although some of the explanations may seem overly complicated, the algorithm is in practice should be already familiar from primary school, where, for example, when dividing pupils into groups of four, each one takes turns shouting 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, ... etc. Just replace the pupils with task submission objects.

### Assignment 6 - Dynamic friends

In previous assignment, the task allocation has been static. This means that the submissions have been allocated by using a predefined size. For example, 20 submissions may have been allocated to four tasks, with each task containing five submissions to grade. This works well if all tasks are equally challenging. But what if the size of the tasks is unknown or, for example, one of the threads happens to have allocated more challenging tasks? In that case, the thread that got the most demanding tasks will struggle with the work alone while the other threads are long gone with their easy tasks. If tasks could be distributed to threads according to the time it takes for each thread to complete a previous task, the efficiency could be improved. Such a model is called dynamic allocation.

The lecture covered the `ExecutorService` interface of Java. The implementing classes of this interface make it relatively easy to implement dynamic job allocation. Briefly, the idea behind ExecutorService is that the `execute` method of the objects implementing ExecutorService, can be called with `Runnable` objects and ExecutorService will handle their execution in a particular way. The way in which the Runnable objects are run, depends on the class implementing ExecuterService. For example, the FixedThreadPool implementation allows the number of concurrent threads to be specified. When a `execute` method of FixedThreadPool is used to submit `Runnable` objects to the executor, the objects will be executed using the specified amount of threads. If all threads are in use, the Runnables are put in a queue until a thread is freed, at which point the task will be executed. The `Executors` class contains factory methods for creating various `ExecutorService` objects.

Implement a grader using `FixedThreadPool`. When using dynamic job allocation, it is best not to split jobs into too large chunks. For example, 1-2 jobs per task may be sufficient. Note that the API documentation page for `ExecutorService` has examples for waiting for ExecutorService to complete (you may need them).

- ExecutorService interface: <https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/ExecutorService.html>
- Executors class containing factory methods for creating different types of ExecutorService instances: <https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/Executors.html>

**Tip**: Assignment 6 can be completed without the allocators of Task 4 or 5. In this case, the "allocation" can be implemented by creating one `GradingTask` instance for each `Submission` instance. The creation of such an allocator should be straightforward. If task 5 has been implemented, you can use it for allocation directly.