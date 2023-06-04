package generico.utils;

import java.util.List;

public class ConfusionMatrixUtils {
    public static void confusionMatrixForBinary(final List<Double> result, final List<Double> expected) {
        int tp = 0;
        int tn = 0;
        int fp = 0;
        int fn = 0;

        final var resultIte = result.iterator();
        final var resultExp = expected.iterator();
        while(resultIte.hasNext()) {
            final var r = resultIte.next();
            final var e = resultExp.next();

            if(e > 0) {
                if (r > 0) tp++; // e > 0 && r > 0
                else fn++; // e > 0 && r <= 0
            } else { // e <= 0
                if (r < 0) tn++; // e <= 0 && r < 0
                else fp++; // e <= 0 && r >= 0
            }
        }

        printConfusionMatrix(tp, tn, fp, fn);
    }

    public static void printConfusionMatrix(final int tp, final int tn, final int fp, final int fn) {
        // Column1
        final String column1 = "Predicted -1";

        int column1Size = Math.max(column1.length(), (tp + "").length());
        column1Size = Math.max(column1Size, (fn + "").length());

        // Column2
        final String column2 = "Predicted  1";

        int column2Size = Math.max(column2.length(), (fp + "").length());
        column2Size = Math.max(column2Size, (tn + "").length());

        // Print
        final String row1 = "Actual -1";
        final String row2 = "Actual  1";
        System.out.println(padLeft("", row1.length()) + " | " + padLeft(column1, column1Size) + " | " + padLeft(column2, column2Size) + " |");
        System.out.println(row1 + " | " + padLeft(tn, column1Size) + " | " + padLeft(fp, column2Size) + " |");
        System.out.println(row2 + " | " + padLeft(fn, column1Size) + " | " + padLeft(tp, column2Size) + " |");
    }

    private static String padLeft(final int str, final int espacos) {
        return padLeft(str + "", espacos);
    }

    private static String padLeft(final String str, final int espacos) {
        StringBuilder result = new StringBuilder(str);

        for(int i = 0; i < espacos - str.length(); i++)
            result.insert(0, " ");

        return result.toString();
    }
}
