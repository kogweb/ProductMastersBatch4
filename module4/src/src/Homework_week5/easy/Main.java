package src.Homework_week5.easy;

public class Main {
    public static void main(String[] args) {
        for (DayofWeek day : DayofWeek.values()) {
            System.out.println(day);
        }
        DayofWeek today = DayofWeek.MONDAY;
        System.out.println("Сегодня: " + today);
    }
}
