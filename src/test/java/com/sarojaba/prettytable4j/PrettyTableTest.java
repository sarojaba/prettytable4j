package com.sarojaba.prettytable4j;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrettyTableTest {

    @Test
    public void testEmpty() {

        PrettyTable pt = PrettyTable.fieldNames();

        assertEquals("", pt.toString());
    }

    @Test
    public void testCreate() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles");

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
    public void testFieldNames() {

        PrettyTable pt = PrettyTable
                .fieldNames("City name", "Area", "Population", "ann");

        assertEquals(
                "+-----------+------+------------+-----+\n" +
                "| City name | Area | Population | ann |\n" +
                "+-----------+------+------------+-----+\n" +
                "+-----------+------+------------+-----+", pt.toString());
    }

    @Test
    public void testAddRow() {

        PrettyTable pt = PrettyTable
                .fieldNames("City name", "Area", "Population", "ann")
                .addRow("Adelaide",1295, 1158259, 600.5)
                .addRow("Brisbane",5905, 1857594, 1146.4)
                .addRow("Darwin", 112, 120900, 1714.7)
                .addRow("Hobart", 1357, 205556, 619.5)
                .addRow("Sydney", 2058, 4336374, 1214.8)
                .addRow("Melbourne", 1566, 3806092, 646.9)
                .addRow("Perth", 5386, 1554769, 869.4);

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
    public void testComma() {

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
    public void testSortTableByInteger() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .sortTable("age");

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
    public void testSortTableByIntegerReverse() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .sortTable("age", true);

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
    public void testSortTableByFloat() {

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
    public void testDeleteRow() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles");

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
    public void testClearTable() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles");

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
    public void testDeleteTable() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles");

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
    public void testBorderless() {

        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .border(false);

        assertEquals(
                "name      age city       \n" +
                "john       22 new york   \n" +
                "elizabeth  43 chicago    \n" +
                "bill       31 atlanta    \n" +
                "mary       18 los angeles", pt.toString());
    }
}
