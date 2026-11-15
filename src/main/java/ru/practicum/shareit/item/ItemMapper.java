package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.UpdateItemRequest;
import ru.practicum.shareit.item.model.Item;

public class ItemMapper {

    public static Item updateItemFields(final Item item, final UpdateItemRequest request) {

        if (request.hasDescription()) {
            item.setDescription(request.getDescription());
        }

        if (request.hasStatus()) {
            item.setAvailable(request.getAvailable());
        }

        if (request.hasTitle()) {
            item.setName(request.getName());
        }

        return item;
    }

    public static Item mapToItem(final ItemDto itemDto) {
        final Item item = new Item();

        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAvailable(itemDto.getAvailable());

        return item;
    }
}
