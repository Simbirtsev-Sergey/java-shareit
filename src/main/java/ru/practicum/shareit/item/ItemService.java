package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.UpdateItemRequest;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;

public interface ItemService {

    Collection<Item> getItemsByOwner(final Long userId);

    Item getItemById(final Long itemId);

    Item update(final Long itemId, final Long userId, final UpdateItemRequest request);

    Item create(final ItemDto itemDto, final Long userId);

    Collection<Item> search(final String text);
}
