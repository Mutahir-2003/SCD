package OnlineExamSystem;
import java.util.concurrent.*;

public class OnlineExamSystem1 {
    public static void main(String[] args) {
        // Number of students attempting the exam
        int numberOfStudents = 5;

        // Executor service to handle student threads
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfStudents);

        // Submit student threads for execution
        for (int i = 1; i <= numberOfStudents; i++) {
            executorService.submit(new Student("Student_" + i));
        }

        // Shut down the executor service once all tasks are submitted
        executorService.shutdown();

        // Wait for all threads to finish
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        // Print the final results after all students have completed the exam
        ExamResult.printResults();
    }
}


