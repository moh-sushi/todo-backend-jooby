package com.todobackend.mohsushi;

import io.jooby.Context;
import io.jooby.Jooby;

public class TodoBackendRoutes {

  private final TodoBackendRepository repository;

  public TodoBackendRoutes(TodoBackendRepository repository) {
    this.repository = repository;
  }

}
