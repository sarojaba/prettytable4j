package com.sarojaba.prettytable4j;

import com.sarojaba.prettytable4j.converter.ConsoleConverter;
import com.sarojaba.prettytable4j.parser.JsonParser;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * PrettyTable class.
 */
public class PrettyTable {

    public List<String> fieldNames = new ArrayList<>();

    public List<Object[]> rows = new ArrayList<>();

    public boolean comma = false;

    public boolean border = true;

    public boolean color = false;

    public String fontColor = "DEFAULT";

    public String borderColor = "DEFAULT";

    private Parser parser = new JsonParser();

    private Converter converter = new ConsoleConverter();

    /**
     * @param fieldNames
     * @return
     */
    public static PrettyTable fieldNames(final String... fieldNames) {
        PrettyTable pt = new PrettyTable();
        pt.fieldNames.addAll(Arrays.asList(fieldNames));
        return pt;
    }

    public static PrettyTable fieldNames(final Iterator<String> fieldNames) {
        PrettyTable pt = new PrettyTable();
        fieldNames.forEachRemaining(pt.fieldNames::add);
        return pt;
    }

    public PrettyTable addRow(final Object... row) {
        this.rows.add(row);
        return this;
    }

    public PrettyTable deleteRow(final int num) {
        this.rows.remove(num - 1);
        return this;
    }

    public PrettyTable addField(final String fieldName) {
        this.fieldNames.add(fieldName);
        return this;
    }

    public PrettyTable comma(final boolean comma) {
        this.comma = comma;
        return this;
    }

    public PrettyTable border(final boolean border) {
        this.border = border;
        return this;
    }

    public PrettyTable color(final boolean color) {
        this.color = color;
        return this;
    }

    public PrettyTable fontColor(final String colorName) {
        this.fontColor = colorName;
        return this;
    }

    public PrettyTable borderColor(final String colorName) {
        this.borderColor = colorName;
        return this;
    }

    public static PrettyTable parser(final Parser parser) {
        PrettyTable pt = new PrettyTable();
        pt.parser = parser;
        return pt;
    }

    public PrettyTable fromString(String text) throws IOException {
        PrettyTable pt = parser.parse(text);
        return pt;
    }

    public PrettyTable converter(final Converter converter) {
        this.converter = converter;
        return this;
    }

    public PrettyTable sortTable(final String fieldName) {
        return sortTable(fieldName, false);
    }

    public PrettyTable sortTable(final String fieldName, final boolean reverse) {
        int idx = Collections.binarySearch(fieldNames, fieldName);

        rows.sort((o1, o2) -> {

            if (o1[idx] instanceof Comparable
                    && o2[idx] instanceof Comparable) {
                int c = ((Comparable) o1[idx]).compareTo(o2[idx]);
                return c * (reverse ? -1 : 1);
            }
            return 0;
        });

        return this;
    }

    public PrettyTable clearTable() {
        rows.clear();
        return this;
    }

    public PrettyTable deleteTable() {
        rows.clear();
        fieldNames.clear();
        return this;
    }

    @Override
    public String toString() {
        return converter.convert(this);
    }

    /*
     * Adjust for max width of the column
     */
    public int[] adjustMaxWidth() {

        // Adjust comma
        List<List<String>> converted = rows.stream()
                .map(r -> Stream.of(r).map(o -> {
                    if (comma && o instanceof Number) {
                        return NumberFormat
                                .getNumberInstance(Locale.US)
                                .format(o);
                    } else {
                        return o.toString();
                    }
                }).collect(Collectors.toList()))
                .collect(Collectors.toList());

        return IntStream.range(0, fieldNames.size())
                .map(i -> {
                    int n = converted.stream()
                            .map(f -> f.get(i).length())
                            .max(Comparator.naturalOrder())
                            .orElse(0);
                    return Math.max(fieldNames.get(i).length(), n);
                }).toArray();
    }
}
