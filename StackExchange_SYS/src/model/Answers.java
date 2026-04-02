package model;

public class Answers {
    private int answerId;
    private int questionId;
    private String body;
    private int votes;

    public Answers(int answerId, int questionId, String body, int votes) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.body = body;
        this.votes = votes;
    }

    public int getQuestionId() { return questionId; }
    public int getVotes() { return votes; }

    public void print() {
        System.out.println("Answer ID: " + answerId + " | Question ID: " + questionId + " | Votes: " + votes);
        System.out.println("Body: " + body);
        System.out.println("-------------------------------------------------");
    }
}