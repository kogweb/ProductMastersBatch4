package Homework.seven;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  private final String username;

  public User(String username) {
    if (username == null || username.isBlank()) {
      throw new IllegalArgumentException("Имя пользователя не может быть пустым.");
    }
    this.username = username.trim();
  }

  public String getUsername() { return username; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    return Objects.equals(username, ((User) o).username);
  }

  @Override
  public int hashCode() { return Objects.hash(username); }

  @Override
  public String toString() { return username; }
}
