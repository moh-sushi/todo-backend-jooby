package com.todobackend.mohsushi;

import io.jooby.MockRouter;
import io.jooby.StatusCode;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {
  @Test
  @Disabled
  public void welcome() {
    MockRouter router = new MockRouter(new App());
    router.get("/", rsp -> {
      assertEquals("Welcome to Jooby!", rsp.value());
      assertEquals(StatusCode.OK, rsp.getStatusCode());
    });
  }
}
