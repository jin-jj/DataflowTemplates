package com.google.cloud.teleport.spanner.common;

import static org.junit.Assert.assertEquals;

import com.google.cloud.spanner.Dialect;
import kotlin.Pair;
import org.junit.jupiter.api.Test;

class DdlUtilsTest {

  @Test
  void quoteIdentifier() {
    String quoted = DdlUtils.quoteIdentifier("Schema.Table", Dialect.POSTGRESQL);
    assertEquals("\"Schema\".\"Table\"", quoted);

    quoted = DdlUtils.quoteIdentifier("Default.Table", Dialect.GOOGLE_STANDARD_SQL);
    assertEquals("`Default`.`Table`", quoted);
  }


  @Test
  void splitName() {
    Pair<String, String> paths = DdlUtils.splitName("Schema.Table", Dialect.POSTGRESQL);
    assertEquals(new Pair<>("Schema","Table"), paths);

    paths = DdlUtils.splitName("Table", Dialect.POSTGRESQL);
    assertEquals(new Pair<>("public","Table"), paths);

    paths = DdlUtils.splitName("Schema.Table", Dialect.GOOGLE_STANDARD_SQL);
    assertEquals(new Pair<>("Schema","Table"), paths);

    paths = DdlUtils.splitName("Table", Dialect.GOOGLE_STANDARD_SQL);
    assertEquals(new Pair<>("","Table"), paths);

  }
}