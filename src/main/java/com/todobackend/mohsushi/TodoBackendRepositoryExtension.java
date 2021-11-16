package com.todobackend.mohsushi;

import io.jooby.Extension;
import io.jooby.Jooby;

import javax.annotation.Nonnull;

public class TodoBackendRepositoryExtension implements Extension {

  @Override
  public void install(@Nonnull Jooby application) throws Exception {
    application.getServices().put(TodoBackendRepository.class, new TodoBackendRepositoryMapImpl());
  }
}
