package util;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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

    private static void printConfusionMatrix(final int tp, final int tn, final int fp, final int fn) {
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

    public static void confusionMatrixForMultiClass(final List<Integer> results, final List<Integer> expected,
                                                    final int totalClasses) {
        if(results.size() != expected.size())
            throw new RuntimeException("Tamanho errado das entradas!");

        // Calculando a matriz
        final int[][] confMatrix = new int[totalClasses][totalClasses];
        for(int i = 0; i < totalClasses; i++)
            for(int j = 0; j < totalClasses; j++)
                confMatrix[i][j] = 0;

        int maxValue = -1; // Usado para saber o tamanho maximo das colunas
        int acertos = 0;
        for (int i = 0; i < results.size(); i++) {
            final int qtd = ++confMatrix[expected.get(i)][results.get(i)];

            if (Objects.equals(expected.get(i), results.get(i))) acertos++;
            if (qtd > maxValue) maxValue = qtd;
        }

        // Mostrando a matrix
        printMultiClassConfusionMatrix(confMatrix, (maxValue + "").length());

        System.out.println("Precisão: " + ((double) acertos) / expected.size());
    }

    private static void printMultiClassConfusionMatrix(final int[][] matrix, final int columnSize) {
        // cabecalho
        StringBuilder headerRow = new StringBuilder(padLeft("", columnSize) + " |");
        for(int i = 0; i < matrix.length; i++)
            headerRow.append(" ").append(padLeft(i, columnSize)).append(" |");

        System.out.println(headerRow);

        // demais linhas
        for(int i = 0; i < matrix.length; i++) {
            StringBuilder row = new StringBuilder(padLeft(i, columnSize) + " |");

            for(int j = 0; j < matrix.length; j++)
                row.append(" ").append(padLeft(matrix[j][i], columnSize)).append(" |");

            System.out.println(row);
        }
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
