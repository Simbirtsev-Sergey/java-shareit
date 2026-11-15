package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.UpdateItemRequest;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public Collection<Item> getItemsByOwner(@RequestHeader("X-Sharer-User-Id") final Long userId) {
        return itemService.getItemsByOwner(userId);
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable final Long itemId) {
        return itemService.getItemById(itemId);
    }

    @PatchMapping("/{itemId}")
    public Item update(@PathVariable final Long itemId,
                       @RequestBody final UpdateItemRequest request,
                       @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.update(itemId, userId, request);
    }

    @PostMapping
    public Item create(@Valid @RequestBody final ItemDto itemDto,
                       @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.create(itemDto, userId);
    }

    @GetMapping("/search")
    public Collection<Item> search(@RequestParam(value = "text", defaultValue = "") final String text) {
        return itemService.search(text);
    }
}