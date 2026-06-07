package bg.pgpet.promptanalyzer.service;

import bg.pgpet.promptanalyzer.model.Prompt;
import bg.pgpet.promptanalyzer.storage.FileStorage;

import java.util.ArrayList;
import java.util.List;

public class PromptService {

    private List<Prompt> prompts = new ArrayList<>();
    private FileStorage fileStorage = new FileStorage();

    public PromptService() {
        this.prompts = fileStorage.loadPrompts();
    }

    public void addPrompt(Prompt prompt) {
        prompts.add(prompt);
        fileStorage.savePrompts(prompts);
    }

    public List<Prompt> getAllPrompts() {
        return prompts;
    }

    public int getPromptCount() {
        return prompts.size();
    }

    public List<Prompt> searchByKeyword(String keyword) {

        List<Prompt> result = new ArrayList<>();

        for (Prompt prompt : prompts) {
            if (prompt.getText().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(prompt);
            }
        }

        return result;
    }

    public boolean deletePrompt(int index) {
        if (index < 0 || index >= prompts.size()) {
            return false;
        }

        prompts.remove(index);
        fileStorage.savePrompts(prompts);
        return true;
    }
}