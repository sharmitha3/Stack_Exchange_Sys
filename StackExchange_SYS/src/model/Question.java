package model;

public class Question {
    private int questionId;
    private String title;
    private String body;
    private String tags;
    private int votes;
    private int views;

    public Question(int questionId, String title, String body, String tags, int votes, int views) {
        this.questionId = questionId;
        this.title = title;
        this.body = body;
        this.tags = tags;
        this.votes = votes;
        this.views = views;
    }

    public int getQuestionId() { return questionId; }
    public String getTags() { return tags; }
    public int getVotes() { return votes; }

    public void print() {
        System.out.println("ID: " + questionId + " | Title: " + title + " | Tags: " + tags +
                " | Votes: " + votes + " | Views: " + views);
        System.out.println("Body: " + body);
        System.out.println("-------------------------------------------------");
    }
}