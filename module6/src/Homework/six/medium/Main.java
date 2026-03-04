package Homework.six.medium;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    String path = args.length > 0 ? args[0] : "file1.txt";

    java.io.File file = new java.io.File(path);
    if (!file.exists()) {
      try (java.io.PrintWriter pw = new java.io.PrintWriter(file)) {
        pw.println("В данный момент обучаюсь на курсе Product Masters");
        pw.println("Дошел до 6 недели.");
      } catch (IOException e) {
        System.err.println("Ошибка создания файла: " + e.getMessage());
        return;
      }
      System.out.println("Тестовый файл создан: " + path);
    }

    WordCounter counter = new WordCounter(path);
    try {
      counter.analyze();
      counter.printResults();
    } catch (IOException e) {
      System.err.println("Ошибка чтения файла: " + e.getMessage());
    }
  }

  public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) {
    return null;
  }
}
