package com.example.cryptographytwo;

import java.util.Arrays;
import java.util.Collections;

public class WorkCipher {
    public static String coding(String line) {
        String temp = "" + line;

        for (int i = 0; i < HilbertMoore.symbol.size(); i++) {
            temp = temp.replaceAll(HilbertMoore.symbol.get(i), "");
        }

        if (!temp.equals("")) {
            return null;
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < HilbertMoore.symbol.size(); i++) {
            String ch=HilbertMoore.symbol.get(i);

            int index = HilbertMoore.symbol.indexOf(ch);
            result.append(HilbertMoore.encoded.get(index));
        }

        return String.valueOf(result);
    }

    public static String decoding(String line) {
        StringBuilder result = new StringBuilder();
        int max= Collections.max(HilbertMoore.sizeCode);
        int i=0;

        while (max*HilbertMoore.sizeCode.size()>i) {
            i++;
            int start = 0;

            for (int j = 0; j < HilbertMoore.encoded.size(); j++) {
                int end = HilbertMoore.sizeCode.get(j);

                if (line.length() >= end && HilbertMoore.encoded.get(j).equals(line.substring(start, end))) {
                    line = line.substring(end);
                    result.append(HilbertMoore.symbol.get(j));
                    break;
                }

            }

            if(line.equals("")){
                break;
            }
        }

        if(!line.equals("")){
           return "Ввели не верные данные!";
        }



        return String.valueOf(result);
    }
}
