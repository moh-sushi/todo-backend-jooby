package com.todobackend.mohsushi;

import io.jooby.Jooby;
import io.jooby.json.JacksonModule;

public class App extends Jooby {
  {
    install(new JacksonModule());

    install("/todos", () -> new TodosApp());
    install("version",() -> new VersionApp());
  }

  public static void main(final String[] args) {
    runApp(args, App::new);
  }

}
