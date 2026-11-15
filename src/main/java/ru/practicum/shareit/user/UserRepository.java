package ru.practicum.shareit.user;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {

    Collection<User> findAll();

    User save(User user);

    Optional<User> getUserById(final Long id);

    User update(final User user);

    void delete(final Long id);

    long duplicateEmail(final String email);
}
