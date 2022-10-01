package com.example.cryptographytwo;

import javafx.scene.control.TextArea;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorkingFiles {

    public void writingСipherSymbol(String line){
        try (FileWriter writer = new FileWriter("cipher_of_code.txt", false)) {
            writer.write(line);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writingSymbolCipher(String line){
        try (FileWriter writer = new FileWriter("code_of_cipher.txt", false)) {
            writer.write(line);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String cipher(String files){
        try {
            return  reading(files);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String words(String files){
        try {
            return reading(files);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writingCharactersCipher(List<String> character, List<String> cipher) {
        try (FileWriter writer = new FileWriter("charAndCode.txt", false)) {

            if(HilbertMoore.symbol.size()!=HilbertMoore.probabilities.size()){
                writer.write("Не равное количество символов и вероятности. Проверти файлы!.");
            }else {
                for (int i = 0; i < character.size(); i++) {
                    writer.write(character.get(i) + " - " + cipher.get(i) + "\n");

                }
            }

            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<String> readingCharacter(String files) throws IOException {

        String[] vals = reading(files).split(",");
        return new ArrayList<>(Arrays.asList(vals));
    }

    public List<Double> readingNumber(String files) throws IOException {
        String temp = reading(files);
        String[] vals = temp.split(",");

        if (temp.indexOf('^') != -1 || temp.indexOf('/') != -1) {
            return powerTwo(vals);
        }

        List<Double> probabilities = new ArrayList<>();

        for (String number : vals) {
            probabilities.add(Double.parseDouble(number));
        }

        return probabilities;
    }

    public String readingCode(String files) throws IOException {
        return reading(files);
    }

    private List<Double> powerTwo(String[] vals) {
        List<Double> probabilities = new ArrayList<>();

        for (int i = 0; i < vals.length; i++) {
            if (vals[i].indexOf('^') != -1) {
                if (vals[i].indexOf('-') != -1) {
                    probabilities.add(Math.pow(2, -Integer.parseInt(vals[i].substring(vals[i].indexOf("-") + 1, vals[i].indexOf(")")))));
                } else {
                    probabilities.add(Math.pow(2, Integer.parseInt(vals[i].substring(vals[i].indexOf("(") + 1, vals[i].indexOf(")")))));
                }
            }

            if (vals[i].indexOf('/') != -1) {
                if (vals[i].indexOf('-') != -1) {
                    probabilities.add((double) (-Integer.parseInt(vals[i].substring(vals[i].indexOf("-") + 1, vals[i].indexOf("/"))) / Integer.parseInt(vals[i].substring(vals[i].indexOf("/") + 1))));
                } else {
                    probabilities.add( Integer.parseInt(vals[i].substring(0, vals[i].indexOf("/"))) /(double)Integer.parseInt(vals[i].substring(vals[i].indexOf("/") + 1)));
                }
            }
        }


        return probabilities;
    }

    private String reading(String files) throws IOException {
        File file = new File(files);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;
        String sourceAlphabet = "";


        while ((line = br.readLine()) != null) {
            sourceAlphabet += line;
        }

        br.close();
        fr.close();

        return sourceAlphabet;
    }
}
