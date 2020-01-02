/*
 * Copyright (C) 2013 - 2019 Oracle and/or its affiliates. All rights reserved.
 */
package oracle.pgql.lang.completions;

import static oracle.pgql.lang.completion.PgqlCompletionGenerator.completion;
import static oracle.pgql.lang.completion.PgqlCompletionGenerator.completions;

import java.util.List;

import org.junit.Test;

import oracle.pgql.lang.editor.completion.PgqlCompletion;

public class LabelCompletionsTest extends AbstractCompletionsTest {

  @Test
  public void testVertexLabelsGraphG() throws Exception {

    List<PgqlCompletion> expected = completions(//
        completion("Person", "vertex label"), //
        completion("Student", "vertex label"), //
        completion("Professor", "vertex label"));

    String query = "SELECT n.name FROM g MATCH (n:???)";
    check(query, expected);

    query = "SELECT n.name FROM g MATCH (n) -> (m:???";
    check(query, expected);

    query = "SELECT n.name FROM g MATCH (n) -> (m:??? GROUP BY n";
    check(query, expected);
  }

  @Test
  public void testEdgeLabelsGraphG() throws Exception {

    List<PgqlCompletion> expected = completions(//
        completion("likes", "edge label"), //
        completion("knows", "edge label"));

    String query = "SELECT e.weight FROM g MATCH () -[e:???]-> ()";
    check(query, expected);

    query = "SELECT e.weight FROM g MATCH () -[e:???";
    check(query, expected);

    query = "SELECT e.weight FROM g MATCH () -[e:??? GROUP BY e";
    check(query, expected);
  }

  @Test
  public void testVertexLabelsDefaultGraph() throws Exception {

    List<PgqlCompletion> expected = completions(//
        completion("Function", "vertex label"), //
        completion("Variable", "vertex label"));

    String query = "SELECT n.name MATCH (n:???)";
    check(query, expected);

    query = "SELECT n.name MATCH (n) -> (m:???";
    check(query, expected);

    query = "SELECT n.name MATCH (n) -> (m:??? GROUP BY n";
    check(query, expected);
  }

  @Test
  public void testEdgeLabelsDefaultGraph() throws Exception {

    List<PgqlCompletion> expected = completions(//
        completion("calls", "edge label"));

    String query = "SELECT e.weight MATCH () -[e:???]-> ()";
    check(query, expected);

    query = "SELECT e.weight MATCH () -[e:???";
    check(query, expected);

    query = "SELECT e.weight MATCH () -[e:??? GROUP BY e";
    check(query, expected);
  }
}