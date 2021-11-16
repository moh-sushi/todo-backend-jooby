package com.todobackend.mohsushi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Deprecated
class TodoBackendRepositoryMapImpl implements TodoBackendRepository {
  private final Map<Long, TodoBackendEntry> map = new HashMap<>();
  private final AtomicLong counter = new AtomicLong(0);

  @Override
  public synchronized List<TodoBackendEntry> all() {
    return new ArrayList<>(map.values());
  }

  @Override
  public synchronized void deleteAll() {
    map.clear();
  }

  @Override
  public synchronized TodoBackendEntry create(TodoBackendEntry entry, String url) {
    entry.setId(counter.incrementAndGet());
    entry.setUrl(url);
    map.put(entry.getId(), entry);
    return entry;
  }

  @Override
  public synchronized TodoBackendEntry get(final long id) {
    return map.get(id);
  }

  @Override
  public synchronized TodoBackendEntry delete(final long id) {
    final TodoBackendEntry entry = get(id);
    if (entry != null) map.remove(id);
    return entry;
  }

  @Override
  public synchronized TodoBackendEntry update(final long id, final TodoBackendEntry entry) {
    final TodoBackendEntry entryFromDb = map.get(id);
    if (entryFromDb == null) return null;
    // updates can be executed
    if (entry.getTitle() != null) {
      entryFromDb.setTitle(entry.getTitle());
    }
    if (entry.getCompleted() != null) {
      entryFromDb.setCompleted(entry.getCompleted());
    }
    if (entry.getOrder() != null) {
      entryFromDb.setOrder(entry.getOrder());
    }
    // update entry in db
    map.put(id, entryFromDb);

    return entryFromDb;
  }
}
