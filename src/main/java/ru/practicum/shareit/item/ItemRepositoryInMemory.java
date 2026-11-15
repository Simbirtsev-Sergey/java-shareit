package ru.practicum.shareit.item;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ItemRepositoryInMemory implements ItemRepository {

    private static final Map<Long, Item> ITEMS = new HashMap<>();

    @Override
    public Collection<Item> getItemsByOwner(final Long userId) {
        return ITEMS.values()
                .stream()
                .filter(item -> userId.equals(item.getIdUser()))
                .toList();
    }

    @Override
    public Optional<Item> getItemById(final Long itemId) {
        if (ITEMS.containsKey(itemId)) {
            return Optional.of(ITEMS.get(itemId));
        }
        return Optional.empty();
    }

    @Override
    public Item update(final Item item) {
        ITEMS.put(item.getId(), item);
        return item;
    }

    @Override
    public Item create(final Item item) {
        Long id = lastId().orElse(0L) + 1L;
        item.setId(id);

        ITEMS.put(id, item);

        return item;

    }

    @Override
    public Collection<Item> search(final String text) {
        final String query = text.toLowerCase();

        return ITEMS.values()
                .stream()
                .filter(item -> Boolean.TRUE.equals(item.getAvailable()))
                .filter(item -> containsIgnoreCase(item.getName(), query)
                        || containsIgnoreCase(item.getDescription(), query))
                .toList();
    }

    private static boolean containsIgnoreCase(final String source, final String query) {
        return source != null && source.toLowerCase().contains(query);
    }

    private Optional<Long> lastId() {
        return ITEMS.keySet()
                .stream()
                .max(Long::compare);
    }
}