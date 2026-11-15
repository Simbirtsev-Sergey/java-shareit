package ru.practicum.shareit.user;

import java.util.Collection;

public interface UserService {

    Collection<User> getAllUsers();

    User saveUser(UserDto userDto);

    User update(UpdateUserRequest request, final Long userId);

    User getUserById(final Long id);

    void delete(final Long id);
}
