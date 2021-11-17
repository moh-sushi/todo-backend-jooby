package com.todobackend.mohsushi;

import io.jooby.Jooby;
import io.jooby.MediaType;
import io.jooby.ModelAndView;
import io.jooby.freemarker.FreemarkerModule;

import java.io.BufferedInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class VersionApp extends Jooby {
  {
    install(new FreemarkerModule());

    get("/", ctx -> {
      final String version;
      try (BufferedInputStream bis = new BufferedInputStream(VersionApp.class.getResourceAsStream("/com/todobackend/mohsushi/version.txt"))) {
        version = new String(bis.readAllBytes(), StandardCharsets.UTF_8);
      }
      final MediaType mediaType = ctx.accept(List.of(MediaType.json, MediaType.xml, MediaType.html));
      if (MediaType.json.equals(mediaType)) {
        ctx.setResponseType(MediaType.json);
        return Map.of("version", version);
      }
      if (MediaType.html.equals(mediaType)) {
        ctx.setResponseType(MediaType.html);
        return new ModelAndView("html/version.ftl")
            .put("version", version);
      }
      if (MediaType.xml.equals(mediaType)) {
        ctx.setResponseType(MediaType.xml);
        return new ModelAndView("xml/version.ftl")
            .put("version", version);
      }
      ctx.setResponseType(MediaType.text);
      return version;
    });
  }
}
