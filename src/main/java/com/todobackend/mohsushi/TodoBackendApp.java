package com.todobackend.mohsushi;

import io.jooby.*;
import io.jooby.json.JacksonModule;

import java.util.List;

public class TodoBackendApp extends Jooby {
  // FIXME default content type -> json

  // alle responses -> json
  // FIXME / get -> alle todos anzeigen
  // FIXME / delete -> alle todos loeschen
  // ----
  // FIXME / post json todo -> todo anlegen
  // FIXME /{id} get json todo -> todo mit id laden
  // FIXME /{id} delete json todo -> todo mit id loeschen
  // FIXME /{id} patch json todo -> todo mit id aktualisieren
  {
    install(new TodoBackendRepositoryExtension());
    install(new JacksonModule());

    final Cors cors = new Cors().setMethods(List.of(GET, POST, DELETE, PATCH));
    decorator(new CorsHandler(cors));

    setContextPath("/todos");
    get("/", ctx -> {
      ctx.setResponseType(MediaType.json);
      final TodoBackendRepository repository = require(TodoBackendRepository.class);
      return repository.all();
    });
    delete("/", ctx -> {
      ctx.setResponseType(MediaType.json);
      final TodoBackendRepository repository = require(TodoBackendRepository.class);
      repository.deleteAll();
      return StatusCode.NO_CONTENT;
    });
    //
    post("/", ctx -> {
      ctx.setResponseType(MediaType.json);
      TodoBackendEntry entry = ctx.body(TodoBackendEntry.class);
      final TodoBackendRepository repository = require(TodoBackendRepository.class);
      return repository.create(entry, ctx.getRequestURL());
    });

    get("/{id:[0-9]+}", ctx -> {
      ctx.setResponseType(MediaType.json);
      final TodoBackendRepository repository = require(TodoBackendRepository.class);
      final TodoBackendEntry entry = repository.get(Long.parseLong(ctx.path("id").value()));
      if (entry == null) {
        return StatusCode.NOT_FOUND_CODE;
      }
      return entry;
    });
    delete("/{id:[0-9]+}", ctx -> {
      ctx.setResponseType(MediaType.json);
      final TodoBackendRepository repository = require(TodoBackendRepository.class);
      return repository.delete(Long.parseLong(ctx.path("id").value()));
    });
    patch("/{id:[0-9]+}", ctx -> {
      ctx.setResponseType(MediaType.json);
      final TodoBackendEntry entryPatch = ctx.body(TodoBackendEntry.class);
      final TodoBackendRepository repository = require(TodoBackendRepository.class);
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
