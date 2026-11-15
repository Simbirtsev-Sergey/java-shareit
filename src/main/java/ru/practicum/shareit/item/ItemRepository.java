package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;

import java.util.Collection;
import java.util.Optional;

public interface ItemRepository {

    Collection<Item> getItemsByOwner(final Long userId);

    Optional<Item> getItemById(final Long itemId);

    Item update(final Item item);

    Item create(final Item item);

    Collection<Item> search(final String text);
}
