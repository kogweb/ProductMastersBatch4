package Homework.six.medium;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class WordCounter {

    private final String filePath;
    private final HashSet<String> uniqueWords = new HashSet<>();
    private final HashMap<String, Integer> wordFrequency = new HashMap<>();

    public WordCounter(String filePath) {
        this.filePath = filePath;
    }

    // Читает файл и заполняет HashSet и HashMap
    public void analyze() throws IOException {
        uniqueWords.clear();
        wordFrequency.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Приводим к нижнему регистру и разбиваем по пробелам/знакам препинания
                String[] words = line.toLowerCase().split("[^a-zA-Zа-яА-ЯёЁ]+");
                for (String word : words) {
                    if (word.isEmpty()) continue;

                    uniqueWords.add(word); // HashSet — только уникальные слова
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1); // HashMap — счётчик
                }
            }
        }
    }

    // Возвращает все уникальные слова
    public HashSet<String> getUniqueWords() {
        return uniqueWords;
    }

    // Возвращает частоту каждого слова
    public HashMap<String, Integer> getWordFrequency() {
        return wordFrequency;
    }

    // Печатает результат, отсортированный по убыванию частоты
    public void printResults() {
        System.out.println("=".repeat(40));
        System.out.printf("Всего уникальных слов: %d%n", uniqueWords.size());
        System.out.println("=".repeat(40));
        System.out.printf("%-20s | %s%n", "Слово", "Частота");
        System.out.println("-".repeat(40));

        wordFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> System.out.printf("%-20s | %d%n", e.getKey(), e.getValue()));

        System.out.println("=".repeat(40));
    }
}
