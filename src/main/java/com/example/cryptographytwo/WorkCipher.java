package com.example.cryptographytwo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**класс который позволяет текст который находиться в строке кодировать и декадировать**/
public class WorkCipher {
    public static String coding(String line) {//статический класс который кодирует информацию которая в строке
        StringBuilder result = new StringBuilder();//класс, представляющий последовательность символов

        while (true) {//бесконечный цикл
            int start = 0;//стартовый элемент
            int count=1;//счетчик создан для того чтобы цикл не зацикливался

            for (int j = 0; j < HilbertMoore.symbol.size(); j++) {
                int end = HilbertMoore.symbol.get(j).length();//берем длинну длинну символа

                if (line.length() >= end && HilbertMoore.symbol.get(j).equals(line.substring(start, end))) {//проверяем чтобы не выйти за придел строки и символ сравнивается с элементом который берется из строки  от начального до конечного
                    line = line.substring(end);//выполняется обрезание строки
                    result.append(HilbertMoore.encoded.get(j));//добавляется элемент в строку
                    break;//выход из цикла
                }else{
                    count++;
                }
            }

            if(line.equals("")){//если строка пустая, то выход из главного цикла
                break;
            }

            if(count==HilbertMoore.symbol.size()){//если счетчик и дланна равны, при этом строка не пуста, то где-то ошибка
                return "Ввели не верные данные!";
            }
        }

        return String.valueOf(result);
    }

    public static String decoding(String line) {//класс создал для декодирования информации
        StringBuilder result = new StringBuilder();//класс, представляющий последовательность символов
        int max= Collections.max(HilbertMoore.sizeCode);//ищеться максимальный элемент в списке
        int i=0;

        while (max*HilbertMoore.sizeCode.size()>i) {//количетство возмодных итерации
            i++;
            int start = 0;

            for (int j = 0; j < HilbertMoore.encoded.size(); j++) {
                int end = HilbertMoore.sizeCode.get(j);//получения длинны кодированной двоичного кода

                if (line.length() >= end && HilbertMoore.encoded.get(j).equals(line.substring(start, end))) {//проверяем чтобы не выйти за придел строки и символ сравнивается с элементом который берется из строки  от начального до конечного
                    line = line.substring(end);//выполняется обрезание строки
                    result.append(HilbertMoore.symbol.get(j));//добавляется элемент в строку
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
