package src.Homework.medium;

public class Main {

    public static void main(String[] args) {

        // --- Box<String> ---
        System.out.println("Box<String>");
        Box<String> stringBox = new Box<>("Hello, Generics!");
        stringBox.showType();
        System.out.println("Содержимое: " + stringBox.getItem());
        stringBox.setItem("Новое значение");
        System.out.println("После setItem: " + stringBox.getItem());

        System.out.println();

        // --- Box<Integer> ---
        System.out.println("Box<Integer>");
        Box<Integer> intBox = new Box<>(42);
        intBox.showType();
        System.out.println("Содержимое: " + intBox.getItem());
        intBox.setItem(100);
        System.out.println("После setItem: " + intBox.getItem());
    }
}