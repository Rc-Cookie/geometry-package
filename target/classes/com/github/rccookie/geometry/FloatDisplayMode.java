package com.github.rccookie.geometry;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public enum FloatDisplayMode {
    FULL() {
        @Override
        public void format(double number, StringBuilder string) {
            string.append(number);
        }
    },
    ROUNDED() {
        final DecimalFormat formatter = new DecimalFormat("#.###", new DecimalFormatSymbols(Locale.US));
        { formatter.setRoundingMode(RoundingMode.HALF_UP); }
        @Override
        public void format(double number, StringBuilder string) {
            string.append(formatter.format(number));
        }
    },
    ROUNDED_INDICATED() {
        final DecimalFormat formatter = new DecimalFormat("#.###", new DecimalFormatSymbols(Locale.US));
        { formatter.setRoundingMode(RoundingMode.HALF_UP); }
        @Override
        public void format(double number, StringBuilder string) {
            String rounded = formatter.format(number);
            if(Double.parseDouble(rounded) != number)
                string.append("\u2248");
            string.append(rounded);
        }
    },
    ROUNDED_DIRECTION() {
        final DecimalFormat formatter = new DecimalFormat("#.###", new DecimalFormatSymbols(Locale.US));
        { formatter.setRoundingMode(RoundingMode.HALF_UP); }
        @Override
        public void format(double number, StringBuilder string) {
            String rounded = formatter.format(number);
            double roundedNum = Double.parseDouble(rounded);
            if(roundedNum != number)
                string.append(roundedNum < number ? ">" : "<");
            string.append(rounded);
        }
    };

    public abstract void format(double number, StringBuilder string);
}
