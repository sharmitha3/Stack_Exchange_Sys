package Service;

import model.Question;
import Repo.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionService {
    private QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<Question> getAllQuestions() {
        return repository.getAllQuestions();
    }

    // NEW: Search by multiple tags, case-insensitive
    public List<Question> getQuestionsByTags(String inputTags) {
        // split user input into individual tags, trim spaces, ignore case
        String[] searchTags = inputTags.toLowerCase().split(",");

        return repository.getAllQuestions().stream()
                .filter(q -> {
                    String[] questionTags = q.getTags().toLowerCase().split(",");
                    for (String tag : searchTags) {
                        for (String qTag : questionTags) {
                            if (qTag.trim().equals(tag.trim())) return true; // matches any tag
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    // Other methods unchanged
    public List<Question> getTopVotedQuestions(int minVotes) {
        return repository.getAllQuestions().stream()
                .filter(q -> q.getVotes() >= minVotes)
                .sorted((q1, q2) -> q2.getVotes() - q1.getVotes())
                .collect(Collectors.toList());
    }
}