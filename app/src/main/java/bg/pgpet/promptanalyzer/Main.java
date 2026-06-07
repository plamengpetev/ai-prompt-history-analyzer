package bg.pgpet.promptanalyzer;

import bg.pgpet.promptanalyzer.model.Prompt;
import bg.pgpet.promptanalyzer.service.PromptService;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        PromptService promptService = new PromptService();


        while (true) {

            System.out.println();
            System.out.println("===== AI Prompt History Analyzer =====");
            System.out.println("1. Add Prompt");
            System.out.println("2. Show All Prompts");
            System.out.println("3. Search Prompt");
            System.out.println("4. Statistics");
            System.out.println("5. Delete Prompt");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 6) {
                System.out.println("Goodbye!");
                break;
            }

            if (choice == 1) {
                System.out.print("Enter prompt text: ");
                String text = scanner.nextLine();

                System.out.print("Enter category: ");
                String category = scanner.nextLine();

                int wordCount = text.split("\\s+").length;
                String createdAt = LocalDateTime.now().toString();

                Prompt prompt = new Prompt(text, category, createdAt, wordCount);
                promptService.addPrompt(prompt);

                System.out.println("Prompt added successfully!");
                continue;
            }

            if (choice == 2) {

                for (Prompt prompt : promptService.getAllPrompts()) {
                    System.out.println("----------------------");
                    System.out.println("Text: " + prompt.getText());
                    System.out.println("Category: " + prompt.getCategory());
                    System.out.println("Created: " + prompt.getCreatedAt());
                    System.out.println("Words: " + prompt.getWordCount());
                }

                continue;
            }

            if (choice == 3) {

                System.out.print("Enter keyword: ");
                String keyword = scanner.nextLine();

                List<Prompt> results = promptService.searchByKeyword(keyword);

                for (Prompt prompt : results) {
                    System.out.println("---------------------");
                    System.out.println(prompt.getText());
                    System.out.println(prompt.getCategory());
                }

                continue;
            }

            if (choice == 4) {
                System.out.println("===== Statistics =====");
                System.out.println("Total prompts: " + promptService.getPromptCount());
                continue;
            }

            if (choice == 5) {

                List<Prompt> allPrompts = promptService.getAllPrompts();

                for (int i = 0; i < allPrompts.size(); i++) {
                    System.out.println((i + 1) + ". " + allPrompts.get(i).getText());
                }

                System.out.print("Choose prompt to delete: ");
                int index = Integer.parseInt(scanner.nextLine());

                if (promptService.deletePrompt(index - 1)) {
                    System.out.println("Prompt deleted successfully!");
                } else {
                    System.out.println("Invalid prompt number!");
                }

                continue;
            }

            System.out.println("Option " + choice + " selected.");
        }
    }
}