package util;

import generico.dto.InputTreinamento;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DataSplitter {
    public static List<InputTreinamento> splitData(final String dataPath, final int entrySize) {
        final List<InputTreinamento> result = new LinkedList<>();
        final Scanner sc;

        try {
            sc = new Scanner(new File(dataPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNext()) {
            final var s = sc.nextLine();
            final var arr = s.split(",");

            final List<Double> entry = new LinkedList<>();
            for(int i = 0; i < entrySize; i++)
                entry.add(Double.parseDouble(arr[i]));

            final List<Double> expected = new LinkedList<>();
            for(int i = entrySize; i < arr.length; i++)
                    expected.add(Double.parseDouble(arr[i]));

            result.add(new InputTreinamento(entry, expected));
        }
        sc.close();

        return result;
    }
}
