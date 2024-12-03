package OnlineExamSystem;
import java.util.*;
import java.util.concurrent.*;

public class ExamResult {
    // Shared map for storing the results (student names and their scores)
    public static final Map<String, Integer> results = new ConcurrentHashMap<>();

    // Utility method to print all results
    public static void printResults() {
        synchronized (results) {
            System.out.println("\nFinal Results:");
            for (Map.Entry<String, Integer> entry : results.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
