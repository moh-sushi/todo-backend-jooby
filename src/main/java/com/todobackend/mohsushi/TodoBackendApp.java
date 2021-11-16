package com.todobackend.mohsushi;

import io.jooby.*;
import io.jooby.hibernate.HibernateModule;
import io.jooby.hibernate.TransactionalRequest;
import io.jooby.hikari.HikariModule;
import io.jooby.json.JacksonModule;
import org.hibernate.Session;

import java.util.List;

public class TodoBackendApp extends Jooby {
  {
    install(new JacksonModule());

    install(new HikariModule());
    install(new HibernateModule());
    decorator(new TransactionalRequest());

    final Cors cors = new Cors().setMethods(List.of(GET, POST, DELETE, PATCH));
    decorator(new CorsHandler(cors));

    setContextPath("/todos");
    get("/", ctx -> {
      ctx.setResponseType(MediaType.json);
      final TodoBackendRepository repository = new TodoBackendRepositoryHibernateImpl(require(Session.class));
      return repository.all();
    });
    delete("/", ctx -> {
      ctx.setResponseType(MediaType.json);
      final TodoBackendRepository repository = new TodoBackendRepositoryHibernateImpl(require(Session.class));
      repository.deleteAll();
      return StatusCode.NO_CONTENT;
    });
    //
    post("/", ctx -> {
      ctx.setResponseType(MediaType.json);
      TodoBackendEntry entry = ctx.body(TodoBackendEntry.class);
      final TodoBackendRepository repository = new TodoBackendRepositoryHibernateImpl(require(Session.class));
      return repository.create(entry, ctx.getRequestURL());
    });

    get("/{id:[0-9]+}", ctx -> {
      ctx.setResponseType(MediaType.json);
      final TodoBackendRepository repository = new TodoBackendRepositoryHibernateImpl(require(Session.class));
      final TodoBackendEntry entry = repository.get(Long.parseLong(ctx.path("id").value()));
      if (entry == null) {
        return StatusCode.NOT_FOUND_CODE;
      }
      return entry;
    });
    delete("/{id:[0-9]+}", ctx -> {
      ctx.setResponseType(MediaType.json);
      final TodoBackendRepository repository = new TodoBackendRepositoryHibernateImpl(require(Session.class));
      return repository.delete(Long.parseLong(ctx.path("id").value()));
    });
    patch("/{id:[0-9]+}", ctx -> {
      ctx.setResponseType(MediaType.json);
      final TodoBackendEntry entryPatch = ctx.body(TodoBackendEntry.class);
      final TodoBackendRepository repository = new TodoBackendRepositoryHibernateImpl(require(Session.class));
      final TodoBackendEntry entryFromDb = repository.get(Long.parseLong(ctx.path("id").value()));
      if (entryFromDb == null) {
        return StatusCode.NOT_FOUND_CODE;
      } else {
        return repository.update(Long.parseLong(ctx.path("id").value()), entryPatch);
      }
    });
  }

  public static void main(final String[] args) {
    runApp(args, TodoBackendApp::new);
  }

}
