package src.Homework.hard;

public class UserDataCloudDataSource {
    public UserData getUserData(int id) {
        System.out.println("CloudDataSource: запрос пользователя id=" + id + " из сети...");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        switch (id) {
            case 1: return new UserData(1, "Alice Johnson", "alice@example.com");
            case 2: return new UserData(2, "Bob Smith",     "bob@example.com");
            case 3: return new UserData(3, "Carol White",   "carol@example.com");
            default:
                System.out.println("CloudDataSource: пользователь id=" + id + " не найден на сервере.");
                return null;
        }
    }
}
