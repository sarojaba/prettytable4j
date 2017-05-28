package com.sarojaba.prettytable4j;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GfmConverterTest {

    @Test
    public void markdown() {
        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .converter(new GfmConverter());

        assertEquals(
                "| name | age | city |\n" +
                "| --- | --- | --- |\n" +
                "| john | 22 | new york |\n" +
                "| elizabeth | 43 | chicago |\n" +
                "| bill | 31 | atlanta |\n" +
                "| mary | 18 | los angeles |", pt.toString());
    }
}
