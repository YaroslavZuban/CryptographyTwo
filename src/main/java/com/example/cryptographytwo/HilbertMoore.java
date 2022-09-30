package com.example.cryptographytwo;

import java.util.ArrayList;
import java.util.List;

public class HilbertMoore {

    private List<Double> probabilities;
    private List<Integer> sizeCode = new ArrayList<>();

    public HilbertMoore(List<Double> probabilities){
        this.probabilities=probabilities;
    }

    public Double middleWord(){
        double l=0;

        for (int i = 0; i < probabilities.size(); i++) {
            l+=probabilities.get(i)*sizeCode.get(i);
        }

        return l;
    }


    public Double entropy(){
        double Hmax=Math.log10(probabilities.size())/Math.log10(2);

        double H=0;

        for (int i = 0; i < probabilities.size(); i++) {
            H+=probabilities.get(i)*Math.log10(probabilities.get(i))/Math.log10(2);
        }

        H*=-1;

        return 1-H/Hmax;
    }


    public String inequalityKrafts(){
        double sum=0;

        for (int i = 0; i < sizeCode.size(); i++) {
            sum+=Math.pow(2,-sizeCode.get(i));
        }

        if(sum<=1){
            return "выполняется ("+sum+")";
        }

        return "не выполняется ("+sum+")";
    }

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
