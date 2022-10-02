package com.example.cryptographytwo;

import javafx.scene.control.TextArea;

import java.io.*;
import java.util.*;

/**Класс разработал для того чтобы реализовать чтения/запись**/
public class WorkingFiles {

    public void writingСipherSymbol(String line){//данный метод записывает в файл декодированный код
        try (FileWriter writer = new FileWriter(System.getProperty("user.home") + "/Desktop/"+"cipher_of_code.txt", false)) {//создаем файл на рабочем столе cipher_of_code.txt и указываем что его можно перезаписать
            writer.write(line);//записываем в файл строку
            writer.flush();//закрывает файл
        } catch (IOException ex) {
            System.out.println(ex.getMessage());//вывод об ошибках
        }
    }
    public void writingSymbolCipher(String line){//данный метод записывает ф файл кодированный данные
        try (FileWriter writer = new FileWriter(System.getProperty("user.home") + "/Desktop/"+"code_of_cipher.txt", false)) {//создаем файл на рабочем столе code_of_cipher.txt и указываем что его можно перезаписать
            writer.write(line);//записываем в файл строку
            writer.flush();//закрывает файл
        } catch (IOException ex) {
            System.out.println(ex.getMessage());//вывод об ошибках
        }
    }
    public String cipher(String files){//метод создан чтобы выдавать строку которая считанная с файла, код
        try {
            return  reading(files);//выдает полученную строку из считанного файла
        } catch (IOException e) {
            throw new RuntimeException(e);//выдает ошибку
        }
    }

    public String words(String files){//метод создан чтобы выдавать строку которая считанная с файла, слова
        try {
            return reading(files);//выдает полученную строку из считанного файла
        } catch (IOException e) {
            throw new RuntimeException(e);//выдает ошибку
        }
    }

    public void writingCharactersCipher(List<String> character, List<String> cipher) {//метод создан для того чтобы записать в файл какой символ соответсвует какой кодировки
        try (FileWriter writer = new FileWriter(System.getProperty("user.home") + "/Desktop/"+"charAndCode.txt", false)) {//создаем файл на рабочем столе и не разрешаем его перезаписывать его

            if(HilbertMoore.symbol.size()!=HilbertMoore.probabilities.size()){//сравниваем если количество символов и вероятности не равны
                writer.write("Не равное количество символов и вероятности. Проверти файлы!.");//файл запишится , что не верно получилось, что-то
            }else {
                for (int i = 0; i < character.size(); i++) {//цикл начинается от нуля и заканчивается когда количетсво символво закончится
                    writer.write(character.get(i) + " - " + cipher.get(i) + "\n");//записываем какой символ соответсвует какому коду
                }
            }

            writer.flush();//закрываем файл
        } catch (IOException ex) {//обработка исключений
            System.out.println(ex.getMessage());//вывод ошибки в консоль
        }
    }

    public List<String> readingCharacter(String files) throws IOException {//функция считывает символы и выдает исключения если что-то не так пойдет

        String[] vals = reading(files).split(",");//разбивает строку в котором встречается запитаяявляется как разделитель и создается массив строк
        return new ArrayList<>(Arrays.asList(vals));//выдает созданный список строк, который переведенный в массив в список
    }

    public List<Double> readingNumber(String files) throws IOException {//функция считывает с файла где двойка в определенной степени или дробная запись
        String temp = reading(files);//получаем строку символов
        String[] vals = temp.split(",");//разбивает строку в котором встречается запитаяявляется как разделитель и создается массив строк

        List<Double> probabilities = new ArrayList<>(); //создаются список вещественных вероятностей

        if (temp.indexOf('^') != -1 || temp.indexOf('/') != -1) {//данное условие проверяет если в созданной строке степень или деление
            probabilities=powerTwo(vals);//вызывает функцию которая возвращает список вещественных чисел, данная функция работает со степенью или делением
        }else {
            for (String number : vals) {
                probabilities.add(Double.parseDouble(number));//переводим строки вещественных чисел в вещественные чисал и добавляем в список
            }
        }

        double sum=probabilities.stream().mapToDouble(a->a).sum();

        if(sum!=1 ){
            return null;
        }

        return probabilities; //выдаем заданый списко
    }

    public String readingCode(String files) throws IOException {//считывание кода
        return reading(files);//функция выдает полученные строку
    }

    private  List<Double> powerTwo(String[] vals) {//возвращает список вещественных чисел, работа с двойками и делением
        List<Double> probabilities = new ArrayList<>();

        for (int i = 0; i < vals.length; i++) {
            if (vals[i].indexOf('^') != -1) {//если в элементе массива есть возведение в степень
                if (vals[i].indexOf('-') != -1) {//проверяется там отрицательно число или положительное
                   probabilities.add(Math.pow(2, -Integer.parseInt(vals[i].substring(vals[i].indexOf("-") + 1, vals[i].indexOf(")")))));//берем число от минуса до закрытой скобки, переводим в целый тип и возводим двойку
                } else {
                    probabilities.add(Math.pow(2, Integer.parseInt(vals[i].substring(vals[i].indexOf("(") + 1, vals[i].indexOf(")")))));//берем число от начальной до закрытой строки, переводим в целый тип и возводим двойку
                }

                continue;
            }

            if (vals[i].indexOf('/') != -1) {//если в элементе массива есть деление
                if (vals[i].indexOf('-') != -1) {//если есть минус в массиве и решается какое число будет положительное/отрицательное
                    probabilities.add((double) (-Integer.parseInt(vals[i].substring(vals[i].indexOf("-") + 1, vals[i].indexOf("/"))) / Integer.parseInt(vals[i].substring(vals[i].indexOf("/") + 1))));
                } else {
                   probabilities.add( Integer.parseInt(vals[i].substring(0, vals[i].indexOf("/"))) /(double)Integer.parseInt(vals[i].substring(vals[i].indexOf("/") + 1)));
                }
            }
        }

        return probabilities;
    }

    private String reading(String files) throws IOException {//считывание с файла  принимает строку пути файла
        File file = new File(files);//открывает файл по пути
        FileReader fr = new FileReader(file);//вставляет файл который нужно считать
        BufferedReader br = new BufferedReader(fr);//Читает текст из потока ввода символов, буферизуя символы, чтобы обеспечить эффективное чтение символов, массивов и строк.

        String line;//создание строки которая равна null
        String sourceAlphabet = "";//создается пустая строка


        while ((line = br.readLine()) != null) {//считавет с файла строк
            sourceAlphabet += line;//присвоение строки
        }

        br.close();//закрытие буфера потока ввода символов
        fr.close();//закрытие буфера чтения файла

        return sourceAlphabet;//выдает строку
    }
}
