package bg.pgpet.promptanalyzer.model;

public class Prompt {

    private String text;
    private String category;
    private String createdAt;
    private int wordCount;

    public Prompt(String text, String category, String createdAt, int wordCount) {
        this.text = text;
        this.category = category;
        this.createdAt = createdAt;
        this.wordCount = wordCount;
    }

    public String getText() {
        return text;
    }

    public String getCategory() {
        return category;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getWordCount() {
        return wordCount;
    }
}