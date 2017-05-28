package com.sarojaba.prettytable4j;

public class GfmConverter implements Converter {

    /*
     * https://help.github.com/articles/organizing-information-with-tables/
     */
    @Override
    public String convert(PrettyTable pt) {

        // Check empty
        if (pt.fieldNames.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("| ");

        for (int i = 0; i < pt.fieldNames.size(); i++) {
            sb.append(pt.fieldNames.get(i));

            if (i < pt.fieldNames.size() - 1) {
                sb.append(" | ");
            } else {
                sb.append(" |");
            }
        }

        sb.append("\n");

        sb.append("| ");

        for (int i = 0; i < pt.fieldNames.size(); i++) {
            sb.append("---");

            if (i < pt.fieldNames.size() - 1) {
                sb.append(" | ");
            } else {
                sb.append(" |");
            }
        }

        // Convert rows to table
        pt.rows.forEach(r -> {
            sb.append("\n");
            sb.append("| ");

            for (int c = 0; c < r.length; c++) {

                sb.append(r[c].toString());

                if (c < r.length - 1) {
                    sb.append(" | ");
                } else {
                    sb.append(" |");
                }
            }
        });

        return sb.toString();
    }
}
