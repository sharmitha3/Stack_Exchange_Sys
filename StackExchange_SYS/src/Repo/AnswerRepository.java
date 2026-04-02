package Repo;

import model.Answers;
import java.util.ArrayList;
import java.util.List;

public class AnswerRepository {
    private List<Answers> answers = new ArrayList<>();

    public void addAnswer(Answers a) {
        answers.add(a);
    }

    public List<Answers> getAllAnswers() {
        return answers;
    }

    public List<Answers> getAnswersByQuestionId(int questionId) {
        List<Answers> result = new ArrayList<>();
        for (Answers a : answers) {
            if (a.getQuestionId() == questionId) {
                result.add(a);
            }
        }
        return result;
    }
}