package OnlineExamSystem;
import java.util.*;

public class QuestionBank {
    // The list of questions for the exam
    private static final List<String> questions = Arrays.asList(
            "What is 2 + 2?", 
            "What is the capital of France?", 
            "What is the square root of 16?"
    );

    // Method to retrieve the questions
    public static List<String> getQuestions() {
        return questions;
    }
}

