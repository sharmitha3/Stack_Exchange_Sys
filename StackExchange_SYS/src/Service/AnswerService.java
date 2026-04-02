package Service;

import model.Answers;
import Repo.AnswerRepository;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerService {
    private AnswerRepository repository;

    public AnswerService(AnswerRepository repository) {
        this.repository = repository;
    }

    public List<Answers> getAllAnswers() {
        return repository.getAllAnswers();
    }

    public List<Answers> getAnswersByQuestionId(int questionId) {
        return repository.getAnswersByQuestionId(questionId);
    }

    public List<Answers> getTopVotedAnswers(int minVotes) {
        return repository.getAllAnswers().stream()
                .filter(a -> a.getVotes() >= minVotes)
                .sorted((a1, a2) -> a2.getVotes() - a1.getVotes())
                .collect(Collectors.toList());
    }
}