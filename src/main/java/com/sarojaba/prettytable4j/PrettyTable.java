package com.sarojaba.prettytable4j;

import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrettyTable {

    private List<String> fieldNames = new ArrayList<>();

    private List<Object[]> rows = new ArrayList<>();

    private boolean comma = false;

    private boolean border = true;

    private PrettyTable(String... fieldNames) {
        this.fieldNames.addAll(Arrays.asList(fieldNames));
    }

    public static PrettyTable fieldNames(String... fieldNames) {
        return new PrettyTable(fieldNames);
    }

    public PrettyTable addRow(Object... row) {
        this.rows.add(row);
        return this;
    }

    public PrettyTable deleteRow(int num) {
        this.rows.remove(num - 1);
        return this;
    }

    public PrettyTable comma(boolean comma) {
        this.comma = comma;
        return this;
    }

    public PrettyTable border(boolean border) {
        this.border = border;
        return this;
    }

    public PrettyTable sortTable(String fieldName) {
        return sortTable(fieldName, false);
    }

    public PrettyTable sortTable(String fieldName, boolean reverse) {
        int idx = Collections.binarySearch(fieldNames, fieldName);

        rows.sort(new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {

                if (o1[idx] instanceof Comparable && o2[idx] instanceof Comparable) {
                    int c = ((Comparable) o1[idx]).compareTo((Comparable) o2[idx]);
                    return c * (reverse ? -1 : 1);
                }
                return 0;
            }
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

        int[] maxWidth = adjustMaxWidth();

        StringBuilder sb = new StringBuilder();

        String line = line(maxWidth);

        // Draw top border line
        if (border) {
            sb.append(line);
            sb.append("\n");
        }

        // Convert headers to table
        if (border) {
            sb.append("|");
        }

        for (int i = 0; i < fieldNames.size(); i++) {
            String nh = StringUtils.rightPad(fieldNames.get(i), maxWidth[i]);
            sb.append(String.format(border ? " %s " : "%s", nh));

            if(i < fieldNames.size() - 1) {
                sb.append(border ? "|" : " ");
            }
        }

        // Draw line
        if (border) {
            sb.append("\n");
            sb.append(line);
        }

        // Convert rows to table
        rows.forEach(r -> {
            sb.append("\n");

            if (border) {
                sb.append("|");
            }

            for (int c = 0; c < r.length; c++) {

                String nc;
                if (r[c] instanceof Number) {
                    String n = comma ?
                            NumberFormat.getNumberInstance(Locale.US).format(r[c]):
                            r[c].toString();
                    nc = StringUtils.leftPad(n, maxWidth[c]);
                } else {
                    nc = StringUtils.rightPad(r[c].toString(), maxWidth[c]);
                }

                sb.append(String.format(border ? " %s " : "%s", nc));

                if(c < r.length - 1) {
                    sb.append(border ? "|": " ");
                }
            }
        });

        // Draw bottom border line
        if (border) {
            sb.append("\n");
            sb.append(line);
        }

        return sb.toString();
    }

    /*
     * Adjust for max width of the column
     */
    private int[] adjustMaxWidth() {

        // Adjust comma
        List<List<String>> converted = rows.stream()
                .map(r -> Stream.of(r).map(o -> {
                    if (comma && o instanceof Number) {
                        return NumberFormat.getNumberInstance(Locale.US).format(o);
                    } else {
                        return o.toString();
                    }
                }).collect(Collectors.toList())).collect(Collectors.toList());

        return IntStream.range(0, fieldNames.size())
                .map(i -> {
                    int n = converted.stream()
                            .map(f -> f.get(i).length())
                            .max(Comparator.naturalOrder())
                            .orElse(0);
                    return Math.max(fieldNames.get(i).length(), n);
                }).toArray();
    }

    private String line(int[] maxWidth) {

        StringBuilder sb = new StringBuilder();

        sb.append("+");
        for (int i = 0; i < fieldNames.size(); i++) {
            sb.append(StringUtils.rightPad("", maxWidth[i] + 2, '-'));
            sb.append("+");
        }
        return sb.toString();
    }
}
