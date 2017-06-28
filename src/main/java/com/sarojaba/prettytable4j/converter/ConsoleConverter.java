package com.sarojaba.prettytable4j.converter;

import com.sarojaba.prettytable4j.Converter;
import com.sarojaba.prettytable4j.PrettyTable;
import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConsoleConverter implements Converter {
    @Override
    public String convert(PrettyTable pt) {

        // Check empty
        if (pt.fieldNames.isEmpty()) {
            return "";
        }

        Stylist stylist = Stylist.of()
                .border(pt.border);
        if (pt.color) {
            stylist.fontColor(pt.fontColor)
                    .borderColor(pt.borderColor);
        }

        int[] maxWidth = adjustMaxWidth(pt);

        stylist.topBorderLine(maxWidth);

        stylist.leftBorder();

        for (int i = 0; i < pt.fieldNames.size(); i++) {
            stylist.af(StringUtils.rightPad(pt.fieldNames.get(i), maxWidth[i]));

            if (i < pt.fieldNames.size() - 1) {
                stylist.centerBorder();
            } else {
                stylist.rightBorder();
            }
        }

        stylist.bottomBorderLine(maxWidth);

        // Convert rows to table
        pt.rows.forEach(r -> {
            stylist.ab("\n");
            stylist.leftBorder();

            for (int c = 0; c < r.length; c++) {

                String nc;
                if (r[c] instanceof Number) {
                    String n = pt.comma
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
    public int[] adjustMaxWidth(PrettyTable pt) {

        // Adjust comma
        List<List<String>> converted = pt.rows.stream()
                .map(r -> Stream.of(r).map(o -> {
                    if (pt.comma && o instanceof Number) {
                        return NumberFormat
                                .getNumberInstance(Locale.US)
                                .format(o);
                    } else {
                        return o.toString();
                    }
                }).collect(Collectors.toList()))
                .collect(Collectors.toList());

        return IntStream.range(0, pt.fieldNames.size())
                .map(i -> {
                    int n = converted.stream()
                            .map(f -> f.get(i).length())
                            .max(Comparator.naturalOrder())
                            .orElse(0);
                    return Math.max(pt.fieldNames.get(i).length(), n);
                }).toArray();
    }
}
