package com.example.cryptographytwo;

import java.util.ArrayList;
import java.util.List;

public class HilbertMoore {
    public static void algorithm(int size, List<Double> symbolProbabilities) {
        List<Integer> sizeCode = new ArrayList<>();
        List<Double> cumulativeProbability = new ArrayList<>();
        List<String> encoded = new ArrayList<>();

        double temp = 0;

        for (int i = 0; i < size; i++) {
            double sum = temp + symbolProbabilities.get(i) / 2;
            cumulativeProbability.add(i, sum);

            temp = temp + symbolProbabilities.get(i);

            double logTwo = -Math.log10(symbolProbabilities.get(i)) / Math.log10(2);
            sizeCode.add(i, (int) Math.ceil(logTwo));
        }

        for (int i = 0; i < size; i++) {
            encoded.add(toBinary(cumulativeProbability.get(i), sizeCode.get(i)));
        }
    }


    public static String toBinary(double d, int precision) {
        long wholePart = (long) d;
        return fractionalToBinary(d - wholePart, precision);
    }

    private static String fractionalToBinary(double num, int precision) {
        StringBuilder binary = new StringBuilder();
        while (num > 0 && binary.length() < precision) {
            double r = num * 2;
            if (r >= 1) {
                binary.append(1);
                num = r - 1;
            } else {
                binary.append(0);
                num = r;
            }
        }
        return binary.toString();
    }

}
