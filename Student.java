package OnlineExamSystem;
import java.util.concurrent.*;

public class Student implements Runnable {
    private final String studentName;

    public Student(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public void run() {
        try {
            // Each student will attempt the exam
            System.out.println(studentName + " is attempting the exam...");

            int score = 0;
            for (String question : QuestionBank.getQuestions()) {
                String answer = attemptQuestion(question);

                // Check if the answer is correct
                if (isCorrectAnswer(question, answer)) {
                    score++;
                }

                // Simulating time delay to process each question
                Thread.sleep(1000);
            }

            // Update the result
            synchronized (ExamResult.results) {
                ExamResult.results.put(studentName, score);
            }
            System.out.println(studentName + " has completed the exam with score: " + score);

        } catch (InterruptedException e) {
            System.out.println(studentName + " was interrupted.");
        }
    }

    // Simulate answering a question
    private String attemptQuestion(String question) {
        switch (question) {
            case "What is 2 + 2?":
                return "4";
            case "What is the capital of France?":
                return "Paris";
            case "What is the square root of 16?":
                return "4";
            default:
                return "";
        }
    }

    // Check if the answer is correct
    private boolean isCorrectAnswer(String question, String answer) {
        if (question.equals("What is 2 + 2?") && answer.equals("4")) return true;
        if (question.equals("What is the capital of France?") && answer.equals("Paris")) return true;
        if (question.equals("What is the square root of 16?") && answer.equals("4")) return true;
        return false;
    }
}

