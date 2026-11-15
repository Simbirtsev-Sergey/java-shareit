package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.UpdateItemRequest;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final UserRepository userRepository;

    @Override
    public Collection<Item> getItemsByOwner(final Long userId) {
        checkUserExists(userId);
        return repository.getItemsByOwner(userId);
    }

    @Override
    public Item getItemById(final Long itemId) {
        Optional<Item> itemOptional = repository.getItemById(itemId);
        if (itemOptional.isEmpty()) {
            throw new NotFoundException("Вещи с id = " + itemId + " не существует");
        }
        return itemOptional.get();
    }

    @Override
    public Item update(final Long itemId, final Long userId, final UpdateItemRequest request) {
        Item item = getItemById(itemId);

        if (!userId.equals(item.getIdUser())) {
            throw new NotFoundException("Пользователь с id = " + userId
                    + " не является владельцем вещи с id = " + itemId);
        }

        ItemMapper.updateItemFields(item, request);
        return repository.update(item);
    }

    @Override
    public Item create(final ItemDto itemDto, final Long userId) {
        checkUserExists(userId);

        Item item = ItemMapper.mapToItem(itemDto);
        item.setIdUser(userId);
        return repository.create(item);
    }

    @Override
    public Collection<Item> search(final String text) {
        if (text == null || text.isBlank()) {
            return List.of();
        }
        return repository.search(text);
    }

    private void checkUserExists(final Long userId) {
        userRepository.getUserById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователя с id = " + userId + " не существует"));
    }
}