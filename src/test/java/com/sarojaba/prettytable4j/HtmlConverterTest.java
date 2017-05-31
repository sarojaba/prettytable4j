package com.sarojaba.prettytable4j;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HtmlConverterTest {

    @Test
    public void html() {
        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .converter(new HtmlConverter());

        assertEquals(
                "<table>\n" +
                "  <tr>\n" +
                "    <th>name</th>\n" +
                "    <th>age</th>\n" +
                "    <th>city</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>john</td>\n" +
                "    <td>22</td>\n" +
                "    <td>new york</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>elizabeth</td>\n" +
                "    <td>43</td>\n" +
                "    <td>chicago</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>bill</td>\n" +
                "    <td>31</td>\n" +
                "    <td>atlanta</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>mary</td>\n" +
                "    <td>18</td>\n" +
                "    <td>los angeles</td>\n" +
                "  </tr>\n" +
                "</table>", pt.toString());
    }

    @Test
    public void minified() {
        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("john", 22, "new york")
                .addRow("elizabeth", 43, "chicago")
                .addRow("bill", 31, "atlanta")
                .addRow("mary", 18, "los angeles")
                .converter(new HtmlConverter().minified(true));

        assertEquals(
                "<table>" +
                "<tr><th>name</th><th>age</th><th>city</th></tr>" +
                "<tr><td>john</td><td>22</td><td>new york</td></tr>" +
                "<tr><td>elizabeth</td><td>43</td><td>chicago</td></tr>" +
                "<tr><td>bill</td><td>31</td><td>atlanta</td></tr>" +
                "<tr><td>mary</td><td>18</td><td>los angeles</td></tr>" +
                "</table>", pt.toString());
    }
}
