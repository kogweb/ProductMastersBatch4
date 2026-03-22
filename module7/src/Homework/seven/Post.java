package Homework.seven;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class Post implements Serializable, Comparable<Post> {
    private static final long serialVersionUID = 1L;
    private static final int MAX_LENGTH = 280;
    private static final AtomicInteger ID_COUNTER = new AtomicInteger(0);
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private final int id;
    private final User author;
    private final String content;
    private final LocalDateTime createdAt;
    private int likes;
    private int reposts;

    public Post(User author, String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Текст поста не может быть пустым.");
        }
        if (content.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "Текст поста превышает " + MAX_LENGTH + " символов.");
        }
        this.id        = ID_COUNTER.incrementAndGet();
        this.author    = author;
        this.content   = content.trim();
        this.createdAt = LocalDateTime.now();
        this.likes     = 0;
        this.reposts   = 0;
    }

    // Constructor used when loading from file (preserves original ID)
    public Post(int id, User author, String content,
                LocalDateTime createdAt, int likes, int reposts) {
        this.id        = id;
        this.author    = author;
        this.content   = content;
        this.createdAt = createdAt;
        this.likes     = likes;
        this.reposts   = reposts;
        ID_COUNTER.accumulateAndGet(id, Math::max);
    }

    public void like()   { likes++;   }
    public void repost() { reposts++; }

    public int           getId()        { return id;        }
    public User          getAuthor()    { return author;    }
    public String        getContent()   { return content;   }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public int           getLikes()     { return likes;     }
    public int           getReposts()   { return reposts;   }

    /** Newest first */
    @Override
    public int compareTo(Post other) {
        return other.createdAt.compareTo(this.createdAt);
    }

    /** Short form — matches the example in the task */
    @Override
    public String toString() {
        return String.format(
                "Post{id=%d, author=%s, content='%s', likes=%d, reposts=%d}",
                id, author.getUsername(), content, likes, reposts);
    }

    /** Pretty multiline form for display */
    public String toPrettyString() {
        return String.format(
                "  #%-3d @%-12s  [%s]\n  %s\n  Likes: %d  Reposts: %d",
                id, author.getUsername(), createdAt.format(FMT),
                content, likes, reposts);
    }
}
