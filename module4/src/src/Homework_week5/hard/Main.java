package src.Homework_week5.hard;
import java.util.*;

public class Main {
    public static void removeDuplicates(ArrayList<Integer> list) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>(list);
        list.clear();
        list.addAll(set);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество чисел: ");
        int n = scanner.nextInt();

        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Введите " + n + " чисел:");
        for (int i = 0; i < n; i++) {
            System.out.print("Число " + (i + 1) + ": ");
            numbers.add(scanner.nextInt());
        }

        System.out.println("\nДо удаления дубликатов: " + numbers);

        removeDuplicates(numbers);

        System.out.println("После удаления дубликатов: " + numbers);

        scanner.close();
    }
}
