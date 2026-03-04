package Homework.six.Easy;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Double> data = List.of(3.0, 1.0, 4.0, 1.5, 9.0, 2.6, 5.0, 3.5);
        NumberList nl = new NumberList(data);

        System.out.println("Исходный список:        " + data);
        System.out.println("Минимум:                " + nl.findMin());
        System.out.println("Максимум:               " + nl.findMax());
        System.out.println("Сортировка ↑:           " + nl.sortAscending());
        System.out.println("Сортировка ↓:           " + nl.sortDescending());
        System.out.println("Содержит 4.0?           " + nl.contains(4.0));
        System.out.println("Содержит 7.0?           " + nl.contains(7.0));
        System.out.println("Числа больше 3.0:       " + nl.filterGreaterThan(3.0));
        System.out.println("Сумма:                  " + nl.sum());
    }

}
