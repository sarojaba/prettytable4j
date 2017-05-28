package com.sarojaba.prettytable4j;

import com.sarojaba.prettytable4j.PrettyTable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsoleConverterTest {

    @Test
    public void empty() {

        PrettyTable pt = PrettyTable.fieldNames();

        ConsoleConverter cvt = new ConsoleConverter();

        assertEquals("", cvt.convert(pt));
    }

    @Test
    public void oneField() {
        PrettyTable pt = PrettyTable
                .fieldNames()
                .addField("name");

        ConsoleConverter cvt = new ConsoleConverter();

        assertEquals(
                "+------+\n" +
                "| name |\n" +
                "+------+\n" +
                "+------+", cvt.convert(pt));
    }

    @Test
    public void oneFieldAndOneRow() {
        PrettyTable pt = PrettyTable.fieldNames()
                .addField("name")
                .addRow("john");

        ConsoleConverter cvt = new ConsoleConverter();

        assertEquals(
                "+------+\n" +
                "| name |\n" +
                "+------+\n" +
                "| john |\n" +
                "+------+", cvt.convert(pt));
    }

    @Test
    public void create() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles");

        ConsoleConverter cvt = new ConsoleConverter();

        assertEquals(
                "+-----------+-----+-------------+\n" +
                "| name      | age | city        |\n" +
                "+-----------+-----+-------------+\n" +
                "| john      |  22 | new york    |\n" +
                "| elizabeth |  43 | chicago     |\n" +
                "| bill      |  31 | atlanta     |\n" +
                "| mary      |  18 | los angeles |\n" +
                "+-----------+-----+-------------+", cvt.convert(pt));
    }

    @Test
    public void fieldNames() {

        PrettyTable pt = PrettyTable
                .fieldNames("City name", "Area", "Population", "ann");

        ConsoleConverter cvt = new ConsoleConverter();

        assertEquals(
                "+-----------+------+------------+-----+\n" +
                "| City name | Area | Population | ann |\n" +
                "+-----------+------+------------+-----+\n" +
                "+-----------+------+------------+-----+", cvt.convert(pt));
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
                .addRow("Perth", 5386, 1554769, 869.4);

        ConsoleConverter cvt = new ConsoleConverter();

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
                "+-----------+------+------------+--------+", cvt.convert(pt));
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
                .comma(true);

        ConsoleConverter cvt = new ConsoleConverter();

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
                "+-----------+-------+------------+---------+", cvt.convert(pt));
    }

    @Test
    public void sortTableByInteger() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .sortTable("age");

        ConsoleConverter cvt = new ConsoleConverter();

        assertEquals(
                "+-----------+-----+-------------+\n" +
                "| name      | age | city        |\n" +
                "+-----------+-----+-------------+\n" +
                "| mary      |  18 | los angeles |\n" +
                "| john      |  22 | new york    |\n" +
                "| bill      |  31 | atlanta     |\n" +
                "| elizabeth |  43 | chicago     |\n" +
                "+-----------+-----+-------------+", cvt.convert(pt));
    }

    @Test
    public void sortTableByIntegerReverse() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .sortTable("age", true);

        ConsoleConverter cvt = new ConsoleConverter();

        assertEquals(
                "+-----------+-----+-------------+\n" +
                "| name      | age | city        |\n" +
                "+-----------+-----+-------------+\n" +
                "| elizabeth |  43 | chicago     |\n" +
                "| bill      |  31 | atlanta     |\n" +
                "| john      |  22 | new york    |\n" +
                "| mary      |  18 | los angeles |\n" +
                "+-----------+-----+-------------+", cvt.convert(pt));
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
                .sortTable("ann");

        ConsoleConverter cvt = new ConsoleConverter();

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
                "+-----------+------+------------+--------+", cvt.convert(pt));
    }

    @Test
    public void deleteRow() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles");

        ConsoleConverter cvt = new ConsoleConverter();

        assertEquals(
                "+-----------+-----+-------------+\n" +
                "| name      | age | city        |\n" +
                "+-----------+-----+-------------+\n" +
                "| john      |  22 | new york    |\n" +
                "| elizabeth |  43 | chicago     |\n" +
                "| bill      |  31 | atlanta     |\n" +
                "| mary      |  18 | los angeles |\n" +
                "+-----------+-----+-------------+", cvt.convert(pt));

        pt.deleteRow(2);
        pt.deleteRow(1);

        assertEquals("+------+-----+-------------+\n" +
                "| name | age | city        |\n" +
                "+------+-----+-------------+\n" +
                "| bill |  31 | atlanta     |\n" +
                "| mary |  18 | los angeles |\n" +
                "+------+-----+-------------+", cvt.convert(pt));
    }

    @Test
    public void clearTable() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles");

        ConsoleConverter cvt = new ConsoleConverter();

        assertEquals(
                "+-----------+-----+-------------+\n" +
                "| name      | age | city        |\n" +
                "+-----------+-----+-------------+\n" +
                "| john      |  22 | new york    |\n" +
                "| elizabeth |  43 | chicago     |\n" +
                "| bill      |  31 | atlanta     |\n" +
                "| mary      |  18 | los angeles |\n" +
                "+-----------+-----+-------------+", cvt.convert(pt));

        pt.clearTable();

        assertEquals(
                "+------+-----+------+\n" +
                "| name | age | city |\n" +
                "+------+-----+------+\n" +
                "+------+-----+------+", cvt.convert(pt));
    }

    @Test
    public void deleteTable() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles");

        ConsoleConverter cvt = new ConsoleConverter();

        assertEquals(
                "+-----------+-----+-------------+\n" +
                "| name      | age | city        |\n" +
                "+-----------+-----+-------------+\n" +
                "| john      |  22 | new york    |\n" +
                "| elizabeth |  43 | chicago     |\n" +
                "| bill      |  31 | atlanta     |\n" +
                "| mary      |  18 | los angeles |\n" +
                "+-----------+-----+-------------+", cvt.convert(pt));

        pt.deleteTable();

        assertEquals("", cvt.convert(pt));
    }

    @Test
    public void borderless() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .border(false);

        ConsoleConverter cvt = new ConsoleConverter();

        assertEquals(
                "name      age city       \n" +
                "john       22 new york   \n" +
                "elizabeth  43 chicago    \n" +
                "bill       31 atlanta    \n" +
                "mary       18 los angeles", cvt.convert(pt));
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
                .borderColor("RED");

        ConsoleConverter cvt = new ConsoleConverter();

        assertEquals(
                "\u001B[31m+-----------+-----+-------------+\n" +
                "\u001B[0;31m| \u001B[0;39mname     \u001B[0;31m | \u001B[0;39mage\u001B[0;31m | \u001B[0;39mcity       \u001B[0;31m |\u001B[0;31m\n" +
                "+-----------+-----+-------------+\u001B[0;31m\n" +
                "\u001B[0;31m| \u001B[0;39mjohn     \u001B[0;31m | \u001B[0;39m 22\u001B[0;31m | \u001B[0;39mnew york   \u001B[0;31m |\u001B[0;31m\n" +
                "\u001B[0;31m| \u001B[0;39melizabeth\u001B[0;31m | \u001B[0;39m 43\u001B[0;31m | \u001B[0;39mchicago    \u001B[0;31m |\u001B[0;31m\n" +
                "\u001B[0;31m| \u001B[0;39mbill     \u001B[0;31m | \u001B[0;39m 31\u001B[0;31m | \u001B[0;39matlanta    \u001B[0;31m |\u001B[0;31m\n" +
                "\u001B[0;31m| \u001B[0;39mmary     \u001B[0;31m | \u001B[0;39m 18\u001B[0;31m | \u001B[0;39mlos angeles\u001B[0;31m |\u001B[0;31m\n" +
                "+-----------+-----+-------------+\u001B[m", cvt.convert(pt));
    }

    @Test
    public void fontColor() {
        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .color(true)
                .borderColor("BLUE");

        ConsoleConverter cvt = new ConsoleConverter();

        assertEquals(
                "\u001B[34m+-----------+-----+-------------+\n" +
                "\u001B[0;34m| \u001B[0;39mname     \u001B[0;34m | \u001B[0;39mage\u001B[0;34m | \u001B[0;39mcity       \u001B[0;34m |\u001B[0;34m\n" +
                "+-----------+-----+-------------+\u001B[0;34m\n" +
                "\u001B[0;34m| \u001B[0;39mjohn     \u001B[0;34m | \u001B[0;39m 22\u001B[0;34m | \u001B[0;39mnew york   \u001B[0;34m |\u001B[0;34m\n" +
                "\u001B[0;34m| \u001B[0;39melizabeth\u001B[0;34m | \u001B[0;39m 43\u001B[0;34m | \u001B[0;39mchicago    \u001B[0;34m |\u001B[0;34m\n" +
                "\u001B[0;34m| \u001B[0;39mbill     \u001B[0;34m | \u001B[0;39m 31\u001B[0;34m | \u001B[0;39matlanta    \u001B[0;34m |\u001B[0;34m\n" +
                "\u001B[0;34m| \u001B[0;39mmary     \u001B[0;34m | \u001B[0;39m 18\u001B[0;34m | \u001B[0;39mlos angeles\u001B[0;34m |\u001B[0;34m\n" +
                "+-----------+-----+-------------+\u001B[m", cvt.convert(pt));
    }
}
