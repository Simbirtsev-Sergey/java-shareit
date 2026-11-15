package ru.practicum.shareit.user;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryInMemory implements UserRepository {
    private static final Map<Long, User> USERS = new HashMap<>();

    @Override
    public Collection<User> findAll() {
        return USERS.values();
    }

    @Override
    public User save(final User user) {
        Long id = lastId().orElse(0L) + 1L;
        user.setId(id);

        USERS.put(id, user);

        return user;
    }

    @Override
    public Optional<User> getUserById(final Long id) {
        if (USERS.containsKey(id)) {
            return Optional.of(USERS.get(id));
        }
        return Optional.empty();
    }

    @Override
    public User update(final User user) {
        USERS.put(user.getId(), user);
        return user;
    }

    @Override
    public void delete(final Long id) {
        USERS.remove(id);
    }

    @Override
    public long duplicateEmail(final String email) {
        return USERS.values()
                .stream()
                .map(User::getEmail)
                .filter(email::equals)
                .count();
    }

    private Optional<Long> lastId() {
        return USERS.keySet()
                .stream()
                .max(Long::compare);
    }
}
