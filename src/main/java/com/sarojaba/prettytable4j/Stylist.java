package com.sarojaba.prettytable4j;

import org.apache.commons.lang3.StringUtils;
import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Stylist {

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

    public Stylist border(boolean border) {
        this.border = border;
        return this;
    }

    public Stylist fontColor(Color font) {
        this.fontColor = font;
        return this;
    }

    public Stylist fontColor(String colorName) {
        return fontColor(valueOf(colorName));
    }

    public Stylist borderColor(Color border) {
        this.borderColor = border;
        return this;
    }

    public Stylist borderColor(String colorName) {
        return borderColor(valueOf(colorName));
    }

    public Stylist af(String value) {

        if (fontColor == null) {
            return plain(value);
        }

        ansi.fg(fontColor);
        ansi.a(value);
        ansi.reset();

        return this;
    }

    public Stylist ab(String value) {

        if (borderColor == null) {
            return plain(value);
        }

        ansi.fg(borderColor);
        ansi.a(value);
        ansi.reset();

        return this;
    }

    public Stylist plain(String value) {
        ansi.a(value);
        return this;
    }

    public Stylist topBorderLine(int[] maxWidth) {
        return ab(border ? line(maxWidth) + "\n" : "");
    }

    public Stylist bottomBorderLine(int[] maxWidth) {
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

    private String line(int[] maxWidth) {

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