package Homework.seven;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class TwitterService {
  public static class Repository<T extends Post> {

    private final List<T> posts = new ArrayList<>();

    public void add(T post) {
      posts.add(post);
    }

    public Optional<T> findById(int id) {
      return posts.stream()
              .filter(p -> p.getId() == id)
              .findFirst();
    }

    public List<T> findAllSortedByDate() {
      return posts.stream()
              .sorted()
              .collect(Collectors.toList());
    }

    public List<T> findTopByLikes(int n) {
      return posts.stream()
              .sorted(Comparator.comparingInt(Post::getLikes).reversed())
              .limit(n)
              .collect(Collectors.toList());
    }

    public List<T> findByUser(User user) {
      return posts.stream()
              .filter(p -> p.getAuthor().equals(user))
              .sorted()
              .collect(Collectors.toList());
    }

    public List<T> getAll() { return Collections.unmodifiableList(posts); }

    public void replaceAll(Collection<T> items) {
      posts.clear();
      posts.addAll(items);
    }

    public boolean isEmpty() { return posts.isEmpty(); }
  }

  public static class FileStorage {
    private static final Path FILE = Paths.get("twitter_posts.dat");

    private FileStorage() {}

    public static void save(List<Post> posts) {
      try (ObjectOutputStream oos =
                   new ObjectOutputStream(Files.newOutputStream(FILE))) {
        oos.writeObject(new ArrayList<>(posts));
      } catch (IOException e) {
        System.out.println("  [!] Не удалось сохранить посты: " + e.getMessage());
      }
    }

    @SuppressWarnings("unchecked")
    public static List<Post> load() {
      if (!Files.exists(FILE)) return new ArrayList<>();
      try (ObjectInputStream ois =
                   new ObjectInputStream(Files.newInputStream(FILE))) {
        return (List<Post>) ois.readObject();
      } catch (IOException | ClassNotFoundException e) {
        System.out.println("  [!] Не удалось загрузить посты: " + e.getMessage());
        return new ArrayList<>();
      }
    }

    public static boolean exists() { return Files.exists(FILE); }
  }

  private final Repository<Post> repository = new Repository<>();

  public void loadSeedPosts() {
    User alice   = new User("Alice");
    User bob     = new User("Bob");
    User charlie = new User("Charlie");

    Post p1 = new Post(alice,   "Привет, мир!");
    Post p2 = new Post(bob,     "Сегодня отличный день!");
    Post p3 = new Post(charlie, "Люблю программировать на Java.");

    p1.like(); p1.like(); p1.like();
    p2.like(); p2.repost();
    p3.like(); p3.like(); p3.repost(); p3.repost();

    repository.add(p1);
    repository.add(p2);
    repository.add(p3);
  }

  public Post createPost(User author, String content) {
    Post post = new Post(author, content);
    repository.add(post);
    return post;
  }

  public Optional<Post> likePost(int id) {
    Optional<Post> opt = repository.findById(id);
    opt.ifPresent(Post::like);
    return opt;
  }

  public Optional<Post> repostPost(int id) {
    Optional<Post> opt = repository.findById(id);
    opt.ifPresent(Post::repost);
    return opt;
  }

  public List<Post> getAllPosts()           { return repository.findAllSortedByDate(); }
  public List<Post> getTopPosts(int n)      { return repository.findTopByLikes(n);    }
  public List<Post> getPostsByUser(User u)  { return repository.findByUser(u);        }
  public Repository<Post> getRepository()  { return repository;                      }
}
