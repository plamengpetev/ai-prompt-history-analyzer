package bg.pgpet.promptanalyzer.storage;

import bg.pgpet.promptanalyzer.model.Prompt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {

    private static final String FILE_NAME = "prompts.txt";

    public void savePrompts(List<Prompt> prompts) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Prompt prompt : prompts) {

                writer.write(
                        prompt.getText() + ";" +
                                prompt.getCategory() + ";" +
                                prompt.getCreatedAt() + ";" +
                                prompt.getWordCount()
                );

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving prompts!");
        }
    }

    public List<Prompt> loadPrompts() {

        List<Prompt> prompts = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return prompts;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(";");

                String text = parts[0];
                String category = parts[1];
                String createdAt = parts[2];
                int wordCount = Integer.parseInt(parts[3]);

                Prompt prompt = new Prompt(text, category, createdAt, wordCount);

                prompts.add(prompt);
            }

        } catch (IOException e) {
            System.out.println("Error loading prompts!");
        }

        return prompts;
    }

}