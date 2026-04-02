package Repo;

import model.Question;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {
    private List<Question> questions = new ArrayList<>();

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public List<Question> getAllQuestions() {
        return questions;
    }

    public Question getQuestionById(int id) {
        return questions.stream().filter(q -> q.getQuestionId() == id).findFirst().orElse(null);
    }
}