package src.Homework.hard;

public class Main {

  public static void main(String[] args) {

    UserDataCloudDataSource cloudDataSource = new UserDataCloudDataSource();
    UserRepository userRepository = new UserRepository(cloudDataSource);

    System.out.println("Первый запрос: данных нет в кэше — идём в сеть");
    UserData user1 = userRepository.getUserData(1);
    System.out.println("Результат: " + user1);

    System.out.println();

    System.out.println("Второй запрос того же пользователя: берём из кэша");
    UserData user1Cached = userRepository.getUserData(1);
    System.out.println("Результат: " + user1Cached);

    System.out.println();

    System.out.println("Запрос другого пользователя: снова идём в сеть");
    UserData user2 = userRepository.getUserData(2);
    System.out.println("Результат: " + user2);

    System.out.println();

    System.out.println("Запрос несуществующего пользователя");
    UserData user99 = userRepository.getUserData(99);
    System.out.println("Результат: " + user99);

    System.out.println();

    System.out.println("Очистка кэша пользователя id=1");
    userRepository.clearCache(1);

    System.out.println("Повторный запрос пользователя id=1 после очистки кэша");
    UserData user1Again = userRepository.getUserData(1);
    System.out.println("Результат: " + user1Again);

    System.out.println();

    System.out.println("Очистка всего кэша");
    userRepository.clearAllCache();
  }

}
