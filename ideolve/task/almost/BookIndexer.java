package ideolve.task.almost;

import java.io.*;
import java.util.*;

public class BookIndexer {
    private Set<String> excludeWords;
    private List<String> bookPages;
    private Map<String, Set<Integer>> wordIndex;

    public BookIndexer() {
        excludeWords = new HashSet<>();
        bookPages = new ArrayList<>();
        wordIndex = new TreeMap<>();
    }

    public void readExcludeWords(String excludeWordsFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(excludeWordsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                excludeWords.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readBookPages() {
        bookPages.add("C:/Users/Sarthak/IdeaProjects/StudentManagementApp/src/ideolve/task/almost/Page1.txt");
        bookPages.add("C:/Users/Sarthak/IdeaProjects/StudentManagementApp/src/ideolve/task/almost/Page2.txt");
        bookPages.add("C:/Users/Sarthak/IdeaProjects/StudentManagementApp/src/ideolve/task/almost/Page3.txt");
    }

    public void generateIndex() {
        int page = 1;
        for (String pageFile : bookPages) {
            try (BufferedReader reader = new BufferedReader(new FileReader(pageFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\W+");
                    for (String word : words) {
                        word = word.toLowerCase();
                        if (!excludeWords.contains(word)) {
                            Set<Integer> pages = wordIndex.getOrDefault(word, new HashSet<>());
                            pages.add(page);
                            wordIndex.put(word, pages);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            page++;
        }
    }

    public void writeIndexToFile(String outputFile) {
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (Map.Entry<String, Set<Integer>> entry : wordIndex.entrySet()) {
                String word = entry.getKey();
                Set<Integer> pages = entry.getValue();
                writer.write(word + " : ");
                boolean firstPage = true;
                for (int page : pages) {
                    if (!firstPage) {
                        writer.write(", ");
                    }
                    writer.write(Integer.toString(page));
                    firstPage = false;
                }
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
