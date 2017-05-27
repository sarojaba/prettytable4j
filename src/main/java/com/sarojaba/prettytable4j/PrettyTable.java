package com.sarojaba.prettytable4j;

import org.apache.commons.lang3.StringUtils;

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

    private List<String> fieldNames = new ArrayList<>();

    private List<Object[]> rows = new ArrayList<>();

    private boolean comma = false;

    private boolean border = true;

    private boolean color = false;

    private String fontColor = "DEFAULT";

    private String borderColor = "DEFAULT";

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

        // Check empty
        if (fieldNames.isEmpty()) {
            return "";
        }

        Stylist stylist = Stylist.of()
                .border(border);
        if (color) {
            stylist.fontColor(fontColor)
                    .borderColor(borderColor);
        }

        int[] maxWidth = adjustMaxWidth();

        stylist.topBorderLine(maxWidth);

        stylist.leftBorder();

        for (int i = 0; i < fieldNames.size(); i++) {
            stylist.af(StringUtils.rightPad(fieldNames.get(i), maxWidth[i]));

            if (i < fieldNames.size() - 1) {
                stylist.centerBorder();
            } else {
                stylist.rightBorder();
            }
        }

        stylist.bottomBorderLine(maxWidth);

        // Convert rows to table
        rows.forEach(r -> {
            stylist.ab("\n");
            stylist.leftBorder();

            for (int c = 0; c < r.length; c++) {

                String nc;
                if (r[c] instanceof Number) {
                    String n = comma
                            ? NumberFormat
                            .getNumberInstance(Locale.US)
                            .format(r[c])
                            : r[c].toString();
                    nc = StringUtils.leftPad(n, maxWidth[c]);
                } else {
                    nc = StringUtils.rightPad(r[c].toString(), maxWidth[c]);
                }

                stylist.af(nc);

                if (c < r.length - 1) {
                    stylist.centerBorder();
                } else {
                    stylist.rightBorder();
                }
            }
        });

        stylist.bottomBorderLine(maxWidth);

        return stylist.toString();
    }

    /*
     * Adjust for max width of the column
     */
    private int[] adjustMaxWidth() {

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
