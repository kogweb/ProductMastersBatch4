package Homework.six.Easy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NumberList {

    private final LinkedList<Double> numbers;

    public NumberList(List<Double> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Список не может быть пустым или null");
        }
        this.numbers = new LinkedList<>(numbers);
    }

    public double findMin() {
        return Collections.min(numbers);
    }

    public double findMax() {
        return Collections.max(numbers);
    }

    public LinkedList<Double> sortAscending() {
        LinkedList<Double> sorted = new LinkedList<>(numbers);
        Collections.sort(sorted);
        return sorted;
    }

    public LinkedList<Double> sortDescending() {
        LinkedList<Double> sorted = new LinkedList<>(numbers);
        sorted.sort(Collections.reverseOrder());
        return sorted;
    }

    public boolean contains(double value) {
        return numbers.contains(value);
    }

    public LinkedList<Double> filterGreaterThan(double threshold) {
        LinkedList<Double> result = new LinkedList<>();
        for (double num : numbers) {
            if (num > threshold) {
                result.add(num);
            }
        }
        return result;
    }

    public double sum() {
        double total = 0;
        for (double num : numbers) {
            total += num;
        }
        return total;
    }
}