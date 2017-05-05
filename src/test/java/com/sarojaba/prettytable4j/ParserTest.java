package com.sarojaba.prettytable4j;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ParserTest {

    @Test
    public void testEmptyObject() {
        try {
            PrettyTable pt = Parser.parseJson("{}");
            assertEquals(
                    "+------+-------+\n" +
                    "| Name | Value |\n" +
                    "+------+-------+\n" +
                    "+------+-------+", pt.toString());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testEmptyArray() {
        try {
            PrettyTable pt = Parser.parseJson("[]");
            assertEquals("", pt.toString());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testEmptyObjectInArray() {
        try {
            PrettyTable pt = Parser.parseJson("[{}]");
            assertEquals("", pt.toString());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testObject() {
        try {
            PrettyTable pt = Parser.parseJson(
                    "{\n" +
                    "  \"name\": \"john\",\n" +
                    "  \"age\": 22,\n" +
                    "  \"city\": \"new york\"\n" +
                    "}");
            assertEquals(
                    "+------+----------+\n" +
                    "| Name | Value    |\n" +
                    "+------+----------+\n" +
                    "| name | john     |\n" +
                    "| age  |       22 |\n" +
                    "| city | new york |\n" +
                    "+------+----------+", pt.toString());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testFloat() {
        try {
            PrettyTable pt = Parser.parseJson(
                    "{\n" +
                    "  \"City name\": \"Adelaide\",\n" +
                    "  \"Area\": 1295,\n" +
                    "  \"Population\": 1158259,\n" +
                    "  \"ann\": 600.5\n" +
                    "}");
            assertEquals(
                    "+------------+----------+\n" +
                    "| Name       | Value    |\n" +
                    "+------------+----------+\n" +
                    "| City name  | Adelaide |\n" +
                    "| Area       |     1295 |\n" +
                    "| Population |  1158259 |\n" +
                    "| ann        |    600.5 |\n" +
                    "+------------+----------+", pt.toString());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testArray() {
        try {
            PrettyTable pt = Parser.parseJson(
                    "[{\n" +
                    "  \"name\": \"john\",\n" +
                    "  \"age\": 22,\n" +
                    "  \"city\": \"new york\"\n" +
                    "},{" +
                    "  \"name\": \"elizabeth\",\n" +
                    "  \"age\": 43,\n" +
                    "  \"city\": \"chicago\"\n" +
                    "}]");
            assertEquals(
                    "+-----------+-----+----------+\n" +
                    "| name      | age | city     |\n" +
                    "+-----------+-----+----------+\n" +
                    "| john      |  22 | new york |\n" +
                    "| elizabeth |  43 | chicago  |\n" +
                    "+-----------+-----+----------+", pt.toString());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testComma() {
        try {
            PrettyTable pt = Parser.parseJson(
                    "{\n" +
                    "  \"City name\": \"Adelaide\",\n" +
                    "  \"Area\": 1295,\n" +
                    "  \"Population\": 1158259,\n" +
                    "  \"ann\": 600.5\n" +
                    "}")
                    .comma(true);
            assertEquals(
                    "+------------+-----------+\n" +
                    "| Name       | Value     |\n" +
                    "+------------+-----------+\n" +
                    "| City name  | Adelaide  |\n" +
                    "| Area       |     1,295 |\n" +
                    "| Population | 1,158,259 |\n" +
                    "| ann        |     600.5 |\n" +
                    "+------------+-----------+", pt.toString());
        } catch (IOException e) {
            fail();
        }
    }
}
