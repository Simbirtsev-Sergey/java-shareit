package ru.practicum.shareit.user;

public class UserMapper {

    public static UserDto mapToUserDto(final User user) {
        UserDto userDto = new UserDto();

        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    public static User updateUserFields(final User user, final UpdateUserRequest request) {

        if (request.hasName()) {
            user.setName(request.getName());
        }

        if (request.hasEmail()) {
            user.setEmail(request.getEmail());
        }

        return user;
    }

    public static User mapToUser(final UserDto userDto) {
        User user = new User();

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        return user;
    }
}
