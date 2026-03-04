package Homework.six.hard;
import java.util.*;

public class Main {

  public static void main(String[] args) {
    SafeList<String> list = new SafeList<>();

    list.add("apple");
    list.add("banana");
    list.add("cherry");
    list.add(null);
    list.add("apple");

    System.out.println("Список:              " + list);
    System.out.println("Размер:              " + list.size());
    System.out.println();

    System.out.println("get(0):              " + list.get(0));
    System.out.println("get(2):              " + list.get(2));
    System.out.println("get(5):              " + list.get(5));
    System.out.println("get(-1):             " + list.get(-1));
    System.out.println();

    System.out.println("set(1, \"mango\"):     " + list.set(1, "mango"));
    System.out.println("set(1, \"apple\"):     " + list.set(1, "apple"));
    System.out.println("set(99, \"kiwi\"):     " + list.set(99, "kiwi"));
    System.out.println("Список после set:    " + list);
    System.out.println();

    System.out.println("remove(0):           " + list.remove(0));
    System.out.println("remove(99):          " + list.remove(99));
    System.out.println("Список после remove: " + list);
    System.out.println();

    SafeList<Integer> nums = new SafeList<>();
    nums.addAll(Arrays.asList(1, 2, 3, 2, null, 4, 1));
    System.out.println("addAll с дублями:    " + nums);
  }
}
