package com.sarojaba.prettytable4j.converter;

import com.sarojaba.prettytable4j.PrettyTable;
import com.sarojaba.prettytable4j.converter.ConsoleConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsoleConverterTest {

    @Test
    public void empty() {
        PrettyTable pt = PrettyTable
                .fieldNames()
                .converter(new PlainConsoleConverter());

        assertEquals("", pt.toString());
    }

    @Test
    public void oneField() {
        PrettyTable pt = PrettyTable
                .fieldNames()
                .addField("name")
                .converter(new PlainConsoleConverter());

        assertEquals(
                "+------+\n" +
                "| name |\n" +
                "+------+\n" +
                "+------+", pt.toString());
    }

    @Test
    public void oneFieldAndOneRow() {
        PrettyTable pt = PrettyTable.fieldNames()
                .addField("name")
                .addRow("john")
                .converter(new PlainConsoleConverter());

        assertEquals(
                "+------+\n" +
                "| name |\n" +
                "+------+\n" +
                "| john |\n" +
                "+------+", pt.toString());
    }

    @Test
    public void create() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .converter(new PlainConsoleConverter());

        assertEquals(
                "+-----------+-----+-------------+\n" +
                "| name      | age | city        |\n" +
                "+-----------+-----+-------------+\n" +
                "| john      |  22 | new york    |\n" +
                "| elizabeth |  43 | chicago     |\n" +
                "| bill      |  31 | atlanta     |\n" +
                "| mary      |  18 | los angeles |\n" +
                "+-----------+-----+-------------+", pt.toString());
    }

    @Test
    public void fieldNames() {

        PrettyTable pt = PrettyTable
                .fieldNames("City name", "Area", "Population", "ann")
                .converter(new PlainConsoleConverter());

        assertEquals(
                "+-----------+------+------------+-----+\n" +
                "| City name | Area | Population | ann |\n" +
                "+-----------+------+------------+-----+\n" +
                "+-----------+------+------------+-----+", pt.toString());
    }

    @Test
    public void addRow() {

        PrettyTable pt = PrettyTable
                .fieldNames("City name", "Area", "Population", "ann")
                .addRow("Adelaide",1295, 1158259, 600.5)
                .addRow("Brisbane",5905, 1857594, 1146.4)
                .addRow("Darwin", 112, 120900, 1714.7)
                .addRow("Hobart", 1357, 205556, 619.5)
                .addRow("Sydney", 2058, 4336374, 1214.8)
                .addRow("Melbourne", 1566, 3806092, 646.9)
                .addRow("Perth", 5386, 1554769, 869.4)
                .converter(new PlainConsoleConverter());

        assertEquals(
                "+-----------+------+------------+--------+\n" +
                "| City name | Area | Population | ann    |\n" +
                "+-----------+------+------------+--------+\n" +
                "| Adelaide  | 1295 |    1158259 |  600.5 |\n" +
                "| Brisbane  | 5905 |    1857594 | 1146.4 |\n" +
                "| Darwin    |  112 |     120900 | 1714.7 |\n" +
                "| Hobart    | 1357 |     205556 |  619.5 |\n" +
                "| Sydney    | 2058 |    4336374 | 1214.8 |\n" +
                "| Melbourne | 1566 |    3806092 |  646.9 |\n" +
                "| Perth     | 5386 |    1554769 |  869.4 |\n" +
                "+-----------+------+------------+--------+", pt.toString());
    }

    @Test
    public void comma() {

        PrettyTable pt = PrettyTable
                .fieldNames("City name", "Area", "Population", "ann")
                .addRow("Adelaide",1295, 1158259, 600.5)
                .addRow("Brisbane",5905, 1857594, 1146.4)
                .addRow("Darwin", 112, 120900, 1714.7)
                .addRow("Hobart", 1357, 205556, 619.5)
                .addRow("Sydney", 2058, 4336374, 1214.8)
                .addRow("Melbourne", 1566, 3806092, 646.9)
                .addRow("Perth", 5386, 1554769, 869.4)
                .comma(true)
                .converter(new PlainConsoleConverter());

        assertEquals(
                "+-----------+-------+------------+---------+\n" +
                "| City name | Area  | Population | ann     |\n" +
                "+-----------+-------+------------+---------+\n" +
                "| Adelaide  | 1,295 |  1,158,259 |   600.5 |\n" +
                "| Brisbane  | 5,905 |  1,857,594 | 1,146.4 |\n" +
                "| Darwin    |   112 |    120,900 | 1,714.7 |\n" +
                "| Hobart    | 1,357 |    205,556 |   619.5 |\n" +
                "| Sydney    | 2,058 |  4,336,374 | 1,214.8 |\n" +
                "| Melbourne | 1,566 |  3,806,092 |   646.9 |\n" +
                "| Perth     | 5,386 |  1,554,769 |   869.4 |\n" +
                "+-----------+-------+------------+---------+", pt.toString());
    }

    @Test
    public void sortTableByInteger() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .sortTable("age")
                .converter(new PlainConsoleConverter());

        assertEquals(
                "+-----------+-----+-------------+\n" +
                "| name      | age | city        |\n" +
                "+-----------+-----+-------------+\n" +
                "| mary      |  18 | los angeles |\n" +
                "| john      |  22 | new york    |\n" +
                "| bill      |  31 | atlanta     |\n" +
                "| elizabeth |  43 | chicago     |\n" +
                "+-----------+-----+-------------+", pt.toString());
    }

    @Test
    public void sortTableByIntegerReverse() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .sortTable("age", true)
                .converter(new PlainConsoleConverter());

        assertEquals(
                "+-----------+-----+-------------+\n" +
                "| name      | age | city        |\n" +
                "+-----------+-----+-------------+\n" +
                "| elizabeth |  43 | chicago     |\n" +
                "| bill      |  31 | atlanta     |\n" +
                "| john      |  22 | new york    |\n" +
                "| mary      |  18 | los angeles |\n" +
                "+-----------+-----+-------------+", pt.toString());
    }

    @Test
    public void sortTableByFloat() {

        PrettyTable pt = PrettyTable
                .fieldNames("City name", "Area", "Population", "ann")
                .addRow("Adelaide",1295, 1158259, 600.5)
                .addRow("Brisbane",5905, 1857594, 1146.4)
                .addRow("Darwin", 112, 120900, 1714.7)
                .addRow("Hobart", 1357, 205556, 619.5)
                .addRow("Sydney", 2058, 4336374, 1214.8)
                .addRow("Melbourne", 1566, 3806092, 646.9)
                .addRow("Perth", 5386, 1554769, 869.4)
                .sortTable("ann")
                .converter(new PlainConsoleConverter());

        assertEquals(
                "+-----------+------+------------+--------+\n" +
                "| City name | Area | Population | ann    |\n" +
                "+-----------+------+------------+--------+\n" +
                "| Adelaide  | 1295 |    1158259 |  600.5 |\n" +
                "| Hobart    | 1357 |     205556 |  619.5 |\n" +
                "| Melbourne | 1566 |    3806092 |  646.9 |\n" +
                "| Perth     | 5386 |    1554769 |  869.4 |\n" +
                "| Brisbane  | 5905 |    1857594 | 1146.4 |\n" +
                "| Sydney    | 2058 |    4336374 | 1214.8 |\n" +
                "| Darwin    |  112 |     120900 | 1714.7 |\n" +
                "+-----------+------+------------+--------+", pt.toString());
    }

    @Test
    public void deleteRow() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .converter(new PlainConsoleConverter());

        assertEquals(
                "+-----------+-----+-------------+\n" +
                "| name      | age | city        |\n" +
                "+-----------+-----+-------------+\n" +
                "| john      |  22 | new york    |\n" +
                "| elizabeth |  43 | chicago     |\n" +
                "| bill      |  31 | atlanta     |\n" +
                "| mary      |  18 | los angeles |\n" +
                "+-----------+-----+-------------+", pt.toString());

        pt.deleteRow(2);
        pt.deleteRow(1);

        assertEquals("+------+-----+-------------+\n" +
                "| name | age | city        |\n" +
                "+------+-----+-------------+\n" +
                "| bill |  31 | atlanta     |\n" +
                "| mary |  18 | los angeles |\n" +
                "+------+-----+-------------+", pt.toString());
    }

    @Test
    public void clearTable() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .converter(new PlainConsoleConverter());

        assertEquals(
                "+-----------+-----+-------------+\n" +
                "| name      | age | city        |\n" +
                "+-----------+-----+-------------+\n" +
                "| john      |  22 | new york    |\n" +
                "| elizabeth |  43 | chicago     |\n" +
                "| bill      |  31 | atlanta     |\n" +
                "| mary      |  18 | los angeles |\n" +
                "+-----------+-----+-------------+", pt.toString());

        pt.clearTable();

        assertEquals(
                "+------+-----+------+\n" +
                "| name | age | city |\n" +
                "+------+-----+------+\n" +
                "+------+-----+------+", pt.toString());
    }

    @Test
    public void deleteTable() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .converter(new PlainConsoleConverter());

        assertEquals(
                "+-----------+-----+-------------+\n" +
                "| name      | age | city        |\n" +
                "+-----------+-----+-------------+\n" +
                "| john      |  22 | new york    |\n" +
                "| elizabeth |  43 | chicago     |\n" +
                "| bill      |  31 | atlanta     |\n" +
                "| mary      |  18 | los angeles |\n" +
                "+-----------+-----+-------------+", pt.toString());

        pt.deleteTable();

        assertEquals("", pt.toString());
    }

    @Test
    public void borderless() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .border(false)
                .converter(new PlainConsoleConverter());

        assertEquals(
                "name      age city       \n" +
                "john       22 new york   \n" +
                "elizabeth  43 chicago    \n" +
                "bill       31 atlanta    \n" +
                "mary       18 los angeles", pt.toString());
    }

    @Test
    public void borderColor() {
        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .color(true)
                .borderColor("RED")
                .converter(new ColoredConsoleConverter());

        assertEquals(
                "\u001B[0;31m+-----------+-----+-------------+\n" +
                "\u001B[0;31m| \u001B[0;39mname     \u001B[0;31m | \u001B[0;39mage\u001B[0;31m | \u001B[0;39mcity       \u001B[0;31m |\u001B[0;31m\n" +
                "+-----------+-----+-------------+\u001B[0;31m\n" +
                "\u001B[0;31m| \u001B[0;39mjohn     \u001B[0;31m | \u001B[0;39m 22\u001B[0;31m | \u001B[0;39mnew york   \u001B[0;31m |\u001B[0;31m\n" +
                "\u001B[0;31m| \u001B[0;39melizabeth\u001B[0;31m | \u001B[0;39m 43\u001B[0;31m | \u001B[0;39mchicago    \u001B[0;31m |\u001B[0;31m\n" +
                "\u001B[0;31m| \u001B[0;39mbill     \u001B[0;31m | \u001B[0;39m 31\u001B[0;31m | \u001B[0;39matlanta    \u001B[0;31m |\u001B[0;31m\n" +
                "\u001B[0;31m| \u001B[0;39mmary     \u001B[0;31m | \u001B[0;39m 18\u001B[0;31m | \u001B[0;39mlos angeles\u001B[0;31m |\u001B[0;31m\n" +
                "+-----------+-----+-------------+\u001B[m", pt.toString());
    }

    @Test
    public void borderColor2() {
        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .color(true)
                .borderColor("BLUE")
                .converter(new ColoredConsoleConverter());

        assertEquals(
                "\u001B[0;34m+-----------+-----+-------------+\n" +
                "\u001B[0;34m| \u001B[0;39mname     \u001B[0;34m | \u001B[0;39mage\u001B[0;34m | \u001B[0;39mcity       \u001B[0;34m |\u001B[0;34m\n" +
                "+-----------+-----+-------------+\u001B[0;34m\n" +
                "\u001B[0;34m| \u001B[0;39mjohn     \u001B[0;34m | \u001B[0;39m 22\u001B[0;34m | \u001B[0;39mnew york   \u001B[0;34m |\u001B[0;34m\n" +
                "\u001B[0;34m| \u001B[0;39melizabeth\u001B[0;34m | \u001B[0;39m 43\u001B[0;34m | \u001B[0;39mchicago    \u001B[0;34m |\u001B[0;34m\n" +
                "\u001B[0;34m| \u001B[0;39mbill     \u001B[0;34m | \u001B[0;39m 31\u001B[0;34m | \u001B[0;39matlanta    \u001B[0;34m |\u001B[0;34m\n" +
                "\u001B[0;34m| \u001B[0;39mmary     \u001B[0;34m | \u001B[0;39m 18\u001B[0;34m | \u001B[0;39mlos angeles\u001B[0;34m |\u001B[0;34m\n" +
                "+-----------+-----+-------------+\u001B[m", pt.toString());
    }
}
