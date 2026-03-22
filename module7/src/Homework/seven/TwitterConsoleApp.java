package Homework.seven;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TwitterConsoleApp {

  private final Scanner        scanner = new Scanner(System.in);
  private final TwitterService service = new TwitterService();
  private       User           currentUser;

  public void run() {
    System.out.println("============================");
    System.out.println("  Twitter Console");
    System.out.println("============================");

    String name = "";
    while (name.isBlank()) {
      System.out.print("Введите ваше имя: ");
      name = scanner.nextLine().trim();
      if (name.isBlank()) System.out.println("Имя не может быть пустым.");
    }
    currentUser = new User(name);
    System.out.println("Добро пожаловать, " + currentUser.getUsername() + "!\n");

    if (TwitterService.FileStorage.exists()) {
      List<Post> saved = TwitterService.FileStorage.load();
      if (!saved.isEmpty()) {
        service.getRepository().replaceAll(saved);
        System.out.println("Загружены сохранённые посты (" + saved.size() + " шт.).");
      } else {
        seedPosts();
      }
    } else {
      seedPosts();
    }

    boolean running = true;
    while (running) {
      printMenu();
      String choice = scanner.nextLine().trim();
      switch (choice) {
        case "1" -> handleCreatePost();
        case "2" -> handleLike();
        case "3" -> handleRepost();
        case "4" -> handleShowAll();
        case "5" -> handleShowTop();
        case "6" -> handleShowMine();
        case "7" -> { running = false; handleExit(); }
        default  -> System.out.println("Неверный выбор. Введите цифру от 1 до 7.");
      }
    }
  }

  private void seedPosts() {
    service.loadSeedPosts();
    System.out.println("Добавлены стартовые посты.\n");
  }

  private void printMenu() {
    System.out.println("\n=== Twitter Console ===");
    System.out.println("1. Написать пост");
    System.out.println("2. Лайкнуть пост");
    System.out.println("3. Сделать репост");
    System.out.println("4. Показать все посты");
    System.out.println("5. Показать популярные посты");
    System.out.println("6. Показать мои посты");
    System.out.println("7. Выход");
    System.out.print("Выберите действие: ");
  }

  private void handleCreatePost() {
    System.out.print("Введите текст поста (макс. 280 символов): ");
    String content = scanner.nextLine().trim();
    try {
      Post post = service.createPost(currentUser, content);
      System.out.println("Пост добавлен! (ID=" + post.getId() + ")");
    } catch (IllegalArgumentException e) {
      System.out.println("Ошибка: " + e.getMessage());
    }
  }

  private void handleLike() {
    int id = readInt("Введите ID поста: ");
    Optional<Post> opt = service.likePost(id);
    if (opt.isPresent()) {
      System.out.println("Лайк поставлен! Лайков: " + opt.get().getLikes());
    } else {
      System.out.println("Пост с ID=" + id + " не найден.");
    }
  }

  private void handleRepost() {
    int id = readInt("Введите ID поста: ");
    Optional<Post> opt = service.repostPost(id);
    if (opt.isPresent()) {
      System.out.println("Репост сделан! Репостов: " + opt.get().getReposts());
    } else {
      System.out.println("Пост с ID=" + id + " не найден.");
    }
  }

  private void handleShowAll() {
    List<Post> posts = service.getAllPosts();
    if (posts.isEmpty()) {
      System.out.println("Постов пока нет.");
      return;
    }
    System.out.println("\nВсе посты:");
    posts.forEach(System.out::println);
  }

  private void handleShowTop() {
    int n = readInt("Сколько популярных постов показать? ");
    if (n <= 0) { System.out.println("Введите положительное число."); return; }
    List<Post> posts = service.getTopPosts(n);
    if (posts.isEmpty()) { System.out.println("Постов пока нет."); return; }
    System.out.println("\nТоп-" + Math.min(n, posts.size()) + " постов по лайкам:");
    for (int i = 0; i < posts.size(); i++) {
      System.out.println((i + 1) + ". " + posts.get(i));
    }
  }

  private void handleShowMine() {
    List<Post> posts = service.getPostsByUser(currentUser);
    if (posts.isEmpty()) {
      System.out.println("У вас пока нет постов. Напишите первый!");
      return;
    }
    System.out.println("\nМои посты (@" + currentUser.getUsername() + "):");
    posts.forEach(System.out::println);
  }

  private void handleExit() {
    TwitterService.FileStorage.save(service.getAllPosts());
    System.out.println("Посты сохранены. Выход...");
  }

  private int readInt(String prompt) {
    while (true) {
      System.out.print(prompt);
      try {
        return Integer.parseInt(scanner.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.println("Введите целое число.");
      }
    }
  }

  public static void main(String[] args) {
    new TwitterConsoleApp().run();
  }
}