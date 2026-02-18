package src.Homework.hard;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private final Map<Integer, UserData> cache = new HashMap<>();
    
    private final UserDataCloudDataSource cloudDataSource;

    public UserRepository(UserDataCloudDataSource cloudDataSource) {
        this.cloudDataSource = cloudDataSource;
    }

    public UserData getUserData(int id) {
        if (cache.containsKey(id)) {
            System.out.println("Repository: данные пользователя id=" + id + " получены из кэша.");
            return cache.get(id);
        }

        UserData userData = cloudDataSource.getUserData(id);

        if (userData != null) {
            cache.put(id, userData);
            System.out.println("Repository: данные пользователя id=" + id + " сохранены в кэш.");
        }

        return userData;
    }

    public void clearCache(int id) {
        cache.remove(id);
        System.out.println("Repository: кэш для пользователя id=" + id + " очищен.");
    }


    public void clearAllCache() {
        cache.clear();
        System.out.println("Repository: весь кэш очищен.");
    }
}
