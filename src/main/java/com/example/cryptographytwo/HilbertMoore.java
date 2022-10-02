package com.example.cryptographytwo;

import java.util.ArrayList;
import java.util.List;
/**Класс который работает с алгоритмом Гильбурда - Мура
 * вычиляется:
 * избыточность
 * Неравенство Крафта
 * Длинна среднего слова
 * Есть приватные методы которые переводят, вещественное число из десятичной системы счисления в двоичную**/

public class HilbertMoore {

    public static List<Double> probabilities = new ArrayList<>();//список вещественных чисел
    public static List<Integer> sizeCode = new ArrayList<>();//список целочисленных

    public static List<String> symbol = new ArrayList<>();//список строковых
    public static List<String> encoded = new ArrayList<>();//список строковых


    public static Double middleWord() {//методы вычисляет длинну среднюю длинну слова
        double l = 0;

        for (int i = 0; i < probabilities.size(); i++) {
            l += probabilities.get(i) *sizeCode.get(i);// (Math.log10(probabilities.get(i)) / Math.log10(2));
        }

        return l;
    }

    public static Double redundancy() {//метод вычиляет избыточность
        double H = 0;

        for (int i = 0; i < probabilities.size(); i++) {
            H += probabilities.get(i) * (Math.log10(probabilities.get(i)) / Math.log10(2));
        }

        return HilbertMoore.middleWord()- (-1) *H;
    }


    public static String inequalityKrafts() {//метод неравенство крафта
        double sum = 0;

        for (int i = 0; i < sizeCode.size(); i++) {
            sum += Math.pow(2, -sizeCode.get(i));
        }

        if (sum <= 1) {
            return "выполняется (" + sum + ")";
        }

        return "не выполняется (" + sum + ")";
    }

    public static void algorithm(List<Double> symbolProbabilities) {//алгоритм Гильберта - Мура
        List<Double> cumulativeProbability = new ArrayList<>();

        double temp = 0;

        for (int i = 0; i < symbolProbabilities.size(); i++) {
            double sum = temp + symbolProbabilities.get(i) / 2;
            cumulativeProbability.add(i, sum);

            temp = temp + symbolProbabilities.get(i);

            double logTwo = -Math.log10(symbolProbabilities.get(i)) / Math.log10(2);
            sizeCode.add(i, (int) Math.ceil(logTwo) + 1);
        }

        for (int i = 0; i < symbolProbabilities.size(); i++) {
            encoded.add(toBinary(cumulativeProbability.get(i), sizeCode.get(i)));//Перевод из 10 СС в 2
        }
    }


    private static String toBinary(double d, int precision) {
        long wholePart = (long) d;
        return fractionalToBinary(d - wholePart, precision);
    }

    private static String fractionalToBinary(double num, int precision) {
        StringBuilder binary = new StringBuilder();

        while (binary.length() < precision) {
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
