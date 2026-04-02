import Repo.QuestionRepository;
import Repo.AnswerRepository;
import Service.QuestionService;
import Service.AnswerService;
import model.Question;
import model.Answers;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        QuestionRepository questionRepo = new QuestionRepository();
        AnswerRepository answerRepo = new AnswerRepository();

        questionRepo.addQuestion(new Question(1, "What is Java?", "I want to know about Java.", "Java,Programming", 15, 100));
        questionRepo.addQuestion(new Question(2, "What is Python?", "I want to know about Python.", "Python,Programming", 20, 200));
        questionRepo.addQuestion(new Question(3, "Best practices for Java", "How to write clean Java code?", "Java,Coding", 8, 50));

        answerRepo.addAnswer(new Answers(1, 1, "Java is a programming language.", 10));
        answerRepo.addAnswer(new Answers(2, 1, "It is used for building applications.", 5));
        answerRepo.addAnswer(new Answers(3, 2, "Python is great for data science.", 12));
        answerRepo.addAnswer(new Answers(4, 3, "Follow clean code principles.", 7));

        QuestionService questionService = new QuestionService(questionRepo);
        AnswerService answerService = new AnswerService(answerRepo);

        int nextQuestionId = 4;
        int nextAnswerId = 5;

        while (true) {
            System.out.println("\n==== Mini Console ====");
            System.out.println("1. View all questions");
            System.out.println("2. View questions by tag");
            System.out.println("3. View answers for a question");
            System.out.println("4. View top voted questions");
            System.out.println("5. View top voted answers");
            System.out.println("6. Add a new question");
            System.out.println("7. Add a new answer");
            System.out.println("8. View questions with no answers");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("==== All Questions ====");
                    for (Question q : questionService.getAllQuestions()) q.print();
                }
                case 2 -> {
                    System.out.print("Enter tags to search (comma separated): ");
                    String tags = scanner.nextLine();
                    System.out.println("==== Questions with tag(s): " + tags + " ====");
                    List<Question> taggedQuestions = questionService.getQuestionsByTags(tags);
                    if (taggedQuestions.isEmpty())
                        System.out.println("No questions found for these tags.");
                    else
                        taggedQuestions.forEach(Question::print);
                }
                case 3 -> {
                    System.out.print("Enter question ID to see answers: ");
                    int qid = scanner.nextInt();
                    List<Answers> answers = answerService.getAnswersByQuestionId(qid);
                    if (answers.isEmpty()) System.out.println("No answers found for this question.");
                    else {
                        System.out.println("==== Answers for Question ID " + qid + " ====");
                        answers.forEach(Answers::print);
                    }
                }
                case 4 -> {
                    System.out.print("Enter minimum votes: ");
                    int minQVotes = scanner.nextInt();
                    System.out.println("==== Top Voted Questions ====");
                    List<Question> topQuestions = questionService.getTopVotedQuestions(minQVotes);
                    if (topQuestions.isEmpty()) System.out.println("No questions found with votes >= " + minQVotes);
                    else topQuestions.forEach(Question::print);
                }
                case 5 -> {
                    System.out.print("Enter minimum votes: ");
                    int minAVotes = scanner.nextInt();
                    System.out.println("==== Top Voted Answers ====");
                    List<Answers> topAnswers = answerService.getTopVotedAnswers(minAVotes);
                    if (topAnswers.isEmpty()) System.out.println("No answers found with votes >= " + minAVotes);
                    else topAnswers.forEach(Answers::print);
                }
                case 6 -> { // Add new question
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter body: ");
                    String body = scanner.nextLine();
                    System.out.print("Enter tags (comma separated): ");
                    String tags = scanner.nextLine();
                    System.out.print("Enter initial votes: ");
                    int votes = scanner.nextInt();
                    System.out.print("Enter initial views: ");
                    int views = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    Question newQ = new Question(nextQuestionId++, title, body, tags, votes, views);
                    questionRepo.addQuestion(newQ);
                    System.out.println("Question added successfully!");
                }
                case 7 -> { // Add new answer
                    System.out.print("Enter question ID to answer: ");
                    int qid = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter answer body: ");
                    String body = scanner.nextLine();
                    System.out.print("Enter votes: ");
                    int votes = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    Answers newA = new Answers(nextAnswerId++, qid, body, votes);
                    answerRepo.addAnswer(newA);
                    System.out.println("Answer added successfully!");
                }
                case 8 -> { // Questions with no answers
                    System.out.println("==== Questions with No Answers ====");
                    List<Question> allQuestions = questionService.getAllQuestions();
                    for (Question q : allQuestions) {
                        if (answerService.getAnswersByQuestionId(q.getQuestionId()).isEmpty()) {
                            q.print();
                        }
                    }
                }
                case 9 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}