package src.Homework_week5.medium;
import java.util.ArrayList;

public class Main {
    public static boolean isWeekend(DayofWeek day) {
        return day == DayofWeek.SATURDAY || day == DayofWeek.SUNDAY;
    }

    public static void main(String[] args) {
        ArrayList<DayofWeek> days = new ArrayList<>();
        for (DayofWeek day : DayofWeek.values()) {
            days.add(day);
        }

        System.out.println("Все дни недели:");
        for (DayofWeek day : days) {
            System.out.println(day);
        }

        System.out.println("\nПроверка выходных дней:");
        for (DayofWeek day : days) {
            System.out.println(day + " выходной: " + isWeekend(day));
        }
    }
}
