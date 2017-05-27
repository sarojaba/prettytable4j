package com.sarojaba.prettytable4j;

import org.apache.commons.lang3.StringUtils;
import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

/**
 * Stylist class.
 */
public final class Stylist {

    private Ansi ansi;

    private boolean border;

    private Color fontColor;

    private Color borderColor;

    private Stylist() {
        ansi = ansi();
    }

    public static Stylist of() {
        return new Stylist();
    }

    public Stylist border(final boolean border) {
        this.border = border;
        return this;
    }

    public Stylist fontColor(final Color font) {
        this.fontColor = font;
        return this;
    }

    public Stylist fontColor(final String colorName) {
        return fontColor(valueOf(colorName));
    }

    public Stylist borderColor(final Color border) {
        this.borderColor = border;
        return this;
    }

    public Stylist borderColor(final String colorName) {
        return borderColor(valueOf(colorName));
    }

    public Stylist af(final String value) {

        if (fontColor == null) {
            return plain(value);
        }

        ansi.fg(fontColor);
        ansi.a(value);
        ansi.reset();

        return this;
    }

    public Stylist ab(final String value) {

        if (borderColor == null) {
            return plain(value);
        }

        ansi.fg(borderColor);
        ansi.a(value);
        ansi.reset();

        return this;
    }

    public Stylist plain(final String value) {
        ansi.a(value);
        return this;
    }

    public Stylist topBorderLine(final int[] maxWidth) {
        return ab(border ? line(maxWidth) + "\n" : "");
    }

    public Stylist bottomBorderLine(final int[] maxWidth) {
        return ab(border ? "\n" + line(maxWidth) : "");
    }

    public Stylist leftBorder() {
        return ab(border ? "| " : "");
    }

    public Stylist rightBorder() {
        return ab(border ? " |" : "");
    }

    public Stylist centerBorder() {
        return ab(border ? " | " : " ");
    }

    private String line(final int[] maxWidth) {

        final StringBuilder sb = new StringBuilder();

        sb.append("+");
        for (int i = 0; i < maxWidth.length; i++) {
            sb.append(StringUtils.rightPad("", maxWidth[i] + 2, '-'));
            sb.append("+");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return ansi.toString();
    }
}
